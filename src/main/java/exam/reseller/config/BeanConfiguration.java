package exam.reseller.config;


import exam.reseller.domain.helpers.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class BeanConfiguration {

    @Bean
    public ModelMapper createMapper(){
        return new ModelMapper();
    }

    @Bean
    @SessionScope
    public LoggedUser loggedUser(){
        return new LoggedUser();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
