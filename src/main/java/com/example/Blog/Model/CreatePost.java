package com.example.Blog.Model;

import com.example.Blog.Entity.Post;
import com.example.Blog.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePost {

    @NotBlank(message = "Must not be empty")
    @Pattern(regexp = "^[a-zA-Z]{2,45}$", message = "First Name must contain only letters!")
    private String title;

    @NotBlank(message = "Must not be empty")
    @Pattern(regexp = "^[a-zA-Z]{2,45}$", message = "Last Name must contain only letters!")
    private String ds;


    @NotNull(message = "Must not be empty")
    private Long userId;


    public Post getPost(){
        return new Post(title, ds);
    }
}
