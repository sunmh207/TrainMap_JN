package com.jitong.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jitong.ContentItemManager;
import com.jitong.JitongConstants;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.domain.CategoryItem;
import com.jitong.console.domain.RoleMenu;
import com.jitong.console.domain.User;

public class CategoryUtil {
	private static Logger logger = Logger.getLogger(CategoryUtil.class);

	/*public static void injectCategoryInfo(JITActionBase action) {
		HttpServletRequest request = action.getRequest();
		if (request == null) {
			return;
		}

		Map<String, Object> session = action.getSession();
		User user = (User) session.get(JitongConstants.USER);
		Map<String, RoleMenu> roleMenuMap = user.getMenusMap();

		List<CategoryItem> tmpTopCategoryItems = ContentItemManager.getTopCategoryItems();

		// remove those categories which current user don't have privilege
		List<CategoryItem> topCategoryItems = new ArrayList<CategoryItem> ();
		for (CategoryItem item : tmpTopCategoryItems) {
			if (hasPrivilege(roleMenuMap, item.getKey())) {
				topCategoryItems.add(item);
			}
		}

		String categoryId = ActionUtil.retrieveCategoryItemFromAction(action);
		logger.debug(categoryId);
		if (categoryId == null) {
			request.setAttribute("categories", topCategoryItems);
			return;
		}

		CategoryItem currentCategory = ContentItemManager.getCategoryItem(categoryId);

		if (currentCategory.getLevel() == 1) {
			String topCategoryId = currentCategory.getTopKey();
			List<CategoryItem> tmpSubCategoryItems = ContentItemManager.getSubCategoryItems(topCategoryId);

			// remove those categories which current user don't have privilege
			List<CategoryItem> subCategoryItems = new ArrayList<CategoryItem>();
			for (CategoryItem item : tmpSubCategoryItems) {
				if (hasPrivilege(roleMenuMap, item.getKey())) {
					subCategoryItems.add(item);
				}
			}

			CategoryItem topCategory = ContentItemManager.getCategoryItem(topCategoryId);

			if (hasPrivilege(roleMenuMap, topCategory.getKey())) {
				request.setAttribute("topCategory", topCategory);
			}
			request.setAttribute("topCategories", topCategoryItems);

			if (hasPrivilege(roleMenuMap, currentCategory.getKey())) {
				request.setAttribute("category", currentCategory);
			}
			request.setAttribute("categories", subCategoryItems);
		} else {
			if (hasPrivilege(roleMenuMap, currentCategory.getKey())) {
				request.setAttribute("topCategory", currentCategory);
			}
			request.setAttribute("topCategories", topCategoryItems);
			
			if (hasPrivilege(roleMenuMap, currentCategory.getKey())) {
				request.setAttribute("category", currentCategory);
			}
			
			request.setAttribute("categories", topCategoryItems);
		}
		action.getSession().put("categoryId", categoryId);
		request.setAttribute("root", request.getContextPath());

	}*/

	private static boolean hasPrivilege(Map<String, RoleMenu> roleMenuMap, String categoryId) {
		return roleMenuMap.get(categoryId) != null ? true : false;
	}

}
