package tictactoe.logic;

import tictactoe.constants.GameValues;

public class GameLogic {
    public boolean checkForCompletion(GameValues[][] board){
        return checkRows(board) || checkColumns(board) || checkDiagonals(board);
    }

    private boolean checkRows(GameValues[][] board){
        for (int xIndex = 0; xIndex < 3; xIndex++) {
            if ( board[xIndex][0] == board[xIndex][1] && board[xIndex][1] == board[xIndex][2] && board[xIndex][0] != GameValues.EMPTY)
                return true;
        }
        return false;
    }

    private boolean checkColumns(GameValues[][] board){
        for (int yIndex = 0; yIndex < 3; yIndex++) {
            if ( board[0][yIndex] == board[1][yIndex] && board[1][yIndex] == board[2][yIndex] && board[0][yIndex] != GameValues.EMPTY)
                return true;
        }
        return false;
    }

    private boolean checkDiagonals(GameValues[][] board){
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != GameValues.EMPTY) return true;
        return board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != GameValues.EMPTY;
    }

    public boolean checkFullness(GameValues[][] board){
        for (int xIndex = 0; xIndex < 3; xIndex++) {
            for (int yIndex = 0; yIndex < 3; yIndex++) {
                if (board[xIndex][yIndex] == GameValues.EMPTY)
                    return false;
            }
        }
        return true;
    }
}
