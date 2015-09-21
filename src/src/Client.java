package src;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
    private BufferedReader in;
    private DataOutputStream out;
    private Socket s;
    public Client(Socket inSocket)
    {
        s=inSocket;
    }
    public void cut()
    {
        
    }
}
