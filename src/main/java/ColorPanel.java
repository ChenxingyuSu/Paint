import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ColorPicker;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;


public class ColorPanel {
    private VBox content;
    private TilePane tilePane;
    private  Color presentColor = Color.BLACK;
    private  ColorPicker colorPicker = new ColorPicker();
    private  ColorPicker colorPicker2 = new ColorPicker();
    private List<MyButton> colorButton = new ArrayList<MyButton>();
    private String[] color = {"#ffffff", "#000000", "#cdbedb", "#b97b56", "#848683", "#c4c3be",
            "#efe2ab", "#b6e51d", "#24b04f", "#93dceb", "#6997bb", "#07a0e6",
            "#fef007", "#ffc80c", "#ff7c26", "#ffadd6", "#f01e1f", "#89010d",
            "#d9a2dc", "#9c4ca1","#3b46e0"};
    public ColorPanel(){
        content = new VBox();
        content.setAlignment(Pos.CENTER);

        // For color of drawing lines and shapes
        colorPicker.setPrefWidth(Size.COLOR_PICKER_WIDTH);
        colorPicker.setStyle("-fx-background-color:orange;-fx-color-label-visible:false;");
        colorPicker.setValue(presentColor);
        colorPicker.setOnAction((ActionEvent t) -> {
            presentColor = colorPicker.getValue();
            Shape.resetColor(presentColor);
        });

        // For color of filling
        colorPicker2.setPrefWidth(Size.COLOR_PICKER_WIDTH);
        colorPicker2.setStyle("-fx-background-color:lightsteelblue;-fx-color-label-visible:false;");
        colorPicker2.setValue(presentColor);
        colorPicker2.setOnAction((ActionEvent t) -> {
            presentColor = colorPicker2.getValue();
            Shape.resetFillColor(presentColor);
        });

        // Only changes the color of drawing lines and shapes
        tilePane = new TilePane();
        tilePane.setAlignment(Pos.CENTER);
        tilePane.setPadding(new Insets(10,10,10,10));
        tilePane.setPrefColumns(2);
        tilePane.setHgap(5);
        tilePane.setVgap(5);

        DropShadow shadow = new DropShadow();
        for(int i=0; i<color.length; i++){
            MyButton cButton = new MyButton(color[i]);
            cButton.setStyle("-fx-base: " + color[i] + ";");
            cButton.setPrefSize(Size.COLOR_BUTTON_WIDTH, Size.COLOR_BUTTON_HEIGHT);
            cButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
                cButton.setEffect(shadow);
            });

            cButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
                cButton.setEffect(null);
            });

            cButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                presentColor = Color.web( ((MyButton) e.getSource()).getName() );
                colorPicker.setValue(presentColor);
                Shape.resetColor(presentColor);
            });
            colorButton.add(cButton);
        }
        tilePane.getChildren().addAll(colorButton);

        content.getChildren().add(colorPicker);
        content.getChildren().add(colorPicker2);
        content.getChildren().add(tilePane);

    }

    public VBox getColorPanel(){
        return content;
    }
}
