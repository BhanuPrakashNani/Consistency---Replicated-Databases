import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class Server extends UnicastRemoteObject implements ServerInterface {
    Vector<Process> writers;
    String line = "----------------------------------------\n";

    public Server() throws RemoteException {
        super();
        writers = new Vector<>(10,1);
    }

    public static void main(String args[]){
        initialize();
    }

    public static void initialize(){
        try{
            Server server = new Server();
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://localhost:5000/main", server);
            System.out.println("[Server]: Server Initialized");
        } catch(Exception e){
            System.out.println("Server Failed to start");
        }
    }
    public void connect(String nickname) {
        registerWriter(nickname);
        sendToAll("[Server] : " + nickname + " connected");
    }

    public void sendMessage(String nickname, String message) {
        message = "[" + nickname + "]: " + message;
        sendToAll(message);
    }
    public void sendStudent(Student student){
        sendStudentToAll(student);
    }
    private void registerWriter(String nickname) {
        try {
            WriteInterface writer = (WriteInterface) Naming.lookup("rmi://localhost:5000/"+nickname);
            writers.addElement(new Process(nickname, writer));
            writer.messageFromServer("[Server]: Connected, proceed");
        } catch (Exception e) {
        System.out.println("Cannot register: "+nickname);
    }
    }

    public void sendToAll(String message) {
        for(int i=0; i<writers.size();i++){
            try {
                System.out.println(writers.get(i));
                writers.get(i).getWriter().messageFromServer(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendStudentToAll(Student student) {
        for(int j=0; j<writers.size();j++){
            try {
                Random rnd = new Random();
                int delay = (rnd.nextInt(200) + 1);
                Thread.sleep(delay);
                writers.get(j).getWriter().studentFromServer(student);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

    public void disconnect(String nickname) {
		for(Process w : writers){
			if(w.getNickname().equals(nickname)) {
				System.out.println(line + nickname + " left the session");
				writers.remove(w);
				break;
			}
        }
	}
}
