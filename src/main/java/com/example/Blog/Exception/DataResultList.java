package com.example.Blog.Exception;

import com.example.Blog.Enum.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataResultList<T> {
    private Integer status;

    private ResponseStatus responseStatus;

    private List<T> data;
}
