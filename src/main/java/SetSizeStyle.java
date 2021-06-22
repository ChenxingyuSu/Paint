import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;


public class SetSizeStyle {
    private VBox content;
    private ComboBox<String> lineStyle, lineSize, lineSize2;
    private ComboBox<Integer> rubberSize, zero1, zero2;

    private ObservableList<String> lineStyleItems = FXCollections.observableArrayList();
    private ObservableList<String> lineSizeItems = FXCollections.observableArrayList();
    private ObservableList<String> lineSizeItems2 = FXCollections.observableArrayList();
    private ObservableList<Integer> rubberSizeItems = FXCollections.observableArrayList();

    public SetSizeStyle() {
        content = new VBox();
        content.setAlignment(Pos.CENTER);

        lineSize = new ComboBox<String>();
        for (int i = 1; i < 14; i += 2) {
            lineSizeItems.add(Integer.toString(i));
        }
        lineSizeItems.add("Fill");
        lineSize.setStyle("-fx-base:#888888;-fx-background-color:#666666;");
        lineSize.setPrefWidth(Size.DETAIL_WIDTH);
        lineSize.setItems(lineSizeItems);
        lineSize.getSelectionModel().select(0);
        lineSize.valueProperty().addListener((ov, oldv, newv) -> {
            Shape.resetLineSize(newv);
        });

        lineSize2 = new ComboBox<String>();
        for (int i = 1; i < 14; i += 2) {
            lineSizeItems2.add(Integer.toString(i));
        }
        lineSize2.setStyle("-fx-base:#888888;-fx-background-color:#666666;");
        lineSize2.setPrefWidth(Size.DETAIL_WIDTH);
        lineSize2.setItems(lineSizeItems2);
        lineSize2.getSelectionModel().select(0);
        lineSize2.valueProperty().addListener((ov, oldv, newv) -> {
            Shape.resetLineSize(newv);
        });

        lineStyle = new ComboBox<String>();
        lineStyleItems.add("SOLID");
        lineStyleItems.add("DASHED");
        lineStyleItems.add("DOTTED");
        lineStyle.setStyle("-fx-base:#888888;-fx-background-color:#666666;");
        lineStyle.setPrefWidth(Size.DETAIL_WIDTH);
        lineStyle.setItems(lineStyleItems);
        lineStyle.getSelectionModel().select(0);
        lineStyle.valueProperty().addListener((ov, oldv, newv) -> {
            Shape.resetLineStyle(newv);
        });

        rubberSize = new ComboBox<Integer>();
        for (int i = 1; i < 14; i += 2) {
            rubberSizeItems.add(i);
        }
        rubberSize.setStyle("-fx-base:#888888;-fx-background-color:#666666;");
        rubberSize.setPrefWidth(Size.DETAIL_WIDTH);
        rubberSize.setItems(rubberSizeItems);
        rubberSize.getSelectionModel().select(0);
        rubberSize.valueProperty().addListener((ov, oldv, newv) -> {
            Shape.resetRubberSize(Integer.valueOf(newv));
        });

        zero1 = new ComboBox<Integer>();
        zero1.setStyle("-fx-base:#888888;-fx-background-color:#666666;");
        zero1.setPrefWidth(Size.DETAIL_WIDTH);
        zero2 = new ComboBox<Integer>();
        zero2.setStyle("-fx-base:#888888;-fx-background-color:#666666;");
        zero2.setPrefWidth(Size.DETAIL_WIDTH);
    }


    public void setLine() {
        content.getChildren().clear();
        lineSize.getSelectionModel().select(0);
        lineStyle.getSelectionModel().select(0);
        Shape.resetLineSize("1");
        Shape.resetLineStyle("SOLID");
        content.getChildren().add(lineSize2);
        content.getChildren().add(lineStyle);
    }

    public void setShape() {
        content.getChildren().clear();
        lineSize.getSelectionModel().select(0);
        Shape.resetLineSize("1");
        content.getChildren().add(lineSize);
        content.getChildren().add(zero1);
    }


    public void  setRubber() {
        content.getChildren().clear();
        rubberSize.getSelectionModel().select(0);
        Shape.resetRubberSize(1);
        content.getChildren().add(rubberSize);
        content.getChildren().add(zero1);
    }

    public void clear() {
        content.getChildren().clear();
        content.getChildren().add(zero1);
        content.getChildren().add(zero2);
    }
    public VBox getDetailPanel(){
        content.getChildren().add(zero1);
        content.getChildren().add(zero2);
        return content;
    }
}