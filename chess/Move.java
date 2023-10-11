package chess;

import chess.ReturnPiece.PieceType;

public class Move {

    Position start;
    Position end;
    PieceType promoType;
    boolean resign;
    boolean draw;
    //can have "draw?"" following move coordinates
    public Move(Position start, Position end, PieceType promoType, boolean resign, boolean draw   ){
        
    }


}
