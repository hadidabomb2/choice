package financecasestudies.realtimestock;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;

public class WebSocketPriceFeed implements PriceFeed {
    private final int port;
    private ServerSocket serverSocket;
    private final Set<ClientHandler> clients = ConcurrentHashMap.newKeySet();
    private volatile boolean running = false;
    private Thread serverThread;

    public WebSocketPriceFeed(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        if (running) return;
        
        running = true;
        serverSocket = new ServerSocket(port);
        System.out.println("WebSocket server started on ws://localhost:" + port);
        
        serverThread = new Thread(() -> {
            try {
                while (running) {
                    Socket clientSocket = serverSocket.accept();
                    ClientHandler handler = new ClientHandler(clientSocket, this);
                    clients.add(handler);
                    new Thread(handler).start();
                }
            } catch (SocketException e) {
                if (!running) {
                    System.out.println("Server stopped");
                } else {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        serverThread.start();
    }

    public void stop() throws IOException {
        running = false;
        if (serverSocket != null && !serverSocket.isClosed()) {
            serverSocket.close();
        }
        clients.forEach(ClientHandler::close);
    }

    @Override
    public void subscribe(PriceListener listener) {
        // Not used in WebSocket version
    }

    @Override
    public void unsubscribe(PriceListener listener) {
        // Not used in WebSocket version
    }

    @Override
    public void publish(PriceUpdate update) {
        Objects.requireNonNull(update, "update");
        
        String json = serializeToJson(update);
        byte[] payload = json.getBytes(StandardCharsets.UTF_8);
        byte[] frame = createWebSocketFrame(payload);
        
        System.out.println("Broadcasting: " + json + " to " + clients.size() + " clients");
        
        for (ClientHandler client : clients) {
            try {
                client.send(frame);
            } catch (IOException e) {
                System.err.println("Failed to send to client: " + e.getMessage());
                clients.remove(client);
                client.close();
            }
        }
    }

    private byte[] createWebSocketFrame(byte[] payload) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        
        try {
            // FIN bit + opcode (0x81 = text frame)
            dos.writeByte(0x81);
            
            // Payload length
            if (payload.length < 126) {
                dos.writeByte(payload.length);
            } else if (payload.length < 65536) {
                dos.writeByte(126);
                dos.writeShort(payload.length);
            } else {
                dos.writeByte(127);
                dos.writeLong(payload.length);
            }
            
            // Payload data
            dos.write(payload);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return baos.toByteArray();
    }

    private String serializeToJson(PriceUpdate update) {
        return "{\"symbol\":\"" + update.getSymbol() + 
               "\",\"price\":" + update.getPrice() + 
               ",\"timestamp\":" + update.getTimestamp() + "}";
    }

    public void removeClient(ClientHandler handler) {
        clients.remove(handler);
    }
}