import java.util.Collections;

public class Ai {
    public static void aiMove(){
        Collections.shuffle(Piece.moves);
        Square.handleMove(Piece.moves.get(0).ori, Piece.moves.get(0).des);
        //System.out.println("MADE MOVE: "+Piece.moves.get(0).ori.getRank()+"("+Piece.moves.get(0).ori.getPiece()+"), "+Piece.moves.get(0).ori.getFile()+"  "+Piece.moves.get(0).des.getRank()+", "+Piece.moves.get(0).des.getFile());
        Piece.moves = Piece.filterMoves();
    }
}