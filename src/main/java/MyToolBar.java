import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;


public class MyToolBar {
    private VBox content;
    private ColorPanel colorPanel;
    private SetSizeStyle setSizeStyle;
    private TilePane tpToolBtn;
    private List<MyButton> toolButton = new ArrayList<MyButton>();

    public MyToolBar() {
        initToolPanel();
        content = new VBox();
        content.setAlignment(Pos.CENTER);
        content.setSpacing(10);

        content.getChildren().add(tpToolBtn);

        colorPanel = new ColorPanel();
        content.getChildren().add(colorPanel.getColorPanel());

        setSizeStyle = new SetSizeStyle();
        content.getChildren().add(setSizeStyle.getDetailPanel());
    }

    private ImageView getImageView(Image curImg) {
        ImageView curImgV = new ImageView(curImg);
        curImgV.setFitHeight(Size.TOOL_BUTTON_HEIGHT);
        curImgV.setFitWidth(Size.TOOL_BUTTON_WIDTH);
        return curImgV;
    }

    private void initToolPanel() {
        tpToolBtn = new TilePane();
        tpToolBtn.setAlignment(Pos.CENTER);
        tpToolBtn.setPadding(new Insets(10,10,10,10));
        tpToolBtn.setPrefColumns(2);
        tpToolBtn.setHgap(5);
        tpToolBtn.setVgap(5);

        Image ImgPen = new Image("pen.png", false);
        MyButton penButton = new MyButton("PEN");
        penButton.setGraphic(getImageView(ImgPen));
        toolButton.add(penButton);

        Image ImgRubber = new Image("rubber.png");
        MyButton rubberButton = new MyButton("RUBBER");
        rubberButton.setGraphic(getImageView(ImgRubber));
        toolButton.add(rubberButton);

        Image ImgBarrel = new Image("barrel.png");
        MyButton barrelButton = new MyButton("BARREL");
        barrelButton.setGraphic(getImageView(ImgBarrel));
        toolButton.add(barrelButton);

        Image ImgLine = new Image("line.png");
        MyButton lineButton = new MyButton("LINE");
        lineButton.setGraphic(getImageView(ImgLine));
        toolButton.add(lineButton);

        Image ImgRectangleZ = new Image("rectangle.png");
        MyButton zButton = new MyButton("RECTANGLE");
        zButton.setGraphic(getImageView(ImgRectangleZ));
        toolButton.add(zButton);

        Image ImgOval = new Image("circle.png");
        MyButton ovalButton = new MyButton("CIRCLE");
        ovalButton.setGraphic(getImageView(ImgOval));
        toolButton.add(ovalButton);

        Image ImgSelect = new Image("select.png");
        MyButton selectButton = new MyButton("SELECT");
        selectButton.setGraphic(getImageView(ImgSelect));
        toolButton.add(selectButton);



        DropShadow shadow = new DropShadow();
        for (Button curBt : toolButton) {
            curBt.setStyle("-fx-base:#aaaaaa;");
            curBt.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
                curBt.setEffect(shadow);
            });
            curBt.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
                curBt.setEffect(null);
            });
            curBt.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                String name = ((MyButton) e.getSource()).getName();
                Shape.resetToolName(name);
                if(name == "RUBBER" ||  name == "PEN"){
                    setSizeStyle.setRubber();
                }else if(name == "BARREL"){
                    setSizeStyle.clear();
                }else if (name == "LINE") {
                    setSizeStyle.setLine();
                } else {
                    setSizeStyle.setShape();
                }
            });
        }
        tpToolBtn.getChildren().addAll(toolButton);
    }
    public VBox getToolBar() {
        return content;
    }
}

