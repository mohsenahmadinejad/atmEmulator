package com.mohsen.bankservice.security.config;



import com.mohsen.bankservice.security.filter.JwtFilter;
import com.mohsen.bankservice.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
//        return NoOpPasswordEncoder.getInstance();

    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Value("${security.activation.status}")
    private String securityActivationStatus;

//        @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/v2/api-docs",
//                "/configuration/ui",
//                "/swagger-resources/**",
//                "/configuration/security",
//                "/swagger-ui.html",
//                "/webjars/**");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

         if (securityActivationStatus.equalsIgnoreCase("off")) {
             http.csrf().disable().authorizeRequests().
                     antMatchers("/v2/api-docs",
                             "/configuration/ui",
                             "/swagger-resources/**",
                             "/configuration/security",
                             "/swagger-ui.html",
                             "/signIn",
                             "/signUp",
                             "/welcome",
                             "/**",
                             "/webjars/**").permitAll().
                     anyRequest().authenticated()
                     .and().exceptionHandling().and().sessionManagement()
                     .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
         }else{
             http.csrf().disable().authorizeRequests().
                     antMatchers("/v2/api-docs",
                             "/configuration/ui",
                             "/swagger-resources/**",
                             "/configuration/security",
                             "/swagger-ui.html",
                             "/signIn",
                             "/signUp",
                             "/welcome",
        //                     "/**",
                             "/webjars/**").permitAll().
        anyRequest().authenticated()
                     .and().exceptionHandling().and().sessionManagement()
                     .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
         }

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
