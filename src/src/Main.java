package src;

import java.io.IOException;
import java.net.ServerSocket;

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
                    welcome=new ServerSocket(666);
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
                    }
                }
            }
        });
        incoming.start();
    }
}