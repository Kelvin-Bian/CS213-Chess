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
        if(x.sameSquare(start)) return false; //not a valid move if did not move
        int startToXDir = whichDiag(start, x);
        int xToEndDir = whichDiag(x, end);
        return startToXDir == xToEndDir;
    }

    public Position leftUpDiag(){
        PieceFile f = (file != PieceFile.a)? PieceFile.values()[file.ordinal()-1]: null;
        int r = (rank!=8)? rank+1: -1;
        return (f== null)? null : new Position(r, f);
    }
    public Position rightUpDiag(){
        //squares in file h and rank 8 have no right up diag
        PieceFile f = (file != PieceFile.h)? PieceFile.values()[file.ordinal()+1]: null;
        int r = (rank!=8)? rank-1: -1;
        return (f== null)? null : new Position(r, f);
    }
    public Position leftDownDiag(){
        //squares in file a and rank 1 have no left down diag
        PieceFile f = (file != PieceFile.a)? PieceFile.values()[file.ordinal()-1]: null;
        int r = (rank!=1)? rank-1: -1;
        return (f== null)? null : new Position(r, f);
    }
    public Position rightDownDiag(){
        //squares in file h and rank 1 have no left down diag
        PieceFile f = (file != PieceFile.h)? PieceFile.values()[file.ordinal()+1]: null;
        int r = (rank!=1)? rank-1: -1;
        return (f== null)? null : new Position(r, f);
    }
    public boolean sameSquare(ReturnPiece p){
        return p.pieceFile==file && p.pieceRank==rank;
    }

    public boolean sameSquare(Position x){
        return file == x.f() && rank == x.r();
    }
}
