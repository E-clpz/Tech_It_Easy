package nl.novi.techiteasy.exceptions;

public class NameTooLongException extends RuntimeException {
  public NameTooLongException(String message) {
    super(message);
  }
}
