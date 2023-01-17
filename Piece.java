import java.awt.image.BufferedImage;
import javax.swing.*;

import javax.imageio.ImageIO;
import javax.sound.midi.VoiceStatus;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

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

    public static final int PIECE_WHITE = 1;
    public static final int PIECE_BLACK = 0;
    public static final int PIECE_NOT_CONTROLLED = -1;

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

    public static void castBishopRay(Square s, ArrayList<Square> rSquares){

        int[][] offsets = {{1,1},{1,-1},{-1,-1},{-1,1}};
        int[] spaces = getSquaresToEdge(s);
        int sRank = s.getRank();
        int sFile = s.getFile();
        for(int offset = 0; offset<4;offset++){
            for(int i = 0; i<spaces[offset+4];i++){
                Square square = Chess.visualBoard[sRank+offsets[offset][0]*(i+1)][sFile+offsets[offset][1]*(i+1)];

                rSquares.add(square);
                if(square.getPiece() != Piece.NONE){
                    break;
                }
            }
        }
    }

    public static void castRookRay(Square s, ArrayList<Square> rSquares){
        int[][] offsets = {{1,0},{0,-1},{-1,0},{0,1}};
        int[] spaces = getSquaresToEdge(s);
        int sRank = s.getRank();
        int sFile = s.getFile();
        for(int offset = 0; offset<4;offset++){
            for(int i = 0; i<spaces[offset];i++){
                Square square = Chess.visualBoard[sRank+offsets[offset][0]*(i+1)][sFile+offsets[offset][1]*(i+1)];
                rSquares.add(square);
                if(square.getPiece() != Piece.NONE){
                    break;
                }
            }
        }
    }
    //[s.getRank()+offsets[direction][0]*(i+1)][s.getFile()+offsets[direction][1]*(i+1)]
    public static void castQueenRay(Square s, ArrayList<Square> rSquares){
        int[][] offsets = {{1,0},{0,-1},{-1,0},{0,1},{1,1},{1,-1},{-1,-1},{-1,1}};
        int[] spaces = getSquaresToEdge(s);
        int sRank = s.getRank();
        int sFile = s.getFile();
        for(int offset = 0; offset<8;offset++){
            for(int i = 0; i<spaces[offset];i++){
                Square square = Chess.visualBoard[sRank+offsets[offset][0]*(i+1)][sFile+offsets[offset][1]*(i+1)];
                rSquares.add(square);
                if(square.getPiece() != Piece.NONE){
                    break;
                }
            }
        }
    }

    public static void castPawnRay(Square s, ArrayList<Square> rSquares){
        //UP RIGHT DOWN LEFT LEFT_UP RIGHT_UP RIGHT_DOWN LEFT_DOWN
        int sRank = s.getRank();
        int sFile = s.getFile();
        int[] spaces = getSquaresToEdge(s);
        if(s.getPieceColor() == Piece.PIECE_WHITE){
            if(spaces[6] != 0){
                rSquares.add(Chess.visualBoard[sRank-1][sFile-1]);
            }
            if(spaces[7] != 0){
                rSquares.add(Chess.visualBoard[sRank-1][sFile+1]);
            }
        }else{
            if(spaces[4] != 0){
                rSquares.add(Chess.visualBoard[sRank+1][sFile+1]); 
            }
            if(spaces[5] != 0){
                rSquares.add(Chess.visualBoard[sRank+1][sFile-1]);
            }
        }
    }

    public static void castKnightRay(Square s, ArrayList<Square> rSquares){
        //UP RIGHT DOWN LEFT LEFT_UP RIGHT_UP RIGHT_DOWN LEFT_DOWN
        int sRank = s.getRank();
        int sFile = s.getFile();
        int offsets[][] = {{2,1},{2,-1},{1,2},{-1,2},{1,-2},{-1,-2},{-2,1},{-2,-1}};
        for(int[] off : offsets){
            if(sRank+off[0]<8 && sRank+off[0]>=0 && sFile+off[1]<8 && sFile+off[1]>=0){
                rSquares.add(Chess.visualBoard[sRank+off[0]][sFile+off[1]]);
            }
        }
    }
    


    public static ArrayList<Move> generatePawnAttacks(Square s){
        ArrayList<Move> moves = new ArrayList<Move>(4);
        int sRank = s.getRank();
        int sFile = s.getFile();
        Square[][] vboard = Chess.visualBoard;
        int color = s.getPieceColor();
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
                Chess.visualBoard[s.getRank()+off[0]][s.getFile()+off[1]].setProt(true);
            }
        }
    }

    public static void generateKingMoves(Square s, ArrayList<Move> moves){

        int[][] offsets = {{1,0},{0,-1},{-1,0},{0,1},{1,1},{1,-1},{-1,-1},{-1,1}};
        for (int[] off : offsets) {
            if(s.getRank()+off[0]<8 && s.getRank()+off[0]>=0 && s.getFile()+off[1]<8 && s.getFile()+off[1]>=0 && (Chess.visualBoard[s.getRank()+off[0]][s.getFile()+off[1]].getPiece() == NONE || Chess.visualBoard[s.getRank()+off[0]][s.getFile()+off[1]].getPieceColor() != s.getPieceColor())){

                moves.add(new Move(s,Chess.visualBoard[s.getRank()+off[0]][s.getFile()+off[1]]));
                Chess.visualBoard[s.getRank()+off[0]][s.getFile()+off[1]].setProt(true);
            }
        }
        
        
    }

    public static ArrayList<Move> generateKnightAttacks(Square s){
        ArrayList<Move> moves = new ArrayList<Move>();
        int offsets[][] = {{2,1},{2,-1},{1,2},{-1,2},{1,-2},{-1,-2},{-2,1},{-2,-1}};
        for(int[] off : offsets){
            if(s.getRank()+off[0]<8 && s.getRank()+off[0]>=0 && s.getFile()+off[1]<8 && s.getFile()+off[1]>=0){
                moves.add(new Move(s,Chess.visualBoard[s.getRank()+off[0]][s.getFile()+off[1]]));
                Chess.visualBoard[s.getRank()+off[0]][s.getFile()+off[1]].setProt(true);
            }
        }
        return moves;
    }
    
    public static void generatePawnMoves(Square s, ArrayList<Move> moves){
        int sRank = s.getRank();
        int sFile = s.getFile();
        int[][] board = Chess.internalBoard;
        Square[][] vboard = Chess.visualBoard;
        int color = s.getPieceColor();

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
            }else if(sRank-1>=0 && sFile-1>=0){
                vboard[sRank-1][sFile-1].setProt(true);
            }

            if(sRank-1>=0 && sFile+1<8 && board[sRank-1][sFile+1] != NONE && vboard[sRank-1][sFile+1].getPieceColor() != color){
                moves.add(new Move(s,vboard[sRank-1][sFile+1]));
            }else if(sRank-1>=0 && sFile+1<8){
                vboard[sRank-1][sFile+1].setProt(true);
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
            }else if(sRank+1<8 && sFile+1<8){
                vboard[sRank+1][sFile+1].setProt(true);
            }

            if(sRank+1<8 && sFile-1>=0 && board[sRank+1][sFile-1] != NONE && vboard[sRank+1][sFile-1].getPieceColor() != color){
                moves.add(new Move(s,vboard[sRank+1][sFile-1]));
            }else if(sRank+1<8 && sFile-1>=0){
                vboard[sRank+1][sFile-1].setProt(true);
            }
        }
    }

    public static void generateLongMoves(Square s, ArrayList<Move> moves){
        //UP RIGHT DOWN LEFT LEFT_UP RIGHT_UP RIGHT_DOWN LEFT_DOWN
        int[][] offsets = {{1,0},{0,-1},{-1,0},{0,1},{1,1},{1,-1},{-1,-1},{-1,1}};
        int[] spaces = getSquaresToEdge(s);
        int startIndex = s.getPiece()==BISHOP?4:0;
        int endIndex = s.getPiece()==ROOK?5:8;
        for(int direction = startIndex; direction<endIndex;direction++){
                if(spaces[direction]!=0){
                    for(int i = 0;i<spaces[direction];i++){
                        Square piece = Chess.visualBoard[s.getRank()+offsets[direction][0]*(i+1)][s.getFile()+offsets[direction][1]*(i+1)];
                        if(piece.getPieceColor() == s.getPieceColor() && piece.getPiece() != Piece.NONE){
                            break;
                        }

                        moves.add(new Move(s,piece));
                        piece.setProt(true);

                        if(piece.getPieceColor() != s.getPieceColor() && piece.getPiece() != Piece.NONE){
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
            int endIndex = s.getPiece()==ROOK?4:8;
            for(int direction = startIndex; direction<endIndex;direction++){
                    if(spaces[direction]!=0){
                        for(int i = 0;i<spaces[direction];i++){
                            Square piece = Chess.visualBoard[s.getRank()+offsets[direction][0]*(i+1)][s.getFile()+offsets[direction][1]*(i+1)];
                            moves.add(new Move(s,piece));
                            if(piece.getPiece() != Piece.NONE){
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
            for(Square p : r){
                if(p.getPieceColor() == Chess.gameTurn){
                    int pieceVal = p.getPiece();
                    if(pieceVal == Piece.PAWN){
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

    public static ArrayList<Square> generateAttackRays(){
        ArrayList<Square> squares = new ArrayList<Square>();
        for (Square[] r : Chess.visualBoard) {
            for (Square s : r) {
                if(s.getPieceColor() == Chess.gameTurn){
                    int val = s.getPiece();
                    switch(val){
                        case NONE:break;
                        case PAWN:
                            castPawnRay(s, squares);
                            break;
                        case BISHOP:
                            castBishopRay(s, squares);
                            break;
                        case KNIGHT:
                            castKnightRay(s, squares);
                            break;
                        case ROOK:
                            castRookRay(s, squares);
                            break;
                        case QUEEN:
                            castQueenRay(s, squares);
                            break;
                        default:break;
                    }
                }
            }
        }
        return squares;
    }

    public static ArrayList<Move> filterMoves(){
        Date date = new Date();
        long milli = date.getTime();
        ArrayList<Move> notFiltered = generateMoves();
        ArrayList<Move> filtered = new ArrayList<Move>(notFiltered.size());
        for(Iterator<Move> iterator = notFiltered.iterator();iterator.hasNext();){
            Move m = iterator.next();
            //System.out.println("IN MOVES: "+m.ori.getPiece()+" "+m.ori.getPieceColor()+" "+m.ori.getRank()+", "+m.ori.getFile()+"    "+m.des.getPiece()+" "+m.des.getPieceColor()+" "+m.des.getRank()+", "+m.des.getFile());
            Square.handleMove(m.ori,m.des);
            //System.out.println(Arrays.deepToString(Chess.visualBoard));
            ArrayList<Square> attacked  = generateAttackRays();
            if(!attacked.contains(Chess.gameTurn==PIECE_BLACK?Chess.whiteKing:Chess.blackKing)){  
                Square.unmakeMove();        
                filtered.add(new Move(Chess.visualBoard[m.ori.getRank()][m.ori.getFile()], Chess.visualBoard[m.des.getRank()][m.des.getFile()]));
            }else{
                Square.unmakeMove();
            }
            
        }
        Chess.board.revalidate();
        Chess.board.repaint();
        System.out.println(Chess.whitePieces.size());
        date = new Date();
        System.out.println(date.getTime() - milli);
        return filtered;
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
