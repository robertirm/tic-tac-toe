package tictactoe.domain;

import tictactoe.constants.GameTurn;
import tictactoe.constants.GameValues;

public class TicTacToeGame {
    private GameTurn gameTurn;
    private final GameValues[][] board;

    public TicTacToeGame(GameTurn gameTurn) {
        this.gameTurn = gameTurn;
        board = new GameValues[3][3];
        for (int xIndex = 0; xIndex < 3; xIndex++) {
            for (int yIndex = 0; yIndex < 3; yIndex++) {
                board[xIndex][yIndex] = GameValues.EMPTY;
            }
        }
    }

    public GameTurn getTurn() {
        return gameTurn;
    }

    public GameValues[][] getBoard() {
        return board;
    }

    public void switchStates(){
        if(gameTurn == GameTurn.X_TURN)
            gameTurn = GameTurn.O_TURN;
        else
            gameTurn = GameTurn.X_TURN;
    }

    public void setValueOnBoard(int x, int y, GameValues value){
        if (!(x < 0 || x > 3 || y < 0 || y > 3))
            board[x][y] = value;
    }
}
