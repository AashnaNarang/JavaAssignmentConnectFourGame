
import java.util.Observable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Aashna Narang
 */
public class ConnectFourGame extends Observable {

    private int nColumns;
    private int nRows;
    private int numToWin;
    private int nMarks;
    private ConnectFourEnum grid[][];
    private ConnectFourEnum gameState;
    private ConnectFourEnum turn;

    /**
     * 1 Argument constructor to start a connect four game with 8 rows,columns,
     * and you need 4 in a row to win. This calls the multi-arg constructor to
     * initialize the instance variables
     *
     * @param initialTurn which player goes first, red or black
     */
    public ConnectFourGame(ConnectFourEnum initialTurn) {
        this(8, 8, 4, initialTurn);
    }

    /**
     * Multi arg constructor to start a connect four game. User can customize
     * the game. This function calls the reset method to avoid repeated code.
     *
     * @param nRows number of rows on the board
     * @param nColumns number of columns on the board
     * @param numToWin number of checkers needed in a row to win
     * @param initialTurn which player goes first, red or black
     */
    public ConnectFourGame(int nRows, int nColumns, int numToWin, ConnectFourEnum initialTurn) {

        this.nColumns = nColumns;
        this.nRows = nRows;
        this.nMarks = 0;
        this.numToWin = numToWin;
        grid = new ConnectFourEnum[nRows][nColumns];
        reset(initialTurn);
    }

    /**
     * Resets the board, gameState is reset, and all of the buttons are set to
     * empty
     *
     * @param initialTurn player that starts the game now
     */
    public void reset(ConnectFourEnum initialTurn) {
        gameState = ConnectFourEnum.IN_PROGRESS;
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                grid[i][j] = ConnectFourEnum.EMPTY;
            }
        }
        this.turn = initialTurn;
    }

    /**
     * Getter method
     *
     * @return who's turn it currently is
     */
    public ConnectFourEnum getTurn() {
        return this.turn;
    }

    /**
     * Getter method
     *
     * @return the state the game is currently in, if in progress, there was a
     * draw or whichever player won
     */
    public ConnectFourEnum getGameState() {
        return this.gameState;
    }

    /**
     * This function takes a turn if the row/column chosen is valid then checks
     * if a player won or if there is a draw
     *
     * @param row The row of the space chosen
     * @param column the column of the space chosen
     * @return the current game state, either in progress, someone won or if
     * there was a draw
     */
    public ConnectFourEnum takeTurn(int row, int column) {

        //Checking if input is valid 
        if (column < 0 || column >= nColumns || row < 0 || row >= nRows) {
            throw new IllegalArgumentException("Invalid square");
        }

        //Check if game is in progress
        if (gameState != ConnectFourEnum.IN_PROGRESS) {
            throw new IllegalArgumentException("Game not in play.");
        }
        //check if there's a checker under 
        if (row != 0 && grid[row - 1][column] == ConnectFourEnum.EMPTY) {
            throw new IllegalArgumentException("Invalid Square");
        }

        if (grid[row][column] == ConnectFourEnum.EMPTY) {
            //place checker
            grid[row][column] = this.turn;
            nMarks++;
            //Check winner
            int countH = 0;
            int countV = 1;
            //check horizontal
            for (int i = 0; i < nColumns; i++) {
                if (grid[row][i] == turn) {
                    countH++;
                    if (countH == numToWin) {
                        gameState = turn;
                    }
                } else {
                    countH = 0;
                }
            }
            //check below checker
            for (int i = row - 1; i > - 1; i--) {
                if (grid[i][column] == turn) {
                    countV++;
                    if (countV == numToWin) {
                        gameState = turn;
                    }
                } else {
                    i = -1;
                }
            }
            if (countH == numToWin || countV == numToWin) {
                gameState = turn;
            }
            //check if there's a draw
            if (nMarks == nRows * nColumns) {
                gameState = ConnectFourEnum.DRAW;
            }
            //change turns
            if (this.turn == ConnectFourEnum.BLACK) {
                this.turn = ConnectFourEnum.RED;
            } else {
                this.turn = ConnectFourEnum.BLACK;
            }

        } else {
            throw new IllegalArgumentException("Invalid Square");
        }
        return gameState;
    }

    /**
     * Converts the game into a string
     *
     * @return
     */
    @Override
    public String toString() {
        String s = new String();
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                s += grid[i][j] + " | ";
            }
            s += "\n";
        }
        return s;
    }

}
