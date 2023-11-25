package com.moucdev.app.DataFixture;

import com.moucdev.app.entities.User;
import com.moucdev.app.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@Slf4j
public class UserDataFixture {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void buildUsers() {
        if (userRepository.count() == 0) {
            log.info("**********************CREATE USERS************************");
            userRepository.saveAll(
                Arrays.asList(
                    new User(null,"Mouctar", "Diallo", "mouc@gmail.com"),
                    new User(null, "John", "Doe", "joh@gmail.com"),
                    new User(null, "Moussa", "keita", "keita@gmail.com"),
                    new User(null, "Abdoukarim", "sidibe", "thiate@gmail.com")
                )
            );
        }
    }
}
