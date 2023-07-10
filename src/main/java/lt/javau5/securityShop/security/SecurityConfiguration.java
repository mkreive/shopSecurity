package lt.javau5.securityShop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id, pw, active from members where user_id=?");

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer -> configurer
                        .requestMatchers(HttpMethod.GET, "/products").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/login").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/home").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/api/products").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/api/products").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/products").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/products/**").hasRole("ADMIN")
                        .anyRequest()
                        .authenticated())
                        .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/authenticateUser")
                        .permitAll()
                )
                .logout(logout -> logout.permitAll())
                .exceptionHandling(confifurer ->
                        confifurer.accessDeniedPage("/access-denied"));

        return http.build();

//        // use HTTP Basic authentication
//        http.httpBasic(Customizer.withDefaults());
//
//        // disable Cross Site Request Forgery (CSRF) - for rest api
//        http.csrf(csrf -> csrf.disable());
//        return http.build();
    }
}
