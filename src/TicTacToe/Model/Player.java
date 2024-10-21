package TicTacToe.Model;

public class Player {
    public String name;
    public PlayingPiece playerPiece;

    public Player(String name, PlayingPiece playerPiece) {
        this.name = name;
        this.playerPiece = playerPiece;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayingPiece getPlayerPiece() {
        return playerPiece;
    }

    public void setPlayerPiece(PlayingPiece playerPiece) {
        this.playerPiece = playerPiece;
    }
}
