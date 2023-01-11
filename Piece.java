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
    static BufferedImage SHEET;
    static BufferedImage PAWN_IMAGE;

    static{
        try {
            SHEET = ImageIO.read(new File("Sprites.png"));
            PAWN_IMAGE = SHEET.getSubimage(0, 0, 90, 90);
        } catch (java.io.IOException e) {
            System.out.print("didn't load");
        }
    }

    
    



    public Piece(int file, int rank, int piece, boolean color){
        super();
        this.setSize(90,90);
        this.setOpaque(false);
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
