package mainP;

public class MissingFieldsException extends Exception{
	
	public MissingFieldsException(String s) {
		super(s);
	}
	
	public MissingFieldsException() {
		super("MissingFieldsException");
	}
}