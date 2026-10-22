package org.example;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration

public class Appconfig {
    @Bean
    public Employee employee(){
        return new Employee();
    }
}
