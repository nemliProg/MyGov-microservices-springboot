package org.mygov.usersservice.security;


import org.mygov.usersservice.etities.Admin;
import org.mygov.usersservice.etities.Employee;
import org.mygov.usersservice.services.admin.AdminService;
import org.mygov.usersservice.services.employee.EmployeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private PasswordEncoder passwordEncoder;

    private AdminService adminService;
    private EmployeeService employeeService;

    public SecurityConfig(PasswordEncoder passwordEncoder, AdminService adminService, EmployeeService employeeService) {
        this.passwordEncoder = passwordEncoder;
        this.adminService = adminService;
        this.employeeService = employeeService;
    }

    @Bean
    UserDetailsService userDetailsService() throws Exception{
        return email -> {
            String newEmail = email.split(":")[0];
            String user = email.split(":")[1];
            GrantedAuthority authority;
            List<GrantedAuthority> authorities;
            switch (user){
                case "admin":
                    Admin admin = this.adminService.loadAdminByEmail(newEmail);
                    authority = new SimpleGrantedAuthority("ADMIN");
                    if(admin==null) throw new UsernameNotFoundException("User not found!");
                    authorities = Collections.singletonList(authority);
                    return new User(admin.getEmail(), admin.getPassword(),authorities);
                case "employee":
                    Employee employee = this.employeeService.loadAdminByEmail(newEmail);
                    if(employee==null) throw new UsernameNotFoundException("User not found!");
                    authority = new SimpleGrantedAuthority("EMPLOYEE");
                    authorities = Collections.singletonList(authority);
                    return new User(employee.getEmail(), employee.getPassword(),authorities);
            }
            throw new UsernameNotFoundException("User not found!");
        };
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable());
        http
                //.authorizeHttpRequests(au->au.anyRequest().permitAll())
                .authorizeHttpRequests(aut->aut.requestMatchers("/actuator/**").permitAll())
                .authorizeHttpRequests(auth->auth.anyRequest().authenticated())
                .sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults());
        http.addFilter(new JwtAuthenticationFilter(authenticationManager(this.authenticationProvider(this.userDetailsService()))));
        http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider) {
        return new ProviderManager(Arrays.asList(authenticationProvider));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(this.passwordEncoder);
        authenticationProvider.setHideUserNotFoundExceptions(true);
        return authenticationProvider;
    }


    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

}
