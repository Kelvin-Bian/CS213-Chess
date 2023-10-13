package chess;
import java.util.ArrayList;

public class legalFunction {
    public static boolean checkLegal(Position end, ReturnPiece p, ArrayList<ReturnPiece> piecesOnBoard) {
        switch(p.pieceType) {
            case WP:
            case BP:
                return Pawn.checkLegal(end, p, piecesOnBoard);
            case WN:
            case BN:
                return Knight.checkLegal(end, p, piecesOnBoard);
            case WR:
            case BR:
                return Rook.checkLegal(end, p, piecesOnBoard);
            case WB:
            case BB:
                return Bishop.checkLegal(end, p, piecesOnBoard);
            case WQ:
            case BQ:
                return Queen.checkLegal(end, p, piecesOnBoard);
            case WK:
            case BK:
                return King.checkLegal(end, p, piecesOnBoard);
        }
        return false;
    }
}
