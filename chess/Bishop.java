package chess;
import java.util.ArrayList;

public class Bishop{

    public static boolean checkLegal(Position end, ReturnPiece b, ArrayList<ReturnPiece> piecesOnBoard){
        Position start = Position.getPosition(b);
        if(start.sameSquare(end)) return false;
        int diagDirection = Position.whichDiag(start, end);
        if(diagDirection == -1) return false;
        if(diagDirection == 1) return Position.btwnDiag(start, farthestLeftUpDiag(b, piecesOnBoard), end);
        else if(diagDirection == 2) return Position.btwnDiag(start, farthestRightUpDiag(b, piecesOnBoard), end);
        else if(diagDirection == 3) return Position.btwnDiag(start, farthestRightDownDiag(b, piecesOnBoard), end);
        else return Position.btwnDiag(start, farthestLeftDownDiag(b, piecesOnBoard), end);
    }

    //returns null if can't move in left up diag direction
    public static Position farthestLeftUpDiag(ReturnPiece b, ArrayList<ReturnPiece> piecesOnBoard){
        //start from closest leftUpDiag and check if another piece is in that spot
        /**if yes, if piece is opponent's and not king, then valid move: capturing, can't move beyond
    -- else can't land on that square and beyond **/
        Position start = Position.getPosition(b);
        Position prevSquare = start; //previous possible landing square
        Position square = start.leftUpDiag();
        while(square != null){
            for(ReturnPiece p: piecesOnBoard){ 
                if(square.sameSquare(p))    //check if any piece is in this left up diag square
                    return (!PieceUtility.sameSide(p, b) && !PieceUtility.isKing(p))? square : prevSquare;
            }
            prevSquare = square; //if no pieces blocking than land in this square
            square = square.leftUpDiag();  
        }
        return prevSquare;
    }

    public static Position farthestRightUpDiag(ReturnPiece b, ArrayList<ReturnPiece> piecesOnBoard){
        Position start = new Position(b.pieceRank, b.pieceFile);
        Position prevSquare = start; //previous possible landing square
        Position square = start.rightUpDiag();
        while(square != null){
            for(ReturnPiece p: piecesOnBoard){ 
                if(square.sameSquare(p))
                    return (!PieceUtility.sameSide(p, b) && !PieceUtility.isKing(p))? square : prevSquare;
            }
            prevSquare = square; //if no pieces blocking than land in this square
            square = square.rightUpDiag();  
        }
        return prevSquare;
    }
    public static Position farthestRightDownDiag(ReturnPiece b, ArrayList<ReturnPiece> piecesOnBoard){
        Position start = new Position(b.pieceRank, b.pieceFile);
        Position prevSquare = start; //previous possible landing square
        Position square = start.rightDownDiag();
        while(square != null){
            for(ReturnPiece p: piecesOnBoard){ 
                if(square.sameSquare(p))
                    return (!PieceUtility.sameSide(p, b) && !PieceUtility.isKing(p))? square : prevSquare;
            }
            prevSquare = square; //if no pieces blocking than land in this square
            square = square.rightDownDiag();  
        }
        return prevSquare;
    }

    public static Position farthestLeftDownDiag(ReturnPiece b, ArrayList<ReturnPiece> piecesOnBoard){
        Position start = new Position(b.pieceRank, b.pieceFile);
        Position prevSquare = start; //previous possible landing square
        Position square = start.leftDownDiag();
        while(square != null){
            for(ReturnPiece p: piecesOnBoard){ 
                if(square.sameSquare(p))
                    return (!PieceUtility.sameSide(p, b) && !PieceUtility.isKing(p))? square : prevSquare;
            }
            prevSquare = square; //if no pieces blocking than land in this square
            square = square.leftDownDiag();  
        }
        return prevSquare;
    }

}
