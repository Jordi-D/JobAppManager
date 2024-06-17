package com.jobs.jobapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity {

    /**
     * Configures UserDetailsManager bean to manage users and their roles using JDBC authentication.
     *
     * @param dataSource The DataSource bean for accessing the database.
     * @return UserDetailsManager instance configured with JDBC queries for authentication and authorization.
     */
    @Bean
    UserDetailsManager users(DataSource dataSource) {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);

        // Query to retrieve user details (username, password, status) for authentication
        users.setUsersByUsernameQuery("SELECT username, password, status FROM Users WHERE username = ?");

        // Query to retrieve user roles/authorities based on username for authorization
        users.setAuthoritiesByUsernameQuery("SELECT u.username, p.profile " +
                "FROM UserProfile up " +
                "INNER JOIN Users u ON u.id = up.idUser " +
                "INNER JOIN Profiles p ON p.id = up.idProfile " +
                "WHERE u.username = ?");

        return users;
    }

    /**
     * Configures HTTP security filters and access rules based on roles and URLs.
     *
     * @param http HttpSecurity instance to configure security settings.
     * @return SecurityFilterChain configured with authorization rules and login configuration.
     * @throws Exception If configuration fails.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String[] roles = {"USUARIO", "SUPERVISOR", "ADMINISTRADOR"};
        http.authorizeHttpRequests(authorize -> authorize
                // Allow access without authentication to static resources
                .requestMatchers("/bootstrap/**", "/images/**", "/tinymce/**", "/logos/**").permitAll()
                // Allow access without authentication to specific URLs
                .requestMatchers("/", "/signup", "/search", "/vacancies/view/**", "/bcrypt/**", "/swagger-ui/**").permitAll()
                // URL-based access rules based on roles
                .requestMatchers("/requests/create/**").hasAnyAuthority(roles[0])
                .requestMatchers("/requests/save").hasAnyAuthority(roles[0])
                .requestMatchers("/vacancies/view/**").hasAnyAuthority(roles[0])
                .requestMatchers("/requests/**").hasAnyAuthority(roles[1], roles[2])
                .requestMatchers("/vacancies/**").hasAnyAuthority(roles[1], roles[2])
                .requestMatchers("/categories/**").hasAnyAuthority(roles[1], roles[2])
                .requestMatchers("/users/**").hasAnyAuthority(roles[2])
                // All other URLs require authentication
                .anyRequest().authenticated()
        );

        // Configure login page URL and allow access without authentication
        http.formLogin(form -> form.loginPage("/login").permitAll());

        return http.build();
    }

    /**
     * Provides a BCryptPasswordEncoder bean for encoding passwords.
     *
     * @return BCryptPasswordEncoder instance.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
