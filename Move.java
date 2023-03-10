public class Move {
    public Square ori,des;
    public int special = 0;

    public final int NONE = 0;
    public final int CASTLE = 1;
    public final int EN_PASSANT = 2;
    public final int PROMOTION = 3;

    public Move(Square ori, Square des){
        this.ori = new Square(ori.getRank(), ori.getFile(), ori.getPiece(), ori.getPieceColor(), ori.isWhiteSquare());
        this.des = new Square(des.getRank(), des.getFile(), des.getPiece(), des.getPieceColor(), des.isWhiteSquare());
    }

    public Move(Square ori, Square des, int special){
        this.ori = ori;
        this.des = des;
        this.special = special;
    }

}
