package com.pong.klient;

import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import javax.swing.text.html.ImageView;
import java.util.Timer;
import java.util.TimerTask;


public class MainMenuView extends Pane {

    private static class MenuText extends Text{
        MenuText(String text){
            setText(text);
            setFont(Font.font("Impact", FontWeight.SEMI_BOLD, 18));
            setFill(Color.WHITE);
        }

    }


    private static class MenuRect extends Rectangle{
        MenuRect(){
           setFill(Color.GRAY);
           setOpacity(0.4);
           setWidth(300);
           setHeight(50);


        }

    }


    private static class MenuPane extends StackPane{

        LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[] {
                new Stop(0, Color.BLACK),
                new Stop(0.2, Color.GRAY),
                new Stop(0.8, Color.GRAY),
                new Stop(1, Color.BLACK)
        });

        MenuPane(MenuRect rect, MenuText text){


            this.setOnMouseEntered(event -> {
                rect.setFill(gradient);
            });
            this.setOnMouseExited(event -> {
                rect.setFill(Color.GRAY);
            });
            getChildren().addAll(rect,text);

            this.setOnMouseClicked(event -> {
                if(text.getText() == "Single Player") {
                    Main.getInstance().setSceneSinglePlayer();
                }else if(text.getText() == "Multi Player"){
                    Main.getInstance().setSceneMultiPlayer();
                }
                else if(text.getText() == "Settings"){
                    Main.getInstance().setSceneSettings();
                }
            });

        }
    }
    private  static class MenuVbox extends VBox{
        MenuVbox(MenuPane... items){
            setLayoutX(150);
            setLayoutY(150);

            getChildren().add(createSeparator());

            for (MenuPane item : items) {
                getChildren().addAll(item, createSeparator());
            }
        }
        private Line createSeparator() {
            Line sep = new Line();
            sep.setEndX(300);
            sep.setStroke(Color.DARKGREY);
            sep.setStyle("-fx-background-size: 99");
            return sep;
        }

    }

    private int WIDTH=800, HEIGHT = 400;
    MenuRect singlePlayerRect, multiPlayerRect,settingsRect;
    MenuText textSingle,textMulti,textSettings;
    StackPane paneSingle,paneMulti,paneSettings;
    VBox vBox = new VBox();

    MainMenuView(){
        setPrefSize(WIDTH,HEIGHT);
        setStyle("-fx-background-image: url(/com/pong/klient/main_menu_logo2.png)");
        singlePlayerRect = new MenuRect();
        textSingle = new MenuText("Single Player");
        paneSingle = new MenuPane(singlePlayerRect,textSingle);

        multiPlayerRect = new MenuRect();
        textMulti = new MenuText("Multi Player");
        paneMulti = new MenuPane(multiPlayerRect,textMulti);

        settingsRect = new MenuRect();
        textSettings = new MenuText("Settings");
        paneSettings = new MenuPane(settingsRect,textSettings);

        MenuVbox menuVbox = new MenuVbox(new MenuPane(singlePlayerRect,textSingle),new MenuPane(multiPlayerRect,textMulti), new MenuPane(settingsRect,textSettings));
        getChildren().add(menuVbox);
        menuVbox.setLayoutY(190);
        menuVbox.setLayoutX(60);
        getChildren().addAll(paneMulti,paneSettings,paneSingle);

    }

}