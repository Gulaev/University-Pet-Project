package com.gulaev.exception;

public class DaoLayerException extends Exception {

  public DaoLayerException() {}

  public DaoLayerException(String message) {
    super(message);
  }

  public DaoLayerException(String message, Throwable cause) {
    super(message, cause);
  }
}
