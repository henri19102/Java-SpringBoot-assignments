/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reactiveweb;

/**
 *
 * @author Henri
 */
public class Greeting {
      private String message;

  public Greeting() {
  }

  public Greeting(String message) {
    this.message = message;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "Greeting{" +
        "message='" + message + '\'' +
        '}';
  }
}
