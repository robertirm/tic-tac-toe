package tictactoe.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import tictactoe.constants.GameStatus;
import tictactoe.constants.GameValues;
import tictactoe.domain.Coordinates;

import java.util.HashMap;

public class UserInterface {
    private final BorderPane layout;
    private final Stage stage;

    private Controller controller;
    private final HashMap<Coordinates, TileButton> tilesCoordinates;

    public UserInterface(Stage stage) {
        this.stage = stage;
        this.layout = new BorderPane();
        tilesCoordinates = new HashMap<>();
        initUserInterface();
    }

    public void setController(Controller ctrl){
        this.controller = ctrl;
    }

    private void initUserInterface(){
        stage.setTitle("Tic Tac Toe");
        drawBackground();
        drawBoard();
    }

    private void drawBackground() {
        Scene scene = new Scene(layout, 400, 400);
        layout.setStyle("-fx-background-color: #CCCCCC");
        stage.setScene(scene);
    }

    private void drawBoard(){
        GridPane boardLayout = new GridPane();

        boardLayout.setPadding(new Insets(3,3,3,3));
        boardLayout.setAlignment(Pos.CENTER);
        boardLayout.setMaxSize(300,300);
        boardLayout.setMinSize(300,300);
        boardLayout.setHgap(3);
        boardLayout.setVgap(3);

        layout.setCenter(boardLayout);

        for (int xIndex = 0; xIndex < 3; xIndex++) {
            for (int yIndex = 0; yIndex < 3; yIndex++) {
                TileButton tile = new TileButton(xIndex,yIndex);
                styleTile(tile);

                tile.setOnAction(e-> handleTileClicked(tile));

                tilesCoordinates.put(new Coordinates(xIndex,yIndex), tile);

                GridPane.setConstraints(tile, yIndex, xIndex);
                boardLayout.getChildren().add(tile);
            }
        }
    }

    private void styleTile(TileButton tile){
        tile.setPrefSize(100,100);
        tile.setStyle(
                "-fx-background-color: #14A098;" +
                "-fx-font-size:40;" +
                "-fx-text-alignment: center;" +
                "-fx-text-fill: #CCCCCC;"
        );
    }

    private void handleTileClicked(TileButton tile){
        tile.setDisable(true);
        tile.setOpacity(1);

        GameValues input = controller.handleNewValue(tile.getX(),tile.getY());
        if (input == GameValues.CROSS)tile.setText("X");
        else tile.setText("O");

        GameStatus status = controller.checkCompletion();

        if(status != GameStatus.ACTIVE){
            switch (status) {
                case DRAW:
                    showDialog("The game resulted in a draw.");
                    break;
                case WIN_BY_X:
                    showDialog("X won! Congratulations!");
                    break;
                case WIN_BY_O:
                    showDialog("O won! Congratulations!");
                    break;
            }
            resetGame();
        }

        controller.switchPlayers();
    }

    private void showDialog(String message){
        Alert dialog = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        dialog.showAndWait();
    }

    private void resetGame(){
        for (int xIndex = 0; xIndex < 3; xIndex++) {
            for (int yIndex = 0; yIndex < 3; yIndex++) {
                TileButton tile = tilesCoordinates.get(new Coordinates(xIndex,yIndex));
                tile.setText("");
                tile.setDisable(false);
            }
        }
        controller.reset();
    }
}
