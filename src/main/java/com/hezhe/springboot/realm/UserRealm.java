package com.hezhe.springboot.realm;

import com.hezhe.springboot.mapper.PermissionMapper;
import com.hezhe.springboot.mapper.RoleMapper;
import com.hezhe.springboot.mapper.UserMapper;
import com.hezhe.springboot.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 贺哲
 * @2020-01-12 12:48
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;


    /**
     * 用户授权
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principalCollection) {

        System.out.println("===执行授权===");
//
//        Subject subject = SecurityUtils.getSubject();
//        User user = (User) subject.getPrincipal();
//        if(user != null){
//            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//            // 角色与权限字符串集合
//            Collection<String> rolesCollection = new HashSet<>();
//            Collection<String> permissionCollection = new HashSet<>();
//            // 读取并赋值用户角色与权限
//            Set<Role> roles = user.getRole();
//            for(RoleBean role : roles){
//                rolesCollection.add(role.getName());
//                Set<PermissionBean> permissions = role.getPermissions();
//                for (PermissionBean permission : permissions){
//                    permissionCollection.add(permission.getUrl());
//                }
//                info.addStringPermissions(permissionCollection);
//            }
//            info.addRoles(rolesCollection);
//            return info;
//        }
//        return null;
        //1 通过反射，拿到内信息类
        User user = principalCollection.oneByType(User.class);
        //2 通过userID获取到该用户所有的角色ID
        Integer[] rid = roleMapper.selectRidByUid(user.getId());
        //3 因为权限资源可能会重复，所以用set，通过所有的角色ID进而获取到对应的permissionName
        Set<String> permissionName = permissionMapper.selectPerNameByRid(rid);
        Set<String> newPermissionName = new HashSet<>();

        /*4 这个时候从数据库中取出的permissionName有这种——>p:userList，带前缀的
            所以从新弄一个newPermissionName集合去装*/

        for (String perName : permissionName) {
            if (perName.startsWith("p:")) {
                //有前缀就替换
                newPermissionName.add(perName.replace("p:", ""));
            } else {
                newPermissionName.add(perName);
            }
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //4 调用setStringPermissions(permissionName);把权限名放入进去
        authorizationInfo.setStringPermissions(newPermissionName);
        //4 然后返回出去
        return authorizationInfo;
    }

    /**
     * 用户认证
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token1) throws AuthenticationException {

        System.out.println("===执行认证===");

//        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
//        UserBean bean = userService.findByName(token.getUsername());
//
//        if(bean == null){
//            throw new UnknownAccountException();
//        }
//
//        ByteSource credentialsSalt = ByteSource.Util.bytes(bean.getName());
//
//        return new SimpleAuthenticationInfo(bean, bean.getPassword(),
//                credentialsSalt, getName());

        //token1（用户输入的用户名和密码）把这个进行强转
        UsernamePasswordToken token = (UsernamePasswordToken) token1;
        //获取到token里面的用户名
        String username = token.getUsername();
        //用 用户名（这里要注意，用户名在数据库中要唯一）去数据库中取数据
        User user = userMapper.getUserByUsername(username);
        //如果没有这条数据就抛出异常
        if (user == null) {
            throw new UnknownAccountException("用户不存在");
        }
        //取出这条数据的状态：如果==0则抛出异常
        if (user.getState() == 2) {
            throw new LockedAccountException("用户被禁用");
        }
        /**
         * 通过SimpleAuthenticationInfo对密码进行比对
         * principal:用户名或密码
         * hashedCredentials：数据库中取出的密码
         * credentialsSalt：盐值 ->ByteSource这种类型
         * realmName：域的名字
         * shiro内部比较：通过用户输入的密码，和数据库（这时需要用户名唯一）取出的密码user.getPassword()进行比对。
         * */
        ByteSource salt = ByteSource.Util.bytes(username); //盐值：用户名
        AuthenticationInfo authenticationInfo =
                new SimpleAuthenticationInfo(user, user.getPassword(), salt, getName());
        return authenticationInfo;
    }

    // 模拟Shiro用户加密，假设用户密码为123456
    public static void main(String[] args){
        // 用户名
        String username = "hezhe";
        // 用户密码
        String password = "123456";
        // 加密方式
        String hashAlgorithName = "MD5";
        // 加密次数
        int hashIterations = 1024;
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        Object obj = new SimpleHash(hashAlgorithName, password,
                credentialsSalt, hashIterations);
        System.out.println(obj);
    }
}
