package com.example.Blog.Model;

import com.example.Blog.Entity.User;
import com.example.Blog.Enum.Status;
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
@AllArgsConstructor
@NoArgsConstructor
public class CreateUser {


    @NotBlank(message = "Must not be empty")
    @Pattern(regexp = "^[a-zA-Z]{2,45}$", message = "First Name must contain only letters!")
    private String name;

    @NotBlank(message = "Must not be empty")
    @Pattern(regexp = "^[a-zA-Z]{2,45}$", message = "Last Name must contain only letters!")
    private String surname;

    @NotBlank(message = "Must not be empty")
    @Email(message = "Email is not valid")
    private String email;

    @NotBlank(message = "Must not be empty")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]{6,45}$", message = "Min 6 characters and Max 45")
    private String password;

    @NotNull(message = "Must not be empty")
    private Long roleId;

    private Status status;


    public User getUser(){
        return new User(name,surname,email, status );
    }
}
