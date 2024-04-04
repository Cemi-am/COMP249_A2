package mainP;

public class BadTitleException extends Exception{
	
	public BadTitleException(String s) {
		super(s);
	}
	
	public BadTitleException() {
		super("BadTitleException Error");
	}
}