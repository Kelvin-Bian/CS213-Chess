package chess;

import java.util.ArrayList;

import chess.ReturnPiece.PieceFile;

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

    //see if white won (checkmated the black king)
    public static boolean blackCheckmate (ArrayList<ReturnPiece> piecesOnBoard) {
        // for each black piece on board, see if there is a legal move to stop the check
        for (int i = 0; i < piecesOnBoard.size(); i++) {
            ReturnPiece cur = piecesOnBoard.get(i);
            if(!PieceUtility.isWhite(cur)) {
                for (int rank = 1; rank < 9; rank++) {
                    for (PieceFile file : PieceFile.values()) {
                        if (legalFunction.checkLegal(new Position(rank, file), cur, piecesOnBoard)) {
                            ArrayList<ReturnPiece> tempBoard = new ArrayList<>(piecesOnBoard);
                            tempBoard.remove(i);
                            ReturnPiece tempPiece = new ReturnPiece();
                            tempPiece.pieceRank = rank;
                            tempPiece.pieceFile = file;
                            tempBoard.add(i, tempPiece);
                            if (!blackCheck(tempBoard)) return false;
                        }
                    }
                }
            }
        }
        return true;
    }


    //see if black won (checkmated the white king)
    public static boolean whiteCheckmate (ArrayList<ReturnPiece> piecesOnBoard) {
        // for each black piece on board, see if there is a legal move to stop the check
        for (int i = 0; i < piecesOnBoard.size(); i++) {
            ReturnPiece cur = piecesOnBoard.get(i);
            if(PieceUtility.isWhite(cur)) {
                for (int rank = 1; rank < 9; rank++) {
                    for (PieceFile file : PieceFile.values()) {
                        if (legalFunction.checkLegal(new Position(rank, file), cur, piecesOnBoard)) {
                            ArrayList<ReturnPiece> tempBoard = new ArrayList<>(piecesOnBoard);
                            tempBoard.remove(i);
                            ReturnPiece tempPiece = new ReturnPiece();
                            tempPiece.pieceRank = rank;
                            tempPiece.pieceFile = file;
                            tempBoard.add(i, tempPiece);
                            if (!whiteCheck(tempBoard)) return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
