package com.server;

import com.client.GreetingService;
import com.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class GreetingServiceImpl extends RemoteServiceServlet implements
    GreetingService {

  public String greetServer(String input) throws IllegalArgumentException {

    if (!FieldVerifier.isValidExpression(input)) {
      throw new IllegalArgumentException(
          "Wrong expression");
    }

    return null;
  }
}
