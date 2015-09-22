package src;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    private BufferedReader in;
    private DataOutputStream out;
    private Socket s;
    private boolean cut=false,send=false;
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
                            send=true;
                        } catch (IOException ex) {System.out.println("30");
                            
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
                            out.write(new byte[]{'R','\n'});
                        } catch (IOException ex) {System.out.println("42");}
                    }
                }
            });
            inT.start();
            outT.start();
        } catch (IOException ex) {
            System.out.println("49");
        }
    }
    public void cut()
    {
        cut=true;
    }
}
