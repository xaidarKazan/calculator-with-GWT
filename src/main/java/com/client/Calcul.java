package com.client;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import java.util.ArrayList;
import java.util.List;
import com.google.gwt.core.client.GWT;

public class Calcul implements EntryPoint {

  public void onModuleLoad() {

    final TextBox display = new TextBox();
    display.setTitle("Введите число");
    display.setReadOnly(true);
    display.setStyleName("input");

    // объявляем кнопки и стили кнопок с цифрами и операторам
    final List<Button> numberButtonList = new ArrayList<>();
    final String[] numAndOper = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "*", "/"};
    for (String elem : numAndOper) {
      final Button button = new Button(String.valueOf(elem));
      button.addStyleName("number");
      numberButtonList.add(button);
    }
    // отдельно определяем кнопку =
    Button buttonEqual = new Button("=");
    buttonEqual.setStyleName("equal");
    numberButtonList.add(buttonEqual);

    // добавляем в панель все кнопки и дисплей
    RootPanel.get("nameFieldContainer").add(display);
    for (int i = 0; i < numberButtonList.size(); i++) {
      String nameButton = "number" + numberButtonList.get(i).getHTML();
      RootPanel.get(nameButton).add(numberButtonList.get(i));
    }

    class MyHandler implements ClickHandler {
      Integer number1, number2;
      String operator;

      public void onClick(ClickEvent event) {
        // После вывода результата и нажатии кнопки происхот затирание значений дисплея
        if (display.getTitle().equals("Результат")) {
          display.setText("");
          display.setTitle("Введите число");
        }

        // значение нажатой кнопки
        String buttonValue =((Button) event.getSource()).getText();

        // если "23+56" и была наж кн с оператором то выводим результат
        if (operator != null && number2 != null && buttonValue.matches("[/*-+=]")) {
          display.setText(display.getText() + buttonValue);
          result(number1, number2, operator);
        }
        else {
          // если "23+" и была наж кн с числом то изменяем number2
          if (operator != null && !buttonValue.matches("[/*-+=]")) {
            if (number2 == null) number2 = Integer.parseInt(buttonValue);
            else number2 = Integer.parseInt(number2 + buttonValue);
            display.setText(display.getText() + buttonValue);
          }
          else {
            // если "23" и была наж кн с оператором то определяем operator
            if (number1 != null && operator == null && (buttonValue.matches("\\D"))) { // "[/*+]") || buttonValue.equals("-")
              operator = buttonValue;
              display.setText(display.getText() + buttonValue);
            }
            else {
              // если нажата кн с числом то меняем number1
              if (!buttonValue.matches("[/*-+]")) {
                if (number1 == null) number1 = Integer.parseInt(buttonValue);
                else number1 = Integer.parseInt(number1 + buttonValue);
                display.setText(display.getText() + buttonValue);
              }
            }
          }
        }
      }

      public void result(Integer num1, Integer num2, String op) {
        display.setTitle("Результат");
        int result = 0;
        switch(op) {
          case "/" : result = num1 / num2;
                     break;
          case "*" : result = num1 * num2;
                     break;
          case "-" : result = num1 - num2;
                     break;
          case "+" : result = num1 + num2;
                     break;
        }
        display.setText(String.valueOf(result));
        number1 = null;
        number2 = null;
        operator = null;
      }
    }

    MyHandler handler = new MyHandler();
    for (Button button : numberButtonList) {
      button.addClickHandler(handler);
    }
  }
}



//        // есть ли в строке операторы
//        boolean isAction = display.getText().matches("\\d+[/*-+]\\d+");
////        GWT.log("proverka --- " + String.valueOf(isAction));
//        if (isAction && (buttonValue.equals("*") || buttonValue.equals("-") || buttonValue.equals("/") ||
//                         buttonValue.equals("+") || buttonValue.equals("="))) {
//          String[] nums = display.getText().split("\\d+[/*-+]");
//
//          number2 = Integer.parseInt(nums[1]);
//
//          result(number1, number2, operator);
//        }
//          else {
//            // если была нажата кнопка с оператором а в строке нет цифр то она не обрабатывается
//            // Пример: "23+"
//            if ((buttonValue.matches("[/*+-]") && display.getText().matches("\\d+"))) {
//              number1 = Integer.parseInt(display.getText());
//              operator = buttonValue;
//              //GWT.log("1 punkt + " + operator);
//              display.setText(display.getText() + buttonValue);
//            }
//            else {
//              if (buttonValue.matches("\\d")) display.setText(display.getText() + buttonValue);
//            }
//          }