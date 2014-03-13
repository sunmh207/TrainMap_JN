package com.jitong.wnp.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

import com.jitong.common.exception.JTException;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.console.domain.User;
import com.jitong.sms.domain.SMS;

public class WnpUtil {
	/**
	 * 
	 * @param in
	 * @param startRow
	 * @param expectedCols, 0:GH,1:Name,2:PhoneNumber,3:SMSContent
	 * @param returnSMSs
	 * @return
	 * @throws JTException
	 */
	public static String readExcelFile(InputStream in, int startRow, int expectedCols,List<SMS> returnSMSs)throws JTException {
		StringBuffer errBuffer=new StringBuffer();
		try {
			Workbook wbook = null;
			wbook = Workbook.getWorkbook(in);
			//Get the first Sheet
			Sheet sheet = wbook.getSheet(0);
			int rows = sheet.getRows(); // sheet rows
			if (rows == 0 || rows < startRow) {
				errBuffer.append("导入的文件内容为空<br>");

			}
			int columns = sheet.getColumns(); // sheet columns
			if (columns != expectedCols && (columns - 1) != expectedCols) {
				errBuffer.append("导入的excel文件的列数不等于规定的导入列数,必须为" + expectedCols + "列数据<br>");
			}

			// Start to read excel
			for (int i = startRow; i < rows; i++) {// rows
				SMS sms = new SMS();
				sms.setRequestTime(DateUtil.getCurrentTime());
				sms.setBusinessType("excelimport");
				
				for (int j = 0; j < expectedCols; j++) { // 列
					String value = StringUtil.fillNull(sheet.getCell(j, i).getContents().trim());
					if ("".equals(value.trim())) {
							errBuffer.append("第" + (i + 1) + "行第" + (j + 1) + "列:为空值<br>");
							break;// read next row
					}

					if (j == 1) {// PhoneNumber						
						String phoneNumber = value;
						User user = SysCache.interpertUserByPhone(phoneNumber);
						if (user == null) {
							errBuffer.append("第" + (i + 1) + "行第" + (j + 1) + "列: 根据电话号码在人员库中无法准确匹配到对应人员,请检查数据正确性或是联系数据管理员" + "<br/>");
							continue;
						}else{
						sms.setPhoneNumber(phoneNumber);
						sms.setRecipientId(user.getId());
						}
					} else if(j==2){
						sms.setSMSContent(value);
					}
				}
				returnSMSs.add(sms);
			}

		} catch (Exception ex) {
			throw new JTException("读取Excel文件错误", WnpUtil.class);
		}
		return errBuffer.toString();
	}
	
	public static void main(String[] args) throws Exception{
		SysCache.loadSysCache();
		InputStream in = new FileInputStream("c:\\tmp\\SMSImport.xls");
		List<SMS> smslist = new ArrayList<SMS>();
		String err = WnpUtil.readExcelFile(in, 1, 3, smslist);
		System.out.println(err);
		for(SMS sms : smslist){
			System.out.println("|"+sms.getRecipientId()+"|"+sms.getRequestTime()+sms.getPhoneNumber()+sms.getSMSContent());
		}
	}
}
