package com.springlab.dao;

import com.springlab.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.List;

public class UserRepositoryTest {

    private UserRepository userRepository;

    @Before
    public void setUp() {
        EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .generateUniqueName(true)
                .addScript("dao/data.sql")
                .build();

        userRepository = new UserRepository(new JdbcTemplate(db));
    }


    @Test
    public void findUserById() throws Exception {
        User user = userRepository.findUserById(1);
        Assert.assertEquals(java.util.Optional.of(1).get(), user.getId());
        Assert.assertEquals("FirstTestUser", user.getUsername());
    }

    @Test
    public void findUserByName() throws Exception {
        User user = userRepository.findUserByName("FirstTestUser");
        Assert.assertEquals(java.util.Optional.of(1).get(), user.getId());
        Assert.assertEquals("FirstTestUser", user.getUsername());
    }

    @Test
    public void findAll() throws Exception {
        List<User> allUsers = userRepository.findAll();
        Assert.assertEquals(2, allUsers.size());
    }
}