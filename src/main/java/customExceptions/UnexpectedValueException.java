package customExceptions;

public class UnexpectedValueException extends RuntimeException{

	public UnexpectedValueException(String errorMsg){
		super(errorMsg);
	}
}
