import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

public class Square extends JPanel {
    private boolean white;
    private int x;
    private int y;

    public Square(int x, int y, boolean white){
        super();
        this.setPreferredSize(new Dimension(100,100));
        setX(x);
        setY(y);
        setWhite(white);
        
        this.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("clicked "+e.getComponent().getX()+e.getComponent().getY());
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
        });
        
    }

    public boolean isWhite(){
        return this.white;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setWhite(boolean white){
        super.setBackground(Color.green);
        this.white = white;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public static boolean sameColour(Square x, Square y){
        return x.isWhite() == y.isWhite();
    }

}
