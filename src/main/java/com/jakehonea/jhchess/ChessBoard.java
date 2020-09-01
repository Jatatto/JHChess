package com.jakehonea.jhchess;

import com.jakehonea.jhchess.piece.PieceReferenceSheet;
import com.jakehonea.jhchess.piece.PieceType;
import com.jakehonea.jhchess.piece.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChessBoard extends JFrame {

    private final PieceReferenceSheet referenceSheet;
    private final int xOffset, yOffset;
    private final List<Square> squares;

    private Square holding = null;
    private boolean turn;

    public ChessBoard() {

        super("Chess Board");

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) (screen.getHeight() * .8), (int) (screen.getHeight() * .8));
        getContentPane().setLayout(new GridLayout(8, 8));

        setIgnoreRepaint(false);

        Insets insets = new Insets(26, 3, 3, 3);

        this.referenceSheet = new PieceReferenceSheet();

        this.squares = new ArrayList<>();
        loadComponents();
        loadListeners();

        this.xOffset = insets.left;
        this.yOffset = insets.top;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public Square getSquareAt(int x, int y) {

        return getSquares().stream()
                .filter(square -> square.getRow() == x && square.getColumn() == y)
                .findFirst()
                .orElse(null);

    }

    public List<Square> getSquares() {

        if (squares.size() == 0)
            squares.addAll(Arrays.stream(getContentPane().getComponents())
                    .filter(component -> component instanceof Square)
                    .map(component -> (Square) component)
                    .collect(Collectors.toList()));

        return squares;

    }

    public void unhighlight() {

        getSquares().forEach(square -> {

            square.setSelected(false);
            square.setHolding(false);
            square.setHighlighted(false);

        });

    }

    public void loadListeners() {

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                Component component = getContentPane().getComponentAt(e.getX() - xOffset, e.getY() - yOffset);

                if (component instanceof Square) {

                    unhighlight();

                    Square square = (Square) component;

                    if (holding == null && square.getPiece() != null) {

                        holding = square;

                        square.getPiece().getProcessedMoves(ChessBoard.this).forEach(move -> move.setHighlighted(true));

                        square.setSelected(true);

                    } else if (holding.getPiece() != null && holding.getPiece().getProcessedMoves(ChessBoard.this).contains(square)) {

                        holding.getPiece().move(square);

                        holding = null;

                    } else holding = null;

                }

                repaint();

            }
        });

    }

    public void loadComponents() {

        Rectangle bounds = new Rectangle(
                getWidth() - 3 - 3,
                getHeight() - 26 - 3
        );

        for (int row = 0; row < 8; row++)

            for (int col = 0; col < 8; col++) {

                Square square = new Square(this, row, col);

                PieceType defaultPiece = getDefaultPieceAt(row, col);

                if (defaultPiece != null)
                    square.setPiece(defaultPiece.createNewPiece(this, row <= 1));

                square.setOpaque(true);

                square.setBackground((row + col) % 2 == 0 ? Color.WHITE : new Color(30, 125, 30));

                square.setBounds(bounds);

                add(square);

            }

    }

    private PieceType getDefaultPieceAt(int row, int col) {

        if (row == 1 || row == 6)
            return PieceType.PAWN;

        if (row == 0 || row == 7)
            if (col == 0 || col == 7)
                return PieceType.ROOK;
            else if (col == 1 || col == 6)
                return PieceType.KNIGHT;
            else if (col == 2 || col == 5)
                return PieceType.BISHOP;
            else if (col == 3)
                return PieceType.QUEEN;
            else if (col == 4)
                return PieceType.KING;

        return null;

    }

    public PieceReferenceSheet getReferenceSheet() {

        return referenceSheet;

    }

}
