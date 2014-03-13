package com.jitong.wap.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.jitong.sms.domain.InBoxVO;

/**
 * 公文查询历史，对应shw_shwlist
 * @author stanley.sun
 *
 */
public class GongwenQuery  implements Serializable{ 
	private String sendYear;
	private String sendNumb;
	private String title;
	private String sendWord;
	private String draftDate;
	public String getSendYear() {
		return sendYear;
	}
	public void setSendYear(String sendYear) {
		this.sendYear = sendYear;
	}
	public String getSendNumb() {
		return sendNumb;
	}
	public void setSendNumb(String sendNumb) {
		this.sendNumb = sendNumb;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle15(){
		if (title==null||title.length()<15){
			return title;
		}else{
			return title.substring(0, 15)+"...";
		}
	}
	public String getSendWord() {
		return sendWord;
	}
	public void setSendWord(String sendWord) {
		this.sendWord = sendWord;
	}
	
	public String getDraftDate() {
		return draftDate;
	}
	public void setDraftDate(String draftDate) {
		this.draftDate = draftDate;
	}
	public boolean equals(Object obj) {
		if (!(obj instanceof InBoxVO)) {
			return false;
		} else {
			GongwenQuery gw = (GongwenQuery) obj;
			return new EqualsBuilder().appendSuper(super.equals(obj)).append(this.sendYear, gw.sendYear).append(this.sendNumb, gw.sendNumb)
					.append(this.title, gw.title).append(this.sendWord, gw.sendWord).isEquals();
		}
	}

	public int hashCode() {
		return new HashCodeBuilder(-528253723, -475504089).appendSuper(super.hashCode()).append(this.sendYear).append(this.sendNumb).append(
				this.title).append(this.sendWord).toHashCode();
	}
	
	public static void main(String[] args){
		System.out.println("1234567890".substring(0, 4));
	}
}
