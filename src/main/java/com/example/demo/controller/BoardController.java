package com.example.demo.controller;

//내장 라이브러리 호출 (import)
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//외장 라이브러리 호출 (import), gradle로 설치한 라이브러리
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Board.Board;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {
    //step1. 일반 문자열 변수 사용
    static String title_string_static = "";
    static String writer_string_static = "";
    static String content_string_static = "";

    //step2. 배열 객체 사용
    static ArrayList<String> title_array = new ArrayList<String>();
    static ArrayList<String> writer_array = new ArrayList<String>();
    static ArrayList<String> content_array = new ArrayList<String>();

    //step3. 사용자 생성 객체 사용
    static ArrayList<Board> board_array = new ArrayList<Board>();
    static int count = 0;
    //@GetMapping 또는 @PostMapping응 @requestMapping의 자식 클래스이다.
    //@RequestMapping의 기능을 모두 쓸 수 있다.
    //자식클래스 어노테이션이 아닌 부모클래스 어노테이션을 쓰는 이유는 기능의 한정을 통해서
    //서버의 리소스 감소 및 보안을 위해서이다.

    //create
    @GetMapping("/insertBoard")
    public String insertBoardView(){
        return "insertBoard";
    }

    //[클라이언트]html form태그의 method 속성의 값이 post를 인삭하여
    //아래의 @PostMapping에 연결
    @PostMapping("insertBoard")
    public String insertBoard(
            @RequestParam("title")String title,
            @RequestParam("writer")String writer,
            @RequestParam("content")String content,
            Model model) {
        title_string_static = title;
        writer_string_static = writer;
        content_string_static = content;

        title_array.add(title);
        writer_array.add(writer);
        content_array.add(content);

        count++;
        Board board = new Board();

        board.setSeq((long) count);
        board.setTitle(title);
        board.setWriter(writer);
        board.setContent(content);
        board.setCreateDate(new Date());
        board.setCnt(0L);
        board_array.add(board);

        //redirect : 페이지 전환 이동
        //redirect:getBoardList : getBoard 페이지로 이동
        return "redirect:getBoardList";
    }

    //@어노테이션은 메서드 혹은 클래스에 속성, 정의를 해서 스프링이나 자바에서 찾기 쉽도록 해주는 선언부
    //얘) @Override은 부모 메서드를 재정의하여 사용한다고 자바나 스프링에게 속성 명시
    //@RequestParam :[클라이언트]에서 String 문자열을 [서버]에 전달하는 매개변수 선언
    //@RequestMapping("title")String title에서 ("title")은 [클라이언트]의 name이라는 속성으로써
    //Key값을 매개변수를 전달
    @RequestMapping("getBoard")
    public String getBoard(
            @RequestParam("seq")String seq,
            @RequestParam("userRole")String userRole,
            @RequestParam("userId")String userId,
            @RequestParam("title")String title,
            @RequestParam("writer")String writer,
            @RequestParam("content")String content,
            @RequestParam("createDate")String createDate,
            @RequestParam("cnt")String cnt,
            Model model) {
        model.addAttribute("seq", seq);
        model.addAttribute("title", title);
        model.addAttribute("writer", writer);
        model.addAttribute("content", content);
        model.addAttribute("createDate", createDate);
        model.addAttribute("cnt", cnt);
        model.addAttribute("userId", userId);
        model.addAttribute("userRole", userRole);
        return "getBoard";
    }

    //클라이언트에서 서버로 무언가 데이터를 전송하기 위한 Mapping (@RequestMapping)
    //@RequestMapping은 서버에서 디스페처서블릿을 통해 [클라이언트]html의 action태그의 주소와 동일한
    //문자열을 찾는 매핑 기능(연결)이 실행되고 하단에 메서드가 실행
    //return String인 이유는 뷰 리졸버가 html파일을 찾기 위한 문자열을 리턴
    @RequestMapping("/getBoardList")
    public String getBoardList(Model model) {
        //List 타입으로 Board객체를 넣는 boardLiset 변수명 선언
        // = (대입연산자) heap 메모리에 ArrayList 타입으로 할당
        //List는 ArrayList의 부모클래스
        List<Board> boardList = new ArrayList<>();
//        if (title_array.size() > 0 ) {
//            for (int i = 0; i<title_array.size(); i++) {
//                //Board 클래스로 board인스턴스 생성
//                Board board = new Board();
//                //롬복로 자동생성된 setter 메서드로 데이터 입력
//                board.setSeq(new Long(i)+1);
//                board.setTitle(title_array.get(i));
//                board.setWriter(writer_array.get(i));
//                board.setContent(content_array.get(i));
//                //내장 클래스인 java.util.Date 객체로 시간 데이터 출력
//                board.setCreateDate(new Date());
//                board.setCnt(0L);
//                //boardList 배열에 board객체 넣기(for문 10번 도니까 board객체 넣기)
//                boardList.add(board);
//            }
//        }
//        title_array 일때는 삭제가 안되었는데 그 이유가 뭐냐
//        getBoard.html에서


        if(board_array.size()> 0) {
            for(int i=0; i<board_array.size(); i++){
                Board board =new Board();
                board.setSeq(board_array.get(i).getSeq());
                board.setTitle(board_array.get(i).getTitle());
                board.setWriter(board_array.get(i).getWriter());
                board.setContent(board_array.get(i).getContent());
                board.setCreateDate(board_array.get(i).getCreateDate());
                board.setCnt(board_array.get(i).getCnt());
                boardList.add(board);
            }
        }
        //model 객체에 boardList(arrayList)를 boardList Key값으로 넣음
        //attributeName = Key
        //attributeValue = value
        //model에는 참조타입만 넣을 수 있다. (addAttribute 메서드 안에 매개변수 타입으로 확인 가능)
        model.addAttribute("boardList", boardList);
        //디스패처서블릿이 뷰 리졸버를 찾아서 연결해준다.
        // viewResolver
        // return getBoardList라는 문자열로 templates에 있는 같은 이름의 html 파일을 찾는다.
        return "getBoardList";
    }

    @GetMapping("/deleteBoard")
    public String deleteBoard(@RequestParam("seq")String seq) {
        //seq매개변수 (getBoard.html에서 받아옴)로 board_array 배열에서
        //.getSeq로 같은 index를 찾아
        //board_array에 있는 board객체 삭제 >> 원하는 게시글 삭제


        for(int i=0; i<board_array.size(); i++){
            //board_array.get(i).getSeq() : board_array의 i번째 객체를 찾아서 getSeq()라는 메서드를 통해 seq필드값 가져오기
            //equals()메서드를 통해서 매개변수 seq값과 비교 (참조 타입이므로)
            //seq의 타입은 Long입니다. 소수점있는 데이터(숫자)이므로 매개변수 seq(String)과 같은 타입이 아니므로 비교 불가
            //board_array.get(i).getSeq()의 값 Long을 String으로 변환 =Long.toString()
            if(Long.toString(board_array.get(i).getSeq()).equals(seq)){
                //board_array의 i번째 객체 삭제
                board_array.remove(i);
            }
        }
        return "redirect:getBoardList";
    }

    //Post 방식으로 [클라이언트]에서 [서버]로 매핑
    @PostMapping("/updateBoard")
    public String updateBoard(
            //HTML에서 name 속성을 가진 값을 매개변수 String seq에 할당
            //=@RequestParam("seq")
            @RequestParam("seq")String seq,
            @RequestParam("title")String title,
            @RequestParam("content")String content
    ) {
        //board_array 배열을 순회하여 board 객체의 seq 필드값을 매개변수 seq와 비교하여 true값 찾기
        for (int i = 0; i < board_array.size(); i++) {
            if (Long.toString(board_array.get(i).getSeq()).equals(seq)) {
                //setTitle과 같은 setter로 데이터 변경
                board_array.get(i).setTitle(title);
                board_array.get(i).setContent(content);
                }
            }
        return "redirect:getBoardList";
    }

//    @GetMapping("/getBoard")
//    public String getBoard(Board board, Model model) {
//        model.addAttribute("Board", boardService.getBoard(board));
//        return "getBoard";
//    }
//    @PostMapping("/updateBoard")
//    public String updateBoard(Board board) {
//        boardService.updateBoard(board);
//        return "forward:getBoardList";
//    }
}