package com.sbs.exam.board.controller;

import com.sbs.exam.board.Member;
import com.sbs.exam.board.Rq;
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
  //------------------------------------회원 임의 생성--------------------------------------------------------
  public void makeTestData() {
    for (int i = 1; i <= 3; i++) {
      members.add(new Member(i, "User" + i, "1234", "회원" + i));
    }
  }

  //---------------------------------------join-----------------------------------------------------
  public void actionJoin() {
    System.out.println("== 회원가입 ==");

    System.out.printf("회원 아이디 : ");
    String loginId = Container.sc.nextLine();

    if (loginId.trim().length() == 0) { // trim() => 좌우공백을 제거
      System.out.println("로그인 아이디를 입력해주세요.");
      return;
    }

    System.out.printf("비밀번호 입력 : ");
    String loginPw = Container.sc.nextLine();

    if (loginPw.trim().length() == 0) {
      System.out.println("로그인 비밀번호를 입력해주세요.");
      return;
    }

    System.out.printf("비밀번호 확인 : ");
    String loginPwConfirm = Container.sc.nextLine();

    if (loginPwConfirm.trim().length() == 0) {
      System.out.println("로그인 비밀번호 확인을 입력해주세요.");
      return;
    }

    System.out.printf("이름 : ");
    String name = Container.sc.nextLine();

    if (name.trim().length() == 0) {
      System.out.println("이름을 입력해주세요.");
      return;
    }

    if (loginPw.equals(loginPwConfirm) == false) {
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

  //----------------------------------login----------------------------------------------------------

  public void actionLogin(Rq rq) {
    // 이미 로그인 되어 있으면 중복 로그인 않게끔 방지
    boolean loginedMemberIsEmpty = rq.hasSessionAttr("loginedMember");

    if(loginedMemberIsEmpty == true){
      System.out.println("이미 로그인 상태입니다.");
      return;
    }

    System.out.printf("로그인 아이디 : ");
    String loginId = Container.sc.nextLine();

    if (loginId.trim().length() == 0) {
      System.out.println("로그인 아이디를 입력해주세요.");
    }

    Member member = getMemberByLoginId(loginId);
    // 내가 입력한 로그인 아이디가 현재 get함수가 찾아온게 일치하면  member 복사리모컨으로 불러옴

    if (member == null) {
      System.out.println("해당 회원은 존재하지 않습니다.");
      return;
    }
    System.out.printf("로그인 비밀번호 :");
    String loginPw = Container.sc.nextLine();

    if (loginPw.trim().length() == 0) {
      System.out.println("로그인 비밀번호를 입력해주세요.");
      return;
    }

    if (member.loginPw.equals(loginPw) == false) {
      System.out.println("로그인 비밀번호가 일치하지 않습니다.");
      return;
    }

    rq.setSessionAttr("loginedMember", member);
    // key, value //rq한테 부탁을함 너가 session을 구해서 member에 저장해줘
    // key로 접근해서 데이터 가져올수잇음.

    System.out.printf("\"%s\"님 환영합니다.\n", member.loginId);
  }

  private Member getMemberByLoginId(String loginId) {
    for (Member member : members) {
      if (member.loginId.equals(loginId)) {
        return member;
      }
    }
    return null;
  }

  public void actionLogout(Rq rq) {
    Member loginedMember = (Member) Container.session.getAttribute("loginedMember");
// 로그인되어 있는 회원들 가져오기. / 형변환된 key값으로 접근

    if(loginedMember == null){ // 로그인된 멤버가 없을경우
      System.out.println("로그인 후 이용해주세요.");
      return;
    }

    rq.removeSessionAttr("loginedMember");
    System.out.println("로그아웃 되었습니다.");
  }
}
