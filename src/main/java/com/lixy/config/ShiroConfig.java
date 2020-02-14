package com.lixy.config;

import com.lixy.credentials.RetryLimitCredentialsMatcher;
import com.lixy.shiro.ShiroRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //将自己的验证方式加入容器
    @Bean(name = "shiroRealm")
    public ShiroRealm myShiroRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher) {
        ShiroRealm myShiroRealm = new ShiroRealm();
        myShiroRealm.setCredentialsMatcher(matcher);
        return myShiroRealm;
    }

    //权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager(@Qualifier("shiroRealm") ShiroRealm shiroRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm);
        return securityManager;
    }

    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //登录
        shiroFilterFactoryBean.setLoginUrl("/admin/login");
        //首页
        shiroFilterFactoryBean.setSuccessUrl("/admin/index");
        //错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String, String>();


        filterChainDefinitionMap.put("/front/**","anon");
        //登录登出
        filterChainDefinitionMap.put("/admin/login","anon");
        filterChainDefinitionMap.put("/admin/getLogin","anon");
        filterChainDefinitionMap.put("/admin/exitLogon","logout");

        //静态资源释放
        filterChainDefinitionMap.put("favicon.ico", "anon");
        filterChainDefinitionMap.put("/plugins/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/source/**", "anon");
        filterChainDefinitionMap.put("/upload/**", "anon");

        //初始化
        filterChainDefinitionMap.put("/install/**", "anon");

        //对所有用户认证
        filterChainDefinitionMap.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "credentialsMatcher")
    public RetryLimitCredentialsMatcher credentialsMatcher() {
        return new RetryLimitCredentialsMatcher();
    }
    //加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
