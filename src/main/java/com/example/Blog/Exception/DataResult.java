package com.example.Blog.Exception;

import com.example.Blog.Enum.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataResult<T> {

    private Integer status;

    private ResponseStatus responseStatus;

    private T data;
}
