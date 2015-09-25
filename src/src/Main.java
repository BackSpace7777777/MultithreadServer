package src;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static Client c[]=new Client[5];
    public static ServerSocket welcome;
    public static void main(String[] args) {
        for(int i=0;i<c.length;i++)
        {
            c[i]=null;
        }
        Thread incoming=new Thread(new Runnable() {
            public void run() {
                try {
                    welcome=new ServerSocket(25565);
                } catch (IOException ex) {System.out.println(ex);}
                while(true)
                {
                    for(int i=0;i<c.length;i++)
                    {
                        if(c[i]==null)
                        {
                            try {
                                c[i]=new Client(welcome.accept());
                            } catch (IOException ex) {System.out.println(27);}catch(NullPointerException ex){}
                        }
                        for(int cli=0;cli<c.length;cli++)
                        {
                            try
                            {
                                System.out.println(cli + " " + c[cli].isConnected());
                            }
                            catch(NullPointerException ex)
                            {
                                System.out.println(cli + " " + false);
                            }
                        }
                    }
                }
            }
        });
        incoming.start();
    }
    public static void killClient(Socket sock)
    {
        for(int i=0;i<c.length;i++)
        {
            if(sock==c[i].returnSocket())
            {
                c[i]=null;
                break;
            }
        }
    }
}