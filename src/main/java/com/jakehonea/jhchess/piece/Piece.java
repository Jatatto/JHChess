package com.jakehonea.jhchess.piece;

import com.jakehonea.jhchess.ChessBoard;
import com.jakehonea.jhchess.piece.pieces.KingPiece;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

public abstract class Piece {

    private Map<Square, Boolean> moves;
    private final ChessBoard board;
    private final PieceType type;
    private final boolean side;
    private ImageIcon icon;

    public Piece(ChessBoard chessBoard, PieceType pieceType, BufferedImage image, boolean side) {

        this.board = chessBoard;
        this.type = pieceType;
        this.icon = image == null ? null : new ImageIcon(image);
        this.side = side;

    }

    public Square getSquare() {

        return board.getSquares().stream()
                .filter(square -> Piece.this.equals(square.getPiece()))
                .findFirst()
                .orElse(null);

    }

    public ChessBoard getBoard() {

        return board;

    }

    public PieceType getType() {

        return type;

    }

    public ImageIcon getIcon() {

        return icon;

    }

    public boolean getSide() {

        return side;

    }

    public void update(Graphics graphics) {

        if (icon.getIconWidth() != getSquare().getWidth())
            icon = new ImageIcon(icon.getImage().getScaledInstance(getSquare().getWidth(), getSquare().getHeight(), Image.SCALE_SMOOTH));

        ((Graphics2D) graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.drawImage(icon.getImage(), 0, 0, icon.getImageObserver());

    }

    public void move(Square square) {

        getSquare().setPiece(null);
        square.setPiece(this);

        board.processMoves();
    }

    public void setMoves(Map<Square, Boolean> moves) {

        this.moves = moves;

    }

    public Map<Square, Boolean> getProcessedMoves(ChessBoard board) {

        if (moves == null)
            moves = getAvailableMoves(board);

        moves.keySet().removeIf(square -> (square.getPiece() != null && square.getPiece().getType() == PieceType.KING) || !isLegalMove(square));

        return moves;

    }

    public boolean isLegalMove(Square square) {

        if (this instanceof KingPiece)
            return getBoard().getTeamSquares(!getSide()).stream()
                    .noneMatch(pieceSquare -> {

                        if (pieceSquare.getPiece().getType() == PieceType.KING)
                            return false;

                        if (pieceSquare.getPiece().getProcessedMoves(getBoard()).containsKey(square))
                            return pieceSquare.getPiece().getProcessedMoves(getBoard()).get(square);

                        return false;

                    });

        return true;

    }


    public abstract Map<Square, Boolean> getAvailableMoves(ChessBoard chessBoard);

}
