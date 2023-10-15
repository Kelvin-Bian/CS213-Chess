package chess;

import java.util.ArrayList;

public class Rook{
    public static boolean checkLegal(Position end, ReturnPiece r, ArrayList<ReturnPiece> piecesOnBoard){
        if(Position.sameSquare(r, end)) return false;
        int cardinalDir = Position.whichCardinalDir(r, end);
        if(cardinalDir == -1) return false;
        if(cardinalDir == 1) return Position.withinLine(r, farthestUp(r, piecesOnBoard), end);
        else if(cardinalDir == 2) return Position.withinLine(r, farthestRight(r, piecesOnBoard), end);
        else if(cardinalDir == 3) return Position.withinLine(r, farthestDown(r, piecesOnBoard), end);
        else return Position.withinLine(r, farthestLeft(r, piecesOnBoard), end);
    }

    public static Position farthestUp(ReturnPiece r, ArrayList<ReturnPiece> piecesOnBoard){
        Position prevSquare = Position.getPosition(r);
        Position square = Position.up(r);
        while(square != null){
            for(ReturnPiece p: piecesOnBoard){ 
                if(square.sameSquare(p))    //check if any piece is in this square
                    return (!PieceUtility.sameSide(p, r) && !PieceUtility.isKing(p))? square : prevSquare;
            }
            prevSquare = square; //if no pieces blocking then land in this square
            square = Position.up(r); 
        }
        return prevSquare;
    }
    public static Position farthestDown(ReturnPiece r, ArrayList<ReturnPiece> piecesOnBoard){
        Position prevSquare = Position.getPosition(r);
        Position square = Position.down(r);
        while(square != null){
            for(ReturnPiece p: piecesOnBoard){ 
                if(square.sameSquare(p))    //check if any piece is in this square
                    return (!PieceUtility.sameSide(p, r) && !PieceUtility.isKing(p))? square : prevSquare;
            }
            prevSquare = square; //if no pieces blocking than land in this square
            square = Position.down(r); 
        }
        return prevSquare;
    }
    public static Position farthestLeft(ReturnPiece r, ArrayList<ReturnPiece> piecesOnBoard){
        Position prevSquare = Position.getPosition(r);
        Position square = Position.left(r);
        while(square != null){
            for(ReturnPiece p: piecesOnBoard){ 
                if(square.sameSquare(p))    
                    return (!PieceUtility.sameSide(p, r) && !PieceUtility.isKing(p))? square : prevSquare;
            }
            prevSquare = square; //if no pieces blocking than land in this square
            square = Position.left(r); 
        }
        return prevSquare;
    }
    public static Position farthestRight(ReturnPiece r, ArrayList<ReturnPiece> piecesOnBoard){
        Position prevSquare = Position.getPosition(r);
        Position square = Position.right(r);
        while(square != null){
            for(ReturnPiece p: piecesOnBoard){ 
                if(square.sameSquare(p))   
                    return (!PieceUtility.sameSide(p, r) && !PieceUtility.isKing(p))? square : prevSquare;
            }
            prevSquare = square; //if no pieces blocking than land in this square
            square = Position.right(r); 
        }
        return prevSquare;
    }
}
