import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class MyCanvas {
    private Group content;
    private VBox vbox;
    private Canvas drawingCanvas;
    private GraphicsContext gc;
    public static int drawingCanvasWidth;
    public static int drawingCanvasHeight;
    private List<Canvas> listCanvas;
    private List<Canvas> canvasRemoved;
    private boolean fill;
    private double x1, y1, x2, y2;

    public MyCanvas() {
        content = new Group();
        vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10, 20, 0, 0));
        vbox.getChildren().add(content);
        drawingCanvas = new Canvas(Size.CANVAS_WIDTH, Size.CANVAS_HEIGHT);
        gc = drawingCanvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, Size.CANVAS_WIDTH, Size.CANVAS_HEIGHT);
        gc.restore();
        content.getChildren().add(drawingCanvas);
        setCanvas(drawingCanvas, Shape.color, Shape.fillColor,false);
        drawingCanvasWidth = (int) drawingCanvas.getWidth();
        drawingCanvasHeight = (int) drawingCanvas.getHeight();
        listCanvas = new ArrayList<>();
        canvasRemoved = new ArrayList<>();
        handleDrawingCanvas();
    }

    private void handleDrawingCanvas() {
        drawingCanvas.setOnMousePressed(event -> {
            Canvas c = new Canvas(drawingCanvasWidth, drawingCanvasHeight);
            gc = c.getGraphicsContext2D();
            c.setOnMousePressed(drawingCanvas.getOnMousePressed());
            c.setOnMouseDragged(drawingCanvas.getOnMouseDragged());
            c.setOnMouseReleased(drawingCanvas.getOnMouseReleased());
            c.setOnMouseMoved(drawingCanvas.getOnMouseMoved());
            c.setOnMouseExited(drawingCanvas.getOnMouseExited());
            if (Shape.toolName.equals("CIRCLE") || Shape.toolName.equals("RECTANGLE")) {
                if (!Shape.lineSize.equals("Fill")) {
                    gc.setLineWidth(Integer.valueOf(Shape.lineSize));
                    setCanvas(c, Shape.color, Shape.fillColor, false);
                } else if (Shape.lineSize.equals("Fill")) {
                    setCanvas(c, Shape.color, Shape.fillColor,true);
                }
            }else if(Shape.toolName.equals("BARREL")){
                setCanvas(c, Shape.color, Shape.fillColor,true);
            } else if (Shape.toolName.equals("LINE")) {
                gc.setLineWidth(Integer. valueOf(Shape.lineSize));
                setCanvas(c, Shape.color, Shape.fillColor,false);
            } else{
                gc.setLineWidth(Shape.rubberSize);
                setCanvas(c, Shape.color, Shape.fillColor,false);
            }

            if (Shape.toolName.equals("RUBBER")) {
                gc.setStroke(Color.WHITE);
            }
            x1 = event.getX();
            y1 = event.getY();
            listCanvas.add(c);
            content.getChildren().add(c);
        });

        drawingCanvas.setOnMouseDragged(event -> {
            if (Shape.toolName.equals("PEN") || Shape.toolName.equals("RUBBER")) {
                gc.lineTo(event.getX(), event.getY());
                gc.stroke();
            }
        });

        drawingCanvas.setOnMouseReleased(event -> {
            x2 = event.getX();
            y2 = event.getY();
            double width = x2 - x1;
            double height = y2 - y1;
            if (Shape.toolName.equals("LINE")) {
                drawLine(x1, y1, x2, y2, Shape.lineStyle, Shape.lineSize);
            } else if (Shape.toolName.equals("CIRCLE")) {
                drawCircle(x1, y1, width, height);
            } else if (Shape.toolName.equals("RECTANGLE")) {
                drawRectangle(x1, y1, width, height);
            } else if (Shape.toolName.equals("BARREL")) {
                drawFillCanvas(drawingCanvasWidth, drawingCanvasHeight);
            }
            gc.stroke();
        });
    }

    public VBox getCanvas() {
        return vbox;
    }

    public void clear() {
        content.getChildren().clear();
        listCanvas.clear();
        canvasRemoved.clear();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, Size.CANVAS_WIDTH, Size.CANVAS_HEIGHT);
        gc.restore();
        content.getChildren().add(drawingCanvas);
    }

    public void addImage(Image image) {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, Size.CANVAS_WIDTH, Size.CANVAS_HEIGHT);
        gc.drawImage(image, 0, 0, Size.CANVAS_WIDTH, Size.CANVAS_HEIGHT);
        content.getChildren().add(drawingCanvas);
    }

    public List<Canvas> getList() {
        return listCanvas;
    }

    public int getW() {
        return drawingCanvasWidth;
    }

    public int getH() {
        return drawingCanvasHeight;
    }

    public void undo() {
        if (!listCanvas.isEmpty()) {
            content.getChildren().remove(listCanvas.get(listCanvas.size()-1));
            canvasRemoved.add(listCanvas.get(listCanvas.size()-1));
            listCanvas.remove(listCanvas.size()-1);
        }
    }

    public void redo() {
        if (!canvasRemoved.isEmpty()) {
            content.getChildren().add(canvasRemoved.get(canvasRemoved.size()-1));
            listCanvas.add(canvasRemoved.get(canvasRemoved.size()-1));
            canvasRemoved.remove(canvasRemoved.get(canvasRemoved.size()-1));
        }
    }



    public void setCanvas(Canvas c, Color color, Color fillColor, boolean f){
        gc = c.getGraphicsContext2D();
        fill = f;
        if(fill)
            gc.setFill(fillColor);
        else
            gc.setStroke(color);
    }

    public void drawLine(double x1, double y1, double x2, double y2, String style, String width) {
        gc.setLineWidth(Integer. valueOf(width));
        if (style.equals("SOLID")) {
            gc.moveTo(x1, y1);
            gc.lineTo(x2, y2);
            gc.stroke();
        } else if (style.equals("DASHED")) {
            gc.setLineDashes(20);
            gc.strokeLine(x1, y1, x2, y2);
        } else {
            gc.setLineDashes(5);
            gc.strokeLine(x1, y1, x2, y2);
        }

    }

    public void drawCircle(double x1, double y1, double width, double height) {
        if (width < 0) {
            width = -width;
            x1 = x1 - width;
        }
        if (height < 0) {
            height = -height;
            y1 = y1 - height;
        }
        if(fill)
            if (height > width) gc.fillOval(x1, y1, height, height);
            else gc.fillOval(x1, y1, width, width);
        else
        if (height > width) gc.strokeOval(x1, y1, height, height);
        else gc.strokeOval(x1, y1, width, width);
    }

    public void drawRectangle(double x1, double y1, double width, double height) {
        if (width < 0) {
            width = -width;
            x1 = x1 - width;
        }
        if (height < 0) {
            height = -height;
            y1 = y1 - height;
        }
        if(fill)
            gc.fillRect(x1, y1, width, height);
        else
            gc.strokeRect(x1, y1, width, height);
    }

    public void drawFillCanvas(double w, double h){
        gc.fillRect(0, 0, w, h);
    }
}