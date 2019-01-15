
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Aashna Narang
 */
public class ConnectFourApplication extends Application implements Observer {

    private static final int NUM_COLUMNS = 8;
    private static final int NUM_ROWS = 8;
    private static final int NUM_TO_WIN = 4;
    private ConnectFourGame gameEngine;
    private ConnectButton buttons[][];
    private ConnectMove move;
    private Random picker;

    /**
     * Creates the gui for game, initializes buttons, creates a text field to
     * show who's turn it is, and create a take turn button that confirms which
     * space the user would like to drop their checker at. An event handler is
     * created for each button. If take turn button is clicked, the model's take
     * turn function is called, the observers were notified and buttons are
     * updated.
     *
     * @param primaryStage window that displays the scene with all of the panes
     * with all of the panes.
     */
    @Override
    public void start(Stage primaryStage) {

        buttons = new ConnectButton[NUM_ROWS][NUM_COLUMNS];
        //randomly pick who goes first
        picker = new Random();
        if (picker.nextInt(1) == 0) {
            gameEngine = new ConnectFourGame(NUM_ROWS, NUM_COLUMNS, NUM_TO_WIN, ConnectFourEnum.RED);
        } else {
            gameEngine = new ConnectFourGame(NUM_ROWS, NUM_COLUMNS, NUM_TO_WIN, ConnectFourEnum.BLACK);
        }
        gameEngine.addObserver(this);
        //set up gui
        BorderPane root = new BorderPane();
        Pane topBranch = new HBox();
        Scene scene = new Scene(root, 510, 380);
        GridPane buttonBranch = new GridPane();

        //create textfield for showing whose turn it is
        TextField tellTurn = new TextField();
        tellTurn.setEditable(false);
        tellTurn.setText(gameEngine.getTurn() + " starts!");
        tellTurn.setMinWidth(510);
        topBranch.getChildren().add(tellTurn);
        root.setTop(topBranch);
        //create all buttons/checker spaces 
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLUMNS; j++) {
                buttons[i][j] = new ConnectButton(ConnectFourEnum.EMPTY.toString(), i, j);
                buttons[i][j].setMinHeight(40);
                buttons[i][j].setMaxWidth(Double.MAX_VALUE);
                buttons[i][j].setOnAction(new ButtonHandler());
                buttonBranch.add(buttons[i][j], j, NUM_ROWS - 1 - i);
            }
        }
        root.setCenter(buttonBranch);
        //create take turn button
        Pane botBranch = new HBox();
        Button takeTurnBtn = new Button();
        takeTurnBtn.setText("Take My Turn");
        takeTurnBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //take turn
                gameEngine.takeTurn(move.getRow(), move.getColumn());
                tellTurn.setText("It's " + gameEngine.getTurn() + "'s turn");
                //Only use commented code if making anonymous class for observer
                //gameEngine.hasChanged();
                //gameEngine.notifyObservers();
                //obs.update(gameEngine, this);
                //check if game over
                if (gameEngine.getGameState() == ConnectFourEnum.RED || gameEngine.getGameState() == ConnectFourEnum.BLACK) {
                    tellTurn.setText(gameEngine.getGameState().toString() + " wins!");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, gameEngine.getGameState().toString() + " wins!");
                    alert.setHeaderText("Game Over");
                    alert.setTitle("Information Alert");
                    alert.showAndWait();
                    //reset board
                    gameEngine.reset(gameEngine.getTurn());
                    for (int i = 0; i < NUM_ROWS; i++) {
                        for (int j = 0; j < NUM_COLUMNS; j++) {
                            buttons[i][j].setDisable(false);
                            buttons[i][j].setText(ConnectFourEnum.EMPTY.toString());
                        }
                    }
                    tellTurn.setText(gameEngine.getTurn() + " starts!");
                }
                //change textfield if draw occurs
                if (gameEngine.getGameState() == ConnectFourEnum.DRAW) {
                    tellTurn.setText("It was a draw, no one wins");
                }

            }
        });
        botBranch.getChildren().add(takeTurnBtn);
        root.setBottom(botBranch);

        primaryStage.setTitle("Connect Four");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Every time a checker button is clicked, make instance variable move so
     * you can access the row and column of that button in the take turn button
     * handler.
     */
    class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            ConnectButton button = (ConnectButton) event.getSource();
            move = new ConnectMove(button.getRow(), button.getColumn(), gameEngine.getTurn());
        }

    }

    //Update the GUI --> but updated the text field in start because it can't
    //be accessed here 
    @Override
    public void update(Observable obs, Object arg) {
        buttons[move.getRow()][move.getColumn()].setText(move.getColour().toString());
        buttons[move.getRow()][move.getColumn()].setDisable(true);
    }

}
