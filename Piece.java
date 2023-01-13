import java.awt.image.BufferedImage;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;
public class Piece extends JPanel implements MouseInputListener{
    private boolean isWhite;
    private int index;
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
            ROOK_BLACK = SHEET.getSubimage(360, 90, 90, 90);
            QUEEN_WHITE = SHEET.getSubimage(90, 0, 90, 90);
            QUEEN_BLACK = SHEET.getSubimage(90, 90, 90, 90);
            KING_WHITE = SHEET.getSubimage(0, 0, 90, 90);
            KING_BLACK = SHEET.getSubimage(0, 90, 90, 90);
        } catch (java.io.IOException e) {
            System.out.print("didn't load");
        }
    }

    
    



    public Piece(int index, int piece, boolean color){
        super();
        this.setSize(90,90);
        this.setOpaque(false);
        this.setPiece(piece);
        this.setWhite(color);
        this.setIndex(index);
        addMouseListener(this);
        addMouseMotionListener(this);
        
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


    
    public void setPiece(int piece) {
        this.piece = piece;
    }

    public void setWhite(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public int getPiece() {
        return piece;
    }

    public void setIndex(int index){
        this.index = index;
    }

    public int getIndex(){
        return this.index;
    }

    public boolean getIsWhite() {
        return this.isWhite;
    }


    public ArrayList<Integer> generatePawnMoves(){
        boolean starting = false;
        ArrayList<Integer> moves = new ArrayList<Integer>();
        if(this.isWhite){
            starting = index>47&&index<56;

            if(this.index-8>=0&&Chess.internalBoard.get(index-8).getPiece() == NONE){
                moves.add(index-8);
            }
            if(starting && this.index-16>=0&&Chess.internalBoard.get(index-16).getPiece() == NONE){
                moves.add(index-16);
            }
            if(this.index-9>=0&&Chess.internalBoard.get(index-9).getPiece() != NONE && Chess.internalBoard.get(index-7).getIsWhite() != this.isWhite){
                moves.add(index-9);
            }
            if(this.index-7>=0&&Chess.internalBoard.get(index-7).getPiece() != NONE && Chess.internalBoard.get(index-7).getIsWhite() != this.isWhite){
                moves.add(index-7);
            }
        }else{
            starting = index>7&&index<16;

            if(this.index+8<=63&&Chess.internalBoard.get(index+8).getPiece() == NONE){
                moves.add(index+8);
            }
            if(starting && this.index+16<=63&&Chess.internalBoard.get(index+16).getPiece() == NONE){
                moves.add(index+16);
            }
            if(this.index+9<=63&&Chess.internalBoard.get(index+9).getPiece() != NONE && Chess.internalBoard.get(index-7).getIsWhite() != this.isWhite){
                moves.add(index+9);
            }
            if(this.index+7<=63&&Chess.internalBoard.get(index+7).getPiece() != NONE && Chess.internalBoard.get(index-7).getIsWhite() != this.isWhite){
                moves.add(index+7);
            }
        }
 
        return moves;

    }


    public void mouseClicked(MouseEvent e) {
        System.out.println(this.index);
        GridBagConstraints g = new GridBagConstraints();
        for(int h : Chess.hilited){
            Chess.visualBoard.get(h).setBackground(Chess.visualBoard.get(h).isWhite()?Color.decode("#ECB069"):Color.decode("#763C2C"));
        }
        Chess.hilited.clear();
        g.weightx = 1.0;
        g.weighty = 1.0;
        g.fill = GridBagConstraints.BOTH;
        switch(this.piece){
            case NONE:
                break;
            case PAWN:
                for(int index : generatePawnMoves()){
                    Chess.visualBoard.get(index).setBackground(Color.decode("#F88379"));;
                    Chess.hilited.add(index);
                    
                }
                break;
        }
    };
    public void mouseDragged(MouseEvent e) {
        System.out.println(e.getPoint());
    };
    public void mouseEntered(MouseEvent e) {};
    public void mouseExited(MouseEvent e) {};
    public void mouseMoved(MouseEvent e) {

    };
    public void mousePressed(MouseEvent e) {

    };
    public void mouseReleased(MouseEvent e) {

    };

}
