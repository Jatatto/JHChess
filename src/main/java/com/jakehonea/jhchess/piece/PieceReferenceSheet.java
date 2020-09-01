package com.jakehonea.jhchess.piece;

import com.jakehonea.jhchess.JHChess;
import com.jakehonea.jhchess.utils.Pair;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PieceReferenceSheet {

    private static BufferedImage PIECE_REFERENCE_SHEET;

    private Map<Pair<Integer, Integer>, BufferedImage> cache = new HashMap<>();

    static {

        try {
            PIECE_REFERENCE_SHEET = ImageIO.read(JHChess.getResource("pieces.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private int getIconWidth() {

        return PIECE_REFERENCE_SHEET.getWidth() / 6;

    }

    private int getIconHeight() {

        return PIECE_REFERENCE_SHEET.getHeight() / 2;

    }

    public BufferedImage crop(int x, int y) {

        Pair<Integer, Integer> key = new Pair<>(x, y);

        if (cache.containsKey(key))
            return cache.get(key);

        cache.put(key, PIECE_REFERENCE_SHEET.getSubimage(x * getIconWidth(), y * getIconHeight(), getIconWidth(), getIconHeight()));

        return cache.get(key);

    }

}
