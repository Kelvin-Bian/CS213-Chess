package chess;

import java.util.HashMap;
import java.util.Set;

public class Test {
    public static void main(String[] args){
        
        // System.out.print(BoardUtility.trimWhiteSpace("     as is    p 3a    "));
        // System.out.print(BoardUtility.trimWhiteSpace("     as is    p 3a    "));
        HashMap<Position, ReturnPiece> board = new HashMap<>();
        BoardUtility.makePieces(board);
        Move m = new Move("a2 a4", true);
        // System.out.println(m.start);
        
    }
}
