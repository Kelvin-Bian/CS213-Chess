package chess;

public class Test {
    public static void main(String[] args){
        
        // System.out.print(BoardUtility.trimWhiteSpace("     as is    p 3a    "));
        // System.out.print(BoardUtility.trimWhiteSpace("     as is    p 3a    "));
        System.out.println(BoardUtility.parseMove("h8 h8", false));
        Piece bishop = new Bishop();
        bishop.checkLegal()
    }
}
