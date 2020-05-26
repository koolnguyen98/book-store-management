package com.esdc.bookstore.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.esdc.bookstore.entity.Account;

@Component
public class Securityhandler implements AuthenticationSuccessHandler {
	
	 @Autowired
	 private UserDetailsServiceImpl userDetailsService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// set our response to OK status
		response.setStatus(HttpServletResponse.SC_OK);

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			User user = (User) authentication.getPrincipal();
			String userName = user.getUsername();
			Account account = userDetailsService.findAccountByUserName(userName);
			if (!account.isEnabled()) {
				response.sendRedirect("/logout");
			}
		}
		String url = request.getHeader("referer");
		
		String[] arrUrl = url.split("/");
		boolean product = false;
		for (int index = 0; index < arrUrl.length; index++) {
			if(arrUrl[index].equals("product")) {
				product = true;
			}
		}
		
		if (product) {
			response.sendRedirect(url);
		}else {
			boolean admin = false;

			for (GrantedAuthority auth : authentication.getAuthorities()) {
				if ("ADMIN".equals(auth.getAuthority())) {
					admin = true;
				}
			}

			if (admin) {
				response.sendRedirect("/admin/book");
			} else {
				response.sendRedirect("/");
			}
		}
		
	}
}