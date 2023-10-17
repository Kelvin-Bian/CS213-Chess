package chess;

import java.util.ArrayList;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;

public class PieceUtility {
    public static boolean sameSide(ReturnPiece a, ReturnPiece b){
        return a.pieceType.name().charAt(0) == b.pieceType.name().charAt(0);
    }
    public static boolean isKing(ReturnPiece p){
        return p.pieceType == PieceType.BK || p.pieceType == PieceType.WK;
    }
    public static boolean isPawn(ReturnPiece p){
        return p.pieceType == PieceType.BP || p.pieceType == PieceType.WP;
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
    public static int rankIndex(Position p){ //convert rank into index for Chess.board row
        return 8-p.r();
    }

    public static void setPiece(ReturnPiece p, PieceFile f, int r, PieceType t){
        p.pieceFile = f;
        p.pieceRank = r;
        p.pieceType = t;
    }
    public static void movePiece(ReturnPiece p, Position end){
        PieceFile f = end.f();
        int r = end.r();
        p.pieceFile = f;
        p.pieceRank = r;
    }

    public static ReturnPiece findPiece(Position p, ArrayList<ReturnPiece> pieces){
        for(ReturnPiece piece: pieces){
            if(Position.getPosition(piece).equals(p)){
                return piece;
            }
        }
        return null;
    }

    public static void promotion(ReturnPiece p, PieceType type){
        if(type == null){
            p.pieceType = (isWhite(p))? PieceType.WQ: PieceType.BQ;
        }
        else{
            p.pieceType = type;
        }
    }
}
