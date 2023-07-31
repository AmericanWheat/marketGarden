package com.human.mg.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.human.mg.vo.MgVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info("~~~~~~ 로그인 성공 ~~~~~~" + authentication.getName());		
		log.info("~~~~~~ 로그인 성공 ~~~~~~" + authentication.getPrincipal());		
		log.info("~~~~~~ 로그인 성공 ~~~~~~" + (MgVO)authentication.getPrincipal());		
	
		request.getSession().setAttribute("MgVO", (MgVO)authentication.getPrincipal());
		
		resultRedirectStrategy(request, response, authentication);
	}
	
	protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if (savedRequest != null) {
			String targetUrl = savedRequest.getRedirectUrl();
			redirectStratgy.sendRedirect(request, response, targetUrl);
		}else {
			redirectStratgy.sendRedirect(request, response, "/main");
		}
	}
	
	
}
