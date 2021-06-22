import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Optional;

public class MyMenuBar {

    private MainStage mainStage;
    private MenuBar menubar;
    private Menu file, edit, help;
    private MenuItem Load, Save, Close, Undo, Redo, New, About;
    private boolean currSaved = false;

    public MyMenuBar(MainStage mainStage) {
        this.mainStage = mainStage;
        menubar = new MenuBar();

        file = new Menu();
        file.setText("File");
        file.setStyle("-fx-font-size:14;");

        New = new MenuItem("New");
        New.setStyle("-fx-font-size:14;");
        New.setOnAction((ActionEvent t) -> {
            if (!currSaved) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation needed");
                alert.setHeaderText("Your sketch has not been saved yet.");
                alert.setContentText("Do you want to save it before creating a new sketch?");
                ButtonType buttonTypeOne = new ButtonType("Save as");
                ButtonType buttonTypeTwo = new ButtonType("Not save and start a new sketch");
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeOne){
                    List<Canvas> saveList = mainStage.getMyCanvas().getList();
                    FileSaver fileSaver = new FileSaver(saveList, mainStage.getMyCanvas().getW(), mainStage.getMyCanvas().getH());
                    fileSaver.saveToFile();
                    fileSaver = null;
                    saveList = null;
                    System.gc();
                    currSaved = false;
                    mainStage.getMyCanvas().clear();
                } else if (result.get() == buttonTypeTwo) {
                    mainStage.getMyCanvas().clear();
                } else {
                    // do nothing
                }
            } else {
                mainStage.getMyCanvas().clear();
            }
        });

        Load = new MenuItem();
        Load.setText("Load");
        Load.setStyle("-fx-font-size:14;");
        Load.setOnAction((ActionEvent t) -> {

            if (!currSaved) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation needed");
                alert.setHeaderText("Your sketch has not been saved yet.");
                alert.setContentText("Do you want to save it before creating a new sketch?");
                ButtonType buttonTypeOne = new ButtonType("Save as");
                ButtonType buttonTypeTwo = new ButtonType("Not save and load a sketch");
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeOne) {
                    List<Canvas> saveList = mainStage.getMyCanvas().getList();
                    FileSaver fileSaver = new FileSaver(saveList, mainStage.getMyCanvas().getW(), mainStage.getMyCanvas().getH());
                    fileSaver.saveToFile();
                    fileSaver = null;
                    saveList = null;
                    System.gc();
                    currSaved = true;
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image", "*.png", "*.jpg", "*.jpeg"));
                    fileChooser.setTitle("Open Image");
                    File file = fileChooser.showOpenDialog(mainStage);
                    if (file != null) {
                        try {
                            Image image = new Image(new FileInputStream(file));
                            mainStage.getMyCanvas().addImage(image);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    //mainStage.getMyCanvas().clear();
                } else if (result.get() == buttonTypeTwo) {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image", "*.png", "*.jpg", "*.jpeg"));
                    fileChooser.setTitle("Open Image");
                    File file = fileChooser.showOpenDialog(mainStage);
                    if (file != null) {
                        try {
                            Image image = new Image(new FileInputStream(file));
                            mainStage.getMyCanvas().addImage(image);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    //mainStage.getMyCanvas().clear();
                } else {
                    // do nothing
                }
            } else {
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image", "*.png", "*.jpg", "*.jpeg"));
                fileChooser.setTitle("Open Image");
                File file = fileChooser.showOpenDialog(mainStage);
                if (file != null) {
                    try {
                        Image image = new Image(new FileInputStream(file));
                        mainStage.getMyCanvas().addImage(image);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
            currSaved = false;
		});


        Save = new MenuItem();
        Save.setText("Save");
        Save.setStyle("-fx-font-size:14;");
        Save.setAccelerator(KeyCombination.keyCombination("Ctrl+s"));
        Save.setOnAction((ActionEvent t) -> {
            List<Canvas> saveList = mainStage.getMyCanvas().getList();
            FileSaver fileSaver = new FileSaver(saveList, mainStage.getMyCanvas().getW(), mainStage.getMyCanvas().getH());
            fileSaver.saveToFile();
            fileSaver = null;
            saveList = null;
            currSaved = true;
            System.gc();
        });

        Close = new MenuItem();
        Close.setText("Close");
        Close.setStyle("-fx-font-size:14;");
        Close.setOnAction((ActionEvent t) -> {
            Platform.exit();
            System.exit(0);
        });

        file.getItems().add(New);
        file.getItems().add(Load);
        file.getItems().add(Save);
        file.getItems().add(Close);

        edit = new Menu();
        edit.setText("Edit");
        edit.setStyle("-fx-font-size:14;");
        Undo = new MenuItem("Undo");
        Undo.setStyle("-fx-font-size:14;");
        Undo.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.SHORTCUT_DOWN));
        Undo.setOnAction((ActionEvent t) -> {
            mainStage.getMyCanvas().undo();
        });

        Redo = new MenuItem("Redo");
        Redo.setStyle("-fx-font-size:14;");
        Redo.setAccelerator(new KeyCodeCombination(KeyCode.Y, KeyCombination.SHORTCUT_DOWN));
        Redo.setOnAction((ActionEvent t) -> {
            mainStage.getMyCanvas().redo();
        });


        edit.getItems().add(Undo);
        edit.getItems().add(Redo);

        help = new Menu();
        help.setText("Help");
        help.setStyle("-fx-font-size:14;");
        About = new MenuItem();
        About.setText("About");
        About.setStyle("-fx-font-size:14;");
        About.setOnAction((ActionEvent t) -> {
            Alert aboutAlert = new Alert(AlertType.INFORMATION);
            aboutAlert.setTitle("About this application");
            aboutAlert.setHeaderText(null);
            aboutAlert.setContentText("Application name: Sketch It\n" + "Developer name: Chenxingyu Su\n" + "WatID: c35su");
            aboutAlert.showAndWait();
        });
        help.getItems().add(About);

        menubar.getMenus().add(file);
        menubar.getMenus().add(edit);
        menubar.getMenus().add(help);
    }
    public MenuBar getMenuBar()
    {
        return menubar;
    }
}