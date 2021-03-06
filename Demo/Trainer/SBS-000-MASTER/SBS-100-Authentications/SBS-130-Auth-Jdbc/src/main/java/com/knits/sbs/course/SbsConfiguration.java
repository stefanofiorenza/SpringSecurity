package com.knits.sbs.course;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SbsConfiguration extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private DataSource dataSource;
	
	
	@Override
    protected void configure(AuthenticationManagerBuilder authorizationBuilder) throws Exception { 
		
		//configureWithDefaultSchema(authorizationBuilder);
	//	configureWithCustomSchema(authorizationBuilder);

		
    }
    
	private void configureWithDefaultSchema(AuthenticationManagerBuilder authorizationBuilder) throws Exception { 
		
		authorizationBuilder.jdbcAuthentication()
	      .dataSource(dataSource)
	      .withDefaultSchema()
	      	.withUser(
	    		  User.withUsername("stefano")
	    		  .password("stefan0"))
	      	.withUser(
		    		  User.withUsername("raul")
		    		  .password("r4ul"))	      
	      .passwordEncoder(passwordEncoder());	
		
    }
	
	private void configureWithCustomSchema(AuthenticationManagerBuilder authorizationBuilder) throws Exception {
		authorizationBuilder.jdbcAuthentication()
	      .dataSource(dataSource)
	  		.usersByUsernameQuery("select email,password,enabled "
		        + "from users "
		        + "where email = ?")
		      .authoritiesByUsernameQuery("select email,authority "
		        + "from roles "
		        + "where email = ?")
		      .passwordEncoder(passwordEncoder());
	}
	
	
    @Override
    protected void configure(HttpSecurity authorizationBuilder) throws Exception {  
    	authorizationBuilder.authorizeRequests()    	
		.anyRequest().authenticated().and().formLogin();
    
    }
       

    
  
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
 
	
}
