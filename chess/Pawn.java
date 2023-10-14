package chess;

import java.util.ArrayList;

public class Pawn{ //move forward one square or two if first move

    public static boolean checkLegal(Position end, ReturnPiece p, ArrayList<ReturnPiece> piecesOnBoard){
        if(firstMove(p)){
            return Position.withinLine(p, Position.twoSqauresForward(p), end);
        }
        else{
            //see if move is capture
            
            return end.sameSquare(Position.oneSquareForward(p));
        }
    }
    public static boolean firstMove(ReturnPiece p){
        int rank = p.pieceRank;
        boolean isWhite = PieceUtility.isWhite(p);
        return (isWhite && rank == 2) || (!isWhite && rank == 7 );
    }

}
