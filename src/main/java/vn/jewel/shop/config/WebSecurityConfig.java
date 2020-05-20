package vn.jewel.shop.config;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import vn.jewel.shop.common.Utils;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        String contents = Utils.insideResourceToString("user.json");
        JSONObject userOBJ = new JSONObject(contents);
        // account kai/123456
        auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser(userOBJ.getString("username"))
                .password(userOBJ.getString("password")).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Các trang không cần login
//        http.authorizeRequests().antMatchers("/registration**", "/js/**", "/css/**", "/img/**", "/webjars/**", "/icons/**", "/dist/**",
//                "/linechar/**", "/plugins/**", "/bootstrap/**","/apis/**","/cart**","/product**","/products**","/images/**","/**").permitAll();

        //Các trang cần role user
        http.authorizeRequests().antMatchers("/admin/**").hasRole("USER");

        //Xử lý truy cập trangg không đúng role
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        http.authorizeRequests().antMatchers("/registration**", "/js/**", "/css/**", "/img/**", "/webjars/**", "/icons/**", "/dist/**",
                "/linechar/**", "/plugins/**", "/bootstrap/**","/apis/**","/cart**","/product**","/products**","/images/**","/**").permitAll()
                .antMatchers("/admin/**").hasRole("USER")
                .and().formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")//
                .failureUrl("/login?message=error")//
                .permitAll().and()
                .logout().invalidateHttpSession(true).clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?message=logout")
                .permitAll();
        // Cấu hình Remember Me.
        http.authorizeRequests().and() //
                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
                .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
    }
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
        return memory;
    }

}
