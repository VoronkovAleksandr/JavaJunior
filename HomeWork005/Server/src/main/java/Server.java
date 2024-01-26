import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final ServerSocket serverSocket;
    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    public void runServer(){
        try {
            while (!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("Подключен новый клиент");
                ClientService clientService = new ClientService(socket);
                Thread thread = new Thread(clientService);
                thread.start();
            }
        }catch (IOException e){
            closeSocket();
        }
    }

    private void closeSocket(){
        try {
            if (serverSocket!= null) serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
