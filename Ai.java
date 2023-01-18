import java.util.Collections;

public class Ai {
    public static void aiMove(){
        Collections.shuffle(Piece.moves);
        Square.handleMove(Piece.moves.get(0).ori, Piece.moves.get(0).des);
        //System.out.println("MADE MOVE: "+Piece.moves.get(0).ori.getRank()+"("+Piece.moves.get(0).ori.getPiece()+"), "+Piece.moves.get(0).ori.getFile()+"  "+Piece.moves.get(0).des.getRank()+", "+Piece.moves.get(0).des.getFile());
        //Attacks generating for wrong color;
        Piece.moves = Piece.filterMoves();
        if(Piece.moves.size() == 0){
            if(Piece.generateAttackRays(true).contains(Chess.gameTurn==Piece.PIECE_BLACK?Chess.blackKing:Chess.whiteKing)){
                System.out.println("CHECKMATE, "+(Chess.gameTurn==Piece.PIECE_BLACK?"White King Wins!":"Black King Wins!"));
            }else{
                System.out.println("STALEMATE, DRAW");
            }
        }
    }
}
