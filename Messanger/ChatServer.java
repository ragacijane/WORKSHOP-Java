package Messanger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

public class ChatServer {
    static Vector ClientSockets;
    static Vector LoginNames;

    ChatServer() throws IOException {
        ServerSocket server = new ServerSocket(5217);
        ClientSockets = new Vector();
        LoginNames = new Vector();

        while(true){
            Socket client = server.accept();
            AcceptClient acceptClient = new AcceptClient(client);
        }
    }
    class AcceptClient extends Thread{
        Socket ClientSocket;
        DataInputStream din;
        DataOutputStream dout;
        AcceptClient(Socket client) throws IOException {
            ClientSocket = client;
            din = new DataInputStream(ClientSocket.getInputStream());
            dout = new DataOutputStream(ClientSocket.getOutputStream());

            String LoginName = din.readUTF();

            LoginNames.add(LoginName);
            ClientSockets.add(ClientSocket);

            start();
        }

        public void run(){
            while(true){
                try {
                    String msgFromClient = din.readUTF();
                    StringTokenizer st = new StringTokenizer(msgFromClient);
                    String LoginName = st.nextToken();
                    String msgType = st.nextToken();
                    String msg = "";
                    int loggedOutIndex=-1;

                    while(st.hasMoreTokens()){
                        msg=msg + " "+ st.nextToken();
                    }

                    if(msgType.equals("LOGIN")) {
                        for (int i = 0; i < LoginNames.size(); i++) {
                            Socket pSocket = (Socket) ClientSockets.elementAt(i);
                            DataOutputStream pOut = new DataOutputStream(pSocket.getOutputStream());
                            pOut.writeUTF(LoginName + " has logged in.");
                        }
                    }
                    else if(msgType.equals("LOGOUT")) {
                        for (int i = 0; i < LoginNames.size(); i++) {
                            if(LoginName.equals(LoginNames.elementAt(i)))
                                loggedOutIndex=i;
                            Socket pSocket = (Socket) ClientSockets.elementAt(i);
                            DataOutputStream pOut = new DataOutputStream(pSocket.getOutputStream());
                            pOut.writeUTF(LoginName + " has logged out.");
                        }
                        if(loggedOutIndex >= 0){
                            LoginNames.removeElementAt(loggedOutIndex);
                            ClientSockets.removeElementAt(loggedOutIndex);
                            loggedOutIndex=-1;
                        }
                    }
                    else {
                        for (int i = 0; i < LoginNames.size(); i++) {
                            Socket pSocket = (Socket) ClientSockets.elementAt(i);
                            DataOutputStream pOut = new DataOutputStream(pSocket.getOutputStream());
                            pOut.writeUTF(LoginName + ": "+msg);
                        }
                    }
                    if(msgType.equals("LOGOUT"))
                        break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ChatServer chatServer= new ChatServer();
    }
}

