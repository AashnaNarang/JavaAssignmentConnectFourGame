/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aashna Narang
 */
public class ConnectMove {

    //Instance Variables
    private int row;
    private int column;
    private ConnectFourEnum colour;

    /**
     * Constructor. This will be used in the application to access the row and
     * column of the button that is currently being clicked
     *
     * @param row row the button is in
     * @param column column the button is in
     * @param colour who's turn is it (and what colour the button will turn if
     * move was legal)
     */
    public ConnectMove(int row, int column, ConnectFourEnum colour) {
        this.row = row;
        this.column = column;
        this.colour = colour;
    }

    /**
     * Getter method
     *
     * @return row the button is in
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Getter method
     *
     * @return column the button is in
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Whose turn it is/ what colour the button will turn if move was legal.
     *
     * @return
     */
    public ConnectFourEnum getColour() {
        return this.colour;
    }
}
