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
    public static Position getPosition(ReturnPiece p){
        return new Position(p.pieceRank, p.pieceFile);
    }
    public static int whichDiag(Position base, Position p){
        //-1= not diagonal to cur square
        //1,2,3,4 correspond to left up, right up, right down, left down diagonal
        int rankDiff = p.r() - base.r();
        int fileDiff = p.f().ordinal() - base.f().ordinal();
        if(rankDiff != fileDiff)    return -1;
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
        return startToXDir == xToEndDir;
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

    public static boolean withinLine(ReturnPiece r, Position farthest, Position x){
        if(sameSquare(r, x)) return false; //not a valid move if did not move
        if(farthest.sameSquare(x)) return true;
        int startToXDir = whichCardinalDir(r, x);
        int xToEndDir = whichCardinalDir(x, farthest);
        return startToXDir == xToEndDir;
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
}
