package chess;

import java.util.ArrayList;
import java.lang.Math;

import chess.ReturnPiece.PieceFile;

public class Knight implements Piece {

    // return boolean signifying whether a Knight move is legal or not
    public static boolean checkLegal (Position end, ReturnPiece n, ArrayList<ReturnPiece> piecesOnBoard) {
        PieceFile startFile = n.pieceFile;
        PieceFile endFile = end.f();
        int startRank = n.pieceRank;
        int endRank = end.r();
        // Check if own side piece is on target square or if move is to stay in place (illegal)
        for (ReturnPiece cur : piecesOnBoard) {
            if(Position.sameSquare(cur, end) && PieceUtility.sameSide(n, cur)) {
                return false;
            }
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