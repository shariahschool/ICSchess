import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.event.MouseInputListener;;
public class PromotionButton extends JPanel implements MouseInputListener{ 
        public int piece = 0;
        public int color = 0;
        public Promotion parent;
        public static Color bg = Color.decode("#534138");
        PromotionButton(int piece, int color, Promotion p){
            super();
            addMouseListener(this);
            this.piece = piece;
            this.color = color;
            this.setBackground(bg);
            this.parent = p;
        }
        @Override

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(this.color == Piece.PIECE_WHITE){
                switch(this.piece){
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
                switch(this.piece){
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

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("hi");
            Chess.glassPane.setVisible(false);
            this.parent.s.setPiece(this.piece);
            Square.handleMove(this.parent.d);
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
