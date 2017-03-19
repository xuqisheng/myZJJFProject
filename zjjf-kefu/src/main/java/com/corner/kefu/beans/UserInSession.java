package com.corner.kefu.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.corner.core.beans.Accounter;
import com.corner.core.beans.Auth;
import com.corner.core.beans.Role;
import com.corner.kefu.beans.vo.MenuSorter;
import com.corner.kefu.beans.vo.MenuTreeNode;
import com.corner.kefu.config.AuthorityConfig;
import com.corner.kefu.dao.AuthorityMapper;


public class UserInSession implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	private Accounter accounter;

	private Set<Role> roleSet;

	private Set<Auth> authSet = new HashSet<Auth>();

	public UserInSession(Accounter accounter,AuthorityMapper authorityMapper) {
		this.accounter = accounter;
		if (accounter != null) {
			this.roleSet = authorityMapper.getSubjectRoleses(accounter.getId());
			this.authSet = authorityMapper.getSubjectAuths(accounter.getId());
		}
	}

	public Accounter getAccounter() {
		return accounter;
	}

	public void setAccounter(Accounter accounter) {
		this.accounter = accounter;
	}

	public Set<Role> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}

	public Set<Auth> getAuthSet() {
		return authSet;
	}

	public void setAuthSet(Set<Auth> authSet) {
		this.authSet = authSet;
	}

	public boolean hasRole(String roleName) {
		if (roleName == null) {
			return false;
		}
		for (Role role : this.roleSet) {
			if (role.getRoleName() != null && roleName.equals(role.getRoleName())) {
				return true;
			}
		}
		return false;
	}

	public boolean hasAuth(String authName) {
		if (authName == null) {
			return false;
		}
		for (Auth auth : this.authSet) {
			if (auth.getAuthName() != null && authName.equals(auth.getAuthName())) {
				return true;
			}
		}
		return false;
	}

	public MenuTreeNode getSubjectMenu() {
		MenuTreeNode menuTreeNode = new MenuTreeNode();
		MenuSorter menuSorter = new MenuSorter();
		List<MenuTreeNode> menulist1 = new ArrayList<MenuTreeNode>();// 一級菜單
		if (this.authSet != null || this.authSet.size() > 0) {
			for (Auth tempAuth : this.authSet) {
				if (AuthorityConfig.ACCOUNT_TOP_MENUID.equals(tempAuth.getUpId())) { // 如果是一級菜單
					MenuTreeNode oneleveMenu = authToMenu(tempAuth); // add
																		// first
					List<MenuTreeNode> menulist2 = new ArrayList<MenuTreeNode>();// 二级菜单
					for (Auth tempAuth2 : this.authSet) {
						if (tempAuth != null && tempAuth2 != null && tempAuth2.getUpId() != null && tempAuth2.getUpId().equals(tempAuth.getId())) {
							menulist2.add(authToMenu(tempAuth2));// add
						}
					}
					Collections.sort(menulist2, menuSorter);
					oneleveMenu.setMenus(menulist2);// set second level menus
					menulist1.add(oneleveMenu);// add this menu node to first
												// level menus
				}
			}
		}
		Collections.sort(menulist1, menuSorter);
		menuTreeNode.setMenus(menulist1);
		return menuTreeNode;
	}

	private MenuTreeNode authToMenu(Auth auth) {
		MenuTreeNode oneleveMenu = new MenuTreeNode();
		oneleveMenu.setMenuid(auth.getId());
		oneleveMenu.setMenuname(auth.getAuthName());
		oneleveMenu.setIcon(auth.getIcon());
		oneleveMenu.setUrl(auth.getAction());
		oneleveMenu.setOrderbyIndex(auth.getOrdId());
		return oneleveMenu;
	}

}
