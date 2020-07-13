import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TestConfig {

	public static void main(String[] args) throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry(); 		
		DBRemote stub_s4 = (DBRemote) registry.lookup("Hello4");
        DBRemote stub_s2 = (DBRemote) registry.lookup("Hello2"); // client 1
        DBRemote stub_s3 = (DBRemote) registry.lookup("Hello3");
        DBRemote stub_s1 = (DBRemote) registry.lookup("Hello1");
        DBRemote stub_s5 = (DBRemote) registry.lookup("Hello5");

        DBRemote stub_arr[] = new DBRemote[5]; 
        stub_arr[0] = stub_s1;
        stub_arr[1] = stub_s2;
        stub_arr[2] = stub_s3;
        stub_arr[3] = stub_s4;
        stub_arr[4] = stub_s5;
		if(args[0].equals("STOP")){
			System.out.println("Stopping writes");
			for(int i=0; i <5;i++) {
				stub_arr[i].notSafe();
			}
			
		}
		else {
		System.out.println("Setting Sleeps:");
		
		Config.p1sleep = Integer.parseInt(args[0]);
		Config.p4sleep = Integer.parseInt(args[1]);

		System.out.println("Set Sleeps p1: "+Config.p1sleep +"  p4: "+Config.p4sleep);;
		}
		
	}
	
}