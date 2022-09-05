package com.example.demo.Board;

import java.util.Date;

//외장 라이브러리 (gradle에서 설치)
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;

//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;

//롬북에 있는 getter,setter라는 메서드를 통해 하단에 있는 클래스 Board는
//자동으로 getter, setter 메서드가 생성됨을 암시함 (@어노테이션)
@Getter
@Setter
@ToString
public class Board {
    //@ID : PK (PRIMARY KEY) SQL문의 기본 키
    //@GeneratedValue 자동 생성 속성
//    @ID @GeneratedValue
    private Long seq;

    //@Column은 title 필드값을 컬럼화할 때 길이와 null 입력 가능 여부 옵션
//    @Column(length = 40; nullable)
    private String title;
    private String writer;
    private String content;
    private Date createDate;
    private Long cnt;

    private String category;
    private String categoryName;
    private String comment;

    //원래는 setter, getter라는 메서드가 있어야 private 필드값에 데이터를 넣을 수 있지만,
    //(gredle에서 설치한) 롬북이라는 라이브러리로 자동 getter, setter 메서드 생성
}