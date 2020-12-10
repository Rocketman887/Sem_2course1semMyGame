package connection;

import connection.TCPConnection;

public interface TCPConnectionListener {
    //соединение готово
    void onConnectionReady(TCPConnection tcpConnection);
    //получение строки строчку
    void onReceiveString(TCPConnection tcpConnection, String value);
    //дисконнект
    void onDisconnect(TCPConnection tcpConnection);
    //исключения
    void onException(TCPConnection tcpConnection, Exception e);
}
