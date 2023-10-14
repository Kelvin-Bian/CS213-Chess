package chess;

import chess.ReturnPiece.PieceType;

public class Move {

    Position start;
    Position end;
    PieceType promoType;
    boolean resign;
    boolean draw;
    boolean whiteTurn;
    //can have "draw?"" following move coordinates
    public Move(Position start, Position end, PieceType promoType, boolean draw , boolean whiteTurn  ){
        this(start, end, promoType, whiteTurn);
        this.draw = draw;
    }
    public Move(Position start, Position end, boolean draw, boolean whiteTurn ){
        this(start, end, whiteTurn);
        this.draw = draw;
    }
    public Move(Position start, Position end, PieceType promoType, boolean whiteTurn ){
        this(start, end, whiteTurn);
        this.promoType = promoType;
    }
    public Move(Position start, Position end, boolean whiteTurn ){
        this.start = start;
        this.end = end;
        this.whiteTurn = whiteTurn;
    }
    public Move(boolean resign, boolean whiteTurn ){
        this.resign = resign;
    }

    public boolean validMove(){
        ReturnPiece p = Chess.board.get(start);
        if(!validPiece(p)) return false;
        
    }

    public boolean validPiece(ReturnPiece p){ //check if starting position contains piece belonging to playing side
        char side = (whiteTurn)? 'W' : 'B';
        if(p==null || p.pieceType.name().charAt(0)!= side){
            return false;
        }
        return true;
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
