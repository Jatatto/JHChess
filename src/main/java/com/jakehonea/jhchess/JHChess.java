package com.jakehonea.jhchess;

import java.net.URL;

public class JHChess {

    private static JHChess INSTANCE;
    private static ChessBoard CHESS_BOARD;

    public static void main(String... args) {

        INSTANCE = new JHChess();
        CHESS_BOARD = new ChessBoard();

    }

    public static ChessBoard getChessBoard() {

        return CHESS_BOARD;

    }

    public static void setChessBoard(ChessBoard chessBoard) {

        CHESS_BOARD = chessBoard;

    }

    public static URL getResource(String path) {

        return INSTANCE.getClass().getClassLoader().getResource(path);

    }

}
