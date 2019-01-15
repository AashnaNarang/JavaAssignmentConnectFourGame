/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.scene.control.Button;

/**
 *
 * @author Aashna Narang
 */
public class ConnectButton extends Button {

    //Instance Variables
    private int row;
    private int column;

    /**
     * Constructor to create a connect button object. These will be used in the
     * application and help create a database of buttons, each with its own
     * unique row and column value.
     *
     * @param label The text on the button --> either red, black or empty
     * @param row The row the button is in
     * @param column The column the button is in
     */
    public ConnectButton(String label, int row, int column) {
        super(label);
        this.row = row;
        this.column = column;

    }

    /**
     * Getter method
     *
     * @return the row the button is in
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Getter Method
     *
     * @return the column the button is in
     */
    public int getColumn() {
        return this.column;
    }

    /**
     *
     * @return a string value of the coordinates of the button (the row and
     * column).
     */
    @Override
    public String toString() {
        return "(" + row + ", " + column + ")";
    }

}
