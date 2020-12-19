package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import shapes.*;

public class DrawController {

    public MenuItem straightLine;
    public MenuItem rectangle;
    public MenuBar menuBar;
    public MenuItem scribble;
    public MenuItem square;
    public MenuItem circle;
    public MenuItem black;
    public MenuItem blue;
    public MenuItem red;
    public MenuItem yellow;
    public MenuItem green;
    public MenuItem white;
    public MenuItem pink;
    public MenuItem clear;
    public MenuItem gettingStartedMenuItem;
    private String mode, color;

    //default
    private GraphicsContext g;
    private ColorPicker cpLine = new ColorPicker(Color.BLACK);
    private ColorPicker cpFill = new ColorPicker(Color.BLACK);

    private MyScribble myScribble;
    private MyLine myLine;
    private MyRectangle myRectangle;
    private MySquare mySquare;
    private MyCircle myCircle;

    @FXML
    private Canvas drawingCanvas;

    @FXML
    void openGettingStarted(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Crocodile");
        alert.setHeaderText("Rules");

        alert.setContentText("");

        alert.show();
    }



    @FXML
    void setColorBlack(ActionEvent event) {
        color = "black";

        cpLine = new ColorPicker(Color.BLACK);
        cpFill = new ColorPicker(Color.BLACK);
    }

    @FXML
    void setColorBlue(ActionEvent event) {
        color = "blue";

        cpLine = new ColorPicker(Color.BLUE);
        cpFill = new ColorPicker(Color.BLUE);
    }

    @FXML
    void setColorRed(ActionEvent event) {
        color = "red";

        cpLine = new ColorPicker(Color.RED);
        cpFill = new ColorPicker(Color.RED);
    }
    @FXML
    void setColorYellow(ActionEvent event) {
        color = "yellow";

        cpLine = new ColorPicker(Color.YELLOW);
        cpFill = new ColorPicker(Color.YELLOW);
    }
    @FXML
    void setColorGreen(ActionEvent event) {
        color = "green";

        cpLine = new ColorPicker(Color.GREEN);
        cpFill = new ColorPicker(Color.GREEN);
    }
    @FXML
    void setColorWhite(ActionEvent event) {
        color = "pink";

        cpLine = new ColorPicker(Color.WHITE);
        cpFill = new ColorPicker(Color.WHITE);
    }
    @FXML
    void setColorPink(ActionEvent event) {
        color = "green";

        cpLine = new ColorPicker(Color.PINK);
        cpFill = new ColorPicker(Color.PINK);
    }





    @FXML
    void setModeRectangle(ActionEvent event) {
        mode = "rectangle";
    }

    @FXML
    void setModeScribble(ActionEvent event) {
        mode = "scribble";
    }

    @FXML
    void setModeSquare(ActionEvent event) {
        mode = "square";
    }

    @FXML
    void setModeStraightLine(ActionEvent event) {
        mode = "straight line";
    }

    @FXML
    void setModeCircle(ActionEvent event) {
        mode = "circle";
    }


    @FXML
    void clearCanvas(ActionEvent event) {
        g = drawingCanvas.getGraphicsContext2D();
        g.clearRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());
    }



    @FXML
    void startDraw(MouseEvent event) {
        g = drawingCanvas.getGraphicsContext2D();
        g.setStroke(cpLine.getValue());

        if (mode.equals("scribble")) {        // Scribble
            g.setStroke(cpLine.getValue());
            g.beginPath();
            g.lineTo(event.getX(), event.getY());

            myScribble = new MyScribble();

            myScribble.setGraphicsContext(drawingCanvas.getGraphicsContext2D());
            myScribble.setColor(cpLine);
            myScribble.setStartPoint(event.getX(), event.getY());
        } else if (mode.equals("straight line")) {      // Straight Line
            g.setStroke(cpLine.getValue());

            myLine = new MyLine();

            myLine.setGraphicsContext(g);
            myLine.setColor(cpLine);
            myLine.setStartPoint(event.getX(), event.getY());
        } else if (mode.equals("rectangle")) {      // Rectangle
            g.setStroke(cpLine.getValue());
            g.setFill(cpFill.getValue());

            myRectangle = new MyRectangle();

            myRectangle.setGraphicsContext(g);
            myRectangle.setColor(cpLine);
            myRectangle.setFill(cpFill);
            myRectangle.setStartPoint(event.getX(), event.getY());
        } else if (mode.equals("square")) {         // Square
            g.setStroke(cpLine.getValue());
            g.setFill(cpFill.getValue());

            mySquare = new MySquare();

            mySquare.setGraphicsContext(g);
            mySquare.setColor(cpLine);
            mySquare.setFill(cpFill);
            mySquare.setStartPoint(event.getX(), event.getY());
        } else if (mode.equals("circle")) {        // Circle
            g.setStroke(cpLine.getValue());
            g.setFill(cpFill.getValue());

            myCircle = new MyCircle();

            myCircle.setGraphicsContext(g);
            myCircle.setColor(cpLine);
            myCircle.setFill(cpFill);
            myCircle.setCenterPoint(event.getX(), event.getY());
        }
    }

    @FXML
    void drag(MouseEvent event) {
        if (mode.equals("scribble")) {        // Scribble
            g.lineTo(event.getX(), event.getY());
            g.stroke();

            myScribble.addPoint(event.getX(), event.getY());
        }
    }


    @FXML
    void endDraw(MouseEvent event) {

        if (mode.equals("scribble")) {
            g.lineTo(event.getX(), event.getY());
            g.stroke();

            myScribble.setEndPoint(event.getX(), event.getY());


        } else if (mode.equals("straight line")) {
            myLine.setEndPoint(event.getX(), event.getY());

            myLine.draw();


        } else if (mode.equals("rectangle")) {
            myRectangle.setEndPoint(event.getX(), event.getY());
            myRectangle.setWidth();
            myRectangle.setHeight();
            myRectangle.check();

            myRectangle.draw();

        } else if (mode.equals("square")) {             // Square
            mySquare.setEndPoint(event.getX(), event.getY());
            mySquare.setWidth();
            mySquare.setHeight();
            mySquare.check();

            mySquare.draw();


        } else if (mode.equals("circle")) {
            myCircle.setEndPoint(event.getX(), event.getY());
            myCircle.setRadius();
            myCircle.check();

            myCircle.draw();

        }
    }
}



