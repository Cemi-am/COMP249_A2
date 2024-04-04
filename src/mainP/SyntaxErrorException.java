package mainP;

public class SyntaxErrorException extends Exception{
	
	public SyntaxErrorException(String s) {
		super(s);
	}
	
	public SyntaxErrorException() {
		super("Syntax Error");
	}
}
