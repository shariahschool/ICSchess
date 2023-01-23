import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.*;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import javax.swing.*;


public class Promotion extends JPanel implements MouseInputListener{
    public static PromotionButton queenBut,rookBut,bishopBut,knightBut;
    public Square s;
    public Square d;
    
    public Promotion(Square s, Square d){
        super();
        addMouseListener(this);

        


        queenBut = new PromotionButton(Piece.QUEEN, Chess.gameTurn, this);
        rookBut = new PromotionButton(Piece.ROOK, Chess.gameTurn, this);
        bishopBut = new PromotionButton(Piece.BISHOP, Chess.gameTurn, this);
        knightBut = new PromotionButton(Piece.KNIGHT, Chess.gameTurn, this);

        this.s = s;        
        this.d = d;
        this.setBackground(new Color(0,0,0,100));
        this.setOpaque(false);
        this.setLayout(new GridLayout(8,8));
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        int sFile = d.getFile();
        if(Chess.gameTurn == Piece.PIECE_WHITE){
            for(int rank = 0; rank<8; rank++){
                for(int file = 0; file<8; file++){
                    System.out.println("HI "+rank+" "+file);
                    if(file == sFile){
                        switch(rank){
                            case 0:
                                this.add(queenBut);
                                break;
                            case 1:
                                this.add(rookBut);
                                break;
                            case 2:
                                this.add(bishopBut);
                                break;
                            case 3:
                                this.add(knightBut);
                                break;
                            default:
                                JPanel filler = new JPanel();
                                filler.setVisible(false);
                                this.add(filler);
                                break;
                                
                        }
                    }else{
                        JPanel filler = new JPanel();
                        filler.setVisible(false);
                        this.add(filler);
                    }
                }
            }
        }else{
            for(int rank = 0; rank<8; rank++){
                for(int file = 0; file<8; file++){
                    System.out.println("HI "+rank+" "+file);
                    if(file == sFile){
                        switch(rank){
                            case 7:
                                this.add(queenBut);
                                break;
                            case 6:
                                this.add(rookBut);
                                break;
                            case 5:
                                this.add(bishopBut);
                                break;
                            case 4:
                                this.add(knightBut);
                                break;
                            default:
                                JPanel filler = new JPanel();
                                filler.setVisible(false);
                                this.add(filler);
                                break;
                                
                        }
                    }else{
                        JPanel filler = new JPanel();
                        filler.setVisible(false);
                        this.add(filler);
                    }
                }
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(this.getBackground());
        Rectangle r = g.getClipBounds();
        g.fillRect(r.x, r.y, r.width, r.height);
        super.paintComponent(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.setVisible(false);
        e.consume();
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
