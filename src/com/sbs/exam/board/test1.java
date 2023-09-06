package com.sbs.exam.board;

import java.util.Scanner;

public class test1 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int articleLastID = 0;

    System.out.println("시스템 시작");
    System.out.println("== 프로그램 시작 ==");

    while (true) {
      System.out.printf("명령 : ");
      String cmd = sc.nextLine();
      System.out.printf("입력된 명령어 : %s\n",cmd);

      if (cmd.equals("exit")) {
        break;
      } else if (cmd.equals("usr/article/write")) {
        System.out.printf("제목 : ");
        String title = sc.nextLine();
        System.out.printf("내용 : ");
        String content = sc.nextLine();
        int id = articleLastID + 1 ;
        System.out.printf("%d번 게시물이 등록 되었습니다.\n",id);
      }

    }
    System.out.println("== 프로그램 종료 ==\n");
    sc.close();
  }

}
