import javafx.scene.paint.Color;

/**
 * @see Shape
 * @version 1.0
 * @author Flyuz
 */
public class Shape {
    static String toolName = "PEN";
    static String lineSize = "7";
    static int rubberSize = 7;
    static String lineStyle = "SOLID";
    static Color color = Color.BLACK;
    static Color fillColor = Color.BLACK;
    static void resetToolName(String name){
        Shape.toolName = name;
    }
    static void resetLineSize(String size){
        Shape.lineSize = size;
    }
    static void resetRubberSize(int size){
        Shape.rubberSize = size;
    }
    static void resetLineStyle(String style) {
        Shape.lineStyle = style;
    }
    static void resetColor(Color c){
        Shape.color = c;
    }
    static void resetFillColor(Color c){
        Shape.fillColor = c;
    }

}