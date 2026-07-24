package sk.tuke.gamestudio.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(authenticationProvider())
                .authorizeRequests()
                    .antMatchers(
                            "/",
                            "/taptiles",
                            "/taptiles/new",
                            "/taptiles/intro",
                            "/taptiles/levels",
                            "/taptiles/play",
                            "/taptiles/undo",
                            "/taptiles/transition",
                            "/taptiles/ending",
                            "/taptiles/more",
                            "/taptiles/rating",
                            "/login",
                            "/register",
                            "/css/**",
                            "/images/**",
                            "/index.html",
                            "/api/**"
                    ).permitAll()
                    .antMatchers(
                            "/taptiles/comment",
                            "/profile"
                    ).authenticated()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/taptiles", false)
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/taptiles?logout")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me")
                    .permitAll()
                .and()
                    .rememberMe()
                    .key("taptiles-session-remember-key")
                    .rememberMeParameter("remember-me")
                    .userDetailsService(userDetailsService)
                    .tokenValiditySeconds(60 * 60 * 24 * 14)
                .and()
                    .csrf()
                    .ignoringAntMatchers("/api/**");
    }
}
