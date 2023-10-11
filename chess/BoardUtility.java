package chess;
import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;

public class BoardUtility {
	public static Move parseMove(String move){
		
        char c = move.charAt(0);
        
        PieceFile f = charToFile(move.charAt(0));
        
    }
	public static String trimBeginningWhiteSpace(String move){
        String trimmed = "";
        boolean strStart = false;
        char[] str = move.toCharArray();
        for(char c: str){
            if(!strStart && c!=' '){
                strStart = true;
                trimmed+=c;
            }
            else if(strStart)
                trimmed +=c;
        }
        return trimmed;
    }
	public static PieceFile charToFile(char c){
        return PieceFile.values()[c-'a'];
    }
    public static void makePieces(){
		//add white pawns
		for(int c = 0; c < 8; c++ ){
			PieceFile f = PieceFile.values()[c];
			Position p = new Position(2, f);
			ReturnPiece pawn = new ReturnPiece();
            PieceUtility.setPiece(pawn, f, 2, PieceType.WP);
			Chess.board.put(p, pawn);
		}
		//add other white pieces
		ReturnPiece rook = new ReturnPiece();
		PieceUtility.setPiece(rook, PieceFile.a, 1, PieceType.WR);
		Chess.board.put(new Position(1, PieceFile.a), rook);

		ReturnPiece rook2 = new ReturnPiece();
		PieceUtility.setPiece(rook2, PieceFile.h, 1, PieceType.WR);
		Chess.board.put(new Position(1, PieceFile.h), rook2);

		ReturnPiece knight = new ReturnPiece();
		PieceUtility.setPiece(knight, PieceFile.b, 1, PieceType.WN);
		Chess.board.put(new Position(1, PieceFile.b), knight);
		ReturnPiece knight2 = new ReturnPiece();
		PieceUtility.setPiece(knight2, PieceFile.g, 1, PieceType.WN);
		Chess.board.put(new Position(1, PieceFile.g), knight2);

		ReturnPiece bishop = new ReturnPiece();
		PieceUtility.setPiece(bishop, PieceFile.c, 1, PieceType.WB);
		Chess.board.put(new Position(1, PieceFile.c), bishop);
		ReturnPiece bishop2 = new ReturnPiece();
		PieceUtility.setPiece(bishop2, PieceFile.f, 1, PieceType.WB);
		Chess.board.put(new Position(1, PieceFile.f), bishop2);

		ReturnPiece queen = new ReturnPiece();
		PieceUtility.setPiece(queen, PieceFile.d, 1, PieceType.WQ);
		Chess.board.put(new Position(1, PieceFile.d), queen);

		ReturnPiece king = new ReturnPiece();
		PieceUtility.setPiece(king, PieceFile.e, 1, PieceType.WK);
		Chess.board.put(new Position(1, PieceFile.e), king);
       
        //add black pieces
        for(int c = 0; c < 8; c++ ){
			PieceFile f = PieceFile.values()[c];
			Position p = new Position(7, f);
			ReturnPiece pawn = new ReturnPiece();
            PieceUtility.setPiece(pawn, f, 7, PieceType.BP);
			Chess.board.put(p, pawn);
		}
		ReturnPiece rookB = new ReturnPiece();
		PieceUtility.setPiece(rookB, PieceFile.a, 8, PieceType.BR);
		Chess.board.put(new Position(8, PieceFile.a), rookB);

		ReturnPiece rook2B = new ReturnPiece();
		PieceUtility.setPiece(rook2B, PieceFile.h, 8, PieceType.BR);
		Chess.board.put(new Position(8, PieceFile.h), rook2B);

		ReturnPiece knightB = new ReturnPiece();
		PieceUtility.setPiece(knightB, PieceFile.b, 8, PieceType.BN);
		Chess.board.put(new Position(8, PieceFile.b), knightB);
		ReturnPiece knight2B = new ReturnPiece();
		PieceUtility.setPiece(knight2B, PieceFile.g, 8, PieceType.BN);
		Chess.board.put(new Position(8, PieceFile.g), knight2B);

		ReturnPiece bishopB = new ReturnPiece();
		PieceUtility.setPiece(bishopB, PieceFile.c, 8, PieceType.BB);
		Chess.board.put(new Position(8, PieceFile.c), bishopB);
		ReturnPiece bishop2B = new ReturnPiece();
		PieceUtility.setPiece(bishop2B, PieceFile.f, 8, PieceType.BB);
		Chess.board.put(new Position(8, PieceFile.f), bishop2B);

		ReturnPiece queenB = new ReturnPiece();
		PieceUtility.setPiece(queenB, PieceFile.d, 8, PieceType.BQ);
		Chess.board.put(new Position(8, PieceFile.d), queenB);

		ReturnPiece kingB = new ReturnPiece();
		PieceUtility.setPiece(kingB, PieceFile.e, 8, PieceType.BK);
		Chess.board.put(new Position(8, PieceFile.e), kingB);
	}
}
