package com.example.demo.Board;

import java.util.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Reply {
    private String writer;
    private String content;
    private Date createDate;
    private Long cnt;

}
