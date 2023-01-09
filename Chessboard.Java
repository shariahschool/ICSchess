import java.awt.*;
import javax.swing.*;
public class Chessboard extends JPanel {
   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.setBackground(color.BLACK);
      for(int i=0;i<8;i++){
        g.setColor(color.WHITE);
        g.fillRect(100*i,100*1,100,100);
      }
   }