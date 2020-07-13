
public class TestConfig {

	public static void main(String[] args) {
		System.out.println("Setting Sleeps:");
		
		Config.p1sleep = Integer.parseInt(args[0]);
		Config.p4sleep = Integer.parseInt(args[1]);

		System.out.println("Set Sleeps p1: "+Config.p1sleep +"  p4: "+Config.p4sleep);;
		
	}
	
}
