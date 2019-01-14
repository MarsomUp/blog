package com.immyc.blog.common.shiro;

import com.immyc.blog.admin.model.Permission;
import com.immyc.blog.admin.model.Role;
import com.immyc.blog.admin.model.User;
import com.immyc.blog.admin.model.UserRole;
import com.immyc.blog.admin.service.IRolePermissionService;
import com.immyc.blog.admin.service.IUserRoleService;
import com.immyc.blog.admin.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: mayc
 * @Date: 2019/01/06 10:56
 */
public class BlogRealm extends AuthorizingRealm {

    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IRolePermissionService rolePermissionService;
    @Autowired
    private IUserService userService;

    /**
     * 这里是授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 因为非正常退出，没有显示的掉用SecurityUtils.getSubject.logout();
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            doClearCache(principalCollection);
            SecurityUtils.getSubject().logout();
            return null;
        }
        User user = (User) principalCollection.getPrimaryPrincipal();
        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            List<Role> roles = userRoleService.findUserRolesById(user.getId());
            if (roles.isEmpty()) {
                System.out.println(user.getUserName()+"没有任何权限");
                return null;
            }
            List<String> roleStr = new ArrayList<>(roles.size());
            for (Role role : roles) {
                roleStr.add(role.getRoleCode());
            }
            info.addRoles(roleStr);// 用户的角色
            // 权限
            List<Permission> permissions = rolePermissionService.findRolePermissionByRoleId(user.getId());
            if (permissions.isEmpty()) {
                System.out.println(user.getUserName()+"没有任何权限");
                return null;
            }
            List<String> permStr = new ArrayList<>(permissions.size());
            for (Permission perm : permissions) {
                permStr.add(perm.getPermCode());
            }
            info.addStringPermissions(permStr);
            return info;
        }
        return null;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        User user = userService.getByLoginName(usernamePasswordToken.getUsername());
        if (user != null) {
            return null;

        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getLoginAccount(), user.getPwd(), getName());
        return info;
    }
}
