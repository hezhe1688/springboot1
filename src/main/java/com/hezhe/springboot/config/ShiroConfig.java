package com.hezhe.springboot.config;

import com.hezhe.springboot.mapper.PermissionMapper;
import com.hezhe.springboot.model.Permission;
import com.hezhe.springboot.realm.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 贺哲
 * @2020-01-12 12:47
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 加密
     * @return
     */
    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher =
                new HashedCredentialsMatcher();
        //指定加密方式为MD5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //加密次数
        credentialsMatcher.setHashIterations(1024);
        //加密后转16进制
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    @Bean("userRealm")
    public UserRealm userRealm(@Qualifier("hashedCredentialsMatcher")
                                       HashedCredentialsMatcher matcher) {

        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(matcher);
        return userRealm;
    }

    @Bean("shirFilter")
    public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager")
                                                     DefaultWebSecurityManager securityManager) {

        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置 SecurityManager
        bean.setSecurityManager(securityManager);
        // 设置登录成功跳转Url
        bean.setSuccessUrl("/main");
        // 设置登录跳转Url
        bean.setLoginUrl("/to_login");
        // 设置未授权提示Url
        bean.setUnauthorizedUrl("/unAuth");

        /**
         * anon：匿名用户可访问
         * authc：认证用户可访问
         * user：使用rememberMe可访问
         * perms：对应权限可访问
         * role：对应角色权限可访问
         * 从数据库中取数据
         **/
        //获取所有的权限资源
        List<Permission> permissions = permissionMapper.selectAllPermission();
        //做一个LinkedHashMap,因为这些键值对都是必须有顺序的，所以要用这个map
        Map<String, String> map = new LinkedHashMap<>();
        for (Permission permission : permissions) {
            //判断该名字有是不是以"p:"开头的
            if (permission.getPermissionName().startsWith("p:")) {
                //拼接成这个样子/admin/userList.html=perms[userList]
                map.put(permission.getUrl(), "perms[" + permission.getPermissionName().substring(2) + "]");
            } else {
                //否则就直接添加进去
                map.put(permission.getUrl(), permission.getPermissionName());
            }
        }
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }

    /**
     * 注入 securityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(
            HashedCredentialsMatcher hashedCredentialsMatcher) {

        DefaultWebSecurityManager securityManager =
                new DefaultWebSecurityManager();
        // 关联realm.
        securityManager.setRealm(userRealm(hashedCredentialsMatcher));
        return securityManager;
    }
}