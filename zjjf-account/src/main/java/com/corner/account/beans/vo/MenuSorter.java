package com.corner.account.beans.vo;

import java.util.Comparator;

/**
 * @ClassName: AuthSorter
 * @Description: TODO(菜单排序实现)
 * @author luke
 * @email luke@mibodoctor.com
 * @date 2014年12月17日 上午9:17:03
 * 
 */
public class MenuSorter implements Comparator<MenuTreeNode> {

	@Override
	public int compare(MenuTreeNode node1, MenuTreeNode node2) {
		int flag = node1.getOrderbyIndex() - node2.getOrderbyIndex();
		if (flag > 0) {
			return 1;
		} else if (flag == 0) {
			return 0;
		} else {
			return -1;
		}
	}

}
