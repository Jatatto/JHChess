package com.jakehonea.jhchess.piece;

import com.jakehonea.jhchess.ChessBoard;
import com.jakehonea.jhchess.JHChess;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    private final List<Square> moves = new ArrayList<>();
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

    public void move(Square square) {

        getSquare().setPiece(null);
        square.setPiece(this);

        square.getBoard().getSquares().forEach(pieceSquare -> {

            if (pieceSquare.getPiece() != null)
                pieceSquare.getPiece().getProcessedMoves(square.getBoard()).clear();

        });

    }

    public List<Square> getProcessedMoves(ChessBoard board) {

        if (moves.size() == 0)
            moves.addAll(getAvailableMoves(board));

        return moves;

    }

    public void update(Graphics graphics) {

        if (icon.getIconWidth() != getSquare().getWidth())
            icon = new ImageIcon(icon.getImage().getScaledInstance(getSquare().getWidth(), getSquare().getHeight(), Image.SCALE_SMOOTH));

        ((Graphics2D) graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.drawImage(icon.getImage(), 0, 0, icon.getImageObserver());

    }

    public abstract List<Square> getAvailableMoves(ChessBoard chessBoard);

}
