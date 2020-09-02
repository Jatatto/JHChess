package com.jakehonea.jhchess.piece.pieces;

import com.jakehonea.jhchess.ChessBoard;
import com.jakehonea.jhchess.piece.PieceType;
import com.jakehonea.jhchess.piece.Square;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class QueenPiece extends RookPiece {

    public QueenPiece(ChessBoard board, PieceType type, BufferedImage image, boolean side) {

        super(board, type, image, side);

    }

    @Override
    public Map<Square, Boolean> getAvailableMoves(ChessBoard chessBoard) {

        Map<Square, Boolean> moves = new HashMap<>();

        getMovesInDiagonal(chessBoard, moves, 1, 1);
        getMovesInDiagonal(chessBoard, moves, -1, 1);
        getMovesInDiagonal(chessBoard, moves, -1, -1);
        getMovesInDiagonal(chessBoard, moves, 1, -1);

        getMovesInAxis(chessBoard, moves, 1, 0);
        getMovesInAxis(chessBoard, moves, -1, 0);
        getMovesInAxis(chessBoard, moves, 0, 1);
        getMovesInAxis(chessBoard, moves, 0, -1);

        return moves;

    }


}
