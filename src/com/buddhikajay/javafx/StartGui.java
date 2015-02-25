package com.buddhikajay.javafx;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Buddhika Jayawardhana on 23/02/2015.
 */
public class StartGui extends Application {
    Logic logic;
    List<File> fileList;
    File directory;
    @Override
    public void start(final Stage primaryStage) throws Exception {

        logic = new Logic();
        fileList = new ArrayList<File>();

        final Label extensionLabel = new Label("File Extension");
        final Label commonNameLabel = new Label("Common Name");
        final Label startingIndex = new Label("Starting Index");

        final TextField extensionTextArea = new javafx.scene.control.TextField("eg: .jpg");
        final TextField commonNameTextArea = new TextField("Paris Tour");
        final TextField startingIndexTextArea = new TextField("eg: 1");

        final Button proceedButton = new Button("Proceed");
        final Button renameButton = new Button("Rename");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(extensionLabel,1,1);
        gridPane.add(commonNameLabel,1,2);
        gridPane.add(startingIndex,1,3);

        gridPane.add(extensionTextArea,2,1);
        gridPane.add(commonNameTextArea,2,2);
        gridPane.add(startingIndexTextArea,2,3);

        gridPane.add(proceedButton,2,6);
        gridPane.add(renameButton,2,7);

        Scene scene = new Scene(gridPane);
        primaryStage.setTitle("Bulk Renamer");
        primaryStage.setWidth(300);
        primaryStage.setHeight(250);
        primaryStage.setScene(scene);

        //hiding all elements than proceed button
        extensionLabel.setVisible(false);
        commonNameLabel.setVisible(false);
        startingIndex.setVisible(false);
        extensionTextArea.setVisible(false);
        commonNameTextArea.setVisible(false);
        startingIndexTextArea.setVisible(false);
        renameButton.setVisible(false);

        primaryStage.show();

        proceedButton.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Bee File Renamer");
                fileChooser.setInitialDirectory(new File("C://Users//Buddhika//Desktop//Temp"));
                fileList.addAll(fileChooser.showOpenMultipleDialog(primaryStage));
                directory = fileList.get(0).getParentFile();

                proceedButton.setVisible(false);//higing the proceedButton

                extensionLabel.setVisible(true);
                commonNameLabel.setVisible(true);
                startingIndex.setVisible(true);
                extensionTextArea.setVisible(true);
                commonNameTextArea.setVisible(true);
                startingIndexTextArea.setVisible(true);
                renameButton.setVisible(true);

            }
        });

        renameButton.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                //String extentionName = extensionTextArea.getText();
                String commonName = commonNameTextArea.getText();
                int startingIndex = Integer.parseInt(startingIndexTextArea.getText());
                try {
                    logic.prepareRename(fileList, startingIndex, commonName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //open the directory containig renamed files
                FileChooser finalFileChooser = new FileChooser();
                finalFileChooser.setTitle("Renamed Files");
                finalFileChooser.setInitialDirectory(directory);
                finalFileChooser.showOpenDialog(primaryStage);

                //Allowing user to rename another file
                renameButton.setVisible(false);
                proceedButton.setText("Select Another File Set");
                proceedButton.setVisible(true);

            }
        });



    }

    public static void main(String[] args){
        Application.launch();
    }
}
