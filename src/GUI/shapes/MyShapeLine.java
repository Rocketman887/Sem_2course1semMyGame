package GUI.shapes;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;

public abstract class MyShapeLine {
    GraphicsContext graphicsContext;
    ColorPicker cpLine;

    public void MyShape(){}

    public void setGraphicsContext(GraphicsContext graphicsContext){
        this.graphicsContext = graphicsContext;
    }

    public void setColor(ColorPicker colorPicker){
        cpLine = colorPicker;
    }

    public ColorPicker getColor(){
        return cpLine;
    }
    public abstract void draw();

}
