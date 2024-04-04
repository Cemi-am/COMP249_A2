package mainP;

public class MissingQuotesException extends Exception{
	
	public MissingQuotesException(String s) {
		super(s);
	}
	
	public MissingQuotesException() {
		super("MissingQuotesException");
	}
}