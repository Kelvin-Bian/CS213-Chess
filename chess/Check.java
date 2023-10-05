package chess;

import java.util.ArrayList;
import java.lang.Math;

import chess.ReturnPiece.PieceFile;

public class Check {
    public static boolean inCheck (PieceFile file, int rank, ArrayList<ReturnPiece> piecesOnBoard) {
        ReturnPiece piece = null;
        // for each piece on board, check if it can move into the given square (see if that square is under check by each piece)
        for (ReturnPiece cur : piecesOnBoard) {
            if(pieceFunction.callPiece(cur.pieceFile, file, cur.pieceRank, rank, piecesOnBoard, cur.pieceType)) {
                return true;
            }
        }
        return false;
    }
}
