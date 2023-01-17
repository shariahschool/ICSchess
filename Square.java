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
    private int pieceColor;
    private boolean pieceProt = false;

    public boolean highlightedSquare = false;
    public static Square lastSelected = null;
    public boolean isPinned = false;
    

    public Square(int x, int y, int piece, int pieceColor, boolean white){
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
        if(this.getPieceColor() == Piece.PIECE_WHITE){
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
                default: 
                    break;
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


    public boolean isProt(){
        return this.pieceProt;
    }
    public void setProt(boolean prot){
        this.pieceProt = prot;
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

    public int getPieceColor() {
        return pieceColor;
    }

    public void setPieceColor(int pieceColor) {
        this.pieceColor = pieceColor;
    }

    public void setWhiteSquare(boolean white){
        super.setBackground(white?Color.decode("#ECB069"):Color.decode("#763C2C"));
        this.whiteSquare = white;
        Chess.board.revalidate();
        Chess.board.repaint();
        this.revalidate();
        this.repaint();
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
        Chess.board.revalidate();
        Chess.board.repaint();
        this.revalidate();
        this.repaint();
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
            Chess.moveHistory.add(new Move(ori,des));
            Chess.internalBoard[ori.getRank()][ori.getFile()] = Piece.NONE;
            Chess.internalBoard[des.getRank()][des.getFile()] = ori.getPiece();

            if(ori.getPiece() == Piece.KING){
                if(ori.getPieceColor() == Piece.PIECE_BLACK){
                    Chess.blackKing = des;
                }else{
                    Chess.whiteKing = des;
                }
            }
            
            
            des.setPiece(ori.getPiece());
            des.setPieceColor(ori.getPieceColor());
            ori.setPiece(Piece.NONE);
            ori.setPieceColor(Piece.PIECE_BLACK);
            ori.revalidate();
            ori.repaint();
            des.revalidate();
            des.repaint();
            

            Chess.gameTurn = Chess.gameTurn==Piece.PIECE_WHITE?Piece.PIECE_BLACK:Piece.PIECE_WHITE;
            //System.out.println(Arrays.deepToString(Chess.internalBoard));
    }

    public static void handleMove(Square oriold,Square desold){
        Square des = Chess.visualBoard[desold.getRank()][desold.getFile()];
        Square ori = Chess.visualBoard[oriold.getRank()][oriold.getFile()];
        Chess.moveHistory.add(new Move(ori,des));
        Chess.internalBoard[ori.getRank()][ori.getFile()] = Piece.NONE;
        Chess.internalBoard[des.getRank()][des.getFile()] = ori.getPiece();

        if(ori.getPiece() == Piece.KING){
            if(ori.getPieceColor() == Piece.PIECE_BLACK){
                Chess.blackKing = des;
            }else{
                Chess.whiteKing = des;
            }
        }
        
        des.setPiece(ori.getPiece());
        des.setPieceColor(ori.getPieceColor());
        ori.setPiece(Piece.NONE);
        ori.setPieceColor(Piece.PIECE_BLACK);
        ori.revalidate();
        ori.repaint();
        des.revalidate();
        des.repaint();

        
        Chess.gameTurn = Chess.gameTurn==Piece.PIECE_WHITE?Piece.PIECE_BLACK:Piece.PIECE_WHITE;

        //System.out.println(Arrays.deepToString(Chess.internalBoard));
}

    public static void unmakeMove(){
        if(Chess.moveHistory.size()!=0){
            Move m = Chess.moveHistory.get(Chess.moveHistory.size()-1);
            //System.out.println("Unmaking move: ("+ m.ori.getRank()+", "+m.ori.getFile()+") "+m.ori.getPiece()+" , ("+m.des.getRank()+", "+m.des.getFile()+") "+m.des.getPiece());

            Square newDes = Chess.visualBoard[m.des.getRank()][m.des.getFile()];
            Square newOri = Chess.visualBoard[m.ori.getRank()][m.ori.getFile()];

            if(newDes.getPiece() == Piece.KING){
                if(newDes.getPieceColor() == Piece.PIECE_BLACK){
                    Chess.blackKing = newOri;
                }else{
                    Chess.whiteKing = newOri;
                }
            }

            Chess.internalBoard[newOri.getRank()][newOri.getFile()] = m.ori.getPiece();
            Chess.internalBoard[newDes.getRank()][newDes.getFile()] = m.des.getPiece();
            newDes.setPiece(m.des.getPiece());
            newDes.setPieceColor(m.des.getPieceColor());
            newOri.setPiece(m.ori.getPiece());
            newOri.setPieceColor(m.ori.getPieceColor());
            newOri.revalidate();
            newOri.repaint();
            newDes.revalidate();
            newDes.repaint();

            Chess.gameTurn = Chess.gameTurn==Piece.PIECE_WHITE?Piece.PIECE_BLACK:Piece.PIECE_WHITE;

            Chess.moveHistory.remove(Chess.moveHistory.size()-1);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(this.highlightedSquare){   
            handleMove(this);
            lastSelected.setWhiteSquare(lastSelected.isWhiteSquare());
            Piece.moves = Piece.filterMoves();
            if(Piece.moves.size() == 0){
                System.out.println("CHECKMATE, "+(Chess.gameTurn==Piece.PIECE_BLACK?"White King Wins!":"Black King Wins!"));
            }else{
                Chess.updateAttacked();
                Ai.aiMove();
            }

            
            unhighlightAll();
        }else{

            unhighlightAll();
            //System.out.println("Fired");
            if(lastSelected!=null){
                lastSelected.setWhiteSquare(lastSelected.isWhiteSquare());
            }
            if(this.piece != Piece.NONE && this.pieceColor == Chess.gameTurn){
                lastSelected = this;
                this.setBackground(Color.green);

                ArrayList<Move> moves = new ArrayList<Move>();
                for (Move move : Piece.moves){
                    //System.out.println("IN MOVES: "+move.ori.getPiece()+" "+move.ori.getPieceColor()+" "+move.ori.getRank()+", "+move.ori.getFile()+"    "+move.des.getPiece()+" "+move.des.getPieceColor()+" "+move.des.getRank()+", "+move.des.getFile());
                    if(this.equals(move.ori)){
                        //System.out.println("Valid MOVES: "+move.ori.getRank()+", "+move.ori.getFile()+"  "+move.des.getRank()+", "+move.des.getFile());
                        moves.add(move);
                    }
                }
                for (Move m : moves) {
                    Chess.visualBoard[m.des.getRank()][m.des.getFile()].highlight();
                }

            }
        }
    }

    @Override
    public boolean equals(Object o){
        Square s = (Square) o;
        return s.getPiece() == this.piece && s.getRank() == this.getRank() && this.getFile() == s.getFile();
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
