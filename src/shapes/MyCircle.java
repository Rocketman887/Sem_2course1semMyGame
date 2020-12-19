package shapes;

import javafx.geometry.Point2D;
import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Circle;
import shapes.MyShape;

public class MyCircle extends MyShape {
    private ColorPicker cpFill;

    private double centerX, centerY, endX, endY;

    private Circle circle = new Circle();

    public MyCircle(){}


    public void setFill(ColorPicker colorPicker){
        cpFill = colorPicker;
    }

    public void setCenterPoint(double centerX, double centerY){
        this.centerX = centerX;
        this.centerY = centerY;

        circle.setCenterX(centerX);
        circle.setCenterY(centerY);
    }

    public void setEndPoint(double endX, double endY){
        this.endX = endX;
        this.endY = endY;
    }

    public void setRadius(){
        circle.setRadius((Math.abs(endX - centerX) + Math.abs(endY - centerY)) / 2);
    }

    public void setRadius(double radius){
        circle.setRadius(radius);
    }

    public boolean containsPoint(Point2D point){
        return circle.contains(point);
    }

    @Override
    public void check(){
        if(centerX > endX) {
            circle.setCenterX(endX);
        }
        if(centerY > endY) {
            circle.setCenterY(endY);
        }
    }

    @Override
    public void draw(){
        graphicsContext.setStroke(cpLine.getValue());
        graphicsContext.setFill(cpFill.getValue());

        graphicsContext.fillOval(circle.getCenterX(), circle.getCenterY(), circle.getRadius(), circle.getRadius());
        graphicsContext.strokeOval(circle.getCenterX(), circle.getCenterY(), circle.getRadius(), circle.getRadius());
    }

}