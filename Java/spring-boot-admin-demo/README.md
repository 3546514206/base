# SpringBoot Admin
> 这是一个学习SpringBoot Admin 的一个学习Demo
> 以及一些坑

## 加入Security组件后客户端注册失败
> 常见的注册失败问题可以分为以下两种
* Spring Boot Admin服务端与客户端不在同一台服务器上
* 提示安全校验不通过

***第一种解决方案***

必须在客户端配置boot.admin.client.instance.service-url属性，让Spring Boot Admin服务端可以通过网络获取客户端的数据（否则默认会通过主机名去获取）
```yaml
  boot:
    admin:
      client:
        url: register url
        username: username
        password: password
        instance:
          prefer-ip: true
          service-url: client url
```

***第二种解决方案***

首先，安全检验问题，其实就是现在服务端配置账号密码，然后客户端在注册的时候提供账号密码进行登录来完成校验

这个过程的实现，作为Spring全家桶项目，推荐使用Spring Security来解决，所以如果出现校验失败，那多半是Spring Security的配置出现问题

### 服务端配置

通过maven加载Spring Security依赖
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

设置服务端的用户名和密码(客户端来注册时使用此账号密码进行登录)
```yaml
spring:
  security:
    user:
      name: liumapp
      password: superliumapp
```
编写Spring Security配置类
```java
package org.sprit.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecuritySecureConfig extends WebSecurityConfigurerAdapter {

    private final String adminContextPath;

    public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties.getContextPath();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(adminContextPath + "/");

        http.authorizeRequests()
                //授予对所有静态资产和登录页面的公共访问权限。
                .antMatchers(adminContextPath + "/assets/**").permitAll()
                .antMatchers(adminContextPath + "/login").permitAll()
                //必须对每个其他请求进行身份验证
                .anyRequest().authenticated()
                .and()
                //配置登录和注销
                .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and()
                .logout().logoutUrl(adminContextPath + "/logout").and()
                //启用HTTP-Basic支持。这是Spring Boot Admin Client注册所必需的
                .httpBasic().and()
                //admin client 注册
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringAntMatchers(
                        adminContextPath + "/instances",
                        adminContextPath + "/actuator/**"
                );
        // @formatter:on
    }
}

```

### 客户端配置
首先对于客户端，我们除了Spring Boot Admin Client依赖外，还需要额外引入 Spring Security依赖:
```xml
<dependency>
    <groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-starter-client</artifactId>
    <version>2.1.6</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

在此基础上通过编写客户端application.yml配置文件来设置账号密码
```yaml
spring:
  application:
    name: admin-client
  boot:
    admin:
      client:
        url: server url
        username: username
        password: password
        auto-registration: true
        auto-deregistration: true
        instance:
          service-base-url: client url
```

接下来对Client端的Spring Security做配置，允许Server端读取actuator暴露的数据
```java
package sprit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll()
                .and().csrf().disable();
    }
}

```

## 参考链接
https://codecentric.github.io/spring-boot-admin/2.1.6/

https://www.liumapp.com/articles/2019/08/03/1564813578454.html


