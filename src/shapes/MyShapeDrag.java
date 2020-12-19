package shapes;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;

public abstract class MyShapeDrag{
    GraphicsContext graphicsContext;
    ColorPicker cpLine;

    public void MyShape(){}

    public boolean containsPoint(Point2D point){
        return false;
    }
    public void setGraphicsContext(GraphicsContext graphicsContext){
        this.graphicsContext = graphicsContext;
    }

    public void setColor(ColorPicker colorPicker){
        cpLine = colorPicker;
    }
    public abstract void draw();
    public ColorPicker getColor(){
        return cpLine;
    }
}
