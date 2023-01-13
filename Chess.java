import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class Chess{
    static JFrame board = new JFrame("Chess");
    static ArrayList<Square> visualBoard = new ArrayList<Square>(64);
    static ArrayList<Piece> internalBoard = new ArrayList<Piece>(64);
    static boolean whiteTurn = true;

    static ArrayList<Integer> hilited = new ArrayList<Integer>();
    
    public static void loadFen(String fen){
        GridBagConstraints g = new GridBagConstraints();
        g.weightx = 1.0;
        g.weighty = 1.0;
        g.fill = GridBagConstraints.BOTH;

        char[] fenArr = fen.toCharArray();
        int counter = 0;
        for(char i: fenArr){
                switch(Character.toLowerCase(i)){
                    case 'p':
                        Piece temp = new Piece(counter, Piece.PAWN, Character.isUpperCase(i));
                        internalBoard.add(temp);
                        visualBoard.get(counter).add(temp,g);
                        counter++;
                        break;
                    case 'n':
                        temp = new Piece(counter, Piece.KNIGHT, Character.isUpperCase(i));
                        internalBoard.add(temp);
                        visualBoard.get(counter).add(temp,g);
                        counter++;
                        break;
                    case 'b':
                        temp = new Piece(counter, Piece.BISHOP, Character.isUpperCase(i));
                        internalBoard.add(temp);
                        visualBoard.get(counter).add(temp,g);
                        counter++;
                        break;
                    case 'r':
                        temp = new Piece(counter, Piece.ROOK, Character.isUpperCase(i));
                        internalBoard.add(temp);
                        visualBoard.get(counter).add(temp,g);
                        counter++;
                        break;
                    case 'q':
                        temp = new Piece(counter, Piece.QUEEN, Character.isUpperCase(i));
                        internalBoard.add(temp);
                        visualBoard.get(counter).add(temp,g);
                        counter++;
                        break;
                    case 'k':
                        temp = new Piece(counter, Piece.KING, Character.isUpperCase(i));
                        internalBoard.add(temp);
                        visualBoard.get(counter).add(temp,g);
                        counter++;
                        break;
                    /*case '/':
                     int remaining = 8-counter%8;
                        for(int j=0;j<remaining;j++){
                            System.out.println("iteration" + j);
                            temp = new Piece(counter, Piece.NONE, false);
                            internalBoard.add(temp);
                            visualBoard.get(counter).add(temp,g);
                            counter++;

                        }
                        break;
                        */
                    default:
                        if("12345678".contains(""+i)){
                            System.out.println("test");
                            for(int j=0;j<Integer.parseInt(""+i);j++){
                                System.out.println(Integer.parseInt(""+i));
                                if((counter+1)%8==0){
                                    temp = new Piece(counter, Piece.NONE, false);
                                    internalBoard.add(temp);
                                    visualBoard.get(counter).add(temp,g);
                                    counter++;
                                    break;
                                } 
                                temp = new Piece(counter, Piece.NONE, false);
                                internalBoard.add(temp);
                                visualBoard.get(counter).add(temp,g);
                                counter++;
                                                               
                            }
                        }
                        break; 

                }
            }
            for(Piece i : internalBoard){
                System.out.println(i.getPiece());
            }
            
    }

    public static void main(String[] args) {

        JPanel visuals = new JPanel();
        visuals.setBackground(Color.gray);
        
        for(int rank = 0; rank<8; rank++){

            for(int file = 0; file<8; file++){
                System.out.println(""+(rank*8+file));
                visualBoard.add(rank*8+file,new Square(file,rank,(rank+file)%2==0));
            }

        }

        for (Square i : visualBoard){
            visuals.add(i);
            visuals.revalidate();
            visuals.repaint();
        }

        visuals.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        visuals.setLayout(new GridLayout(8,8));
        loadFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
        board.setSize(800, 800);
        board.setMaximumSize(new Dimension(90,90));
        board.add(visuals);
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.pack();
        board.setVisible(true);
    }

}

