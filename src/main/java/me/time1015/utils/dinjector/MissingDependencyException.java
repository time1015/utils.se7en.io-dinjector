package me.time1015.utils.dinjector;

public class MissingDependencyException extends RuntimeException {
  private static final long serialVersionUID = -7817121600220800333L;

  public MissingDependencyException() {
    super();
  }

  public MissingDependencyException(String message) {
    super(message);
  }

  public MissingDependencyException(Throwable cause) {
    super(cause);
  }

  public MissingDependencyException(String message, Throwable cause) {
    super(message, cause);
  }
}
