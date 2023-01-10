import java.awt.*;
import javax.swing.*;

public class Chess{
    static JFrame board = new JFrame("Chess");
    static Square[] visualBoard = new Square[64];
    static Piece[] internalBoard = new Piece[64];

    public static void main(String[] args) {

        JPanel visuals = new JPanel();
        visuals.setBackground(Color.gray);
        
        for(int rank = 0; rank<8; rank++){

            for(int file = 0; file<8; file++){
                System.out.println(""+(rank*8+file));
                visualBoard[rank*8+file] = new Square(file,rank,(rank+file)%2==0);
            }

        }

        for (Square i : visualBoard){
            visuals.add(i);
            visuals.revalidate();
            visuals.repaint();
        }

        visuals.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        visuals.setLayout(new GridLayout(8,8));

        board.setSize(800, 800);
        board.add(visuals);
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.pack();
        board.setVisible(true);
    }

}

