package TicTacToe.Model;

import TicTacToe.Pair;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public int size;

    public PlayingPiece[][] board;

    public Board(int size) {
        this.size = size;
        board = new PlayingPiece[size][size];
    }

    public List<Pair<Integer, Integer>> freeSpacesInBoard() {
        List<Pair<Integer, Integer>> freeCells = new ArrayList<>();

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(board[i][j] == null) {
                    Pair<Integer, Integer> pair = new Pair<>(i, j);
                    freeCells.add(pair);
                }
            }
        }
        return freeCells;
    }

    public boolean addPiece(int row, int col, PlayingPiece playingPiece) {
        if(row >= board.length || col >= board.length) {
            return false;
        }
        if(board[row][col] != null) {
            return false;
        }
        board[row][col] = playingPiece;
        System.out.print("added: " + playingPiece);
        return true;
    }

    public void printBoard() {
        System.out.println();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j].pieceType + "   ");
                } else {
                    System.out.print("    ");
                }
                System.out.print(" | ");
            }
            System.out.println();
        }
    }
}
