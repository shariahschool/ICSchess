import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Chess{
    static JFrame board = new JFrame("Chess");

    public static void main(String[] args) {

        Chessboard visuals = new Chessboard();
        board.setSize(800, 800);
        board.add(visuals);
        board.setVisible(true);
    }

}

