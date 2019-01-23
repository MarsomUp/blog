package com.immyc.blog.common.shiro;

import com.immyc.blog.admin.model.Permission;
import com.immyc.blog.admin.model.Role;
import com.immyc.blog.admin.model.User;
import com.immyc.blog.admin.model.UserRole;
import com.immyc.blog.admin.service.IRolePermissionService;
import com.immyc.blog.admin.service.IUserRoleService;
import com.immyc.blog.admin.service.IUserService;
import com.immyc.blog.common.constants.MyConstants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: mayc
 * @Date: 2019/01/06 10:56
 */
public class BlogRealm extends AuthorizingRealm {
    private static final Logger LOGGER = LoggerFactory.getLogger(BlogRealm.class);

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
            List<Role> roles = userRoleService.findUserRolesByUserId(user.getId());
            if (roles.isEmpty()) {
                LOGGER.info(user.getUserName()+"没有任何权限");
                return null;
            }
            List<String> roleStr = new ArrayList<>(roles.size());
            List<Permission> permissionList = new ArrayList<>(10);
            for (Role role : roles) {
                roleStr.add(role.getRoleCode());
                // 权限
                List<Permission> permissions = rolePermissionService.findRolePermissionByRoleId(role.getId());
                if (!permissions.isEmpty()) {
                    permissionList.addAll(permissions);
                }
            }
            info.addRoles(roleStr);// 用户的角色

            if (permissionList.isEmpty()) {
                LOGGER.info(user.getUserName()+"没有任何权限");
                return null;
            }
            List<String> permStr = new ArrayList<>(permissionList.size());
            for (Permission perm : permissionList) {
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
        if (user == null) {
<<<<<<< HEAD
=======
            LOGGER.debug("未获取到人员信息");
>>>>>>> ccaa2161b8d7627f87820755cd018165cc1c217a
            return null;
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getLoginAccount(), user.getPwd(), getName());
        info.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
        SecurityUtils.getSubject().getSession().setAttribute(MyConstants.SESSION_USER_INFO, user);
        return info;
    }
}
