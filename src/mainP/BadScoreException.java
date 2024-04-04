package mainP;

public class BadScoreException extends Exception{
	
	public BadScoreException(String s) {
		super(s);
	}
	
	public BadScoreException() {
		super("BadScoreException Error");
	}
}