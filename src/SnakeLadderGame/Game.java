package SnakeLadderGame;

import SnakeLadderGame.Model.Board;
import SnakeLadderGame.Model.Cell;
import SnakeLadderGame.Model.Dice;
import SnakeLadderGame.Model.Player;

import java.util.Deque;
import java.util.LinkedList;

public class Game {
    private Board board;
    private Dice dice;
    private final Deque<Player> playerList = new LinkedList<>();
    private Player winner;

    public Game() {
        initializeGame();
    }

    public void initializeGame() {
        board = new Board(10, 5, 4);
        dice = new Dice(1);
        winner = null;
        addPlayers();
    }

    private void addPlayers() {
        Player player1 = new Player("p1", 0);
        Player player2 = new Player("p2", 0);

        playerList.add(player1);
        playerList.add(player2);
    }

    public void startGame() {

        while(winner == null) {
            // check whose turn
            Player playerTurn = findPlayerTurn();
            System.out.println("Player turn is: " + playerTurn.id + " current Position is: " + playerTurn.currPosition);

            // roll the Dice
            int diceNumbers = dice.rollDice();

            // get the new position
            int playerNewPos = playerTurn.currPosition + diceNumbers;
            playerNewPos = jumpCheck(playerNewPos);
            playerTurn.currPosition = playerNewPos;

            System.out.println("Player turn is: " + playerTurn.id + " new Position is: " + playerTurn.currPosition);
            if(playerNewPos >= board.cells.length*board.cells.length-1) {
                winner = playerTurn;
            }
        }

        System.out.println("WINNER IS: " + winner.id);
    }

    private Player findPlayerTurn() {
        Player player = playerList.removeFirst();
        playerList.addLast(player);
        return player;
    }

    private int jumpCheck(int playerNewPosition) {
        if(playerNewPosition > board.cells.length*board.cells.length-1) {
            return playerNewPosition;
        }

        Cell cell = board.getCell(playerNewPosition);
        if(cell.jump != null && cell.jump.start == playerNewPosition) {
            return cell.jump.end;
        }
        return playerNewPosition;
    }
}
