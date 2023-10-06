package chess;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;

public class PieceUtility {
    public static boolean sameSide(ReturnPiece a, ReturnPiece b){
        return a.pieceType.name().charAt(0) == b.pieceType.name().charAt(0);
    }
    public static boolean isKing(ReturnPiece p){
        return p.pieceType == PieceType.BK || p.pieceType == PieceType.WK;
    }

    public static boolean isWhite(ReturnPiece p){
        return p.pieceType.name().charAt(0)=='W';
    }

    public static int fileInt(ReturnPiece rp){
        return (""+rp.pieceFile).charAt(0) - 'a';
    }
    public static int fileInt(Position p){
        return (""+p.f()).charAt(0) - 'a';
    }
    public static int rankIndex(Position p){ //convert rank into index for Chess.board row
        return 8-p.r();
    }

    public static void setPiece(ReturnPiece p, PieceFile f, int r, PieceType t){
        p.pieceFile = f;
        p.pieceRank = r;
        p.pieceType = t;
    }
    public static void initialPieces(){
		//add white pawns
		for(int c = 0; c < 8; c++ ){
			PieceFile f = PieceFile.values()[c];
			Position p = new Position(2, f);
			ReturnPiece pawn = new ReturnPiece();
            setPiece(pawn, f, 2, PieceType.WP);
			Chess.board.put(p, pawn);
		}
		//add other white pieces
		ReturnPiece rook = new ReturnPiece();
		setPiece(rook, PieceFile.a, 1, PieceType.WR);
		Chess.board.put(new Position(1, PieceFile.a), rook);

		ReturnPiece rook2 = new ReturnPiece();
		setPiece(rook2, PieceFile.h, 1, PieceType.WR);
		Chess.board.put(new Position(1, PieceFile.h), rook2);

		ReturnPiece knight = new ReturnPiece();
		setPiece(knight, PieceFile.b, 1, PieceType.WN);
		Chess.board.put(new Position(1, PieceFile.b), knight);
		ReturnPiece knight2 = new ReturnPiece();
		setPiece(knight2, PieceFile.g, 1, PieceType.WN);
		Chess.board.put(new Position(1, PieceFile.g), knight2);

		ReturnPiece bishop = new ReturnPiece();
		setPiece(bishop, PieceFile.c, 1, PieceType.WB);
		Chess.board.put(new Position(1, PieceFile.c), bishop);
		ReturnPiece bishop2 = new ReturnPiece();
		setPiece(bishop2, PieceFile.f, 1, PieceType.WB);
		Chess.board.put(new Position(1, PieceFile.f), bishop2);

		ReturnPiece queen = new ReturnPiece();
		setPiece(queen, PieceFile.d, 1, PieceType.WQ);
		Chess.board.put(new Position(1, PieceFile.d), queen);

		ReturnPiece king = new ReturnPiece();
		setPiece(king, PieceFile.e, 1, PieceType.WK);
		Chess.board.put(new Position(1, PieceFile.e), king);
       
        //add black pieces
        for(int c = 0; c < 8; c++ ){
			PieceFile f = PieceFile.values()[c];
			Position p = new Position(7, f);
			ReturnPiece pawn = new ReturnPiece();
            setPiece(pawn, f, 7, PieceType.BP);
			Chess.board.put(p, pawn);
		}
		ReturnPiece rookB = new ReturnPiece();
		setPiece(rookB, PieceFile.a, 8, PieceType.BR);
		Chess.board.put(new Position(8, PieceFile.a), rookB);

		ReturnPiece rook2B = new ReturnPiece();
		setPiece(rook2B, PieceFile.h, 8, PieceType.BR);
		Chess.board.put(new Position(8, PieceFile.h), rook2B);

		ReturnPiece knightB = new ReturnPiece();
		setPiece(knightB, PieceFile.b, 8, PieceType.BN);
		Chess.board.put(new Position(8, PieceFile.b), knightB);
		ReturnPiece knight2B = new ReturnPiece();
		setPiece(knight2B, PieceFile.g, 8, PieceType.BN);
		Chess.board.put(new Position(8, PieceFile.g), knight2B);

		ReturnPiece bishopB = new ReturnPiece();
		setPiece(bishopB, PieceFile.c, 8, PieceType.BB);
		Chess.board.put(new Position(8, PieceFile.c), bishopB);
		ReturnPiece bishop2B = new ReturnPiece();
		setPiece(bishop2B, PieceFile.f, 8, PieceType.BB);
		Chess.board.put(new Position(8, PieceFile.f), bishop2B);

		ReturnPiece queenB = new ReturnPiece();
		setPiece(queenB, PieceFile.d, 8, PieceType.BQ);
		Chess.board.put(new Position(8, PieceFile.d), queenB);

		ReturnPiece kingB = new ReturnPiece();
		setPiece(kingB, PieceFile.e, 8, PieceType.BK);
		Chess.board.put(new Position(8, PieceFile.e), kingB);
	}
}
