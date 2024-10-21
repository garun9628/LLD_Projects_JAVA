package TicTacToe;

import TicTacToe.Model.*;

import java.security.KeyPair;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Game {
    // Game has list of players
    // Game has board
    Deque<Player> playerList;
    Board gameBoard;

    public Game() {
        initializeGame();
    }

    public void initializeGame() {
        // initialize board
        gameBoard = new Board(3);

        playerList = new LinkedList<>();
        // create 2 players
        PlayingPieceX playingPieceX = new PlayingPieceX();
        Player player1 = new Player("Player1", playingPieceX);
        PlayingPieceO playingPieceO = new PlayingPieceO();
        Player player2 = new Player("Player2", playingPieceO);

        playerList.add(player1);
        playerList.add(player2);
    }

    public String startGame() {
        boolean flag = true;
        while(flag) {
            Player currPlayer = playerList.removeFirst();
            // check if board has empty cell or not
            gameBoard.printBoard();
            List<Pair<Integer, Integer>> freeSpaces = gameBoard.freeSpacesInBoard(gameBoard);
            if(freeSpaces.isEmpty()) {
                flag = false;
                break;
            }

            // read the currentPlayer input
            System.out.print("Player " + currPlayer.name + " Enter row, col: ");
            Scanner inputScanner = new Scanner(System.in);
            String s = inputScanner.nextLine();
            String[] values = s.split(",");
            int inputRow = Integer.parseInt(values[0]);
            int inputCol = Integer.parseInt(values[1]);

            // place the current player piece
            boolean pieceAddedSuccessfully = gameBoard.addPiece(inputRow, inputCol, currPlayer.playerPiece);
            if(!pieceAddedSuccessfully) {
                System.out.println("Incorrect position, try again");
                playerList.addFirst(currPlayer);
                continue;
            }

            playerList.addLast(currPlayer);
            // after placing the piece check if current player is the winner or not
            boolean isWinner = isPlayerWinner(inputRow, inputCol, currPlayer.playerPiece.pieceType);
            if(isWinner) {
                return currPlayer.name;
            }
        }
        return "Tie";
    }

    public boolean isPlayerWinner(int row, int col, PieceType pieceType) {
        boolean rowMatch = true;
        boolean colMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        // row Match check
        for(int i = 0; i < gameBoard.size; i++) {
            if(gameBoard.board[row][i] == null || gameBoard.board[row][i].pieceType != pieceType) {
                rowMatch = false;
                break;
            }
        }

        // col match check
        for(int i = 0; i < gameBoard.size; i++) {
            if(gameBoard.board[i][col] == null || gameBoard.board[i][col].pieceType != pieceType) {
                colMatch = false;
                break;
            }
        }

        // diagonal match check
        for(int i = 0, j = 0; i < gameBoard.size; i++, j++) {
            if(gameBoard.board[i][j] == null || gameBoard.board[i][j].pieceType != pieceType) {
                diagonalMatch = false;
                break;
            }
        }

        // anti diagonal match check
        for(int i = 0, j = gameBoard.size-1; i < gameBoard.size; i++, j--) {
            if(gameBoard.board[i][j] == null || gameBoard.board[i][j].pieceType != pieceType) {
                antiDiagonalMatch = false;
                break;
            }
        }
        return rowMatch || colMatch || diagonalMatch || antiDiagonalMatch;
    }
}
