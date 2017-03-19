package com.corner.kefu.security;

import java.util.List;
import java.util.Set;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.WildcardPermission;

public class WildcardExtPermission extends WildcardPermission {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 8474556235351146981L;
	
	private static final String WILDCARD_EXT_TOKEN = "+";
	
	
	 /*--------------------------------------------
    |               M E T H O D S               |
    ============================================*/

	public WildcardExtPermission(String permissionString) {
		super(permissionString);
	}


	/**
	 * 
	 */
	@Override
    public boolean implies(Permission p) {
        // By default only supports comparisons with other WildcardPermissions
        if (!(p instanceof WildcardPermission)) {
            return false;
        }

        WildcardExtPermission wp = (WildcardExtPermission) p;

        List<Set<String>> otherParts = wp.getParts();

        int i = 0;
        for (Set<String> otherPart : otherParts) {
            // If this permission has less parts than the other permission, everything after the number of parts contained
            // in this permission is automatically implied, so return true
            if(otherPart.contains(WILDCARD_EXT_TOKEN)){
            	if(otherParts.size() - 1 < ++i){
                	return true;
            	}
            	continue;
            }
        	if (getParts().size() - 1 < i) {
                return true;
            } else {
                Set<String> part = getParts().get(i);
                if (!part.contains(WILDCARD_TOKEN) && !part.containsAll(otherPart)) {
                    return false;
                }
                i++;
            }
        }

        // If this permission has more parts than the other parts, only imply it if all of the other parts are wildcards
        for (; i < getParts().size(); i++) {
            Set<String> part = getParts().get(i);
            if (!part.contains(WILDCARD_TOKEN)) {
                return false;
            }
        }

        return true;
    }

}
