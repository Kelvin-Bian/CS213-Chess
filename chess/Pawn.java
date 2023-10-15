package chess;

import java.util.ArrayList;

public class Pawn{ //move forward one square or two if first move

    public static boolean checkLegal(Position end, ReturnPiece p, ArrayList<ReturnPiece> piecesOnBoard){
        if(firstMove(p)){
            return Position.withinLine(p, Position.twoSqauresForward(p), end);
        }
        else{
            //need to check for en passant


            //
            return end.sameSquare(Position.oneSquareForward(p)) || captureMove(end, p, piecesOnBoard);
        }
    }
    public static boolean firstMove(ReturnPiece p){
        int rank = p.pieceRank;
        boolean isWhite = PieceUtility.isWhite(p);
        return (isWhite && rank == 2) || (!isWhite && rank == 7 );
    }

    public static boolean captureMove(Position end, ReturnPiece p, ArrayList<ReturnPiece> pieces){
        Position start = Position.getPosition(p);
        if(PieceUtility.isWhite(p)){
            return (end == start.leftUpDiag()|| end == start.rightUpDiag()) && !PieceUtility.isWhite(PieceUtility.findPiece(end, pieces));
        }
        else
            return (end == start.leftDownDiag()|| end == start.rightDownDiag()) && PieceUtility.isWhite(PieceUtility.findPiece(end, pieces));
    }

}
