package com.jitong.common.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.jitong.ContentItemManager;
import com.jitong.JitongConstants;
import com.jitong.common.domain.CategoryItem;
import com.jitong.console.domain.Menu;
import com.jitong.console.domain.RoleMenu;
import com.jitong.console.domain.User;



public class CategoryAction extends JITActionBase {
	private static Logger logger = Logger.getLogger(CategoryAction.class);
	@Override
	public String execute() throws Exception {
		logger.debug("in welcome action.");
		Set<Entry<CategoryItem, List<CategoryItem>>> fullCategories = getFullCategories();
		request.setAttribute("fullCategories", fullCategories);
		logger.debug(fullCategories);
		logger.debug(this.session);
		return SUCCESS;
	}
	
	public Set<Entry<CategoryItem, List<CategoryItem>>> getFullCategories(){
		Map<CategoryItem, List<CategoryItem>> categories = new LinkedHashMap<CategoryItem, List<CategoryItem>>();
		List<CategoryItem> topCategoryItems = ContentItemManager.getTopCategoryItems();
		User user = (User) session.get(JitongConstants.USER);
		Map<String, List<Menu>> roleMenuMap = user.getMenusMap();
		for (CategoryItem topCategoryItem : topCategoryItems) {
			boolean hasPrivilege = hasPrivilege(roleMenuMap, topCategoryItem.getKey());
			logger.debug(topCategoryItem.getKey()+" -> " +hasPrivilege);
			if(hasPrivilege){
				ArrayList<CategoryItem> filteredCIs = new ArrayList<CategoryItem>();
				categories.put(topCategoryItem, filteredCIs);
				List<CategoryItem> subCIs = ContentItemManager.getSubCategoryItems(topCategoryItem.getKey());
				for (CategoryItem subCI : subCIs) {
					if(hasPrivilege(roleMenuMap, subCI.getKey())){
						filteredCIs.add(subCI);
					}
				}
			}
		}
		return categories.entrySet();
	}
	private boolean hasPrivilege(Map<String, List<Menu>> roleMenuMap, String categoryId) {
		return true;
	}
}
