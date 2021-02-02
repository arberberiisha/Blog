package com.example.Blog.Model;


import com.example.Blog.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class UserData {

    private Long id;

    private String name;

    private String surname;

    private String role;

    private Status status;


    public UserData(Long id, String name, String surname, String role,Status status){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.status = status;
    }

}
