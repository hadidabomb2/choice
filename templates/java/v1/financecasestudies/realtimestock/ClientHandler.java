package financecasestudies.realtimestock;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private final WebSocketPriceFeed feed;
    private InputStream input;
    private OutputStream output;
    private volatile boolean connected = false;

    public ClientHandler(Socket socket, WebSocketPriceFeed feed) {
        this.socket = socket;
        this.feed = feed;
    }

    @Override
    public void run() {
        try {
            input = socket.getInputStream();
            output = socket.getOutputStream();
            
            if (performHandshake()) {
                connected = true;
                System.out.println("Client connected: " + socket.getInetAddress());
                readMessages();
            }
        } catch (IOException e) {
            System.err.println("Client handler error: " + e.getMessage());
        } finally {
            close();
        }
    }

    private boolean performHandshake() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line;
        String key = null;
        
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            if (line.startsWith("Sec-WebSocket-Key:")) {
                key = line.substring(19).trim();
            }
        }
        
        if (key == null) {
            System.err.println("Invalid WebSocket handshake");
            return false;
        }
        
        String magic = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
        String hashInput = key + magic;
        String hash = base64Encode(sha1(hashInput));
        
        String response = "HTTP/1.1 101 Switching Protocols\r\n" +
                        "Upgrade: websocket\r\n" +
                        "Connection: Upgrade\r\n" +
                        "Sec-WebSocket-Accept: " + hash + "\r\n\r\n";
        
        output.write(response.getBytes(StandardCharsets.UTF_8));
        output.flush();
        
        System.out.println("WebSocket handshake completed for " + socket.getInetAddress());
        return true;
    }

    private void readMessages() throws IOException {
        byte[] buffer = new byte[1024];
        
        while (connected) {
            int bytesRead = input.read(buffer);
            if (bytesRead == -1) {
                break;
            }
            
            if (bytesRead > 2) {
                int opcode = buffer[0] & 0x0f;
                
                if (opcode == 1) {  // Text frame
                    // Client sent text message
                } else if (opcode == 8) {  // Close frame
                    break;
                }
            }
        }
    }

    public void send(byte[] frame) throws IOException {
        if (connected && output != null) {
            synchronized (output) {
                output.write(frame);
                output.flush();
            }
        }
    }

    public void close() {
        connected = false;
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        feed.removeClient(this);
        System.out.println("Client disconnected: " + socket.getInetAddress());
    }

    private byte[] sha1(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            return digest.digest(input.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String base64Encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }
}