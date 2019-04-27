package final_project;

public class UnauthorizedException extends Exception {
	
	public UnauthorizedException()
	{
		System.out.println("Transaction is not verified");
	}
}
