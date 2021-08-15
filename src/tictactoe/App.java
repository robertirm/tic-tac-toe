package tictactoe;

import javafx.application.Application;
import javafx.stage.Stage;
import tictactoe.logic.GameLogic;
import tictactoe.ui.Controller;
import tictactoe.ui.UserInterface;

public class App extends Application {
    public UserInterface ui;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        ui = new UserInterface(stage);
        GameLogic gameLogic = new GameLogic();
        Controller controller = new Controller(ui,gameLogic);
        ui.setController(controller);
        stage.show();
    }
}
