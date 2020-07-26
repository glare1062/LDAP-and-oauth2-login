package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${ldap.urls}")
    private String ldapUrls;
    @Value("${ldap.base.dn}")
    private String ldapBaseDn;
    @Value("${ldap.username}")
    private String ldapSecurityPrincipal;
    @Value("${ldap.password}")
    private String ldapPrincipalPassword;
    @Value("${ldap.user.dn.pattern}")
    private String ldapUserDnPattern;
    @Value("${ldap.enabled}")
    private String ldapEnabled;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().anyRequest().fullyAuthenticated().and().formLogin();


    }



    @Override
    public void configure(AuthenticationManagerBuilder auth)  {

        try {
            auth
                .ldapAuthentication()
//                .userDnPatterns("uid={0},ou=people")
//                .groupSearchBase("ou=groups")
//                .contextSource()
//                .url("ldap://localhost:8389/dc=springframework,dc=org")
//                .and()
//                .passwordCompare()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .passwordAttribute("userPassword");

                    .contextSource()
                    .url(ldapUrls + ldapBaseDn)
                    .managerDn(ldapSecurityPrincipal)
                    .managerPassword(ldapPrincipalPassword)
                    .and()
                    .userDnPatterns(ldapUserDnPattern)


//                    .contextSource()
//                    .url("ldap://localhost:389/dc=planetexpress,dc=com")
//                    .managerDn("cn=admin,dc=planetexpress,dc=com")
//                    .managerPassword("GoodNewsEveryone")
//                    .and()
//                    .userDnPatterns(ldapUserDnPattern)

            ;

            auth.eraseCredentials(false);

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
