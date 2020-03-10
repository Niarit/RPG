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
import javafx.scene.layout.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

import java.util.ArrayList;

public class Main extends Application {
    MapLoader mapLoader = new MapLoader(this);
    BorderPane borderPane = new BorderPane();
    public GameMap map;
    MediaPlayer mediaPlayer;
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
                500);
        contextInv = canvasInv.getGraphicsContext2D();


        mapLoader.loadMap("/map.txt", "/home/kalnaipeter/IdeaProjects/RPG/src/main/resources/Hootsforce.mp3");

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

        primaryStage.setTitle("Gloryhammer: Rise of the Chaos Wizard");
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
        int invCountX = 1;
        int invCountY = 0;
        int itemCounter = 0;
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
        while (invCountY < 3) {
            if (itemCounter < items.size()) {
                Tiles.getTileForItem(contextInv, items.get(itemCounter), invCountX, invCountY);
                itemCounter++;
            } else {
                Tiles.getTileForItem(contextInv, "empty", invCountX, invCountY);
            }
            if (invCountX < 3) {
                invCountX++;
            } else {
                invCountX = 0;
                invCountX++;
                invCountY++;
            }
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
        } else if(map.getPlayer().getX() == 17 && map.getPlayer().getY() == 18){
            LegendaryBattle();
        }
    }

    private void LegendaryBattle() {
        BackgroundFill myBG = new BackgroundFill(Color.BLACK, new CornerRadii(1), new Insets(0.0,0.0,0.0,0.0));
        Label LegendaryBattle =  new Label("After the stealth blow Zargotrax fell to the ground cursing." + "\n" +
                                                "The hero was about to finish him out, when the warlock managed to cast" + "\n" +
                                                "one last spell. The hammer landed on the ground, where the warlock was" + "\n" +
                                                "hitting no more the air in its way" + "\n"+ "\n" +
                                                "You maybe strong, but not strong enough mortal! Our battle is not finished!" +"\n"+"\n"+
                                                "It seems your journey is not over yet!");
        borderPane.setRight(null);
        borderPane.setCenter(LegendaryBattle);
        borderPane.setBackground(new Background(myBG));
        LegendaryBattle.setFont(Font.font("Manaspace", 30));
        LegendaryBattle.setTextFill(Color.WHITE);
    }

    private void MeetYourDoom() {
        mapLoader.getMediaPlayer().stop();
        String path = "/home/kalnaipeter/IdeaProjects/RPG/src/main/resources/Universe On Fire.mp3";
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        BackgroundFill myBG = new BackgroundFill(Color.BLACK, new CornerRadii(1), new Insets(0.0,0.0,0.0,0.0));

        Label MeetYourDoom = new Label("As you aproach the figure in front of you, the sudden realization"+ "\n" +
                                            "makes you dizzy. His eyes are burning with the flames of hatred as he looks at you"+ "\n" +
                                            "He was no other then the Warlock Zargotrax himself!" + "\n" +
                                            "You stand rooted in front of him for seconds, but when you made your first move" + "\n" +
                                            "the warlock disappears in thin air." + "\n" + "\n" +
                                            "'Foolish mortal! You can't get me this easily!'" + "\n" + "\n" +
                                            "It seems your journey is not over yet!");
        MeetYourDoom.setFont(Font.font("Manaspace", 30));
        borderPane.setCenter(MeetYourDoom);
        borderPane.setRight(null);
        borderPane.setBackground(new Background(myBG));
        MeetYourDoom.setTextFill(Color.WHITE);
    }

    private void gameOver() {
        mapLoader.getMediaPlayer().stop();
        BackgroundFill myBG = new BackgroundFill(Color.BLACK, new CornerRadii(1), new Insets(0.0,0.0,0.0,0.0));
        String path = "/home/kalnaipeter/IdeaProjects/RPG/src/main/resources/Magic Dragon.mp3";
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        Label GameOver = new Label("Game Over");
        GameOver.setFont(Font.font("Back to 1982", 100));
        borderPane.setRight(null);
        borderPane.setCenter(GameOver);
        borderPane.setBackground(new Background(myBG));
        GameOver.setTextFill(Color.WHITE);
    }
}
