package com.jakehonea.jhchess.piece;

import com.jakehonea.jhchess.ChessBoard;

import javax.swing.*;
import java.awt.*;

public class Square extends JLabel {

    private final ChessBoard board;
    private final int row, col;

    private Piece piece;
    private boolean selected = false, highlight = false, holding = false;

    public Square(ChessBoard board, int row, int col) {

        this.board = board;

        this.row = row;
        this.col = col;

    }

    public boolean holdsPiece() {

        return piece != null;

    }

    public Piece getPiece() {

        return piece;

    }

    public void setPiece(Piece piece) {

        this.piece = piece;

    }

    public int getRow() {

        return row;

    }

    public int getColumn() {

        return col;

    }

    public boolean isHolding() {

        return holding;

    }

    public void setHighlighted(boolean highlight) {

        this.highlight = highlight;

    }

    public boolean isHighlighted() {

        return highlight;

    }

    public void setSelected(boolean selected) {

        this.selected = selected;

    }

    public boolean isSelected() {

        return selected;

    }

    public void setHolding(boolean holding) {

        this.holding = holding;

    }

    public ChessBoard getBoard() {

        return board;

    }

    @Override
    protected void paintComponent(Graphics g) {

        if (isSelected()) {

            Color current = getBackground();

            setBackground(current.darker());

            super.paintComponent(g);

            setBackground(current);

        } else if (!isHighlighted())
            super.paintComponent(g);

        if (getPiece() != null && !isHighlighted())
            getPiece().update(g);

        if (isHighlighted()) {

            super.paintComponent(g);

            if (getPiece() != null)
                getPiece().update(g);

            g.setColor(getBackground().darker());

            g.fillOval(getWidth() / 2 - (getWidth() / 6), getHeight() / 2 - (getHeight() / 6), getWidth() / 3, getHeight() / 3);

        }

    }

}
