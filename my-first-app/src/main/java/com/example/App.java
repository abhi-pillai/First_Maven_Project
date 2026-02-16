package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;
import java.util.Scanner;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose what to play:");
        System.out.println("1 - MP3");
        System.out.println("2 - MP4");

        int choice = scanner.nextInt();

        String path = null;

        if (choice == 1) {
            path = System.getenv("MP3_PATH");
             File file = new File(path);
            Media media = new Media(file.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);

            mediaPlayer.play();
            System.out.println("Playing...");
        } else if (choice == 2) {
            path = System.getenv("MP4_PATH");
            File file = new File(path);
            Media media = new Media(file.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);
            mediaView.setFitWidth(800);   // width of the scene
            mediaView.setFitHeight(600);  // height of the scene
            mediaView.setPreserveRatio(true); // prevents stretching or zooming


            StackPane root = new StackPane(mediaView);
            Scene scene = new Scene(root, 800, 600); // window size
            stage.setScene(scene);
            stage.setTitle("MP4 Player");
            stage.show();

            mediaPlayer.play();
        } else {
            System.out.println("Invalid choice.");
            System.exit(0);
        }

        if (path == null) {
            System.out.println("Environment variable not set!");
            System.exit(0);
        }
        scanner.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
