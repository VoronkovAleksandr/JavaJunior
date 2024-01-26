import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ClientService implements Runnable {
    private final String WELCOME_MESSAGE = "Добро пожаловать в чат.\n" +
            "Чтобы отправить сообщение введите его в поле ввода\n" +
            "Чтобы отправить личное сообщение начните его с @<имя>";
    public static ArrayList<ClientService> clients = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;

    public ClientService(Socket socket) {
        try {
            this.socket = socket;
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            name = bufferedReader.readLine();
            clients.add(this);
            System.out.printf("%s подключился к чату.\n", name);
            broadcastMessage(String.format("Server: %s подключился к чату", name));
            privateMessage(this, WELCOME_MESSAGE);
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    private void removeClient() {
        clients.remove(this);
        System.out.printf("%s покинул к чату.\n", name);
        broadcastMessage(String.format("Server: %s покинул к чату", name));
    }


    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        removeClient();
        try {
            if (bufferedReader != null) bufferedReader.close();
            if (bufferedWriter != null) bufferedWriter.close();
            if (socket != null) socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String messageFromClient;
        String nameForPrivateMessage;
        String privateMessage;
        while (socket.isConnected()) {
            try {
                messageFromClient = bufferedReader.readLine();

                if (messageFromClient == null) {
                    closeEverything(socket, bufferedReader, bufferedWriter);
                    break;
                }
                if (messageFromClient.contains("@")) {
                    nameForPrivateMessage = Arrays.stream(messageFromClient.split(" "))
                            .filter(s -> s.startsWith("@"))
                            .findFirst()
                            .get()
                            .replace("@", "");
                    privateMessage = Arrays.stream(messageFromClient.split(" "))
                            .filter(s -> !s.startsWith("@"))
                            .collect(Collectors.joining(" "));
                    privateMessage = "(private) " + privateMessage;
                    privateMessage(getClient(nameForPrivateMessage), privateMessage);
                } else {
                    broadcastMessage(messageFromClient);
                }
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    private void broadcastMessage(String message) {
        for (ClientService client :
                clients) {
            try {
                if (!client.name.equals(name) && message != null) {
                    client.bufferedWriter.write(message);
                    client.bufferedWriter.newLine();
                    client.bufferedWriter.flush();
                }
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }

        }
    }

    private void privateMessage(ClientService client, String message) {
        if (client != null) {
            try {
                client.bufferedWriter.write(message);
                client.bufferedWriter.newLine();
                client.bufferedWriter.flush();
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        } else {
            privateMessage(this, "Server: запрашиваемого клиента нет в чате");
        }
    }

    private ClientService getClient(String clientName) {
        return clients.stream()
                .filter(client -> client.name.equals(clientName))
                .findFirst()
                .orElse(null);
    }
}
