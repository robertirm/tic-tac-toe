package tictactoe.ui;

import tictactoe.constants.GameStatus;
import tictactoe.constants.GameTurn;
import tictactoe.constants.GameValues;
import tictactoe.domain.TicTacToeGame;
import tictactoe.logic.GameLogic;

public class Controller {
    private TicTacToeGame game;
    private final GameLogic gameLogic;

    public Controller(UserInterface ui,GameLogic gameLogic) {
        this.game = new TicTacToeGame(GameTurn.X_TURN);
        this.gameLogic = gameLogic;
    }

    public void switchPlayers(){
        game.switchStates();
    }

    public GameValues handleNewValue(int x, int y){
        GameTurn currentTurn = game.getTurn();

        if(currentTurn == GameTurn.O_TURN){
            game.setValueOnBoard(x,y,GameValues.NOUGHT);
            return GameValues.NOUGHT;
        }
        else{
            game.setValueOnBoard(x,y,GameValues.CROSS);
            return GameValues.CROSS;
        }
    }

    public GameStatus checkCompletion(){
        if(gameLogic.checkForCompletion(game.getBoard())){
            if(game.getTurn() == GameTurn.X_TURN)
                return GameStatus.WIN_BY_X;
            else
                return GameStatus.WIN_BY_O;
        }
        else if (gameLogic.checkFullness(game.getBoard())){
            return GameStatus.DRAW;
        }
        return GameStatus.ACTIVE;
    }

    public void reset() {
        this.game = new TicTacToeGame(GameTurn.X_TURN);
    }
}
