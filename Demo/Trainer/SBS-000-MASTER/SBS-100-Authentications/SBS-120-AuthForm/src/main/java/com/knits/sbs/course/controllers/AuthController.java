package com.knits.sbs.course.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auth")
public class AuthController {

	
	@Autowired
    private AuthenticationManager authManager;


	@GetMapping("login")
    public void login(HttpServletRequest req, String user, String pass) { 
    	
	    UsernamePasswordAuthenticationToken authReq
	      = new UsernamePasswordAuthenticationToken(user, pass);
	    
	    Authentication auth = authManager.authenticate(authReq);
	     
	    SecurityContext sc = SecurityContextHolder.getContext();
	    sc.setAuthentication(auth);
	    HttpSession session = req.getSession(true);
	    session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);
    }
       
    
    @GetMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
    	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
          if (auth != null){    
              new SecurityContextLogoutHandler().logout(request, response, auth);
          }
        return "home/logout";
    }
}
