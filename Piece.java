import java.awt.image.BufferedImage;
import javax.swing.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;

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
                moves.add(new Move(s,vboard[sRank+1][sFile+1], true));
            }

            if(sRank+1<8 && sFile-1>=0){
                moves.add(new Move(s,vboard[sRank+1][sFile-1], true));
            }
        }
        return moves;
    }

    public static ArrayList<Move> generatePawnMoves(Square s){
        ArrayList<Move> moves = new ArrayList<Move>(4);
        int sRank = s.getRank();
        int sFile = s.getFile();
        int[][] board = Chess.internalBoard;
        Square[][] vboard = Chess.visualBoard;
        boolean color = s.getPieceColor();

        if(color == PIECE_WHITE){
            boolean atStart = sRank==6;
            if(sRank-1>=0 && board[sRank-1][sFile] == NONE){
                moves.add(new Move(s,vboard[sRank-1][sFile], false));

                if(atStart && sRank-2>=0 && board[sRank-2][sFile] == NONE){
                    moves.add(new Move(s,vboard[sRank-2][sFile], false));
                }
            }

            if(sRank-1>=0 && sFile-1>=0 && board[sRank-1][sFile-1] != NONE && vboard[sRank-1][sFile-1].getPieceColor() != color){
                moves.add(new Move(s,vboard[sRank-1][sFile-1], true));
            }

            if(sRank-1>=0 && sFile+1<8 && board[sRank-1][sFile+1] != NONE && vboard[sRank-1][sFile+1].getPieceColor() != color){
                moves.add(new Move(s,vboard[sRank-1][sFile+1], true));
            }
        }
        else{
            boolean atStart = sRank==1;
            if(sRank+1<8 && board[sRank+1][sFile] == NONE){
                moves.add(new Move(s,vboard[sRank+1][sFile], false));

                if(atStart && sRank+2<8 && board[sRank+2][sFile] == NONE){
                    moves.add(new Move(s,vboard[sRank+2][sFile], false));
                }
            }

            if(sRank+1<8 && sFile+1<8 && board[sRank+1][sFile+1] != NONE && vboard[sRank+1][sFile+1].getPieceColor() != color){
                moves.add(new Move(s,vboard[sRank+1][sFile+1], true));
            }

            if(sRank+1<8 && sFile-1>=0 && board[sRank+1][sFile-1] != NONE && vboard[sRank+1][sFile-1].getPieceColor() != color){
                moves.add(new Move(s,vboard[sRank+1][sFile-1], true));
            }
        }
        
        
        

        return moves;
    }

}
