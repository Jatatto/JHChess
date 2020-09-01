package com.jakehonea.jhchess.piece;

import com.jakehonea.jhchess.ChessBoard;
import com.jakehonea.jhchess.piece.pieces.*;

import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;

public enum PieceType {

    KING(KingPiece.class, 0),
    QUEEN(QueenPiece.class, 1),
    BISHOP(BishopPiece.class, 2),
    KNIGHT(KnightPiece.class, 3),
    ROOK(RookPiece.class, 4),
    PAWN(PawnPiece.class, 5);

    private Class<? extends Piece> classInstance;
    private int x;

    PieceType(Class<? extends Piece> instance, int x) {

        this.classInstance = instance;
        this.x = x;

    }

    public Piece createNewPiece(ChessBoard board, boolean side) {

        try {

            BufferedImage pieceImage = getPieceImage(board.getReferenceSheet(), side);

            return classInstance.getConstructor(ChessBoard.class, PieceType.class, BufferedImage.class, boolean.class)
                    .newInstance(board, this, pieceImage, side);

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * @param side true = white, false = dark piece
     * @return the image associated with the piece
     */
    public BufferedImage getPieceImage(PieceReferenceSheet reference, boolean side) {

        return reference.crop(this.x, side ? 1 : 0);

    }

}
