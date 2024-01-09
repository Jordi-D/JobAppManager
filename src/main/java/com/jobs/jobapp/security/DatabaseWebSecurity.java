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

    @Bean
    UserDetailsManager users(DataSource dataSource) {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);

// Consulta para obtener usuarios por nombre de usuario (autenticación)
        users.setUsersByUsernameQuery("SELECT username, password, status FROM Users WHERE username = ?");

// Consulta para obtener roles/autoridades por nombre de usuario (autorización)
        users.setAuthoritiesByUsernameQuery("SELECT u.username, p.profile " +
                "FROM UserProfile up " +
                "INNER JOIN Users u ON u.id = up.idUser " +
                "INNER JOIN Profiles p ON p.id = up.idProfile " +
                "WHERE u.username = ?");

        return users;
    }

    //metodo para dar acceso a urls
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authorize -> authorize
                //No identificacion
                .requestMatchers("/bootstrap/**", "images/**", "tinymce/**", "/logos/**").permitAll()
                //No Identificacion
                .requestMatchers("/", "signup", "/search", "/vacantes/view/**","/bcrypt/**").permitAll()
                //URL POR ROLES
                .requestMatchers("/requests/create/**").hasAnyAuthority("USUARIO")
                .requestMatchers("/requests/save").hasAnyAuthority("USUARIO")
                .requestMatchers("/vacancies/view/**").hasAnyAuthority("USUARIO")
                .requestMatchers("/requests/**").hasAnyAuthority("SUPERVISOR","ADMINISTRADOR")
                .requestMatchers("/vacancies/**").hasAnyAuthority("SUPERVISOR", "ADMINISTRADOR")
                .requestMatchers("/categories/**").hasAnyAuthority("SUPERVISOR", "ADMINISTRADOR")
                .requestMatchers("/users/**").hasAnyAuthority("ADMINISTRADOR")

                //Otras urls requieren aut
                .anyRequest().authenticated()
        );
        //Formulario Login no requiere aut
        http.formLogin(form -> form.loginPage("/login").permitAll());
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
