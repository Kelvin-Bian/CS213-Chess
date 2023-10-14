package chess;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;
import chess.ReturnPlay.Message;

public class Move {

    Position start;
    Position end;
    PieceType promoType;
    boolean resign;
    boolean draw;
    boolean whiteTurn;
    boolean invalid;

    public Move(String move, boolean whiteTurn){
        //return 5 variations of Move and null if string format not as expected
		//resign	 p1 to p2		p1 to p2 draw?		p1 to p2 P		p1 to p2 P draw?
		move = trimWhiteSpace(move);
		int len = move.length();
		if(len>=5){
			if(move.equals("resign"))   set(true, whiteTurn);
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
    }

    public void validMove(boolean whiteCheck, boolean blackCheck){
        ReturnPiece p = Chess.board.get(start);
        if(!invalid){
            if(!validPiece(p)) invalid = true; //is there a valid piece at starting position?
            if(!legalFunction.checkLegal(end, p, Chess.pieces)) invalid = true; //does move follow piece rules?
            if(whiteTurn && Check.whiteCheck(Chess.pieces)) invalid = true; //putting own side's king in check?
            if(!whiteTurn && Check.blackCheck(Chess.pieces)) invalid = true; 
            if(whiteTurn && whiteCheck && Check.whiteCheck(Chess.pieces)) invalid = true; //did not remove own side's king from check
            if(!whiteTurn && blackCheck && Check.blackCheck(Chess.pieces)) invalid = true;
        }
    }
    public void playMove(){
        ReturnPiece movePiece = Chess.board.remove(start);
        Chess.pieces.remove(movePiece);
        PieceUtility.movePiece(movePiece, end); //update rank and file in returnpiece
        if(Chess.board.get(end)!= null){ //if capturing, remove piece
            ReturnPiece captured = Chess.board.remove(end);
            Chess.pieces.remove(captured);
        }
        Chess.board.put(end, movePiece);
        Chess.pieces.add(movePiece);
        if(Chess.blackCheck && !Check.blackCheck(Chess.pieces)){
            Chess.blackCheck = false;
        }
        if(Chess.whiteCheck && !Check.whiteCheck(Chess.pieces))
            Chess.whiteCheck = false;
    }

    public ReturnPlay returnPlay(){
        ReturnPlay r = new ReturnPlay();
        if(invalid) 
            r.message = Message.ILLEGAL_MOVE;
        else{
            //carry out move
            playMove();
            if(draw)
                r.message = Message.DRAW;
            else if((whiteTurn && Check.blackCheck(Chess.pieces))){
                r.message = Message.CHECK;
                Chess.blackCheck = true;
            }
            else if(!whiteTurn && Check.whiteCheck(Chess.pieces)){
                r.message = Message.CHECK;
                Chess.whiteCheck = true;
            }
            else if(whiteTurn && Check.blackCheckmate(Chess.pieces))
                r.message = Message.CHECKMATE_WHITE_WINS;
            else if(!whiteTurn && Check.whiteCheckmate(Chess.pieces))
                r.message = Message.CHECKMATE_BLACK_WINS;
        }
        r.piecesOnBoard = Chess.pieces;
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
