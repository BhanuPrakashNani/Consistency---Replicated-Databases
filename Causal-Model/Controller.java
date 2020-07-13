import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class Controller extends UnicastRemoteObject implements WriteInterface {
    protected ServerInterface serverInterface;

    public Controller(ServerInterface serverInterface) throws RemoteException {
        super();
        this.serverInterface = serverInterface;
    }

    public static void initialize(Controller controller) {
        try {
            Naming.rebind("rmi://localhost:5000/controller", controller);
        } catch (Exception e) {
            System.out.println("Failed initializing controller");
        }
    }

    public static void main(String[] args) {
        Controller controller = null;
        Scanner scan = new Scanner(System.in);
        try {
            ServerInterface serverInterface = (ServerInterface) Naming.lookup("rmi://localhost:5000/main");
            controller = new Controller(serverInterface);
        } catch (Exception e) {
            System.out.println("Failed initializing controller");
        }
        if (controller == null) {
            System.out.println("Try again");
            scan.close();
            return;
        }
        initialize(controller);
        try {
            controller.serverInterface.connect("controller");
        } catch (Exception e) {
            System.out.println("Failed connecting controller");
        }
        System.out.println("Enter your command: give exit to kill everyone.");
        while (true) {
            String message = scan.nextLine();
            try {
                controller.serverInterface.sendMessage("controller", message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            if(message.equals("exit"))
                break;
        }
        scan.close();
        System.exit(0);

    }
    @Override
    public void messageFromServer(String message) throws RemoteException {
        // TODO Auto-generated method stub

    }

    @Override
    public void studentFromServer(Student student) throws RemoteException {
        // TODO Auto-generated method stub

    }

}
