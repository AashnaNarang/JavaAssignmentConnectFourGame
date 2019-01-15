
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Aashna Narang
 */
public class ConnectFourTestClient {

    /**
     * Main method that tests out the connect four game without a GUI. A game
     * object was instantiated, and while the game state is in progress, turns
     * are taken and a string of the board is outputted.
     *
     * @param args
     */
    public static void main(String[] args) {
        ConnectFourGame game = new ConnectFourGame(ConnectFourEnum.BLACK);
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(game.toString());
            System.out.println(game.getTurn()
                    + ": Where do you want to mark? Enter row column");
            int row = scanner.nextInt() - 1;
            int column = scanner.nextInt() - 1;
            scanner.nextLine();
            game.takeTurn(row, column);

        } while (game.getGameState() == ConnectFourEnum.IN_PROGRESS);
        System.out.println(game.getGameState());
    }
}
