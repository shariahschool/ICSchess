public class Move {
    public Square ori,des;
    public boolean attacking = false;
    public int special = 0;

    public final int NONE = 0;
    public final int CASTLE = 1;
    public final int EN_PASSANT = 2;

    public Move(Square ori, Square des, boolean attacking){
        this.ori = ori;
        this.des = des;
    }

    public Move(Square ori, Square des, boolean attacking, int special){
        this.ori = ori;
        this.des = des;
        this.special = special;
    }

}
