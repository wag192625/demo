package com.example.demo.controller;

import com.example.demo.Board.Board;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class BoardController {

    @RequestMapping("/getBoardList")
    public String getBoardList(Model model) {
        //List 타입으로 Board객체를 넣는 boardLiset 변수명 선언
        // = (대입연산자) heap 메모리에 ArrayList 타입으로 할당
        //List는 ArrayList의 부모클래스
        List<Board> boardList = new ArrayList<Board>();
        for (int i=1; i<= 10; i++) {
            //Board 클래스로 board인스턴스 생성
            Board board = new Board();
            //롬복로 자동생성된 setter 메서드로 데이터 입력
            board.setSeq(new Long(i));
            board.setTitle("게시판 프로그램 테스트");
            board.setWriter("정명훈");
            board.setContent("게시판 프로그램 테스트입니다...");
            board.setCreateDate(new Date());
            board.setCnt(0L);
            //boardList 배열에 boarf객체 넣기(for문 10번 도니까 board객체 넣기)
            boardList.add(board);
        }
        model.addAttribute("boardList", boardList);
        // viewResolver
        // return getBoardList라는 문자열로 templates에 있는 같은 이름의 html 파일을 찾늗나
        return "getBoardList";
    }
}
