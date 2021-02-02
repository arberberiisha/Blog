package com.example.Blog.Service;


import com.example.Blog.Entity.Post;
import com.example.Blog.Entity.Role;
import com.example.Blog.Entity.User;
import com.example.Blog.Enum.ResponseStatus;
import com.example.Blog.Enum.Status;
import com.example.Blog.Exception.ResponseResult;
import com.example.Blog.Model.CreatePost;
import com.example.Blog.Model.UpdatePost;
import com.example.Blog.Model.UpdateUser;
import com.example.Blog.Repository.PostRepository;
import com.example.Blog.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;


    @Autowired
    private UserRepository userRepository;




        public List<Post> getAll(){
           List<Post> listaPostimeve = postRepository.findAll();
            return listaPostimeve;
        }

        public Post getById(Long id){
           return postRepository.findById(id).get();
        }


        public ResponseResult save(CreatePost createPost){

            ResponseResult result = new ResponseResult();

            result.setResponseStatus(ResponseStatus.CONFLICT);
            result.setStatus(ResponseStatus.CONFLICT.getStatusCode());


            Optional<User> optionalRole = userRepository.findById(createPost.getUserId());
            User user = optionalRole.get();
            try {


                Post post = createPost.getPost();

                post.setUser(user);
                post.setCreatedAt(LocalDateTime.now());

                postRepository.save(post);

                result.setResponseStatus(ResponseStatus.SUCCESS);
                result.setStatus(ResponseStatus.SUCCESS.getStatusCode());
                result.setMessage(ResponseStatus.SUCCESS.getMsg());
            }catch (Exception e){
                result.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
                result.setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getStatusCode());
                result.setMessage(ResponseStatus.INTERNAL_SERVER_ERROR.getMsg());
                System.err.println(e.getMessage());
            }

            return result;
        }

        public ResponseResult deleteById(Long id){

             ResponseResult result = new ResponseResult();

        Optional<Post> optionalPost = postRepository.findById(id);


        try {
            Post post = optionalPost.get();
            post.setStatus(Status.DELETED);
            post.setDeletedAt(LocalDateTime.now());

            postRepository.save(post);

            result.setResponseStatus(ResponseStatus.SUCCESS);
            result.setStatus(ResponseStatus.SUCCESS.getStatusCode());
            result.setMessage(ResponseStatus.SUCCESS.getMsg());
        }catch (Exception e){
            result.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
            result.setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getStatusCode());
            result.setMessage(ResponseStatus.INTERNAL_SERVER_ERROR.getMsg());
            System.err.println(e.getMessage());
        }


        return result;

        }

    public ResponseResult updatePost(UpdatePost updatePost, Long id) {
        ResponseResult result = new ResponseResult();

        result.setResponseStatus(ResponseStatus.CONFLICT);

        try {

            Post post =  postRepository.getOne(id);
            post.setTitle(updatePost.getTitle());
            post.setDs(updatePost.getDs());


            postRepository.save(post);

            result.setResponseStatus(ResponseStatus.SUCCESS);
            result.setStatus(ResponseStatus.SUCCESS.getStatusCode());
            result.setMessage(ResponseStatus.SUCCESS.getMsg());

        } catch (Exception e) {
            result.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
            result.setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getStatusCode());
            result.setMessage(e.getLocalizedMessage());
        }

        return result;
    }

}
