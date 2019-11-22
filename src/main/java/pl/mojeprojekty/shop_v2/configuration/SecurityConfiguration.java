package pl.mojeprojekty.shop_v2.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/register").permitAll()
                .antMatchers("/", "/shop").hasAnyRole("ADMIN", "USER")
                .antMatchers("/secret").hasRole("ADMIN")
                .and().csrf().disable()
                .formLogin()
//                .loginPage("/login").permitAll()
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/")
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout()
                .and()
                .httpBasic();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
