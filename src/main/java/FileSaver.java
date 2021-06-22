import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileSaver {
    private List<Canvas> list;
    private int canvasWidth;
    private int canvasHeigth;

    public FileSaver(List<Canvas> list, int drawingCanvasHeight, int drawingCanvasWidth) {
        if (list.isEmpty()) {
            this.list = new ArrayList<>();
            canvasHeigth = drawingCanvasHeight;
            canvasWidth = drawingCanvasWidth;
        } else {
            this.list = new ArrayList<>(list);
            canvasWidth = (int) list.get(list.size() - 1).getWidth();
            canvasHeigth = (int) list.get(list.size() - 1).getHeight();
        }
    }

    public void saveToFile() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG", "*.png"));
        fc.setTitle("Save image");
        File img = fc.showSaveDialog(null);
        String type = "PNG";

        try {
            Canvas myCanvas = createCanvas(this.list);
            WritableImage writableImage = new WritableImage(canvasWidth, canvasHeigth);
            myCanvas.snapshot(null, writableImage);
            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
            if(img != null) {
                ImageIO.write(renderedImage, type, img);
            }
            img = null;
            myCanvas = null;
        } catch (IOException ignored) {
        }
    }

    private Canvas createCanvas(List<Canvas> list) {
        Canvas tempCanvas = new Canvas(canvasWidth, canvasHeigth);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        for (Canvas aList : list) {
            WritableImage image = aList.snapshot(params, null);
            tempCanvas.getGraphicsContext2D().drawImage(image, 0, 0);
        }
        return tempCanvas;
    }
}
