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
}
