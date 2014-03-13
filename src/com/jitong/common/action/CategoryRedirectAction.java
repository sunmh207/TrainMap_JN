package com.jitong.common.action;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jitong.ContentItemManager;
import com.jitong.JitongConstants;
import com.jitong.common.domain.CategoryItem;
import com.jitong.common.util.StringUtil;
import com.jitong.console.domain.RoleMenu;
import com.jitong.console.domain.User;
/**
 * Jump to the first sub menu(category) which the current user has privilege.
 * @author stanley.sun
 *
 */
public class CategoryRedirectAction extends JITActionBase {

	private static Logger logger = Logger.getLogger(CategoryRedirectAction.class);
	private String redirect;

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	@Override
	public String execute() throws Exception {
/*
		String categoryId = this.getCategoryId();
		if (StringUtil.isEmpty(categoryId)) {
			return SUCCESS;//
		}
		List<CategoryItem> subCategoryItems = ContentItemManager.getSubCategoryItems(categoryId);
		User user = (User) session.get(JitongConstants.USER);
		Map<String, RoleMenu> roleMenuMap = user.getMenusMap();

		for (CategoryItem item : subCategoryItems) {
			if (hasPrivilege(roleMenuMap, item.getKey())) {
				String key = item.getKey();
				if (key != null && key.indexOf('.') > 0) {
					redirect = "/" + (key.replace('.', '/')) + ".do";
					logger.debug(redirect);
					return "redirect";
				}
			}
		}*/
		return SUCCESS;

	}

	private static boolean hasPrivilege(Map<String, RoleMenu> roleMenuMap, String categoryId) {
		return roleMenuMap.get(categoryId) != null ? true : false;
	}
}
