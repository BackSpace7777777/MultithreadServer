package src;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private BufferedReader in;
    private DataOutputStream out;
    private Socket s;
    private boolean cut=false;
    public Client(Socket inSocket)
    {
        try {
            s=inSocket;
            System.out.println(s);
            in=new BufferedReader(new InputStreamReader(s.getInputStream()));
            out=new DataOutputStream(s.getOutputStream());
            Thread inT=new Thread(new Runnable() {
                public void run() {
                    while(true)
                    {
                        if(cut)break;
                        try {
                            System.out.println(in.readLine());
                        } catch (IOException ex) {System.out.println(ex);}
                    }
                }
            });
            Thread outT=new Thread(new Runnable() {
                public void run() {
                    while(true)
                    {
                        if(cut)break;
                    }
                }
            });
            inT.start();
            outT.start();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    public void cut()
    {
        cut=true;
    }
}
