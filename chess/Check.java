package chess;

import java.util.ArrayList;

public class Check {
    public static boolean whiteCheck (ArrayList<ReturnPiece> piecesOnBoard) {
        Position whiteKingPosition = null;

        // find white king
        for (ReturnPiece cur : piecesOnBoard) {
            if(PieceUtility.isKing(cur) && PieceUtility.isWhite(cur)) {
                whiteKingPosition = Position.getPosition(cur);
                break;
            }
        }

        // for each opposing piece on board, check if it can move into the white king's square (AKA put it under check)
        for (ReturnPiece cur : piecesOnBoard) {
            if(!PieceUtility.isWhite(cur) && legalFunction.checkLegal(whiteKingPosition, cur, piecesOnBoard)) {
                return true;
            }
        }
        return false;
    }

    public static boolean blackCheck (ArrayList<ReturnPiece> piecesOnBoard) {
        Position blackKingPosition = null;

        // find black king
        for (ReturnPiece cur : piecesOnBoard) {
            if(PieceUtility.isKing(cur) && !PieceUtility.isWhite(cur)) {
                blackKingPosition = Position.getPosition(cur);
                break;
            }
        }

        // for each opposing piece on board, check if it can move into the black king's square (AKA put it under check)
        for (ReturnPiece cur : piecesOnBoard) {
            if(!PieceUtility.isWhite(cur) && legalFunction.checkLegal(blackKingPosition, cur, piecesOnBoard)) {
                return true;
            }
        }
        return false;
    }
}
