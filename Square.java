import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.event.MouseInputListener;


public class Square extends JPanel implements MouseInputListener{
    private boolean whiteSquare;
    private int rank;
    private int file;

    public static ArrayList<Square> highlighted = new ArrayList<Square>();
    private int piece;
    private boolean pieceColor;

    public boolean highlightedSquare = false;
    public static Square lastSelected = null;
    

    public Square(int x, int y, int piece, boolean pieceColor, boolean white){
        super();
        this.setPreferredSize(new Dimension(100,100));
        this.setLayout(new GridBagLayout());
        setRank(x);
        setFile(y);
        setPiece(piece);
        setPieceColor(pieceColor);
        setWhiteSquare(white);
        addMouseListener(this);
    }
        
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(this.getPieceColor()){
            switch(this.getPiece()){
                case Piece.PAWN: g.drawImage(Piece.PAWN_WHITE,(getWidth()-90)/2,(getHeight()-90)/2,null);
                    break;
                case Piece.KNIGHT: g.drawImage(Piece.KNIGHT_WHITE,(getWidth()-90)/2,(getHeight()-90)/2,null);
                    break;
                case Piece.BISHOP: g.drawImage(Piece.BISHOP_WHITE,(getWidth()-90)/2,(getHeight()-90)/2,null);
                    break;
                case Piece.ROOK: g.drawImage(Piece.ROOK_WHITE,(getWidth()-90)/2,(getHeight()-90)/2,null);
                    break;
                case Piece.QUEEN: g.drawImage(Piece.QUEEN_WHITE,(getWidth()-90)/2,(getHeight()-90)/2,null);
                    break;
                case Piece.KING: g.drawImage(Piece.KING_WHITE,(getWidth()-90)/2,(getHeight()-90)/2,null);
                    break;
                default: break;
            }
        }else{
            switch(this.getPiece()){
                case Piece.PAWN: g.drawImage(Piece.PAWN_BLACK,(getWidth()-90)/2,(getHeight()-90)/2,null);
                    break;
                case Piece.KNIGHT: g.drawImage(Piece.KNIGHT_BLACK,(getWidth()-90)/2,(getHeight()-90)/2,null);
                    break;
                case Piece.BISHOP: g.drawImage(Piece.BISHOP_BLACK,(getWidth()-90)/2,(getHeight()-90)/2,null);
                    break;
                case Piece.ROOK: g.drawImage(Piece.ROOK_BLACK,(getWidth()-90)/2,(getHeight()-90)/2,null);
                    break;
                case Piece.QUEEN: g.drawImage(Piece.QUEEN_BLACK,(getWidth()-90)/2,(getHeight()-90)/2,null);
                    break;
                case Piece.KING: g.drawImage(Piece.KING_BLACK,(getWidth()-90)/2,(getHeight()-90)/2,null);
                    break;
                default: break;
            }
        }
    }


    public boolean isWhiteSquare(){
        return this.whiteSquare;
    }

    public int getRank(){
        return this.rank;
    }

    public int getFile(){
        return this.file;
    }
    
    public int getPiece() {
        return piece;
    }

    public void setPiece(int piece) {
        this.piece = piece;
    }

    public boolean getPieceColor() {
        return pieceColor;
    }

    public void setPieceColor(boolean pieceColor) {
        this.pieceColor = pieceColor;
    }

    public void setWhiteSquare(boolean white){
        super.setBackground(white?Color.decode("#ECB069"):Color.decode("#763C2C"));
        this.whiteSquare = white;
    }

    public void setRank(int x){
        this.rank = x;
    }

    public void setFile(int y){
        this.file = y;
    }

    public void highlight(){
        highlighted.add(this);
        this.highlightedSquare = true;
        this.setBackground(Color.decode("#FF5733"));
    }

    public void unhighlight(){
        this.highlightedSquare = false;
        this.setWhiteSquare(whiteSquare);
    }

    public static void unhighlightAll(){
        for (Iterator<Square> i = highlighted.iterator(); i.hasNext();) {
            Square s = i.next();
            s.unhighlight();
            i.remove();
        }
    }

    public static void handleMove(Square des){
            Square ori = lastSelected;
            Chess.moveHistory.add(new Move(ori,des,des.getPiece()!=Piece.NONE));
            Chess.internalBoard[ori.getRank()][ori.getFile()] = Piece.NONE;
            Chess.internalBoard[des.getRank()][des.getFile()] = ori.getPiece();

            
            des.setPiece(ori.getPiece());
            des.setPieceColor(ori.getPieceColor());
            ori.setPiece(Piece.NONE);
            ori.setPieceColor(Piece.PIECE_BLACK);
            ori.revalidate();
            ori.repaint();
            des.revalidate();
            des.repaint();

            Chess.whiteTurn = !Chess.whiteTurn;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(this.rank + ", "+this.file);
        if(this.highlightedSquare){
            handleMove(this);
            unhighlightAll();
        }else{

            unhighlightAll();
            System.out.println("Fired");
            if(this.piece != Piece.NONE && this.pieceColor == Chess.whiteTurn){
                lastSelected = this;
                System.out.println("Clicked on "+this.piece);
                ArrayList<Move> moves = new ArrayList<Move>();
                moves = Piece.generatePawnMoves(this);
                for (Move m : moves) {
                    m.des.highlight();
                }

            }
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
}
