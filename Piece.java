import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import java.io.File;
public class Piece extends JPanel{
    private boolean isWhite;
    private int file;
    private int rank;
    private int piece;

    public static final int NONE = 0;
    public static final int PAWN = 1;
    public static final int KNIGHT = 3;
    public static final int BISHOP = 4;
    public static final int ROOK = 5;
    public static final int QUEEN = 9;
    public static final int KING = 10;
    static BufferedImage SHEET;
    static BufferedImage PAWN_WHITE, PAWN_BLACK, KNIGHT_WHITE, KNIGHT_BLACK, BISHOP_WHITE, BISHOP_BLACK, ROOK_WHITE, ROOK_BLACK, QUEEN_WHITE, QUEEN_BLACK, KING_WHITE, KING_BLACK;
    private MouseListener eveListener = new MouseInputListener() {
        public void mouseClicked(MouseEvent e) {
            System.out.println("Click detected");
        };
        public void mouseDragged(MouseEvent e) {};
        public void mouseEntered(MouseEvent e) {};
        public void mouseExited(MouseEvent e) {};
        public void mouseMoved(MouseEvent e) {};
        public void mousePressed(MouseEvent e) {};
        public void mouseReleased(MouseEvent e) {};

    };

    static{
        try {
            SHEET = ImageIO.read(new File("Sprites.png"));
            PAWN_WHITE = SHEET.getSubimage(450, 0, 90, 90);
            PAWN_BLACK = SHEET.getSubimage(450, 90, 90, 90);
            KNIGHT_WHITE = SHEET.getSubimage(270, 0, 90, 90);
            KNIGHT_BLACK = SHEET.getSubimage(270, 90, 90, 90);
            BISHOP_WHITE = SHEET.getSubimage(180, 0, 90, 90);
            BISHOP_BLACK = SHEET.getSubimage(180, 90, 90, 90);
            ROOK_WHITE = SHEET.getSubimage(360, 0, 90, 90);
            ROOK_WHITE = SHEET.getSubimage(360, 90, 90, 90);
            QUEEN_WHITE = SHEET.getSubimage(90, 0, 90, 90);
            QUEEN_WHITE = SHEET.getSubimage(90, 90, 90, 90);
            KING_WHITE = SHEET.getSubimage(0, 0, 90, 90);
            KING_BLACK = SHEET.getSubimage(0, 90, 90, 90);
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
        this.addMouseListener(eveListener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(this.getIsWhite()){
            switch(this.getPiece()){
                case PAWN: g.drawImage(PAWN_WHITE,(getWidth()-90)/2,(getHeight()-90)/2,null);
                    break;
                case KNIGHT: g.drawImage(KNIGHT_WHITE,(getWidth()-90)/2,(getHeight()-90)/2,null);
                    break;
                case BISHOP: g.drawImage(BISHOP_WHITE,(getWidth()-90)/2,(getHeight()-90)/2,null);
                    break;
                case ROOK: g.drawImage(ROOK_WHITE,(getWidth()-90)/2,(getHeight()-90)/2,null);
                    break;
                case QUEEN: g.drawImage(QUEEN_WHITE,(getWidth()-90)/2,(getHeight()-90)/2,null);
                    break;
                case KING: g.drawImage(KING_WHITE,(getWidth()-90)/2,(getHeight()-90)/2,null);
                    break;
                default: break;
            }
        }else{
            switch(this.getPiece()){
                case PAWN: g.drawImage(PAWN_BLACK,(getWidth()-90)/2,(getHeight()-90)/2,null);
                    break;
                case KNIGHT: g.drawImage(KNIGHT_BLACK,(getWidth()-90)/2,(getHeight()-90)/2,null);
                    break;
                case BISHOP: g.drawImage(BISHOP_BLACK,(getWidth()-90)/2,(getHeight()-90)/2,null);
                    break;
                case ROOK: g.drawImage(ROOK_BLACK,(getWidth()-90)/2,(getHeight()-90)/2,null);
                    break;
                case QUEEN: g.drawImage(QUEEN_BLACK,(getWidth()-90)/2,(getHeight()-90)/2,null);
                    break;
                case KING: g.drawImage(KING_BLACK,(getWidth()-90)/2,(getHeight()-90)/2,null);
                    break;
                default: break;
            }
        }
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
