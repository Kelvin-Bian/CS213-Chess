package chess;

import java.util.ArrayList;
import java.lang.Math;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;

public class King {
    // return boolean signifying whether a King move is legal or not
    public static boolean checkLegal (Position end, ReturnPiece n, ArrayList<ReturnPiece> piecesOnBoard) {
        PieceFile startFile = n.pieceFile;
        PieceFile endFile = end.f();
        int startRank = n.pieceRank;
        int endRank = end.r();
        Position startPosition = Position.getPosition(n);

        // Check if own side piece is on target square or if move is to stay in place (illegal)
        for (ReturnPiece cur : piecesOnBoard) {
            if(Position.sameSquare(cur, end) && PieceUtility.sameSide(n, cur)) {
                return false;
            }
        }

        if(checkCastling(end, n, piecesOnBoard)>0) return true;

        // Check to see if target square follows Knight movement pattern
        int fileOne = Character.getNumericValue(startFile.toString().charAt(0));
        int fileTwo = Character.getNumericValue(endFile.toString().charAt(0));

        int fileDiff = Math.abs(fileOne - fileTwo);
        int rankDiff = Math.abs(startRank - endRank);


        // check if move is to a bordering square
        if (fileDiff < 2 && rankDiff < 2) {
            Chess.whiteKingsideCastlePossible = false;
            Chess.whiteQueensideCastlePossible = false;
            Chess.blackKingsideCastlePossible = false;
            Chess.blackQueensideCastlePossible = false;
            return true;
        }

        return false;
    }

    //returns -1 if move isn't valid castling OR int btwn 1-4 for valid castling
    public static int checkCastling(Position end, ReturnPiece n, ArrayList<ReturnPiece> piecesOnBoard) {
        //check if move is castling and if it's valid 
        PieceFile startFile = n.pieceFile;
        PieceFile endFile = end.f();
        int startRank = n.pieceRank;
        int endRank = end.r();
        Position startPosition = Position.getPosition(n);
        boolean whiteKing = PieceUtility.isWhite(n);

        // check for WHITE KING CASTLING
        // Kingside castling
        // check if white kingside castling is possible and for correct king and rook position and movement
        if (whiteKing && Chess.whiteKingsideCastlePossible && n.pieceRank == 1 && n.pieceFile == PieceFile.e && end.r() == 1 && end.f() == PieceFile.g && PieceUtility.findPiece(new Position(1, PieceFile.h), piecesOnBoard).pieceType == PieceType.WR) {
            //check if squares that king moves through are not occupied or in check (would make castling illegal)
            if (PieceUtility.findPiece(Position.right(n), piecesOnBoard) == null && !Check.whiteCheck(piecesOnBoard)) {
                PieceUtility.movePiece(n, Position.right(n));
                if (PieceUtility.findPiece(Position.right(n), piecesOnBoard) == null && !Check.whiteCheck(piecesOnBoard)) {
                    PieceUtility.movePiece(n, Position.right(n));
                    if (!Check.whiteCheck(piecesOnBoard)) {
                        PieceUtility.movePiece(n, startPosition);
                        
                        return 1;
                    }
                }
            }
        }

        // check if white queenside castling is possible and for correct king and rook position and movement
        if (whiteKing && Chess.whiteQueensideCastlePossible && n.pieceRank == 1 && n.pieceFile == PieceFile.e && end.r() == 1 && end.f() == PieceFile.c && PieceUtility.findPiece(new Position(1, PieceFile.a), piecesOnBoard).pieceType == PieceType.WR) {
            //check if squares that king moves through are not occupied or in check (would make castling illegal) [AND for white queenside, check if b1 is not occupied for rook to move through]
            if (PieceUtility.findPiece(Position.left(n), piecesOnBoard) == null && !Check.whiteCheck(piecesOnBoard)) {
                PieceUtility.movePiece(n, Position.left(n));
                if (PieceUtility.findPiece(Position.left(n), piecesOnBoard) == null && !Check.whiteCheck(piecesOnBoard)) {
                    PieceUtility.movePiece(n, Position.left(n));
                    if (PieceUtility.findPiece(Position.left(n), piecesOnBoard) == null && !Check.whiteCheck(piecesOnBoard)) {
                        PieceUtility.movePiece(n, startPosition);
                        
                        return 2;
                    }
                }
            }
        }

        // check for BLACK KING CASTLING
        // Kingside castling
        // check if black kingside castling is possible and for correct king and rook position and movement
        if (!whiteKing &&Chess.blackKingsideCastlePossible && n.pieceRank == 8 && n.pieceFile == PieceFile.e && end.r() == 8 && end.f() == PieceFile.g && PieceUtility.findPiece(new Position(8, PieceFile.h), piecesOnBoard).pieceType == PieceType.BR) {
            //check if squares that king moves through are not occupied or in check (would make castling illegal)
            if (PieceUtility.findPiece(Position.right(n), piecesOnBoard) == null && !Check.blackCheck(piecesOnBoard)) {
                PieceUtility.movePiece(n, Position.right(n));
                if (PieceUtility.findPiece(Position.right(n), piecesOnBoard) == null && !Check.blackCheck(piecesOnBoard)) {
                    PieceUtility.movePiece(n, Position.right(n));
                    if (!Check.blackCheck(piecesOnBoard)) {
                        PieceUtility.movePiece(n, startPosition);
                        
                        return 3;
                    }
                }
            }
        }

        // check if black queenside castling is possible and for correct king and rook position and movement
        if (!whiteKing &&Chess.blackQueensideCastlePossible && n.pieceRank == 8 && n.pieceFile == PieceFile.e && end.r() == 8 && end.f() == PieceFile.c && PieceUtility.findPiece(new Position(8, PieceFile.a), piecesOnBoard).pieceType == PieceType.BR) {
            //check if squares that king moves through are not occupied or in check (would make castling illegal) [AND for black queenside, check if b8 is not occupied for rook to move through]
            if (PieceUtility.findPiece(Position.left(n), piecesOnBoard) == null && !Check.blackCheck(piecesOnBoard)) {
                PieceUtility.movePiece(n, Position.left(n));
                if (PieceUtility.findPiece(Position.left(n), piecesOnBoard) == null && !Check.blackCheck(piecesOnBoard)) {
                    PieceUtility.movePiece(n, Position.left(n));
                    if (PieceUtility.findPiece(Position.left(n), piecesOnBoard) == null && !Check.blackCheck(piecesOnBoard)) {
                        PieceUtility.movePiece(n, startPosition);
                        return 4;
                    }
                }
            }
        }
        return -1;
    }
}
