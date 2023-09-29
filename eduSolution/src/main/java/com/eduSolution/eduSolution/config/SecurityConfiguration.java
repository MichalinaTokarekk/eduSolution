package com.eduSolution.eduSolution.config;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final AuthenticationFilter authFilter;
    private final AuthenticationProvider authenticationProvider;

    private final LogoutHandler logoutHandler;

    @Bean
    public org.springframework.web.filter.CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Header"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> corsFilter())
                .authorizeHttpRequests((authorizeHttpRequests) ->
                                authorizeHttpRequests
                                        .requestMatchers("/auth/**").permitAll()
//                                        .requestMatchers("/user-controller/users").hasAnyAuthority("USER","LIBRARIAN","ADMIN")
//                                        .requestMatchers("/user-controller/user/{id}").hasAnyAuthority("USER","LIBRARIAN","ADMIN")
//                                        .requestMatchers("/user-controller/**").hasAuthority("ADMIN")
                                        .requestMatchers("/user-controller/**").permitAll()

                            //COURSE
//                            .requestMatchers("/course-controller/**").hasAnyAuthority("USER","LIBRARIAN","ADMIN")
//                            .requestMatchers("/course-controller/courses").hasAnyAuthority("USER","LIBRARIAN","ADMIN")
//                            .requestMatchers("/course-controller/course/{id}").hasAnyAuthority("USER","LIBRARIAN","ADMIN")
//                            .requestMatchers("/course-controller/courseName/{name}").hasAnyAuthority("USER","LIBRARIAN","ADMIN")
//                            .requestMatchers("/course-controller/**").hasAnyAuthority("ADMIN", "TEACHER", "STUDENT")
                            .requestMatchers("/course-controller/**").permitAll()

//                            .requestMatchers("/class-group-controller/**").hasAnyAuthority("ADMIN", "STUDENT")
                            .requestMatchers("/class-group-controller/**").permitAll()


                            .requestMatchers("/semester-controller/**").permitAll()


                            .requestMatchers("/section-controller/**").permitAll()

                            .requestMatchers("/edu-material-controller/**").permitAll()



                            //SEMESTER
//                            .requestMatchers("/semester-controller/semesters").hasAnyAuthority("USER","LIBRARIAN","ADMIN")
//                            .requestMatchers("/semester-controller/semester/{id}").hasAnyAuthority("USER","LIBRARIAN","ADMIN")
//                            .requestMatchers("/semester-controller/semesterTitle/{title}").hasAnyAuthority("USER","LIBRARIAN","ADMIN")
//                            .requestMatchers("/semester-controller/**").hasAnyAuthority("USER","ADMIN", "LIBRARIAN")
                                        .requestMatchers("/semester-controller/**").permitAll()//.hasAnyAuthority("USER","ADMIN", "LIBRARIAN")



                                        .anyRequest().authenticated()
                )
                .sessionManagement(sessionManagment ->
                        sessionManagment
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(corsFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore((Filter) authFilter, UsernamePasswordAuthenticationFilter.class)

                .logout((logout)    ->
                        logout
                                .logoutUrl("/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                );

        return http.build();
    }
}
