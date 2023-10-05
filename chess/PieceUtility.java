package chess;

import chess.ReturnPiece.PieceType;

public class PieceUtility {
    public static boolean sameSide(ReturnPiece a, ReturnPiece b){
        return a.pieceType.name().charAt(0) == b.pieceType.name().charAt(0);
    }
    public static boolean isKing(ReturnPiece p){
        return p.pieceType == PieceType.BK || p.pieceType == PieceType.WK;
    }

    public static boolean isWhite(ReturnPiece p){
        return p.pieceType.name().charAt(0)=='W';
    }

    public static int fileInt(ReturnPiece rp){
        return (""+rp.pieceFile).charAt(0) - 'a';
    }
    public static int fileInt(Position p){
        return (""+p.f()).charAt(0) - 'a';
    }
    public static int rankIndex(Position p){ //convert rank into index for board row
        return 8-p.r();
    }
}
