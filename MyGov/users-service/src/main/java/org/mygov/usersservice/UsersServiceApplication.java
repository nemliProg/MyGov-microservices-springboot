package org.mygov.usersservice;

import org.mygov.usersservice.etities.Admin;
import org.mygov.usersservice.repositoies.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class UsersServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersServiceApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*
    @Bean
    CommandLineRunner runner(AdminRepository repository, PasswordEncoder passwordEncoder){
        return args->{
          repository.save(Admin.builder().email("admin@mygov.com").password(passwordEncoder().encode("admin123")).build());
          repository.findAll().forEach(System.out::println);
        };
    }

     */


}
