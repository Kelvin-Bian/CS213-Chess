package chess;

import java.util.ArrayList;
import java.lang.Math;

import chess.ReturnPiece.PieceFile;

public class Knight {

    // return boolean signifying whether a Knight move is legal or not
    public static boolean checkLegal (Position end, ReturnPiece n, ArrayList<ReturnPiece> piecesOnBoard) {
        PieceFile startFile = n.pieceFile;
        PieceFile endFile = end.f();
        int startRank = n.pieceRank;
        int endRank = end.r();
        ReturnPiece piece = null;
        // loop may not be necessary for current implementation because ReturnPiece n is provided
        // (this method originally took starting square instead of a ReturnPiece as arguments)
        for (ReturnPiece cur : piecesOnBoard) {
            if(startFile == cur.pieceFile && startRank == cur.pieceRank) {
                piece = cur;
                break;
            }
        }
        if (piece != null) {
            return false;
        }

        // Check if own piece is on target square
        for (ReturnPiece current : piecesOnBoard) {
            if (Position.sameSquare(current, end) && PieceUtility.sameSide(current, n)) {return false;} 
        }

        // Check to see if target square follows Knight movement pattern
        int fileOne = Character.getNumericValue(startFile.toString().charAt(0));
        int fileTwo = Character.getNumericValue(endFile.toString().charAt(0));

        int fileDiff = Math.abs(fileOne - fileTwo);
        int rankDiff = Math.abs(startRank - endRank);

        if (fileDiff > 0 && rankDiff > 0 && fileDiff + rankDiff == 3) {return true;}

        return false;
    }
}