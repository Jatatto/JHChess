package com.jakehonea.jhchess.piece.pieces;

import com.jakehonea.jhchess.ChessBoard;
import com.jakehonea.jhchess.piece.PieceType;
import com.jakehonea.jhchess.piece.Square;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class RookPiece extends BishopPiece {

    public RookPiece(ChessBoard board, PieceType type, BufferedImage image, boolean side) {

        super(board, type, image, side);

    }

    @Override
    public Map<Square, Boolean> getAvailableMoves(ChessBoard chessBoard) {

        Map<Square, Boolean> moves = new HashMap<>();

        getMovesInAxis(chessBoard, moves, 1, 0);
        getMovesInAxis(chessBoard, moves, -1, 0);
        getMovesInAxis(chessBoard, moves, 0, 1);
        getMovesInAxis(chessBoard, moves, 0, -1);

        return moves;

    }

    public void getMovesInAxis(ChessBoard board, Map<Square, Boolean> moves, int xDirection, int yDirection) {

        for (int i = 1; i < 9; i++) {

            Square square = board.getSquareAt(
                    xDirection == 0 ?
                            getSquare().getRow() :
                            getSquare().getRow() + (i * xDirection),
                    yDirection == 0 ?
                            getSquare().getColumn() :
                            getSquare().getColumn() + (i * yDirection)
            );

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