package com.example.Blog.Security.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Access;

@Getter
@AllArgsConstructor
public class AuthenticationResponse {

     private final String jwt;

}
