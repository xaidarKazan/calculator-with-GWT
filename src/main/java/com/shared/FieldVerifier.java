package com.shared;

public class FieldVerifier {

  public static boolean isValidExpression(String name) {
    if (name == null) {
      return false;
    }
    return !name.startsWith("0");
  }

}
