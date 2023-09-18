package com.sbs.exam.board;


import com.sbs.exam.board.container.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main {
  /* 사용시에는 static 을 사용해야함.
  App app = new App();
  app.run();
  */
  public static void main(String[] args) {
    new App().run(); // main에 static사용해서 App애 있는 모든 static을 사용하지 않아도된다.
  }
}
