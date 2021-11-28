package com.example.QuanLyPhongKham.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.QuanLyPhongKham.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//	@Autowired
//	private DataSource dataSource;
//	@Autowired
//	private CustomOAuth2UserService oauth2UserService;

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(passwordEncoder());
		authProvider.setUserDetailsService(userDetailsService());
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/yourAppointment" , "/yourMedicine","/cart","/checkOut","/bill").hasAuthority("USER")
		.antMatchers("/examination","/test","/patient","/doctor","/medicalNote","/appointment","/department","/medicine","/statistic").hasAuthority("ADMIN")
		.antMatchers("/register","/save","/listUsers").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin().permitAll().loginPage("/login")
		.usernameParameter("username")
		.passwordParameter("password")
		.defaultSuccessUrl("/")
		.and()
		.logout().permitAll()
		.and().logout().logoutSuccessUrl("/login").logoutUrl("/logout").permitAll();

	}
	 @Override
     public void configure(WebSecurity web) throws Exception {
         web
             .ignoring()
             .antMatchers("/static/**", "/jsFile/**");
     }
	
}
