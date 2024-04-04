package mainP;

public class BadDurationException extends Exception{
	
	public BadDurationException(String s) {
		super(s);
	}
	
	public BadDurationException() {
		super("BadDurationException Error");
	}
}