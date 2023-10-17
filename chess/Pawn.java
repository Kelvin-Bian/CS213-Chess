package chess;

import java.util.ArrayList;

public class Pawn{ 
    //will check if can capture King, but can't actually capture King when playing move
    public static boolean checkLegal(Position end, ReturnPiece p, ArrayList<ReturnPiece> piecesOnBoard){
        if (Position.withinLine(p, farthestForward(p, piecesOnBoard), end)){
            return true;
        }
        if(validEnPassant(end, p, piecesOnBoard))
            return true;
        return captureMove(end, p, piecesOnBoard); //valid capture move
    }
    public static boolean validEnPassant(Position end, ReturnPiece p, ArrayList<ReturnPiece> pieces){
        if(Chess.enPassantPossible && !PieceUtility.sameSide(p, Chess.prev.movePiece) &&end.equals(Position.squareBtwn(Chess.prev.start, Chess.prev.end))){
                //is ending square btwn start and end of prev pawn move
                //is ending square empty and diagonally forward from cur square?
                if(isCaptureSquare( end, p) && PieceUtility.findPiece(end, pieces)==null)
                    return true;
        }
        return false;
    }
    public static Position farthestForward(ReturnPiece r, ArrayList<ReturnPiece> pieces){
        return (PieceUtility.isWhite(r))? farthestUp(r, pieces) : farthestDown(r, pieces);
    }
    public static Position farthestUp(ReturnPiece r, ArrayList<ReturnPiece> piecesOnBoard){
        Position prevSquare = Position.getPosition(r);
        Position square = Position.up(r);
        int n = 0;
        int max = (firstMove(r))? 2:1;
        while(square != null && n < max){
            for(ReturnPiece p: piecesOnBoard){ 
                if(square.sameSquare(p))    //check if any piece is in this square
                    return prevSquare;
            }
            prevSquare = square; //if no pieces blocking then land in this square
            square = square.up(); 
            n++;
        }
        return prevSquare;
    }
    public static Position farthestDown(ReturnPiece r, ArrayList<ReturnPiece> piecesOnBoard){
        Position prevSquare = Position.getPosition(r);
        Position square = Position.down(r);
        int n = 0;
        int max = (firstMove(r))? 2:1;
        while(square != null && n < max){
            for(ReturnPiece p: piecesOnBoard){ 
                if(square.sameSquare(p))    //check if any piece is in this square
                    return  prevSquare;
            }
            prevSquare = square; //if no pieces blocking than land in this square
            square = square.down(); 
            n++;
        }
        return prevSquare;
    }

    public static boolean firstMove(ReturnPiece p){
        int rank = p.pieceRank;
        boolean isWhite = PieceUtility.isWhite(p);
        return (isWhite && rank == 2) || (!isWhite && rank == 7 );
    }

    public static boolean captureMove(Position end, ReturnPiece p, ArrayList<ReturnPiece> pieces){
        Position start = Position.getPosition(p);
        if(isCaptureSquare(end, p)){ //forwardly diagonal square
            ReturnPiece captured = PieceUtility.findPiece(end, pieces); //is there a piece to capture
            return (captured!= null && !PieceUtility.sameSide(captured, p)); //is the piece valid
        }
        return false;
    }
    public static boolean isCaptureSquare(Position end, ReturnPiece p){
        Position start = Position.getPosition(p);
        if(PieceUtility.isWhite(p)){
                return (end.equals(start.leftUpDiag())|| end.equals(start.rightUpDiag()));
            }
            else
                return (end.equals(start.leftDownDiag())|| end.equals(start.rightDownDiag()));
    }

    //assuming move is valid
    public static boolean canPromote(Position end, ReturnPiece p){
        if(PieceUtility.isWhite(p)){
            return end.r() == 8;
        }
        else
            return end.r() == 1;
    }
}
