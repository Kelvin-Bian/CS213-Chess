package chess;

import java.util.ArrayList;

import chess.ReturnPiece.PieceFile;

public class Rook {
    public static void castleMove(int castletype, ArrayList<ReturnPiece> piecesOnBoard){
        /** 1: white kingside      3: black kingside
         *  2: white queenside     4: black queenside
         */
        if(castletype==1){
            PieceUtility.movePiece(PieceUtility.findPiece(new Position(1, PieceFile.h), piecesOnBoard), new Position(1, PieceFile.f));
            Chess.whiteKingsideCastlePossible = false;
            Chess.whiteQueensideCastlePossible = false;
        }
        else if(castletype==2){
            PieceUtility.movePiece(PieceUtility.findPiece(new Position(1, PieceFile.a), piecesOnBoard), new Position(1, PieceFile.d));
            Chess.whiteKingsideCastlePossible = false;
            Chess.whiteQueensideCastlePossible = false;  
        }
        else if(castletype==3){
            PieceUtility.movePiece(PieceUtility.findPiece(new Position(8, PieceFile.h), piecesOnBoard), new Position(8, PieceFile.f));
                            Chess.blackKingsideCastlePossible = false;
                            Chess.blackQueensideCastlePossible = false;
        }
        else if(castletype==4){
            PieceUtility.movePiece(PieceUtility.findPiece(new Position(8, PieceFile.a), piecesOnBoard), new Position(8, PieceFile.d));
            Chess.blackKingsideCastlePossible = false;
            Chess.blackQueensideCastlePossible = false;
        }

    }
    private static void setCastleVariable (ReturnPiece r) {
        if (r.pieceRank == 1 && r.pieceFile == PieceFile.h) Chess.whiteKingsideCastlePossible = false;
        else if (r.pieceRank == 1 && r.pieceFile == PieceFile.a) Chess.whiteQueensideCastlePossible = false;
        else if (r.pieceRank == 8 && r.pieceFile == PieceFile.h) Chess.blackKingsideCastlePossible = false;
        else if (r.pieceRank == 8 && r.pieceFile == PieceFile.a) Chess.blackQueensideCastlePossible = false;
    }

    public static boolean checkLegal(Position end, ReturnPiece r, ArrayList<ReturnPiece> piecesOnBoard){
        if(Position.sameSquare(r, end)) return false;
        int cardinalDir = Position.whichCardinalDir(r, end);
        if(cardinalDir == -1) return false;
        if(cardinalDir == 1) {
            if(Position.withinLine(r, farthestUp(r, piecesOnBoard), end)) {
                setCastleVariable(r);
                return true;
            }
            else return false;
        }
        else if(cardinalDir == 2) {
            if (Position.withinLine(r, farthestRight(r, piecesOnBoard), end)) {
                setCastleVariable(r);
                return true;
            }
            else return false;
        }
        else if(cardinalDir == 3) {
            if (Position.withinLine(r, farthestDown(r, piecesOnBoard), end)) {
                setCastleVariable(r);
                return true;
            }
            else return false;
        }


        else if (Position.withinLine(r, farthestLeft(r, piecesOnBoard), end)) {
            setCastleVariable(r);
            return true;
        }
        else return false;

    }

    public static Position farthestUp(ReturnPiece r, ArrayList<ReturnPiece> piecesOnBoard){
        Position prevSquare = Position.getPosition(r);
        Position square = Position.up(r);
        while(square != null){
            for(ReturnPiece p: piecesOnBoard){ 
                if(square.sameSquare(p))    //check if any piece is in this square
                    return (!PieceUtility.sameSide(p, r) )? square : prevSquare;
            }
            prevSquare = square; //if no pieces blocking then land in this square
            square = square.up(); 
        }
        return prevSquare;
    }
    public static Position farthestDown(ReturnPiece r, ArrayList<ReturnPiece> piecesOnBoard){
        Position prevSquare = Position.getPosition(r);
        Position square = Position.down(r);
        while(square != null){
            for(ReturnPiece p: piecesOnBoard){ 
                if(square.sameSquare(p))    //check if any piece is in this square
                    return (!PieceUtility.sameSide(p, r) )? square : prevSquare;
            }
            prevSquare = square; //if no pieces blocking than land in this square
            square = square.down(); 
        }
        return prevSquare;
    }
    public static Position farthestLeft(ReturnPiece r, ArrayList<ReturnPiece> piecesOnBoard){
        Position prevSquare = Position.getPosition(r);
        Position square = Position.left(r);
        while(square != null){
            for(ReturnPiece p: piecesOnBoard){ 
                if(square.sameSquare(p))    
                    return (!PieceUtility.sameSide(p, r) )? square : prevSquare;
            }
            prevSquare = square; //if no pieces blocking than land in this square
            square = square.left(); 
        }
        return prevSquare;
    }
    public static Position farthestRight(ReturnPiece r, ArrayList<ReturnPiece> piecesOnBoard){
        Position prevSquare = Position.getPosition(r);
        Position square = Position.right(r);
        while(square != null){
            for(ReturnPiece p: piecesOnBoard){ 
                if(square.sameSquare(p))   
                    return (!PieceUtility.sameSide(p, r) )? square : prevSquare;
            }
            prevSquare = square; //if no pieces blocking than land in this square
            square = square.right(); 
        }
        return prevSquare;
    }
}
