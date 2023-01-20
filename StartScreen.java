import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.event.ActionEvent;

import java.awt.*;
import java.awt.event.MouseEvent;


public class StartScreen extends JPanel implements MouseInputListener{
    JLabel button = new JLabel("New Game");
    public StartScreen(){
        super();
        addMouseListener(this);
        this.setBackground(new Color(0,0,0,200));
        this.setOpaque(false);
        this.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setMaximumSize(new Dimension(500,200));
        mainPanel.setSize(500,200);
        mainPanel.setOpaque(false);
        BoxLayout bl = new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS);
        mainPanel.setLayout(bl);
        JPanel fillerx = new JPanel();
        fillerx.setPreferredSize(new Dimension(150,Chess.board.getHeight()));
        fillerx.setOpaque(false);

        JPanel fillerx2 = new JPanel();
        fillerx2.setOpaque(false);
        fillerx2.setPreferredSize(new Dimension(150,Chess.board.getHeight()));
        JPanel fillery = new JPanel();
        fillery.setOpaque(false);
        fillery.setPreferredSize(new Dimension(Chess.board.getWidth(),200));
        JPanel fillery2 = new JPanel();
        fillery2.setOpaque(false);
        fillery2.setPreferredSize(new Dimension(Chess.board.getWidth(),200));

        JLabel label = new JLabel("CHESS");
        label.setForeground(Color.white);
        label.setFont(new Font("Verdana", Font.BOLD,48));
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        JLabel label2 = new JLabel("lol");
        label2.setForeground(Color.white);
        label2.setFont(new Font("Verdana", Font.BOLD,48));
        label2.setAlignmentX(CENTER_ALIGNMENT);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setVerticalAlignment(SwingConstants.CENTER);
        mainPanel.add(label);
        mainPanel.add(label2);
        mainPanel.add(Box.createVerticalGlue());
        
        button.setSize(new Dimension(300,200));
        button.setForeground(Color.white);
        button.setBackground(Color.decode("#534138"));
        button.setOpaque(true);
        button.setFont(new Font("Verdana", Font.BOLD,36));
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.addMouseListener(this);
        mainPanel.add(button);

        this.add(fillerx, BorderLayout.WEST);
        this.add(fillery, BorderLayout.NORTH);
        this.add(fillerx2, BorderLayout.EAST);
        this.add(fillery2, BorderLayout.SOUTH);
        this.add(mainPanel,BorderLayout.CENTER);
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
        if(e.getSource() == button){
            Chess.loadFen(Chess.START_FEN);
            Chess.gameTurn = Piece.PIECE_WHITE;
            Piece.moves = Piece.filterMoves();
            this.setVisible(false);
        }else{
            e.consume();
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
