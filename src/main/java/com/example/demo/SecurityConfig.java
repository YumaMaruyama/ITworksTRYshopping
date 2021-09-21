package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// パスワードエンコードのBean定義
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}

	@Autowired
	private DataSource dataSource;

	private static final String USER_SQL = "SELECT" + " user_id," + " password," + " true" + " FROM" + " Users"
			+ " WHERE" + " user_id = ?";

	private static final String ROLE_SQL = "SELECT" + " user_id," + " role" + " FROM" + " Users" + " WHERE"
			+ " user_id = ?";

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/webjars/**").permitAll().antMatchers("/css/**").permitAll()
				.antMatchers("/no").permitAll().antMatchers("/login").permitAll().antMatchers("/signup").permitAll()
				.antMatchers("/inquiryBeforeLogin").permitAll().antMatchers("/inquiryBeforeLoginFinish").permitAll()
				.antMatchers("/privacyPolicyBeforeLogin").permitAll().antMatchers("/termsOfUseBeforeLogin").permitAll()
				.antMatchers("/appDetail").permitAll().anyRequest().authenticated();

		http.formLogin().loginProcessingUrl("/login")// ログイン処理を行う場所
				.loginPage("/login")// ログインページ
				.failureUrl("/no")// ログイン失敗時の推移先
				.usernameParameter("user_id")// ログインページのuser_id
				.passwordParameter("password")// ログインページのpassword
				.defaultSuccessUrl("/productList", true);// ログイン成功後の推移先

		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutUrl("/logout")
				.logoutSuccessUrl("/login");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(USER_SQL)
				.authoritiesByUsernameQuery(ROLE_SQL).passwordEncoder(passwordEncoder());
	}
}
