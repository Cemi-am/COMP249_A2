package mainP;

public class ExcessFieldsException extends Exception{
	
	public ExcessFieldsException(String s) {
		super(s);
	}
	
	public ExcessFieldsException() {
		super("ExcessFieldsException");
	}
}