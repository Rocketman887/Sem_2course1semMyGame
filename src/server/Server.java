package server;

import connection.TCPConnection;
import connection.TCPConnectionListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server implements TCPConnectionListener {
    public static void main(String[] args) {
        new Server();
    }
    //список соединений
    private final ArrayList<TCPConnection> connections = new ArrayList<>();

    private Server() {
        System.out.println("Server is running...");
        //класс ServerSocket умеет слушать порт и принимать входящее соединение
        try (ServerSocket serverSocket = new ServerSocket(8022)) {
            while (true){
                //здесь try ловит исключения при подключении клиентов
                try {
                    //объект Socket при входящем соединении возвращает метод Accept()
                    new TCPConnection(serverSocket.accept(),this);
                }
                catch (IOException e){
                    System.out.println("TCPConnection exception: "+e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void onConnectionReady(TCPConnection tcpConnection) {
        connections.add(tcpConnection);
        toAllConnections("Client "+ tcpConnection + " connected");
    }

    @Override
    public synchronized void onReceiveString(TCPConnection tcpConnection, String value) {
        toAllConnections(value);
    }

    @Override
    public synchronized void onDisconnect(TCPConnection tcpConnection) {
        connections.remove(tcpConnection);
        toAllConnections("Client "+ tcpConnection + " disconnected");

    }

    @Override
    public synchronized void onException(TCPConnection tcpConnection, Exception e) {
        //пишем в консоль о случившемся исключении
        System.out.println("TCPConnection exception: " + e);
    }
    //общий метод, который рассылает сообщение всем
    private void toAllConnections(String value){
        System.out.println(value);
        for (TCPConnection connection:connections){
            connection.sendMessage(value);
        }
    }
}
