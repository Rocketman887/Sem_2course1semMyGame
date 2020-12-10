package connection;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TCPConnection {
    //Socket, связанный с соединением
    private final Socket socket;
    //Поток, который слушает входящие сообщения, читает поток ввода
    private final Thread rxThread;
    //слушатель событий
    private final TCPConnectionListener eventListener;
    private final BufferedReader in;
    private final BufferedWriter out;
    //Socket создается внутри

    public TCPConnection(TCPConnectionListener eventListener, String ipAddress, int port)throws IOException {
        //из одного конструктора вызываем другой
        this(new Socket(ipAddress,port), eventListener);
    }

    //Socket создается снаружи
    public TCPConnection(Socket socket,TCPConnectionListener eventListener) throws IOException {
        this.eventListener = eventListener;
        //создается соединение с Socket
        this.socket = socket;
        //входящие и исходящие потоки Socket
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
        this.rxThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //делаю ссылку на себя
                    //если передать this - то получится ссылка на анонимный класс Runnable
                    //TCPConnection.this - передали экземпляр обрамляющего класса
                    eventListener.onConnectionReady(TCPConnection.this);
                    //любое сетевое взаимодействие - бесконечный цикл
                    //пока поток не прерван - получаем строчку и передаем слушателю TCP
                    while (!rxThread.isInterrupted()) {
                        String message = in.readLine();
                        eventListener.onReceiveString(TCPConnection.this,message);
                    }
                } catch (IOException e) {
                    eventListener.onException(TCPConnection.this,e);
                }
                finally {
                    eventListener.onDisconnect(TCPConnection.this);
                }
            }
        });
        rxThread.start();
    }
    //метод отправления сообшения
    public synchronized void sendMessage(String value){
        try {
            //нужно добавить символы конца строки
            out.write(value + "\r\n");
            out.flush();
            //если сообшщение передать не получилось
        } catch (IOException e) {
            eventListener.onException(TCPConnection.this,e);
            //прерываем соединение
            disconnect();
        }
    }
    //метод для прерывания соединения
    public synchronized void disconnect() {
        rxThread.interrupt();
        try {

            socket.close();
        } catch (IOException e) {
            eventListener.onException(TCPConnection.this,e);
        }
    }

    @Override
    public String toString() {
        return "TCPConnection: " + socket.getInetAddress() + "Port: " + socket.getPort();
    }
}
