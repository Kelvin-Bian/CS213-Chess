package chess;

import chess.ReturnPiece.PieceFile;

public class Position {
    private int rank;
    private PieceFile file;

    public Position(int r, PieceFile pF){
        rank = r;
        file = pF;
    }

    public int r(){
        return rank;
    }

    public PieceFile f(){
        return file;
    }
    public boolean equals(Object o){
        if(o instanceof Position == false){
            return false;
        }
        else{
            Position p2 = (Position) o;
            return p2.r()==rank && p2.f() == file;
        }
    }
    public String toString(){
        return ""+file+rank;
    }
    public static int rankDiff(ReturnPiece p, Position end){
        //+ int means end position is above starting position
        //- int means end is below position
        return end.r() - p.pieceRank;
    }
    public static  Position oneSquareForward(ReturnPiece p){
        //forward means toward opp side of board (ex: white to black's side)
        //if white pawn, rank increases, square above     else rank decreases, square below
        int r = (PieceUtility.isWhite(p))? p.pieceRank+1: p.pieceRank-1;
        PieceFile f = p.pieceFile;
        return new Position(r, f);
    }
    public static  Position oneSquareForward(Position p, boolean isWhite){
        //forward means toward opp side of board (ex: white to black's side)
        //if white pawn, rank increases, square above     else rank decreases, square below
        int r = (isWhite)? p.r()+1: p.r()-1;
        PieceFile f = p.f();
        return new Position(r, f);
    }
    public static Position twoSqauresForward(ReturnPiece p){ //forward means toward opp side of board (ex: white to black's side)
        //if black pawn, rank decreases     else rank increases
        Position oneForward = oneSquareForward(p);
        return oneSquareForward(oneForward, PieceUtility.isWhite(p));
    }

    public static Position getPosition(ReturnPiece p){
        return new Position(p.pieceRank, p.pieceFile);
    }
    public static int whichDiag(Position base, Position p){
        //-1= not diagonal to cur square
        //1,2,3,4 correspond to left up, right up, right down, left down diagonal
        int rankDiff = p.r() - base.r();
        int fileDiff = p.f().ordinal() - base.f().ordinal();
        if(Math.abs(rankDiff) != Math.abs(fileDiff)) return -1;
        if(rankDiff > 0 && fileDiff <0) return 1;
        else if(rankDiff > 0 && fileDiff > 0 ) return 2;
        else if(rankDiff < 0 && fileDiff > 0) return 3;
        else return 4;
    }

    public static boolean btwnDiag(Position start, Position end, Position x){ 
        //true if x is in the diagnoal line btwn start and end, excludes start, includes end
        if(end == null || x.sameSquare(start)) return false; //not a valid move if did not move
        if(end.sameSquare(x)) return true;
        int startToXDir = whichDiag(start, x);
        int xToEndDir = whichDiag(x, end);
        return startToXDir != -1 && xToEndDir != -1 && startToXDir == xToEndDir;
    }

    public static int whichCardinalDir(ReturnPiece r, Position p){
        //-1 = not up down left or right
        //1,2,3,4  correspond to up, right, down, left
        Position base = getPosition(r);
        return whichCardinalDir(base, p);
    }
    public static int whichCardinalDir(Position base, Position p){
        //-1 = not up down left or right
        //1,2,3,4  correspond to up, right, down, left
        int rankDiff = p.r() - base.r();
        int fileDiff = p.f().ordinal() - base.f().ordinal();
        if(rankDiff !=0 && fileDiff != 0)    return -1;
        if(rankDiff>0) return 1;
        else if(rankDiff < 0) return 3;
        else if(fileDiff < 0) return 4;
        else return 2;
    }

    public static boolean withinLine(ReturnPiece r, Position farthest, Position x){ //within horizontal/vertical line
        if(sameSquare(r, x)) return false; //not a valid move if did not move
        if(farthest.sameSquare(x)) return true; //can move to farthest square possible
        int startToXDir = whichCardinalDir(r, x); //if in btwn start and farthest, check to make sure within line from start to farthest
        int xToEndDir = whichCardinalDir(x, farthest);
        return startToXDir != -1 && xToEndDir != -1 && startToXDir == xToEndDir;
    }
    public static Position up(ReturnPiece p){ //no squares above if rank 8
        int r = p.pieceRank;
        if(r == 8) return null;
        return new Position(r+1, p.pieceFile);
    }
    public static Position down(ReturnPiece p){ //no squares below if rank 1
        int r = p.pieceRank;
        if(r == 1) return null;
        return new Position(r-1, p.pieceFile);
    }
    public static Position left(ReturnPiece p){ //no squares to left if file a
        PieceFile f = p.pieceFile;
        if(f == PieceFile.a) return null;
        return new Position(p.pieceRank, PieceFile.values()[f.ordinal()-1]);
    }
    public static Position right(ReturnPiece p){ //no squares to right if file h
        PieceFile f = p.pieceFile;
        if(f == PieceFile.h) return null;
        return new Position(p.pieceRank, PieceFile.values()[f.ordinal()+1]);
    }

    public Position leftUpDiag(){
        PieceFile f = (file != PieceFile.a)? PieceFile.values()[file.ordinal()-1]: null;
        int r = (rank!=8)? rank+1: -1;
        return (f== null|| r==-1)? null : new Position(r, f);
    }
    public Position rightUpDiag(){
        //squares in file h and rank 8 have no right up diag
        PieceFile f = (file != PieceFile.h)? PieceFile.values()[file.ordinal()+1]: null;
        int r = (rank!=8)? rank-1: -1;
        return (f== null|| r==-1)? null : new Position(r, f);
    }
    public Position leftDownDiag(){
        //squares in file a and rank 1 have no left down diag
        PieceFile f = (file != PieceFile.a)? PieceFile.values()[file.ordinal()-1]: null;
        int r = (rank!=1)? rank-1: -1;
        return (f== null|| r==-1)? null : new Position(r, f);
    }
    public Position rightDownDiag(){
        //squares in file h and rank 1 have no left down diag
        PieceFile f = (file != PieceFile.h)? PieceFile.values()[file.ordinal()+1]: null;
        int r = (rank!=1)? rank-1: -1;
        return (f== null|| r==-1)? null : new Position(r, f);
    }
    public boolean sameSquare(ReturnPiece p){
        return p.pieceFile==file && p.pieceRank==rank;
    }
    public static boolean sameSquare(ReturnPiece a, Position x){
        return a.pieceFile == x.f() && a.pieceRank == x.r();
    }
    public boolean sameSquare(Position x){
        return file == x.f() && rank == x.r();
    }
    public  Position up(){ //no squares above if rank 8
        if(rank == 8) return null;
        return new Position(rank+1, file);
    }
    public  Position down(){ //no squares below if rank 1
        if(rank == 1) return null;
        return new Position(rank-1, file);
    }
    public  Position left(){ //no squares to left if file a

        if(file == PieceFile.a) return null;
        return new Position(rank, PieceFile.values()[file.ordinal()-1]);
    }
    public  Position right(){ //no squares to right if file h

        if(file == PieceFile.h) return null;
        return new Position(rank, PieceFile.values()[file.ordinal()+1]);
    }
}
