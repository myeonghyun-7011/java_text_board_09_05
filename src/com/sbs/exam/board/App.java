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
    Session session = Container.getSession(); // session을 얻어옴.


    System.out.println("== 게시판 v 0.1 == ");
    System.out.println("== 프로그램 시작== ");

//--------------------------------------whil문 시작 ------------------------------------------------------------------
    while (true) {
      Member loginedMember = (Member) session.getAttribute("loginedMember");
      // 매 요청마다 로그인되었는지 확인 (Member) 형변환됨.

      String promptName = "명령어";


      if(loginedMember != null){ //로그인된사람이있다.
        promptName = loginedMember.loginId; // 로그인아이디로 나오게끔 해줌
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
//--------------------------------------login------------------------------------------------------------------
      else if (rq.getUrlPath().equals("/usr/member/login")) {
        Container.usrMemberController.actionLogin(rq);
      }
//--------------------------------------logout------------------------------------------------------------------
      else if (rq.getUrlPath().equals("/usr/member/logout")) {
        Container.usrMemberController.actionLogout(rq);
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