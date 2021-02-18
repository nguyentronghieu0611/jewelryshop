package vn.jewel.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import vn.jewel.shop.exception.CustomAuthenticationFailureHandler;
import vn.jewel.shop.exception.CustomAuthenticationSuccessHandler;
import vn.jewel.shop.interceptor.CustomFilter;
import vn.jewel.shop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserService userService;

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

//    @Bean
//    public AuthenticationFailureHandler authenticationFailureHandler() {
//        return new CustomAuthenticationSuccessHandler();
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        String contents = Utils.insideResourceToString("user.json");
//        JSONObject userOBJ = new JSONObject(contents);
//        // account kai/123456
//        auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser(userOBJ.getString("username"))
//                .password(userOBJ.getString("password")).roles("USER");
//    }




    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Các trang không cần login
//        http.authorizeRequests().antMatchers("/registration**", "/js/**", "/css/**", "/img/**", "/webjars/**", "/icons/**", "/dist/**",
//                "/linechar/**", "/plugins/**", "/bootstrap/**","/apis/**","/cart**","/product**","/products**","/images/**","/**").permitAll();

        //Các trang cần role admin
        http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");

        //Xử lý truy cập trangg không đúng role
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        http.authorizeRequests().antMatchers("/confirm-account**","/registration**", "/js/**", "/css/**", "/img/**", "/webjars/**", "/icons/**", "/dist/**",
                "/linechar/**", "/plugins/**", "/bootstrap/**","/apis/**","/cart**","/product**","/products**","/images/**","/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and().formLogin()
//                .loginProcessingUrl("/j_spring_security_login")//
                .loginPage("/login")
                .defaultSuccessUrl("/home")//
//                .successHandler(authenticationFailureHandler())
                .failureHandler(authenticationFailureHandler())
//                .failureUrl("/login?message=error")//
                .permitAll().and()
                .logout().invalidateHttpSession(true).clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?message=logout")
                .permitAll();
        // Cấu hình Remember Me.
        http.authorizeRequests().and() //
                .rememberMe().tokenRepository(this.persistentTokenRepository()).userDetailsService(userService) //
                .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
    }
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        //enable customize throw exception in failurehandle
        auth.setHideUserNotFoundExceptions(false);
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

}
