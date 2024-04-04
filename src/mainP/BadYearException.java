package mainP;

public class BadYearException extends Exception{
	
	public BadYearException(String s) {
		super(s);
	}
	
	public BadYearException() {
		super("BadYearException Error");
	}
}