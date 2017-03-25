package com.springlab;

import com.springlab.dao.UserRepository;
import com.springlab.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Configuration
public class MockConfig {

    @Bean
    @Primary
    public UserRepository userRepositoryMock(){

        List<User> users = new ArrayList<>();
        users.add(new User(1,"olya"));
        users.add(new User(2,"bob"));

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.getUsers()).thenReturn(users);

        when(userRepository.getUser("olya"))
                .thenReturn(users.get(0));

        when(userRepository.getUser("bob"))
                .thenReturn(users.get(1));

        return userRepository;
    }

}
