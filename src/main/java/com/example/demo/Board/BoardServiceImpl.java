//package com.example.demo.Board;
//
//public class BoardServiceImpl {
//    public void insertBoard(Board board) {
//        boardRepo.save(board);
//    }
//    public Board getBoard(Board board) {
//        return boardRepo.findById(board.getSeq()).get();
//    }
//    public void updateBoard(Board board) {
//        Board findBoard = baordRepo.findById(board.getSeq()).get();
//
//        findBoard.setTitle(board.getTitle());
//        findBoard.setContent(board.getContent());
//        boardRepo.save(findBoard);
//    }
//}
