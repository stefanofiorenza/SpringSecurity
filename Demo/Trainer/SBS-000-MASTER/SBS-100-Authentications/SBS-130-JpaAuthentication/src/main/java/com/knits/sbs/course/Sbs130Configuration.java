package com.knits.sbs.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class Sbs130Configuration extends WebSecurityConfigurerAdapter{
	
	 @Autowired
	 private UserDetailsService  userDetailsService;
	    
	    
	@Override
    protected void configure(AuthenticationManagerBuilder authorizationBuilder) throws Exception { 		
		authorizationBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
	
	
    @Override
    protected void configure(HttpSecurity authorizationBuilder) throws Exception {  
    	authorizationBuilder
	         .authorizeRequests().anyRequest().authenticated()
	             .and().formLogin();	               
    }
      
    
    /*
    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }*/
    
  
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
 
	
}
