package chess;

import java.util.HashMap;
import java.util.Set;
import chess.ReturnPiece.PieceFile;

public class Test {
    public static void main(String[] args){
        
        // System.out.print(BoardUtility.trimWhiteSpace("     as is    p 3a    "));
        // System.out.print(BoardUtility.trimWhiteSpace("     as is    p 3a    "));
        HashMap<Position, ReturnPiece> board = new HashMap<>();
        BoardUtility.makePieces(board);
        Move m = new Move("a2 a4", true);
        // System.out.println(m.start);
        Position p = new Position(8, PieceFile.f);
        Position p2 = new Position(6, PieceFile.h);
        System.out.println(Position.whichDiag(p, p2));
    }
}
