package chess;

import java.util.ArrayList;

public class Queen {
    
    public static boolean checkLegal(Position end, ReturnPiece b, ArrayList<ReturnPiece> piecesOnBoard){
        Position start = Position.getPosition(b);
        if(start.sameSquare(end)) return false;
        int diagDirection = Position.whichDiag(start, end);
        int cardinalDir = Position.whichCardinalDir(start, end);
        if(diagDirection == -1 && cardinalDir == -1) return false;
        if(diagDirection == 1) return Position.btwnDiag(start, farthestLeftUpDiag(b, piecesOnBoard), end);
        else if(diagDirection == 2) return Position.btwnDiag(start, farthestRightUpDiag(b, piecesOnBoard), end);
        else if(diagDirection == 3) return Position.btwnDiag(start, farthestRightDownDiag(b, piecesOnBoard), end);
        else if(diagDirection == 4) return Position.btwnDiag(start, farthestLeftDownDiag(b, piecesOnBoard), end);
        else if(cardinalDir == 1) return Position.withinLine(b, farthestUp(b, piecesOnBoard), end);
        else if(cardinalDir == 2) return Position.withinLine(b, farthestRight(b, piecesOnBoard), end);
        else if(cardinalDir == 3) return Position.withinLine(b, farthestDown(b, piecesOnBoard), end);
        else return Position.withinLine(b, farthestLeft(b, piecesOnBoard), end);
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

    public static Position farthestUp(ReturnPiece r, ArrayList<ReturnPiece> piecesOnBoard){
        Position prevSquare = Position.getPosition(r);
        Position square = Position.up(r);
        while(square != null){
            for(ReturnPiece p: piecesOnBoard){ 
                if(square.sameSquare(p))    //check if any piece is in this square
                    return (!PieceUtility.sameSide(p, r) && !PieceUtility.isKing(p))? square : prevSquare;
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
                    return (!PieceUtility.sameSide(p, r) && !PieceUtility.isKing(p))? square : prevSquare;
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
                    return (!PieceUtility.sameSide(p, r) && !PieceUtility.isKing(p))? square : prevSquare;
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
                    return (!PieceUtility.sameSide(p, r) && !PieceUtility.isKing(p))? square : prevSquare;
            }
            prevSquare = square; //if no pieces blocking than land in this square
            square = square.right(); 
        }
        return prevSquare;
    }



    // public static boolean checkLegal (PieceFile startFile, PieceFile endFile, int startRank, int endRank, ArrayList<ReturnPiece> piecesOnBoard) {
    //     ReturnPiece piece = null;
    //     for (ReturnPiece cur : piecesOnBoard) {
    //         if(startFile == cur.pieceFile && startRank == cur.pieceRank) {
    //             piece = cur;
    //             break;
    //         }
    //     }
    //     if (piece != null) {
    //         return false;
    //     }
    //     int fileOne = Character.getNumericValue(startFile.toString().charAt(0));
    //     int fileTwo = Character.getNumericValue(endFile.toString().charAt(0));

    //     int fileDiff = Math.abs(fileOne - fileTwo);
    //     int rankDiff = Math.abs(startRank - endRank);

    //     int fileSign = 1;
    //     int rankSign = 1;
    //     if (fileTwo - fileOne < 0) {
    //         fileSign = -1;
    //     }
    //     if (endRank - startRank < 0) {
    //         rankSign = -1;
    //     }

    //     if (fileDiff > 0 && fileDiff == rankDiff) {
    //         for (int i = 1; i <= rankDiff; i++) {
    //             for (ReturnPiece cur : piecesOnBoard) {
    //                 if (Character.getNumericValue(cur.pieceFile.toString().charAt(0)) == fileOne + (i * fileSign) && cur.pieceRank == startRank + (i * rankSign)) {
    //                     return false;
    //                 }
    //             }
    //         }
    //         return true;
    //     }

    //     return false;
    // }
}