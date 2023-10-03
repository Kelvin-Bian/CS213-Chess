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

    public Position leftDiag(){
        PieceFile f = (file != PieceFile.a)? PieceFile.values()[file.ordinal()-1]: null;
        int r = (rank!=8)? rank-1: -1;
        return (f== null)? null : new Position(r, f);
    }
    public Position rightDiag(){

    }
}
