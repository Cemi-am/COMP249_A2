package mainP;

public class BadRatingException extends Exception{
	
	public BadRatingException(String s) {
		super(s);
	}
	
	public BadRatingException() {
		super("BadRatingException");
	}
}