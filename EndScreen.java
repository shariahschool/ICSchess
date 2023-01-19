import javax.swing.*;
import java.awt.*;


public class EndScreen extends JPanel{

    public EndScreen(String status){
        super();
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

        JLabel label = new JLabel(status);
        label.setForeground(Color.white);
        label.setFont(new Font("Verdana", Font.BOLD,48));
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(label);
        mainPanel.add(Box.createVerticalGlue());
        JLabel button = new JLabel("New Game");
        button.setSize(new Dimension(300,200));
        button.setForeground(Color.white);
        button.setBackground(Color.decode("#534138"));
        button.setOpaque(true);
        button.setFont(new Font("Verdana", Font.BOLD,36));
        button.setAlignmentX(CENTER_ALIGNMENT);
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
}
