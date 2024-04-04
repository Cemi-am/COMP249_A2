package mainP;

public class BadGenreException extends Exception{
	
	public BadGenreException(String s) {
		super(s);
	}
	
	public BadGenreException() {
		super("BadGenreException Error");
	}
}