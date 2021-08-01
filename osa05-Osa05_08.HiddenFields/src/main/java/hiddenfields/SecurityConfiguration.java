package hiddenfields;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .anyRequest().authenticated();
        http.formLogin()
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        // withdefaultpasswordencoder on deprekoitu mutta toimii yhä
        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .authorities("USER")
                .build();
        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username("postman")
                .password("pat")
                .authorities("POSTER")
                .build();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(user1);
        manager.createUser(user2);

        return manager;
    }

}
