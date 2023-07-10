package lt.codeacademy.javau5.securitybasics;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig {
	@Bean
    InMemoryUserDetailsManager userDetailsService() {
        UserDetails user1 = User.withUsername("user1")
            .password(passwordEncoder().encode("user1Pass"))
            .roles("USER")
            .build();
        UserDetails user2 = User.withUsername("user2")
            .password(passwordEncoder().encode("user2Pass"))
            .roles("USER")
            .build();
        UserDetails admin = User.withUsername("admin")
            .password(passwordEncoder().encode("adminPass"))
            .roles("ADMIN")
            .build();
        return new InMemoryUserDetailsManager(user1, user2, admin);
    }

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        	.authorizeHttpRequests(authorize -> authorize
        				.requestMatchers("/admin/**").hasRole("ADMIN")
        				.requestMatchers("/anonymous*").anonymous()
        				.requestMatchers("/login*").permitAll()
        				.anyRequest().authenticated()
        	)
        	
        	.formLogin(withDefaults()
        	)
        
        	.logout(logout -> logout
        			.logoutUrl("/anonymous")
        			.deleteCookies("JSESSIONID")
        	)
        			
        ;
	    return http.build();
	}
    

	@Bean 
	PasswordEncoder passwordEncoder() { 
	    return new BCryptPasswordEncoder(); 
	}
}

//.csrf( c -> 
//c.disable()
//)
//.authorizeHttpRequests(authorize -> authorize  // changed from authorizeRequests to authorizeHttpRequests
//.requestMatchers("/admin/**").hasRole("ADMIN")
//.requestMatchers("/anonymous*").anonymous()
//.requestMatchers("/login*").permitAll()
//.anyRequest().authenticated()
//)
//.formLogin(withDefaults()
//
//)
//.logout(logout -> logout
//.logoutUrl("/perform_logout")
//.deleteCookies("JSESSIONID")
//.logoutSuccessHandler(logoutSuccessHandler())
//)