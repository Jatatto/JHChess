package com.jakehonea.jhchess.piece.pieces;

import com.jakehonea.jhchess.ChessBoard;
import com.jakehonea.jhchess.piece.Piece;
import com.jakehonea.jhchess.piece.PieceType;
import com.jakehonea.jhchess.piece.Square;
import com.jakehonea.jhchess.utils.Pair;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KnightPiece extends Piece {

    private final Pair<Integer, Integer>[] knightOffsets;

    public KnightPiece(ChessBoard board, PieceType type, BufferedImage image, boolean side) {

        super(board, type, image, side);

        this.knightOffsets = new Pair[8];

        knightOffsets[0] = new Pair<>(1, 2);
        knightOffsets[1] = new Pair<>(1, -2);

        knightOffsets[2] = new Pair<>(-1, 2);
        knightOffsets[3] = new Pair<>(-1, -2);

        knightOffsets[4] = new Pair<>(2, 1);
        knightOffsets[5] = new Pair<>(2, -1);

        knightOffsets[6] = new Pair<>(-2, 1);
        knightOffsets[7] = new Pair<>(-2, -1);

    }

    @Override
    public List<Square> getAvailableMoves(ChessBoard chessBoard) {

        List<Square> moves = new ArrayList<>();

        Arrays.stream(knightOffsets).forEach(pair -> {

            Square square = chessBoard.getSquareAt(getSquare().getRow() + pair.getKey(), getSquare().getColumn() + pair.getValue());

            if (square != null) {

                if (square.getPiece() != null && square.getPiece().getSide() != getSide())
                    moves.add(square);
                else if (square.getPiece() == null)
                    moves.add(square);

            }

        });

        return moves;

    }

}
