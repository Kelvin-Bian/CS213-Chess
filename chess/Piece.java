package chess;

import java.util.ArrayList;

public interface Piece {
     static boolean checkLegal(Position end, ReturnPiece n, ArrayList<ReturnPiece> piecesOnBoard);
}
