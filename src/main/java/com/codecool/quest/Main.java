package com.codecool.quest;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.logic.MapLoader;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
import java.util.List;
import java.util.Random;

import java.util.ArrayList;

public class Main extends Application {

    public MapLoader mapLoader = new MapLoader(this);
    public BorderPane borderPane = new BorderPane();
    String playerName;

    public GameMap map;
    public MediaPlayer mediaPlayer;
    public Canvas canvasMain;
    public static ArrayList<String> items = new ArrayList<>();
    public Canvas canvasInv;
    public Canvas canvasDialogue;
    public GraphicsContext contextMain;
    public GraphicsContext contextInv;
    public GraphicsContext contextDialogue;
    public Label healthLabel = new Label();
    public Label damageLabel = new Label();
    public Label DialogueLabel = new Label();
    public Label playerNameLabel = new Label();
    public int[][] possibleMovements = {{0,-1},{0,1},{-1,0},{1,0}};
    public Random randomChance = new Random();



    public static void main(String[] args) {
        launch(args);
    }


    public static int[] getRandomDirection(int[][] list){
        Random random = new Random();
        return list[random.nextInt(4)];
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane ui = new GridPane();

        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));
        Label healthText = new Label("Health: ");
        healthText.setTextFill(Color.WHITE);
        Label damageText = new Label("Damage: ");
        damageText.setTextFill(Color.WHITE);
        damageText.setFont(Font.font("Manaspace", 20));
        healthText.setFont(Font.font("Manaspace", 20));


        ui.add(healthText, 0, 2);
        ui.add(healthLabel, 1, 2);
        ui.add(damageText, 0, 3);
        ui.add(damageLabel, 1, 3);

        playerNameLabel.setStyle("-fx-font-weight: bold");
        ui.add(playerNameLabel,0,0);
//         ui.add(new Label("Health: "), 0, 2);
//         ui.add(healthLabel, 1, 2);
//         ui.add(new Label("Damage:"), 0, 3);
//         ui.add(damageLabel, 1, 3);

        Label inventoryLabel = new Label("Inventory: ");
        inventoryLabel.setFont(Font.font("Manaspace", 20));
        inventoryLabel.setTextFill(Color.WHITE);
        inventoryLabel.setStyle("-fx-font-weight: bold");
        ui.add(inventoryLabel,0,5);

        canvasInv = new Canvas(
                200,
                500);
        contextInv = canvasInv.getGraphicsContext2D();

        canvasDialogue = new Canvas(
                50,
                100);
        contextDialogue = canvasDialogue.getGraphicsContext2D();

        mapLoader.loadMap("/map.txt", System.getProperty("user.dir") + "/src/main/resources/Hootsforce.mp3");

        canvasMain = new Canvas(
                map.getWidth() * Tiles.TILE_WIDTH,
                map.getHeight() * Tiles.TILE_WIDTH);
        contextMain = canvasMain.getGraphicsContext2D();
        refresh();

        VBox vbox = new VBox(ui,canvasInv);
        borderPane.setCenter(canvasMain);
        borderPane.setRight(vbox);

         HBox Dialogue = new HBox(canvasDialogue);
         borderPane.setCenter(canvasMain);
         borderPane.setBottom(Dialogue);
         BackgroundFill myBG = new BackgroundFill(Color.BLACK, new CornerRadii(1), new Insets(0.0,0.0,0.0,0.0));
         borderPane.setBackground(new Background(myBG));



        //name inputwindow
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);


        @FXML
        final TextField name = new TextField();
        Label titleLabel = new Label("Enter a player name");
        GridPane.setConstraints(titleLabel,0,0);
        grid.getChildren().add(titleLabel);
        name.setPromptText("Name... ");
        name.setPrefColumnCount(10);
        name.getText();
        GridPane.setConstraints(name, 0, 1);
        grid.getChildren().add(name);
        Button submit = new Button("Submit");
        GridPane.setConstraints(submit, 1, 1);
        grid.getChildren().add(submit);

        showWindow(grid,primaryStage);
        submit.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            playerName = name.getText();
            showWindow(borderPane,primaryStage);
        });
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                monsterMove();
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                monsterMove();
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                monsterMove();
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1, 0);
                monsterMove();
                refresh();
                break;
            case SPACE:
                mapLoader.loadMap("/placeholder.txt", System.getProperty("user.dir") + "/src/main/resources/Hootsforce.mp3");
        }
    }
    public void monsterMove() {
        if(randomChance.nextInt(100) <= 20) {
            for (int i = 0; i < map.getAllSkeletons().size(); i++) {
                if(map.getAllSkeletons().get(i).getHealth() <= 0){
                    map.getAllSkeletons().get(i).setHealth(0);
                    map.getAllSkeletons().remove(i);
                }else{
                    map.getAllSkeletons().get(i).move(getRandomDirection(possibleMovements)[0],getRandomDirection(possibleMovements)[1]);
                }
            }
        }
    }
    public int getXOffset(){
        return Math.max(map.getPlayer().getX() - (map.getWidth() / 2), 0);

    }
    public int getYOffset(){
        return Math.max(map.getPlayer().getY() - (map.getHeight() / 2), 0);
    }

    public void refresh() {
        int invCountX = 1;
        int invCountY = 0;
        int itemCounter = 0;
        contextMain.setFill(Color.BLACK);
        contextMain.fillRect(0, 0, canvasMain.getWidth(), canvasMain.getHeight());
        for (int x = getXOffset(); x < map.getWidth(); x++) {
            for (int y = getYOffset(); y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(contextMain, cell.getActor(), x-getXOffset(), y-getYOffset());
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(contextMain, cell.getItem(), x-getXOffset(), y-getYOffset());
                } else {
                    Tiles.drawTile(contextMain, cell, x-getXOffset(), y-getYOffset());
                }
            }
        }
        while (invCountY < 3) {
            if (itemCounter < items.size()) {
                Tiles.getTileForItem(contextInv, items.get(itemCounter), invCountX, invCountY);
                itemCounter++;
            } else {
                Tiles.getTileForItem(contextInv, "inventory", invCountX, invCountY);
            }
            if (invCountX < 3) {
                invCountX++;
            } else {
                invCountX = 0;
                invCountX++;
                invCountY++;
            }
        }


        healthLabel.setTextFill(Color.WHITE);
        damageLabel.setTextFill(Color.WHITE);
        damageLabel.setFont(Font.font("Manaspace", 20));
        healthLabel.setFont(Font.font("Manaspace", 20));
        healthLabel.setText("" + map.getPlayer().getHealth());
        damageLabel.setText("" + map.getPlayer().getDamage());

        playerNameLabel.setText("     " + playerName);
        playerNameLabel.setTextFill(Color.WHITE);
        playerNameLabel.setFont(Font.font("Manaspace", 20));

        if (map.getPlayer().getHealth() <= 0) {
            gameOver();
        } else if (map.getPlayer().getX() == 17 && map.getPlayer().getY() == 16){
            mapLoader.loadMap("/placeholder.txt", System.getProperty("user.dir") + "/src/main/resources/Hootsforce.mp3");
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

//    private void MeetYourDoom() {
//        mapLoader.getMediaPlayer().stop();
//        String path = System.getProperty("user.dir") + "/src/main/resources/Universe On Fire.mp3";
//        Media media = new Media(new File(path).toURI().toString());
//        mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.setAutoPlay(true);
//
//        BackgroundFill myBG = new BackgroundFill(Color.BLACK, new CornerRadii(1), new Insets(0.0,0.0,0.0,0.0));
//
//        Label MeetYourDoom = new Label("As you aproach the figure in front of you, the sudden realization"+ "\n" +
//                                            "makes you dizzy. His eyes are burning with the flames of hatred as he looks at you"+ "\n" +
//                                            "He was no other then the Warlock Zargotrax himself!" + "\n" +
//                                            "You stand rooted in front of him for seconds, but when you made your first move" + "\n" +
//                                            "the warlock disappears in thin air." + "\n" + "\n" +
//                                            "'Foolish mortal! You can't get me this easily!'" + "\n" + "\n" +
//                                            "It seems your journey is not over yet!");
//        MeetYourDoom.setFont(Font.font("Manaspace", 30));
//        borderPane.setCenter(MeetYourDoom);
//        borderPane.setRight(null);
//        borderPane.setBackground(new Background(myBG));
//        MeetYourDoom.setTextFill(Color.WHITE);
//    }

    private void gameOver() {
        mapLoader.getMediaPlayer().stop();
        BackgroundFill myBG = new BackgroundFill(Color.BLACK, new CornerRadii(1), new Insets(0.0,0.0,0.0,0.0));
        String path = System.getProperty("user.dir") + "/src/main/resources/Magic Dragon.mp3";
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
    private void showWindow(Pane pane,Stage primaryStage){
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
        primaryStage.setTitle("Gloryhammer: Rise of the Chaos Wizard");
        primaryStage.show();
    }
}
