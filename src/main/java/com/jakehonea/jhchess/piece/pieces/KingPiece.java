package com.jakehonea.jhchess.piece.pieces;

import com.jakehonea.jhchess.ChessBoard;
import com.jakehonea.jhchess.piece.Piece;
import com.jakehonea.jhchess.piece.PieceType;
import com.jakehonea.jhchess.piece.Square;
import com.jakehonea.jhchess.utils.Pair;

import java.awt.image.BufferedImage;
import java.util.*;

public class KingPiece extends Piece {

    private final Pair<Integer, Integer>[] kingOffsets;

    public KingPiece(ChessBoard board, PieceType type, BufferedImage image, boolean side) {

        super(board, type, image, side);

        this.kingOffsets = new Pair[8];

        kingOffsets[0] = new Pair<>(1, 0);
        kingOffsets[1] = new Pair<>(1, 1);
        kingOffsets[2] = new Pair<>(1, -1);

        kingOffsets[3] = new Pair<>(-1, 0);
        kingOffsets[4] = new Pair<>(-1, 1);
        kingOffsets[5] = new Pair<>(-1, -1);

        kingOffsets[6] = new Pair<>(0, 1);
        kingOffsets[7] = new Pair<>(0, -1);

    }

    @Override
    public Map<Square, Boolean> getAvailableMoves(ChessBoard chessBoard) {

        Map<Square, Boolean> moves = new HashMap<>();

        Arrays.stream(kingOffsets).forEach(pair -> {

            Square square = chessBoard.getSquareAt(getSquare().getRow() + pair.getKey(), getSquare().getColumn() + pair.getValue());

            if (square != null) {

                if (square.getPiece() != null && square.getPiece().getSide() != getSide())
                    moves.put(square, true);
                else if (square.getPiece() == null)
                    moves.put(square, true);

            }

        });

        return moves;

    }

}
