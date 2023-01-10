import java.awt.*;

import javax.swing.*;


public class Square extends JPanel {
    private boolean white;
    private int rank;
    private int file;

    public Square(int x, int y, boolean white){
        super();
        this.setPreferredSize(new Dimension(100,100));
        setRank(x);
        setFile(y);
        setWhite(white);
    }
        


    public boolean isWhite(){
        return this.white;
    }

    public int getRank(){
        return this.rank;
    }

    public int getFile(){
        return this.file;
    }

    public void setWhite(boolean white){
        super.setBackground(white?Color.decode("#ECB069"):Color.decode("#763C2C"));
        this.white = white;
    }

    public void setRank(int x){
        this.rank = x;
    }

    public void setFile(int y){
        this.file = y;
    }

    public static boolean sameColour(Square x, Square y){
        return x.isWhite() == y.isWhite();
    }

}
