package com.jitong;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jitong.common.domain.CategoryItem;

public class ContentItemManager {
	private static boolean initialized = false;
	private static HashMap<String, CategoryItem> itemsMap = new HashMap<String, CategoryItem>();
	private static ArrayList<CategoryItem> topItems = new ArrayList<CategoryItem>();
	private static HashMap<String, ArrayList<CategoryItem>> subItemsMap = new HashMap<String, ArrayList<CategoryItem>>();
	public static synchronized void init() {
		try {
			InputStream in = ContentItemManager.class.getClassLoader().getResourceAsStream("categoryItems.properties");
			byte[] bytes = new byte[1000];
			int c = 0;
			boolean stop = false; 
			int read = 0;
			while(!stop){
				c = in.read();
				if(c==-1) {
					stop = true;
					c = '\n';
				}
				if(c=='\r') {
					continue;
				}
				if(c == '\n'){
					String line = new String(bytes, 0, read, "utf-8");
					read = 0;
					CategoryItem item = lineToItem(line);
					if(item!=null){
						addItem(item);
					}
				}else{
					bytes[read++] = (byte) c;
					if(read == bytes.length-1){
						byte[] newbuff = new byte[bytes.length*2];
						System.arraycopy(bytes, 0, newbuff, 0, bytes.length);
						bytes = newbuff;
					}
				}


			}
			initialized =  true;
			in.close();
		} catch (IOException e) {
			throw new RuntimeException();
		}

	}

	private static void addItem(CategoryItem item) {
		itemsMap.put(item.getKey(),item);
		if(item .getLevel()==0){
			topItems.add(item);
		}
		else {
			ArrayList<CategoryItem> subItems = subItemsMap.get(item.getTopKey());
			if(subItems==null){
				subItems = new ArrayList<CategoryItem>();
				subItemsMap.put(item.getTopKey(), subItems);
			}
			subItems.add(item);
		}
	}
	
	private static CategoryItem lineToItem(String line){
		int idxSharp = line.indexOf('#');
		if (idxSharp >= 0) {
			line = line.substring(0, idxSharp);
		}
		int idxEq = line.indexOf('=');
		if (idxEq < 0) {
			return null;
		}


		String key = line.substring(0, idxEq).trim();
		String key0 = key;
		String key1 = "";
		int level = 0;
		int idxDot = key.indexOf('.');
		if(idxDot>0){
			key0 = key.substring(0, idxDot);
			key1 = key.substring(idxDot+1);
			level = 1;
		}
		String value = line.substring(idxEq+1).trim();
		CategoryItem item = new CategoryItem();
		item.setDisplay(value);
		item.setKey(key);
		item.setTopKey(key0);
		item.setSubKey(key1);
		item.setLevel(level);
		return item;
	}
	public static void main(String[] args) {
		init();
	}
	public static List<CategoryItem> getSubCategoryItems(String key0) {
		if(!initialized) {
			init();
		}
		return subItemsMap.get(key0);
	}
	public static CategoryItem getCategoryItem(String key0) {
		if(!initialized) {
			init();
		}
		return itemsMap.get(key0);
	}
	public static List<CategoryItem> getTopCategoryItems () {
		if(!initialized) {
			init();
		}
		return topItems;
	}
}
