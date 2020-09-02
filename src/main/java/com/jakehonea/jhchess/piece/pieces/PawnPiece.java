package com.jakehonea.jhchess.piece.pieces;

import com.jakehonea.jhchess.ChessBoard;
import com.jakehonea.jhchess.piece.Piece;
import com.jakehonea.jhchess.piece.PieceType;
import com.jakehonea.jhchess.piece.Square;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class PawnPiece extends Piece {

    private boolean hasMoved = false;

    public PawnPiece(ChessBoard board, PieceType type, BufferedImage image, boolean side) {

        super(board, type, image, side);

    }

    @Override
    public void move(Square square) {

        super.move(square);

        this.hasMoved = true;

    }

    @Override
    public Map<Square, Boolean> getAvailableMoves(ChessBoard chessBoard) {

        Map<Square, Boolean> moves = new HashMap<>();

        int direction = getSide() ? 1 : -1;

        for (int i = 1; i <= (hasMoved ? 1 : 2); i++) {

            Square square = chessBoard.getSquareAt(getSquare().getRow() + (direction * i), getSquare().getColumn());
            if (square != null) {

                if (square.getPiece() != null)
                    break;

                moves.put(square, false);

            }

        }

        Square[] diagonal = new Square[]{
                chessBoard.getSquareAt(getSquare().getRow() + direction, getSquare().getColumn() - 1),
                chessBoard.getSquareAt(getSquare().getRow() + direction, getSquare().getColumn() + 1)
        };

        for (Square square : diagonal)
            if (square != null && square.getPiece() != null && square.getPiece().getSide() != getSide())
                moves.put(square, true);

        return moves;

    }

}