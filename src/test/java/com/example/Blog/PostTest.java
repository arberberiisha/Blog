package com.example.Blog;


import com.example.Blog.Entity.Post;
import com.example.Blog.Repository.PostRepository;
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
public class PostTest {

    @Autowired
    private PostRepository pRepo;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private TestEntityManager em;


    @Test
    public void userTest(){

        Post test = new Post();
        test.setTitle("test");
        test.setDs("test");
        test.setUser(userRepository.getOne(1L));

        Post saveTest =  pRepo.save(test);

        Post existTest = em.find(Post.class, saveTest.getId());

        assertThat(existTest.getId()).isEqualTo(test.getId());


    }
}
