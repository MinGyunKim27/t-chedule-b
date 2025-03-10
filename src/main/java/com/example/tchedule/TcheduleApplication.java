package com.example.tchedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.tchedule.repository")  // ✅ Repository 패키지 지정
public class TcheduleApplication {
    public static void main(String[] args) {
        SpringApplication.run(TcheduleApplication.class, args);
    }
}
