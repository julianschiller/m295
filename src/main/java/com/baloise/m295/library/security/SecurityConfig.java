package com.baloise.m295.library.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private static final String ROLE_LIBRARIAN = "LIBRARIAN";
    private static final String ROLE_USER = "USER";

    @Value("${ad.domain}")  private String adDomain;
    @Value("${ad.url}")     private String adUrl;
    @Value("${ad.root-dn}") private String adRootDn;

    @Bean
    public ActiveDirectoryLdapAuthenticationProvider adProvider() {
        ActiveDirectoryLdapAuthenticationProvider provider =
                new ActiveDirectoryLdapAuthenticationProvider(adDomain, adUrl, adRootDn);
        provider.setConvertSubErrorCodesToExceptions(true);
        provider.setUseAuthenticationRequestCredentials(true);
        provider.setAuthoritiesMapper(new AdGroupToRoleMapper());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(adProvider());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/library/borrowings").hasRole(ROLE_LIBRARIAN)
                        .requestMatchers("/library/borrowings").hasAnyRole(ROLE_LIBRARIAN, ROLE_USER)
                        .requestMatchers(HttpMethod.GET, "/library/media").hasAnyRole(ROLE_LIBRARIAN, ROLE_USER)
                        .requestMatchers("/library/**").hasRole(ROLE_LIBRARIAN)
                        .anyRequest().denyAll())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .logout(logout -> logout.logoutSuccessUrl("/logout-success.html").permitAll())
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }
}