package uy.kerri.representations.exception;

public class UnsupportedFormatException extends Exception {
  public UnsupportedFormatException() {}

  public UnsupportedFormatException(String message)
  {
     super(message);
  }

  public UnsupportedFormatException(String message, Exception cause)
  {
     super(message, cause);
  }

}
