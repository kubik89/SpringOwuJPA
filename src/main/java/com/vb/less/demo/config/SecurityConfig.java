package com.vb.less.demo.config;

import com.vb.less.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userDetailsService;

    // цей метод конфігурує автентифікацію (створення логіну та паролів)
// .and() - повертає на крок-клас вище, як в папці, для виходу на головну.
// roles обовязкове значення, бо падає помилка.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1").password("user1").roles("USER").and()
                .withUser("user2").password("user2").roles("ADMIN").and()
                .and()
                .authenticationProvider(daoAuthenticationProvider());
    }

    // DaoAuthenticationProvider - провайдер, який автентифікує юзерів використовуючи базу даних.
// userDetailsService - механізм пошуку юзерів.
// це клас який буде шифрувати паролі, перевіряти всі властивості користувача і повертати користувача із БД.
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder()); //password decoder
        return daoAuthenticationProvider;
    }


    // це клас який буде шифрувати пароль для методу configure що вище, щоб паролі не гуляли відкриті.
    @Bean
    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

    // .antMatchers("/**").authenticated() - все має бути відгукуватись, АЛЕ якщо користувач авторизований
    // .antMatchers(HttpMethod.POST).hasRole("ADMIN") - методи POST може виконувати ЛИШЕ користувач ролі ADMIN
    // antmatchers мають йти від НАЙБІЛЬШ КОНКРЕТНОГО до БІЛЬШ ЗАГАЛЬНОГО
    // POST /users є більш конкретним, ніж просто /POST, тому має йти найвище
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/movies").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/movies").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST, "/users").anonymous()
//                .antMatchers(HttpMethod.PUT, "/directors").hasRole("ADMIN")
                .anyRequest().anonymous()
//                .antMatchers("/**").authenticated()
                .and()
// якщо писати .formLogin() то по урлі http://localhost:8081/movies?page=2 вискочить форма для введення логіна
// якщо ми пишемо свою UI, тоді використовуємо .httpBasic()
//        .formLogin();
                .httpBasic();
    }
}
