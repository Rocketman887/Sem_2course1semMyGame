package GUI.shapes;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;

public abstract class MyShape {
    GraphicsContext graphicsContext;
    ColorPicker cpLine;

    public void MyShape(){}

    public void setGraphicsContext(GraphicsContext graphicsContext){
        this.graphicsContext = graphicsContext;
    }

    public void setColor(ColorPicker colorPicker){
        cpLine = colorPicker;
    }

    public abstract void check();
    public abstract void draw();
}