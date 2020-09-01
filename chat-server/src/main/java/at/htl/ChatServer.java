package at.htl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

@ServerEndpoint("/chat/{username}")
@ApplicationScoped
public class ChatServer {

    static Logger log;
    {
        log = Logger.getLogger(ChatServer.class.getName());
    }

    ObjectMapper objectMapper = new ObjectMapper();
    Map<String, Session> sessions = new ConcurrentHashMap<>();

    // OnOpen
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        sessions.put(username, session);
        log.info("User " + username + " joined!");
        broadcast(new Message(username, "joined"));
    }

    // OnClose
    @OnClose
    public void onClose(Session session, @PathParam("username") String username) {
        sessions.remove(username);
        log.info("User " + username + " left!");
        broadcast(new Message(username, "left"));
    }

    // OnError
    @OnError
    public void onError(Session session, @PathParam("username") String username, Throwable throwable) {
        sessions.remove(username);
        log.info("User " + username + " left on Error! " + throwable.getMessage());
    }

    // OnMessage
    /*
    {
        sender: "Max",
        message: "Hallo"
    }
     */
    @OnMessage
    public void onMessage(String message, @PathParam("username") String username) {
        try {
            Message m = objectMapper.readValue(message, Message.class);
            broadcast(m);
        } catch (JsonProcessingException e) {
            log.severe(e.getMessage());
        }
        log.info("Message from " + username + ": " + message);
    }

    private void broadcast(Message msg) {
        String msgString = "";
        try {
            msgString = objectMapper.writeValueAsString(msg);
            String sendMsg = msgString;
            sessions.values().forEach(
                    s -> {
                        s.getAsyncRemote().sendObject(
                                sendMsg, result -> {
                                    if (result.getException() != null) {
                                        log.severe(result.getException().getMessage());
                                    }
                                }
                        );
                    }
            );
        } catch (JsonProcessingException e) {
            log.severe(e.getMessage());
        }

    }
}

@RegisterForReflection
class Message {
    String sender;
    String message;

    public Message(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public Message() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
