package chess;

import chess.ReturnPiece.PieceType;

public class Move {

    Position start;
    Position end;
    PieceType promoType;
    boolean resign;
    boolean draw;
    //can have "draw?"" following move coordinates
    public Move(Position start, Position end, PieceType promoType, boolean draw   ){
        this(start, end, promoType);
        this.draw = draw;
    }
    public Move(Position start, Position end, boolean draw   ){
        this(start, end);
        this.draw = draw;
    }
    public Move(Position start, Position end, PieceType promoType){
        this(start, end);
        this.promoType = promoType;
    }
    public Move(Position start, Position end){
        this.start = start;
        this.end = end;
    }
    public Move(boolean resign){
        this.resign = resign;
    }
    public String toString(){
        String s="";
        if(start!=null){
            s+=start+" "+end; 
        }
        if(promoType!=null){
            s+=" "+promoType;
        }
        if(draw){
            s+=" draw?";
        }
        if(resign){
            s+="resign";
        }
        return s;
    }
}
