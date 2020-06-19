package threads;
import java.io.*;
import java.net.Socket;


public class WorkerRunnable implements Runnable{

    protected Socket clientSocket = null;

    public WorkerRunnable(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader input  = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
            while (true){
                String str = input.readLine();
                if(str.equals("Bue")){
                    break;
                }
               // System.out.println(str);
                output.println(str);
            }
            //output.close();
            //input.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
