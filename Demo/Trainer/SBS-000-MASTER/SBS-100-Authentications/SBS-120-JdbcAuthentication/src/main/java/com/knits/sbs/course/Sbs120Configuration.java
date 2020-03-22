package com.knits.sbs.course;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class Sbs120Configuration extends WebSecurityConfigurerAdapter{
	
	
	@Override
    protected void configure(AuthenticationManagerBuilder authorizationBuilder) throws Exception { 
		
		//configureWithDefaultSchema(authorizationBuilder);
		configureWithCustomSchema(authorizationBuilder);

		
    }
    
	private void configureWithDefaultSchema(AuthenticationManagerBuilder authorizationBuilder) throws Exception { 
		
		authorizationBuilder.jdbcAuthentication()
	      .dataSource(dataSource())
	      .withDefaultSchema()
	      	.withUser(
	    		  User.withUsername("stefano")
	    		  .password("stefan0")
	    		  .roles("ROLE_MANAGER"))	
	      	.withUser(
		    		  User.withUsername("raul")
		    		  .password("r4ul")
		    		  .roles("ROLE_USER"))	      
	      .passwordEncoder(passwordEncoder());	
		
    }
	
	private void configureWithCustomSchema(AuthenticationManagerBuilder authorizationBuilder) throws Exception {
		authorizationBuilder.jdbcAuthentication()
	      .dataSource(dataSource())
	      	.usersByUsernameQuery("select username, password, enabled from users where username=?")
	      	.authoritiesByUsernameQuery("select username, authority from roles where username=?")
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
    
    
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/ss-jdbc");
        dataSource.setUsername("root");
        dataSource.setPassword("stefan0");
        return dataSource;
    }
	
}
