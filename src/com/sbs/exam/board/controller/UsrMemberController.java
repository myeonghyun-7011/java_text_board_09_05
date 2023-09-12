package com.sbs.exam.board.controller;

import com.sbs.exam.board.Article;
import com.sbs.exam.board.Member;
import com.sbs.exam.board.container.Container;

import java.util.ArrayList;
import java.util.List;

public class UsrMemberController {
  private int membersLastId; //공유자원

  //private써주는건 관례 App에서만 사용하기때문에 사용권장
  private List<Member> members; // Article 전용 리스트 생성
  // 위에 테스트 게시물을 리스트로 불러오기 위해서 사용.

  public UsrMemberController() {
    membersLastId = 0;
    members = new ArrayList<>();

    makeTestData(); // testdata 생성하기위한 함수

    if (members.size() > 0) //size 마지막을 구해오기위한함수

    {
      membersLastId = members.get(members.size() - 1).id;
    }
  }

  public void makeTestData() {
    for (int i = 1; i <= 3; i++) {
      members.add(new Member(i, "User" + i, "1234","회원"+i));
    }
  }


  public void actionJoin() {
    System.out.println("== 회원가입 ==");

    System.out.printf("회원 아이디 : ");
    String loginId = Container.sc.nextLine();

    if(loginId.length() == 0){
      System.out.println("로그인 아이디를 입력해주세요.");
      return;
    }

    System.out.printf("비밀번호 입력 : ");
    String loginPw = Container.sc.nextLine();

    if(loginPw.length() == 0){
      System.out.println("로그인 비밀번호를 입력해주세요.");
      return;
    }

    System.out.printf("비밀번호 확인 : ");
    String loginPwConfirm = Container.sc.nextLine();

    if(loginPwConfirm.length() == 0){
      System.out.println("로그인 비밀번호 확인을 입력해주세요.");
      return;
    }

    System.out.printf("이름 : ");
    String name = Container.sc.nextLine();

    if(name.length() == 0){
      System.out.println("이름을 입력해주세요.");
      return;
    }

    if(loginPw.equals(loginPwConfirm) == false){
      System.out.println("==== 안내===");
      System.out.println("비밀번호가 일치하지 않습니다.");
      System.out.println("비밀번호 확인을 다시 입력해주세요.");
      return;

    }

    int id = ++membersLastId;

    Member member = new Member(id, loginId, loginPw, name);

    members.add(member);


    System.out.printf("\"%s\"님 회원가입 되었습니다.\n", member.name);
  }

}
