import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class Chess{
    static JFrame board = new JFrame("Chess");
    static Square[][] visualBoard = new Square[8][8];
    static int[][] internalBoard = new int[8][8];
    static ArrayList<Square> attackedBoard = new ArrayList<Square>();
    static ArrayList<Move> moveHistory = new ArrayList<Move>();
    static int gameTurn = Piece.PIECE_WHITE;

    static Square whiteKing;
    static Square blackKing;


    static ArrayList<Integer> hilited = new ArrayList<Integer>();
    

    public static void updateAttacked(){
        attackedBoard.clear();
        for(Square[] r : visualBoard){
            for(Square piece : r){
                if(piece.isProt()){
                    attackedBoard.add(piece);
                }
            }
        }
    }

    

    public static void loadFen(String fen){
        GridBagConstraints g = new GridBagConstraints();
        g.weightx = 1.0;
        g.weighty = 1.0;
        g.fill = GridBagConstraints.BOTH;

        char[] fenArr = fen.toCharArray();
        int file = 0;
        int rank = 0;
        for(char i: fenArr){
            if(file==8){
                file = 0;
                rank++;
            }
                switch(Character.toLowerCase(i)){
                    case 'p':
                        internalBoard[rank][file] = Piece.PAWN;
                        Square s = visualBoard[rank][file];
                        s.setPiece(Piece.PAWN);
                        s.setPieceColor(Character.isUpperCase(i)?Piece.PIECE_WHITE:Piece.PIECE_BLACK);
                        s.revalidate();
                        s.repaint();
                        file++;
                        break;
                    case 'n':
                        internalBoard[rank][file] = Piece.KNIGHT;
                        s = visualBoard[rank][file];
                        s.setPiece(Piece.KNIGHT);
                        s.setPieceColor(Character.isUpperCase(i)?Piece.PIECE_WHITE:Piece.PIECE_BLACK);
                        s.revalidate();
                        s.repaint();
                        file++;
                        break;
                    case 'b':
                        internalBoard[rank][file] = Piece.BISHOP;
                        s = visualBoard[rank][file];
                        s.setPiece(Piece.BISHOP);
                        s.setPieceColor(Character.isUpperCase(i)?Piece.PIECE_WHITE:Piece.PIECE_BLACK);
                        s.revalidate();
                        s.repaint();
                        file++;
                        break;
                    case 'r':
                        internalBoard[rank][file] = Piece.ROOK;
                        s = visualBoard[rank][file];
                        s.setPiece(Piece.ROOK);
                        s.setPieceColor(Character.isUpperCase(i)?Piece.PIECE_WHITE:Piece.PIECE_BLACK);
                        s.revalidate();
                        s.repaint();
                        file++;
                        break;
                    case 'q':
                        internalBoard[rank][file] = Piece.QUEEN;
                        s = visualBoard[rank][file];
                        s.setPiece(Piece.QUEEN);
                        s.setPieceColor(Character.isUpperCase(i)?Piece.PIECE_WHITE:Piece.PIECE_BLACK);
                        s.revalidate();
                        s.repaint();
                        file++;
                        break;
                    case 'k':
                        internalBoard[rank][file] = Piece.KING;
                        s = visualBoard[rank][file];
                        s.setPiece(Piece.KING);
                        s.setPieceColor(Character.isUpperCase(i)?Piece.PIECE_WHITE:Piece.PIECE_BLACK);
                        s.revalidate();
                        s.repaint();
                        file++;
                        if(Character.isUpperCase(i)){
                            whiteKing = visualBoard[rank][file];
                        }else{
                            blackKing = visualBoard[rank][file];
                        }
                        break;
                    default:
                        if("12345678".contains(""+i)){
                            for(int j=0;j<Integer.parseInt(""+i);j++){
                                s = visualBoard[rank][file];
                                internalBoard[rank][file] = Piece.NONE;
                                s.setPiece(Piece.NONE);
                                s.setPieceColor(Piece.PIECE_NOT_CONTROLLED);
                                file++;
                                                               
                            }
                        }
                        break; 

                }
            }
            
    }

    public static void main(String[] args) {

        JPanel visuals = new JPanel();
        visuals.setBackground(Color.gray);
        
        for(int rank = 0; rank<8; rank++){

            for(int file = 0; file<8; file++){
                System.out.println(""+(rank*8+file));
                Square s = new Square(rank,file,Piece.NONE,Piece.PIECE_BLACK,(rank+file)%2==0);
                visualBoard[rank][file] = s;
                visuals.add(s);
            }

        }



        visuals.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        visuals.setLayout(new GridLayout(8,8));
        loadFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
        updateAttacked();
        Piece.moves = Piece.filterMoves();

        board.setSize(800, 800);
        board.setMaximumSize(new Dimension(90,90));
        board.add(visuals);
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.pack();
        board.setVisible(true);
    }

}

