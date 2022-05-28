package utils;

import io.socket.client.IO;
import io.socket.client.Socket;
import java.net.URISyntaxException;

/**
 *
 * @author nateesun
 */
public class SyncSocketClient {

//    public static void main(String[] args) throws URISyntaxException {
//        IO.Options options = new IO.Options();
//        options.transports = new String[]{"websocket"};
//        options.reconnectionAttempts = 10;
//        options.reconnectionDelay = 1000;
//        options.timeout = 500;
//        Socket socket = IO.socket("http://localhost:5000", options);
//        socket.on(Socket.EVENT_CONNECT, objects -> {
//            socket.emit("sub", "I'm a subscriber");
//            System.out.println("client:" + "successful connection");
//        });
//        socket.on(Socket.EVENT_CONNECT, objects -> System.out.println("client:" + "in connection"));
//        socket.on(Socket.EVENT_DISCONNECT, objects -> System.out.println("client:" + "connection timeout"));
//        socket.on(Socket.EVENT_CONNECT_ERROR, objects -> System.out.println("client:" + "connection failure"));
//        socket.connect();
//    }
    
    public static void main(String[] args) throws URISyntaxException {
        Socket s = IO.socket("http://localhost:5000");
        s.on(Socket.EVENT_CONNECT, (Object... os) -> {
            System.out.println("Online");
        });
        
        s.on(Socket.EVENT_DISCONNECT, (Object... os) -> {
            System.out.println("Offline");
        });
        
        s.on("getBranch", (Object... os) -> {
            System.out.println("Message from server: " + os[0].toString());
        });
        
        s.open();
    }
}
