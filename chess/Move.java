package chess;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;
import chess.ReturnPlay.Message;
import java.util.ArrayList;

public class Move {

    Position start;
    Position end;
    PieceType promoType;
    boolean resign;
    boolean draw;
    boolean whiteTurn;
    boolean invalid;
    ReturnPiece movePiece;
    ReturnPiece captured;

    public Move(String move, boolean whiteTurn){
        // 5 variations of Move, invalid = true if not one of the variations
		//resign	 p1 to p2		p1 to p2 draw?		p1 to p2 P		p1 to p2 P draw?
		move = trimWhiteSpace(move);
		int len = move.length();
		if(len>=5){
			if(move.equals("resign"))   set(true, whiteTurn);
            else{
                PieceFile f = charToFile(move.charAt(0));
                PieceFile f2 = charToFile(move.charAt(3));
                int r = move.charAt(1)-'0';
                int r2 = move.charAt(4)-'0';
                if(f== null || f2 == null || r > 8 || r2 > 8 || r < 1 || r2 <1 )
                    invalid = true;
                Position start = new Position(r, f);
                Position end = new Position(r2, f2);
                
                if(len ==5) set(start, end, whiteTurn);
                else if(len == 11 && move.substring(6).equals("draw?")){
                    set(start, end, true, whiteTurn);
                }
                else if(len== 7){
                    char ptype = move.charAt(6);
                    PieceType pt = charToPieceType(ptype, whiteTurn);
                    set(start, end, pt, whiteTurn);
                }
                else if(len == 13){
                    if(move.substring(8).equals("draw?")){
                        char ptype = move.charAt(6);
                        PieceType pt = charToPieceType(ptype, whiteTurn);
                        set(start, end, pt, true, whiteTurn);
                    }
                }
                else{
                    invalid = true;
                }
            }
		}
        else 
            invalid = true;
    }

    //can have "draw?"" following move coordinates
    public void set(Position start, Position end, PieceType promoType, boolean draw , boolean whiteTurn  ){
        set(start, end, promoType, whiteTurn);
        this.draw = draw;
    }
    public void set(Position start, Position end, boolean draw, boolean whiteTurn ){
        set(start, end, whiteTurn);
        this.draw = draw;
    }
    public void set(Position start, Position end, PieceType promoType, boolean whiteTurn ){
        set(start, end, whiteTurn);
        this.promoType = promoType;
    }
    public void set(Position start, Position end, boolean whiteTurn ){
        this.start = start;
        this.end = end;
        this.whiteTurn = whiteTurn;
    }
    public void set(boolean resign, boolean whiteTurn ){
        this.resign = resign;
        this.whiteTurn = whiteTurn;
    }

    public boolean validPreMove(ArrayList<ReturnPiece> pieces){
        movePiece = PieceUtility.findPiece(start, pieces);
        if(invalid) return false;
        else{
            if(!validPiece(movePiece)) {
                invalid = true; //is there a valid piece at starting position?
                return false;
            }
            if(!legalFunction.checkLegal(end, movePiece, pieces)) {
                invalid = true; //does move follow piece rules?
                return false;
            }
        }
        return true;
    }

    public boolean validPostMove(boolean whiteCheck, boolean blackCheck, ArrayList<ReturnPiece> pieces){
        if((whiteTurn && Check.whiteCheck(pieces)) || (!whiteTurn && Check.blackCheck(pieces))){
             invalid = true; //putting own side's king in check?
            return false;
        }
        if(whiteTurn && whiteCheck && Check.whiteCheck(pieces)) {
            invalid = true; //did not remove own side's king from check
            return false;
        }
        if(!whiteTurn && blackCheck && Check.blackCheck(pieces)){
            invalid = true; //did not remove own side's king from check
            return false;
        }
        //otherwise valid move; if was in check, now not in check
        if(!whiteTurn && Chess.blackCheck && !Check.blackCheck(pieces)){
            Chess.blackCheck = false;
        }
        if(whiteTurn && Chess.whiteCheck && !Check.whiteCheck(pieces))
            Chess.whiteCheck = false;

        return true;
    }

    public void playMove(ArrayList<ReturnPiece> pieces){
        PieceUtility.movePiece(movePiece, end); //update rank and file in returnpiece
        captured = PieceUtility.findPiece(end, pieces);
        if(captured!= null){ //if capturing, remove piece
            pieces.remove(captured);
        }
    }

    public void reverseMove(ArrayList<ReturnPiece> pieces){
        if(captured!= null){
            pieces.add(captured);
        }
        PieceUtility.movePiece(movePiece, start);
    }
    public ReturnPlay returnPlay(boolean whiteCheck, boolean blackCheck, ArrayList<ReturnPiece> pieces){
        ReturnPlay r = new ReturnPlay();
        if(resign) {
            r.message = (whiteTurn)? Message.RESIGN_BLACK_WINS: Message.RESIGN_WHITE_WINS;
            r.piecesOnBoard = pieces;
            return r;
        }
        if(validPreMove(pieces)){
            playMove(pieces);
            if(!validPostMove(whiteCheck, blackCheck, pieces)) {
                r.message = Message.ILLEGAL_MOVE;
                reverseMove(pieces);
            }
            else{
                if(draw)
                    r.message = Message.DRAW;
                else if((whiteTurn && Check.blackCheck(pieces))){
                    r.message = Message.CHECK;
                    Chess.blackCheck = true;
                }
                else if(!whiteTurn && Check.whiteCheck(pieces)){
                    r.message = Message.CHECK;
                    Chess.whiteCheck = true;
                }
                else if(whiteTurn && Check.blackCheckmate(pieces))
                    r.message = Message.CHECKMATE_WHITE_WINS;
                else if(!whiteTurn && Check.whiteCheckmate(pieces))
                    r.message = Message.CHECKMATE_BLACK_WINS;
                Chess.whiteTurn = !whiteTurn;
                }
        }
        else{
            r.message = Message.ILLEGAL_MOVE;
        }

        r.piecesOnBoard = pieces;
        return r;
    }

    public boolean validPiece(ReturnPiece p){ //check if starting position contains piece belonging to playing side
        char side = (whiteTurn)? 'W' : 'B';
        if(p==null || p.pieceType.name().charAt(0)!= side){
            return false;
        }
        return true;
    }

	public static String trimWhiteSpace(String move){
        String trimmed = "";
		int len = move.length();
		int lastInd = len-1; //index of last letter/num in move, all whitespace after

		for(int i = len-1; i>=0; i--){
			char c = move.charAt(i);
			if(c!=' '){
                lastInd = i;
				break;
            }
			else if (i == 0){
				lastInd = 0;
			}
		}

		boolean strStart = false;
        for(int i = 0; i <= lastInd; i++){
			char c = move.charAt(i);
            if(!strStart && c!=' '){
                strStart = true;
                trimmed+=c;
            }
            else if(strStart)
                trimmed +=c;
        }
		
        return trimmed;
    }

	public static PieceType charToPieceType(char c, boolean whiteTurn){
		int pTypeInd = (whiteTurn)? 1:7; //1 - White rook, 7 - Black rook
		PieceType[] pT = PieceType.values();
		if(c =='R'){
			return pT[pTypeInd];
		}
		else if(c=='N')
			return pT[pTypeInd+1];
		else if(c=='B')
			return pT[pTypeInd+2];
		else if(c=='Q'){
			return (whiteTurn)? pT[pTypeInd+3]: pT[11];
		}
		else return null;
	}
	public static PieceFile charToFile(char c){
        return (c>='a' && c<='h')? PieceFile.values()[c-'a']: null;
    }
    public String toString(){
        if(invalid) return "invalid move";
        String s="";
        if(start!=null){
            s+=start+" "+end; 
        }
        if(promoType!=null){
            s+=" "+promoType;
        }
        if(draw){
            s+=" draw?";
        }
        if(resign){
            s+="resign";
        }
        return s;
    }
}
