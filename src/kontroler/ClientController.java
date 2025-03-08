package kontroler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import requestResponse.Request;
import requestResponse.Response;

public class ClientController {

    private static ClientController instance;
    private Socket s;
    private int port;
    private User user;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    // moram negde u kodu na pocetku ovaj da pozovme prvi -> kod login forme
    public static ClientController getInstance(int port) {
        if (instance == null) {
            instance = new ClientController(port);
        }
        return instance;
    }
    
    public static ClientController getInstance() {
        return instance;
    }

    private ClientController(int port) {
        try {
            s = new Socket("localhost", port);
            oos = new ObjectOutputStream(s.getOutputStream());
            ois = new ObjectInputStream(s.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Response readReponse() {
        try {
            return (Response) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void sendRequest(Request request) {
        try {
            oos.writeObject(request);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    

}
