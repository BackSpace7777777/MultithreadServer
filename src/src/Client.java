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
    private boolean cut=false,send=false;
    private String inS;
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
                            inS=in.readLine();
                            if(inS!=null)
                            {
                                System.out.println(inS);
                                send=true;
                            }
                        } catch (IOException ex) {System.out.println(1);cut();
                        }
                    }
                }
            });
            Thread outT=new Thread(new Runnable() {
                public void run() {
                    while(true)
                    {
                        if(cut)break;
                        if(send)try {
                            out.write(new byte[]{'G','u','e','s','s',' ','w','h','a','t','\n'});
                            send=false;
                        } catch (IOException ex) {System.out.println(2);cut();}
                    }
                }
            });
            inT.start();
            outT.start();
        } catch (IOException ex) {
            System.out.println(3);
        }
    }
    private void cut()
    {
        cut=true;
        try {
            in.close();
            out.close();
            s.close();
            Main.killClient(s);
        } catch (IOException ex) {System.out.println(4);}
    }
    public Socket returnSocket()
    {
        return s;
    }
    public boolean isConnected()
    {
        return s.isConnected();
    }
}
