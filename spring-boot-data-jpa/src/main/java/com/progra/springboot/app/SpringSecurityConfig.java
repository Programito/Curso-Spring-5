package com.progra.springboot.app;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.progra.springboot.app.auth.handler.LoginSuccesHandler;
import com.progra.springboot.app.models.service.JpaUserDetailsService;

// para poder utilizar anotaciones en controladores
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	// hay que poner como component el LoginSuccessHandler
	@Autowired
	private LoginSuccesHandler successHandler;
	
	@Autowired 
	private DataSource dataSource;
	
	@Autowired
	private JpaUserDetailsService userDetaisService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**", "/listar","/locale").permitAll()
		/*.antMatchers("/ver/**").hasAnyRole("USER")*/
		/*.antMatchers("/uploads/**").hasAnyRole("USER")*/
		/*.antMatchers("/form/**").hasAnyRole("ADMIN")*/
		/*.antMatchers("/eliminar/**").hasAnyRole("ADMIN")*/
		/*.antMatchers("/factura/**").hasAnyRole("ADMIN")*/
		.anyRequest().authenticated()
		.and()
	    	.formLogin()
	    		.successHandler(successHandler)
	    		.loginPage("/login")
	    	.permitAll()
	    .and()
	    .logout().permitAll()
	    .and()
	    .exceptionHandling().accessDeniedPage("/error_403");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder build) throws Exception {
//		PasswordEncoder encoder=PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		UserBuilder users= User.builder().passwordEncoder(encoder::encode);
//		
//		build.inMemoryAuthentication()
//			.withUser(users.username("admin").password("12345").roles("ADMIN","USER"))
//			.withUser(users.username("andres").password("12345").roles("USER"));

	//jdbc config
	build.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(passwordEncoder)
		.usersByUsernameQuery("select username, password, enabled from users where username=?")
		.authoritiesByUsernameQuery("select u.username, a.authority from authorities a inner join users u on (a.user_id=u.id) where u.username=?");
		
		
//		build.userDetailsService(userDetaisService)
//		.passwordEncoder(passwordEncoder);
	}
}
