
// Kelvin Bian and Jessica Luo

package chess;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.text.Position;

class ReturnPiece {
	static enum PieceType {WP, WR, WN, WB, WQ, WK, BP, BR, BN, BB, BK, BQ};
	static enum PieceFile {a, b, c, d, e, f, g, h};
	
	PieceType pieceType;
	PieceFile pieceFile;
	int pieceRank;  // 1..8
	public String toString() {
		return ""+pieceFile+pieceRank+":"+pieceType;
	}
	public boolean equals(Object other) {
		if (other == null || !(other instanceof ReturnPiece)) {
			return false;
		}
		ReturnPiece otherPiece = (ReturnPiece)other;
		return pieceType == otherPiece.pieceType &&
				pieceFile == otherPiece.pieceFile &&
				pieceRank == otherPiece.pieceRank;
	}
}

class ReturnPlay {
	enum Message {ILLEGAL_MOVE, DRAW, 
				  RESIGN_BLACK_WINS, RESIGN_WHITE_WINS, 
				  CHECK, CHECKMATE_BLACK_WINS,	CHECKMATE_WHITE_WINS, 
				  STALEMATE};
	
	ArrayList<ReturnPiece> piecesOnBoard;
	Message message;
}

public class Chess {
	
	enum Player { white, black }
	
	static HashMap<Position, ReturnPiece> board = new HashMap<>();
	static ArrayList<ReturnPiece> pieces;
	static boolean whiteCheck;
	static boolean blackCheck;
	static boolean whiteTurn;
	static boolean enPassantPossible;
	static boolean whiteKingsideCastlePossible;
	static boolean blackKingsideCastlePossible;
	static boolean whiteQueensideCastlePossible;
	static boolean blackQueensideCastlePossible;
	static Move prev;
	
	/**
	 * Plays the next move for whichever player has the turn.
	 * 
	 * @param move String for next move, e.g. "a2 a3"
	 * 
	 * @return A ReturnPlay instance that contains the result of the move.
	 *         See the section "The Chess class" in the assignment description for details of
	 *         the contents of the returned ReturnPlay instance.
	 */
	public static ReturnPlay play(String move) {

		/* FILL IN THIS METHOD */
		Move m = new Move(move, whiteTurn);
		ReturnPlay r = m.returnPlay(whiteCheck, blackCheck, pieces);

		return r;
	}
	
	
	/**
	 * This method should reset the game, and start from scratch.
	 */
	public static void start() {
		/* FILL IN THIS METHOD */
		board = new HashMap<>();
		whiteKingsideCastlePossible = true;
		blackKingsideCastlePossible = true;
		whiteQueensideCastlePossible = true;
		blackQueensideCastlePossible = true;
		BoardUtility.makePieces(board);  //adds all pieces to board (hashmap)
		pieces = new ArrayList<>();
		for(ReturnPiece p: board.values()){
			pieces.add(p);
		}
		whiteTurn = true;
	}
}
