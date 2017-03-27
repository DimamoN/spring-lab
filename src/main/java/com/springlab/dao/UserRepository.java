package com.springlab.dao;

import com.springlab.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private JdbcTemplate jdbcTemplate;

    public User findUserById(int id){
        return this.jdbcTemplate.queryForObject(
                "select id, username from Users where id = ?",
                new Object[]{id},
                UserRepository::mapRow);
    }

    public User findUserByName(String username){
        return this.jdbcTemplate.queryForObject(
                "select id, username from Users where username = ?",
                new Object[]{username},
                UserRepository::mapRow);
    }

    public List<User> findAll(){
        return this.jdbcTemplate.query(
                "select id, username from Users",
                UserRepository::mapRow);
    }

    public User createUser(User user){
        final String sql = "insert into Users (username) values (?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int row = this.jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, user.getUsername());
                    return ps;
                },
                keyHolder);

        Object newUserId = keyHolder.getKeys().get("id");
        user.setId((Integer)newUserId);
        return user;
    }

    public static User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        return user;
    }
}
