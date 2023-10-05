package chess;

import java.util.ArrayList;
import java.lang.Math;

import chess.ReturnPiece.PieceFile;

public class King {
    public static boolean checkLegal (PieceFile startFile, PieceFile endFile, int startRank, int endRank, ArrayList<ReturnPiece> piecesOnBoard) {
        ReturnPiece piece = null;
        for (ReturnPiece cur : piecesOnBoard) {
            if(startFile == cur.pieceFile && startRank == cur.pieceRank) {
                piece = cur;
                break;
            }
        }
        if (piece != null) {
            return false;
        }
        int fileOne = Character.getNumericValue(startFile.toString().charAt(0));
        int fileTwo = Character.getNumericValue(endFile.toString().charAt(0));

        int fileDiff = Math.abs(fileOne - fileTwo);
        int rankDiff = Math.abs(startRank - endRank);

        if (fileDiff > 0 && rankDiff > 0 && fileDiff + rankDiff == 3) {
            return true;
        }

        return false;
    }
}
