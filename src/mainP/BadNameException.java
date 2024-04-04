package mainP;

public class BadNameException extends Exception{
	
	public BadNameException(String s) {
		super(s);
	}
	
	public BadNameException() {
		super("BadNameException Error");
	}
}