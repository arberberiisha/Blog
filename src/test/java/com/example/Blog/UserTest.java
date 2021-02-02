package com.example.Blog;


import com.example.Blog.Entity.User;
import com.example.Blog.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserTest {

    @Autowired
    private UserRepository uRepo;

    @Autowired
    private TestEntityManager em;


    @Test
    public void userTest(){

        User user = new User();
        user.setName("Test");
        user.setSurname("test2");
        user.setEmail("test3@hotmail.com");
        user.setPassword("testtest");
        user.setRoli("test");

        User saveUser =  uRepo.save(user);

        User existUser = em.find(User.class, saveUser.getId());

        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());


    }
}
