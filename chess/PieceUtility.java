package chess;

import chess.ReturnPiece.PieceType;

public class PieceUtility {
    public static boolean piecesOfSameSide(ReturnPiece a, ReturnPiece b){
        return a.pieceType.name().charAt(0) == b.pieceType.name().charAt(0);
    }
    public static boolean isKing(ReturnPiece p){
        return p.pieceType == PieceType.BK || p.pieceType == PieceType.WK;
    }
}
