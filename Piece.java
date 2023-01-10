import java.awt.image.BufferedImage;
import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
public class Piece extends JPanel{
    private boolean isWhite;
    private int file;
    private int rank;
    private int piece;

    public static final int PAWN = 1;
    public static final int KNIGHT = 3;
    public static final int BISHOP = 4;
    public static final int ROOK = 5;
    public static final int QUEEN = 9;

    static final BufferedImage PAWN_IMAGE = ImageIO.read(new File("Piece-Sprites/p.png"));



    public Piece(int file, int rank, int piece, boolean color){
        super();
        this.setFile(file);
        this.setRank(rank);
        this.setPiece(piece);
        this.setWhite(color);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(PAWN_IMAGE,0,0,null);
    }

    public void setFile(int file) {
        this.file = file;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
    
    public void setPiece(int piece) {
        this.piece = piece;
    }

    public void setWhite(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public int getPiece() {
        return piece;
    }

    public int getRank() {
        return rank;
    }

    public int getFile() {
        return file;
    }

    public boolean getIsWhite() {
        return this.isWhite;
    }
}
