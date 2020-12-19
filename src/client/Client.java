package client;

import connection.TCPConnection;
import connection.TCPConnectionListener;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class Client extends Application implements TCPConnectionListener {
    private static final String ip_address = "176.59.104.155";
    private static final int port = 8022;
    private static final String painterPATH = "/sample/scenes/draw.fxml";

    private TCPConnection connection;
    @FXML
    public TextField input;
    @FXML
    public TextArea text;
    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            connection = new TCPConnection(this, ip_address, port);
        }catch (IOException e){
            onException(connection,e);
        }
        input = new TextField();
        text = new TextArea();
        Parent root = FXMLLoader.load(getClass().getResource(painterPATH));
        primaryStage.setTitle("Crocodile");
        primaryStage.setScene(new Scene(root, 1000, 650));
        primaryStage.show();

    }


    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {
        printMessage("Connection ready...");
    }

    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) {
        printMessage(value);
    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
        printMessage("Connection close...");
    }

    @Override
    public void onException(TCPConnection tcpConnection, Exception e) {
        printMessage("Connection exception: "+ e);
    }
    private synchronized void printMessage(String message){
        Platform.runLater(() ->{
            text.append(message);
        });
    }
    public static void main(String[] args) {

        launch(args);
    }

}
