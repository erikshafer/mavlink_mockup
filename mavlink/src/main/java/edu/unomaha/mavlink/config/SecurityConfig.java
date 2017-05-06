package edu.unomaha.mavlink.config;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import edu.unomaha.mavlink.repository.UserSecurityService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;

    @Autowired
    private UserSecurityService userSecurityService;

    private static final String SALT = "salt"; // Salt should be protected carefully

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {	// SHA-1
        return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { 	
        http
		        .authorizeRequests()                                            
				.antMatchers("/", "/css/**", "/js/**", "/images/**",
						     "/courses/**", "/signup/**", "/password/**",
						     "/error/**/", "/index").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")           
				.anyRequest().authenticated().and()
				.formLogin().failureUrl("/index?error").defaultSuccessUrl("/home/").loginPage("/index").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/index?logout").deleteCookies("remember-me").permitAll()
                .and()
                .rememberMe();
	
        http
        		.authorizeRequests().antMatchers("/h2-console/**").permitAll();
        
        http
        		.headers().frameOptions().disable();

        http
        		.csrf().disable().cors().disable();

        http
        		.exceptionHandling().accessDeniedPage("/index?error");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//    	 auth.inMemoryAuthentication().withUser("user").password("password").roles("USER"); //This is in-memory authentication
    	auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
    	auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
    }


}