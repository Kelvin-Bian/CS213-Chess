package chess;

import java.util.ArrayList;

import chess.ReturnPiece.PieceFile;

public class Check {

    public static boolean checkmateChecking = false;

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
            if(PieceUtility.isWhite(cur) && legalFunction.checkLegal(blackKingPosition, cur, piecesOnBoard)) {
                return true;
            }
        }
        return false;
    }

    //see if white won (checkmated the black king)
    public static boolean blackCheckmate (ArrayList<ReturnPiece> piecesOnBoard) {
        // for each black piece on board, see if there is a legal move to stop the check
        boolean checkmate = true;
        checkmateChecking = true;
        for (int i = 0; i < piecesOnBoard.size(); i++) {
            ReturnPiece cur = piecesOnBoard.get(i);
            Position start = Position.getPosition(cur);
            if(!PieceUtility.isWhite(cur)) {
                for (int rank = 1; rank < 9; rank++) {
                    for (PieceFile file : PieceFile.values()) {
                        Position test = new Position(rank, file);
                        if (legalFunction.checkLegal(test, cur, piecesOnBoard)) {
                            PieceUtility.movePiece(cur, test);
                            if (!blackCheck(piecesOnBoard)){
                                checkmate = false;
                            }
                            PieceUtility.movePiece(cur, start);
                        }
                    }
                }
            }
        }
        checkmateChecking = false;
        return checkmate;
    }


    //see if black won (checkmated the white king)
    public static boolean whiteCheckmate (ArrayList<ReturnPiece> piecesOnBoard) {
        // for each white piece on board, see if there is a legal move to stop the check
        boolean checkmate = true;
        checkmateChecking = true;
        for (int i = 0; i < piecesOnBoard.size(); i++) {
            ReturnPiece cur = piecesOnBoard.get(i);
            Position start = Position.getPosition(cur);
            if(PieceUtility.isWhite(cur)) {
                for (int rank = 1; rank < 9; rank++) {
                    for (PieceFile file : PieceFile.values()) {
                        Position test = new Position(rank, file);
                        if (legalFunction.checkLegal(test, cur, piecesOnBoard)) {
                            PieceUtility.movePiece(cur, test);
                            if (!whiteCheck(piecesOnBoard)){
                                checkmate = false;
                            }
                            PieceUtility.movePiece(cur, start);
                        }
                    }
                }
            }
        }
        checkmateChecking = false;
        return checkmate;
    }
}
