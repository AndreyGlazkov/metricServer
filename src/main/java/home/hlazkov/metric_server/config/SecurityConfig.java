package home.hlazkov.metric_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurity(ServerHttpSecurity http) {
        http
                .authorizeExchange((authorize) -> authorize
                        .pathMatchers("/", "/css/*").permitAll()
                        .anyExchange().authenticated())
                .formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        var user = User.withDefaultPasswordEncoder()
                .username("Andrii")
                .password("123456")
                .roles("USER")
                .build();
        return new MapReactiveUserDetailsService(user);
    }

}
