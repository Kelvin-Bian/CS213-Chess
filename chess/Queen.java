package chess;

import java.util.ArrayList;
import java.lang.Math;

import chess.ReturnPiece.PieceFile;

public class Queen {
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

        int fileSign = 1;
        int rankSign = 1;
        if (fileTwo - fileOne < 0) {
            fileSign = -1;
        }
        if (endRank - startRank < 0) {
            rankSign = -1;
        }

        if (fileDiff > 0 && fileDiff == rankDiff) {
            for (int i = 1; i <= rankDiff; i++) {
                for (ReturnPiece cur : piecesOnBoard) {
                    if (Character.getNumericValue(cur.pieceFile.toString().charAt(0)) == fileOne + (i * fileSign) && cur.pieceRank == startRank + (i * rankSign)) {
                        return false;
                    }
                }
            }
            return true;
        }

        return false;
    }
}