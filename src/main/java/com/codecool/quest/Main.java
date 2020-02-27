package com.codecool.quest;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.logic.MapLoader;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    MapLoader mapLoader = new MapLoader(this);
    BorderPane borderPane = new BorderPane();
    public GameMap map;
    Canvas canvasMain;
    public static ArrayList<String> items = new ArrayList<>();
    Canvas canvasInv;
    GraphicsContext contextMain;
    GraphicsContext contextInv;
    Label healthLabel = new Label();
    Label damageLabel = new Label();
    Label weaponLabel = new Label();
    Label armorLabel = new Label();
    Label emptyLabel = new Label();

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();

        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
        ui.add(new Label("Damage:"), 0, 1);
        ui.add(damageLabel, 1, 1);
        ui.add(new Label(""),0,2);
        ui.add(emptyLabel,1,2);
        ui.add(new Label("Number of weapons: "),0,3);
        ui.add(weaponLabel,1,3);
        ui.add(new Label("Number of armors: "),0,4);
        ui.add(armorLabel,1,4);
        canvasInv = new Canvas(
                200,
                200);
        contextInv = canvasInv.getGraphicsContext2D();


        mapLoader.loadMap("/map.txt");
        canvasMain = new Canvas(
                map.getWidth() * Tiles.TILE_WIDTH,
                map.getHeight() * Tiles.TILE_WIDTH);
        contextMain = canvasMain.getGraphicsContext2D();
        refresh();


        VBox vbox = new VBox(ui,canvasInv);
        borderPane.setCenter(canvasMain);
        borderPane.setRight(vbox);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Codecool Quest");
        primaryStage.show();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1, 0);
                refresh();
                break;
        }
    }

    public void refresh() {
        int invCount = 0;
        contextMain.setFill(Color.BLACK);
        contextMain.fillRect(0, 0, canvasMain.getWidth(), canvasMain.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(contextMain, cell.getActor(), x, y);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(contextMain, cell.getItem(), x, y);
                } else {
                    Tiles.drawTile(contextMain, cell, x, y);
                }
            }
        }
        for (String word: items){
            Tiles.getTileForItem(contextInv, word, 2, invCount);
            invCount++;
        }

        healthLabel.setText("" + map.getPlayer().getHealth());
        damageLabel.setText("" + map.getPlayer().getDamage());
        weaponLabel.setText(""+ (map.getPlayer().getDamage()/5 -1));
        if (map.getPlayer().getHealth()/5-1>=0) {
            armorLabel.setText("" + (map.getPlayer().getHealth() / 5 - 1));
        }
        if (map.getPlayer().getHealth() <= 0) {
            gameOver();
        } else if (map.getPlayer().getX() == 17 && map.getPlayer().getY() == 17){
            MeetYourDoom();
        }
    }

    private void MeetYourDoom() {
        Label MeetYourDoom = new Label("As you aproach the figure in front of you, the sudden realization"+ "\n" +
                                            "makes you dizzy. The eye of him burning with the flames of hatred as he looks at you"+ "\n" +
                                            "He was no other then the Warlock Zalgotrax himself!" + "\n" +
                                            "You stand rooted in front of him for seconds, but when you made your first move" + "\n" +
                                            "the warlock disappeared in thin air." + "\n" + "\n" +
                                            "'Foolish Angus! You can't get me this easily!'");
        MeetYourDoom.setFont(Font.font("Manaspace", 30));
        borderPane.setCenter(MeetYourDoom);
        borderPane.setRight(null);
    }

    private void gameOver() {
        Label GameOver = new Label("Game Over");
        GameOver.setFont(Font.font("Back to 1982", 100));
        borderPane.setRight(null);
        borderPane.setCenter(GameOver);

    }
}
