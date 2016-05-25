package br.com.casadocodigo2.loja.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.casadocodigo2.loja.daos.UsuarioDAO;

@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private UsuarioDAO usuarioDao;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/produtos/form").hasRole("admin")
		.antMatchers("/carrinho").permitAll()
		.antMatchers(HttpMethod.POST,"/produtos").hasRole("admin")
		.antMatchers(HttpMethod.GET,"/produtos").hasRole("admin")
		.antMatchers("/produtos/**").permitAll()
		.antMatchers("/").permitAll()
		.anyRequest().authenticated().and().formLogin();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
		auth.userDetailsService(usuarioDao).passwordEncoder(new BCryptPasswordEncoder());
	}
	
}
