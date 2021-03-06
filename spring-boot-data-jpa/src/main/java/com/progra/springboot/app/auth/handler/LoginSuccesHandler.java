package com.progra.springboot.app.auth.handler;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.SessionFlashMapManager;

@Component
public class LoginSuccesHandler extends SimpleUrlAuthenticationSuccessHandler {

	
	@Autowired
	private MessageSource messageSource;
	
	// porque no lo tienes de entrada en el metodo
	@Autowired
	private LocaleResolver localeResolver;
	
	
	// porque no funciona con redirect
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		SessionFlashMapManager flashMapManager= new SessionFlashMapManager();
		FlashMap flashMap= new FlashMap();
		
		Locale locale= localeResolver.resolveLocale(request);
		
		flashMap.put("success", "Hola " + authentication.getName() + " hasiniciado sesión con éxito!");
		
		flashMapManager.saveOutputFlashMap(flashMap, request, response);
		
		if(authentication!=null) {
			logger.info("El usuario '" + authentication.getName() + "' a iniciado sesión con éxito");
		}
		
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
}
