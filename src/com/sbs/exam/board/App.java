package com.sbs.exam.board;

import com.sbs.exam.board.container.Container;
import com.sbs.exam.board.session.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {


  public void run() {
    Scanner sc = Container.sc;
    Session session = Container.getSession();


    System.out.println("== 게시판 v 0.1 == ");
    System.out.println("== 프로그램 시작== ");

//--------------------------------------whil문 시작 ------------------------------------------------------------------
    while (true) {
      Member loginedMember = (Member) session.getAttribute("loginedMember");

      String promptName = "명령어";

      if(loginedMember != null){
        promptName = loginedMember.loginId;
      }

      System.out.printf("%s : ", promptName);
      String cmd = sc.nextLine();

      Rq rq = new Rq(cmd);

      if (rq.getUrlPath().equals("exit")) {
        break;
      }
//--------------------------------------write------------------------------------------------------------------
      else if (rq.getUrlPath().equals("/usr/article/write")) {
        Container.usrArticleController.actionWrite();
      }
//--------------------------------------detail------------------------------------------------------------------
      else if (rq.getUrlPath().equals("/usr/article/detail")) {
        Container.usrArticleController.showDetail(rq);
      }
//--------------------------------------modify------------------------------------------------------------------
      else if (rq.getUrlPath().equals("/usr/article/modify")) {
        Container.usrArticleController.actionModify(rq);
      }
//--------------------------------------delete------------------------------------------------------------------
      else if (rq.getUrlPath().equals("/usr/article/delete")) {
        Container.usrArticleController.actionDelete(rq);
      }
//--------------------------------------list------------------------------------------------------------------
      else if (rq.getUrlPath().equals("/usr/article/list")) {
        Container.usrArticleController.showList(rq);
      }
//--------------------------------------Join------------------------------------------------------------------
      else if (rq.getUrlPath().equals("/usr/member/join")) {
        Container.usrMemberController.actionJoin();
      }
//--------------------------------------------------------------------------------------------------------
      else if (rq.getUrlPath().equals("/usr/member/login")) {
        Container.usrMemberController.actionLogin(rq);
      }
//--------------------------------------종료문------------------------------------------------------------------
      else {
        System.out.println("잘못된 명령어 입니다");
      }
    }
    System.out.println("== 프로그램 종료 == ");
    sc.close();
  }

}