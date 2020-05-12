package com.knits.sbs.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.knits.sbs.course.web.JwtRequestFilter;

@EnableWebSecurity
public class Sbs160Configuration extends WebSecurityConfigurerAdapter{
	
	 @Autowired
	 private UserDetailsService  userDetailsService;


	 @Autowired
	private JwtRequestFilter jwtRequestFilter;
	 
	@Override
    protected void configure(AuthenticationManagerBuilder authorizationBuilder) throws Exception { 		
		authorizationBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
	
	
    @Override
    protected void configure(HttpSecurity authorizationBuilder) throws Exception {  
    	    
    	authorizationBuilder.csrf().disable()
			.authorizeRequests()
				.antMatchers("/authenticate").permitAll()
				.anyRequest().authenticated().and()
				.exceptionHandling().and()
				.sessionManagement() //dont save Principal in http session
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	
    	//add filter to filter chain
    	authorizationBuilder.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    
    }
    
  
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /*
    @Bean
    public PasswordEncoder noOpPasswordEncoder() {
        return  NoOpPasswordEncoder.getInstance();
    }*/
 
	
}
