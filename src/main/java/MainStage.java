import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class MainStage extends Stage {
    private Group root;
    private BorderPane content;
    private MyCanvas myCanvas;

    public MainStage() {
        root = new Group();
        content = new BorderPane();

        setScene(new Scene(root, Color.web("#CACACA")));
        setTitle("Sketch Tool");
        setResizable(true);
        getIcons().add(new Image("logo.png"));
        initUI();
        root.getChildren().add(content);
        show();
    }


    private void initUI() {
        MyMenuBar menuBar = new MyMenuBar(this);
        menuBar.getMenuBar().prefWidthProperty().bind(this.widthProperty());
        content.setTop(menuBar.getMenuBar());

        MyToolBar toolBar = new MyToolBar();
        content.setLeft(toolBar.getToolBar());

        myCanvas = new MyCanvas();
        content.setRight(myCanvas.getCanvas());

    }
    public MyCanvas getMyCanvas() {
        return myCanvas;
    }
}