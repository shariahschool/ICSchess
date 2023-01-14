import java.awt.image.BufferedImage;
import javax.swing.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Piece extends JPanel{
    private boolean isWhite;
    private int file;
    private int rank;
    private int piece;

    public static final int NONE = 0;
    public static final int PAWN = 1;
    public static final int KNIGHT = 3;
    public static final int BISHOP = 4;
    public static final int ROOK = 5;
    public static final int QUEEN = 9;
    public static final int KING = 10;

    public static final boolean PIECE_WHITE = true;
    public static final boolean PIECE_BLACK = false;

    public static ArrayList<Move> moves;

    static BufferedImage SHEET;
    static BufferedImage PAWN_WHITE, PAWN_BLACK, KNIGHT_WHITE, KNIGHT_BLACK, BISHOP_WHITE, BISHOP_BLACK, ROOK_WHITE, ROOK_BLACK, QUEEN_WHITE, QUEEN_BLACK, KING_WHITE, KING_BLACK;


    static{
        try {
            SHEET = ImageIO.read(new File("Sprites.png"));
            PAWN_WHITE = SHEET.getSubimage(450, 0, 90, 90);
            PAWN_BLACK = SHEET.getSubimage(450, 90, 90, 90);
            KNIGHT_WHITE = SHEET.getSubimage(270, 0, 90, 90);
            KNIGHT_BLACK = SHEET.getSubimage(270, 90, 90, 90);
            BISHOP_WHITE = SHEET.getSubimage(180, 0, 90, 90);
            BISHOP_BLACK = SHEET.getSubimage(180, 90, 90, 90);
            ROOK_WHITE = SHEET.getSubimage(360, 0, 90, 90);
            ROOK_BLACK = SHEET.getSubimage(360, 90, 90, 90);
            QUEEN_WHITE = SHEET.getSubimage(90, 0, 90, 90);
            QUEEN_BLACK = SHEET.getSubimage(90, 90, 90, 90);
            KING_WHITE = SHEET.getSubimage(0, 0, 90, 90);
            KING_BLACK = SHEET.getSubimage(0, 90, 90, 90);
        } catch (java.io.IOException e) {
            System.out.print("didn't load");
        }
    }

    
    public void setPiece(int piece) {
        this.piece = piece;
    }

    public void setWhite(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public int getPiece() {
        return piece;
    }

    public void setFile(int file){
        this.file = file;
    }

    public int getfile(){
        return this.file;
    }

    public void setRank(int rank){
        this.rank = rank;
    }

    public int getRank(){
        return this.rank;
    }

    public boolean getIsWhite() {
        return this.isWhite;
    }

    public static ArrayList<Move> generatePawnAttacks(Square s){
        ArrayList<Move> moves = new ArrayList<Move>(4);
        int sRank = s.getRank();
        int sFile = s.getFile();
        Square[][] vboard = Chess.visualBoard;
        boolean color = s.getPieceColor();
        if(color == PIECE_BLACK && s.getPiece()!=Piece.NONE){
            if(sRank+1<8 && sFile+1<8){
                moves.add(new Move(s,vboard[sRank+1][sFile+1]));
            }

            if(sRank+1<8 && sFile-1>=0){
                moves.add(new Move(s,vboard[sRank+1][sFile-1]));
            }
        }
        return moves;
    }

    public static void generateKnightMoves(Square s, ArrayList<Move> moves){
        int offsets[][] = {{2,1},{2,-1},{1,2},{-1,2},{1,-2},{-1,-2},{-2,1},{-2,-1}};
        for(int[] off : offsets){
            if(s.getRank()+off[0]<8 && s.getRank()+off[0]>=0 && s.getFile()+off[1]<8 && s.getFile()+off[1]>=0 && (Chess.visualBoard[s.getRank()+off[0]][s.getFile()+off[1]].getPiece() == NONE || Chess.visualBoard[s.getRank()+off[0]][s.getFile()+off[1]].getPieceColor() != s.getPieceColor())){
                moves.add(new Move(s,Chess.visualBoard[s.getRank()+off[0]][s.getFile()+off[1]]));
            }
        }
    }

    public static void generateKingMoves(Square s, ArrayList<Move> moves){
        int[][] offsets = {{1,0},{0,-1},{-1,0},{0,1},{1,1},{1,-1},{-1,-1},{-1,1}};
        for (int[] off : offsets) {
            if(s.getRank()+off[0]<8 && s.getRank()+off[0]>=0 && s.getFile()+off[1]<8 && s.getFile()+off[1]>=0 && Chess.visualBoard[s.getRank()+off[0]][s.getFile()+off[1]].getPieceColor() != s.getPieceColor() && (Chess.attackedBoard.contains(Chess.visualBoard[s.getRank()+off[0]][s.getFile()+off[1]]) == false)){
                System.out.println("Passed if");
                moves.add(new Move(s,Chess.visualBoard[s.getRank()+off[0]][s.getFile()+off[1]]));
            }
        }
        
    }

    public static ArrayList<Move> generateKnightAttacks(Square s){
        ArrayList<Move> moves = new ArrayList<Move>();
        int offsets[][] = {{2,1},{2,-1},{1,2},{-1,2},{1,-2},{-1,-2},{-2,1},{-2,-1}};
        for(int[] off : offsets){
            if(s.getRank()+off[0]<8 && s.getRank()+off[0]>=0 && s.getFile()+off[1]<8 && s.getFile()+off[1]>=0){
                moves.add(new Move(s,Chess.visualBoard[s.getRank()+off[0]][s.getFile()+off[1]]));
            }
        }
        return moves;
    }
    
    public static void generatePawnMoves(Square s, ArrayList<Move> moves){
        int sRank = s.getRank();
        int sFile = s.getFile();
        int[][] board = Chess.internalBoard;
        Square[][] vboard = Chess.visualBoard;
        boolean color = s.getPieceColor();

        if(color == PIECE_WHITE){
            boolean atStart = sRank==6;
            if(sRank-1>=0 && board[sRank-1][sFile] == NONE){
                moves.add(new Move(s,vboard[sRank-1][sFile]));

                if(atStart && sRank-2>=0 && board[sRank-2][sFile] == NONE){
                    moves.add(new Move(s,vboard[sRank-2][sFile]));
                }
            }

            if(sRank-1>=0 && sFile-1>=0 && board[sRank-1][sFile-1] != NONE && vboard[sRank-1][sFile-1].getPieceColor() != color){
                moves.add(new Move(s,vboard[sRank-1][sFile-1]));
            }

            if(sRank-1>=0 && sFile+1<8 && board[sRank-1][sFile+1] != NONE && vboard[sRank-1][sFile+1].getPieceColor() != color){
                moves.add(new Move(s,vboard[sRank-1][sFile+1]));
            }
        }
        else{
            boolean atStart = sRank==1;
            if(sRank+1<8 && board[sRank+1][sFile] == NONE){
                moves.add(new Move(s,vboard[sRank+1][sFile]));

                if(atStart && sRank+2<8 && board[sRank+2][sFile] == NONE){
                    moves.add(new Move(s,vboard[sRank+2][sFile]));
                }
            }

            if(sRank+1<8 && sFile+1<8 && board[sRank+1][sFile+1] != NONE && vboard[sRank+1][sFile+1].getPieceColor() != color){
                moves.add(new Move(s,vboard[sRank+1][sFile+1]));
            }

            if(sRank+1<8 && sFile-1>=0 && board[sRank+1][sFile-1] != NONE && vboard[sRank+1][sFile-1].getPieceColor() != color){
                moves.add(new Move(s,vboard[sRank+1][sFile-1]));
            }
        }
    }

    public static void generateLongMoves(Square s, ArrayList<Move> moves){
        //UP RIGHT DOWN LEFT LEFT_UP RIGHT_UP RIGHT_DOWN LEFT_DOWN
        int[][] offsets = {{1,0},{0,-1},{-1,0},{0,1},{1,1},{1,-1},{-1,-1},{-1,1}};
        int[] spaces = getSquaresToEdge(s);
        int startIndex = s.getPiece()==BISHOP?4:0;
        int endIndex = s.getPiece()==ROOK?5:8;
        System.out.println(Arrays.toString(spaces));
        for(int direction = startIndex; direction<endIndex;direction++){
                if(spaces[direction]!=0){
                    System.out.println(direction);
                    for(int i = 0;i<spaces[direction];i++){
                        Square piece = Chess.visualBoard[s.getRank()+offsets[direction][0]*(i+1)][s.getFile()+offsets[direction][1]*(i+1)];
                        System.out.println("Piece: "+piece.getPiece()+" , color: "+piece.getPieceColor());
                        if(piece.getPieceColor() == s.getPieceColor() && piece.getPiece() != Piece.NONE){
                            System.out.println("same color piece blocking");
                            break;
                        }

                        moves.add(new Move(s,piece));

                        if(piece.getPieceColor() != s.getPieceColor() && piece.getPiece() != Piece.NONE){
                            System.out.println("Different color piece breaking");
                            break;
                        }
                    }
                }
            }
        }


        public static ArrayList<Move> generateLongAttacks(Square s){
            //UP RIGHT DOWN LEFT LEFT_UP RIGHT_UP RIGHT_DOWN LEFT_DOWN
            ArrayList<Move> moves = new ArrayList<Move>();
            int[][] offsets = {{1,0},{0,-1},{-1,0},{0,1},{1,1},{1,-1},{-1,-1},{-1,1}};
            int[] spaces = getSquaresToEdge(s);
            int startIndex = s.getPiece()==BISHOP?4:0;
            int endIndex = s.getPiece()==ROOK?5:8;
            for(int direction = startIndex; direction<endIndex;direction++){
                    if(spaces[direction]!=0){
                        System.out.println(direction);
                        for(int i = 0;i<spaces[direction];i++){
                            Square piece = Chess.visualBoard[s.getRank()+offsets[direction][0]*(i+1)][s.getFile()+offsets[direction][1]*(i+1)];
                            moves.add(new Move(s,piece));
                            if(piece.getPiece() != Piece.NONE){
                                System.out.println("Different color piece breaking");
                                break;
                            }
                        }
                    }
                }
                return moves;
            }


    public static ArrayList<Move> generateMoves(){
        moves = new ArrayList<Move>();
        for(Square[] r : Chess.visualBoard){
            for(Square p:r ){
                if(p.getPieceColor() == Chess.whiteTurn){
                    int pieceVal = p.getPiece();
                    if(p.getPiece() == Piece.PAWN){
                        generatePawnMoves(p, moves);
                    }else if(pieceVal == Piece.BISHOP || pieceVal == Piece.QUEEN || pieceVal == Piece.ROOK){
                        generateLongMoves(p,moves);
                    }else if(pieceVal == Piece.KNIGHT){
                        generateKnightMoves(p,moves);
                    }else if(pieceVal == Piece.KING){
                        generateKingMoves(p, moves);
                    }
                }
            }
        }
        return moves;

    }
//UP RIGHT DOWN LEFT LEFT_UP RIGHT_UP RIGHT_DOWN LEFT_DOWN
    public static int[] getSquaresToEdge(Square s){
        int[] spaces = new int[8];

        spaces[0] = 7-s.getRank();
        spaces[1] = s.getFile();
        spaces[2] = s.getRank();
        spaces[3] = 7-s.getFile();
        spaces[4] = Math.min(spaces[0],spaces[3]);
        spaces[5] = Math.min(spaces[0],spaces[1]);
        spaces[6] = Math.min(spaces[2],spaces[1]);
        spaces[7] = Math.min(spaces[2],spaces[3]);

        return spaces;

    }

}
