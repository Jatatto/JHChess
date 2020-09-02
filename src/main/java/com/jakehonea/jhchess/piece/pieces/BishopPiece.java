package com.jakehonea.jhchess.piece.pieces;

import com.jakehonea.jhchess.ChessBoard;
import com.jakehonea.jhchess.piece.Piece;
import com.jakehonea.jhchess.piece.PieceType;
import com.jakehonea.jhchess.piece.Square;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class BishopPiece extends Piece {

    public BishopPiece(ChessBoard board, PieceType type, BufferedImage image, boolean side) {

        super(board, type, image, side);

    }

    @Override
    public Map<Square, Boolean> getAvailableMoves(ChessBoard chessBoard) {

        Map<Square, Boolean> moves = new HashMap<>();

        getMovesInDiagonal(chessBoard, moves, 1, 1);
        getMovesInDiagonal(chessBoard, moves, -1, 1);
        getMovesInDiagonal(chessBoard, moves, -1, -1);
        getMovesInDiagonal(chessBoard, moves, 1, -1);

        return moves;

    }

    public void getMovesInDiagonal(ChessBoard board, Map<Square, Boolean> moves, int xDirection, int yDirection) {

        for (int x = 1; x < 9; x++) {

            Square square = board.getSquareAt(getSquare().getRow() + (x * xDirection), getSquare().getColumn() + (x * yDirection));

            if (square != null) {

                if (square.getPiece() == null)
                    moves.put(square, true);
                else {

                    if (square.getPiece().getSide() != getSide())
                        moves.put(square, true);

                    break;

                }

            }

        }

    }

}