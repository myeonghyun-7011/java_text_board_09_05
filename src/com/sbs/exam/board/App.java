package com.sbs.exam.board;

import com.sbs.exam.board.container.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {
  int articlesLastId; //공유자원 // static이용 해야함.

  List<Article> articles; // Article 전용 리스트 생성 // 중복해결
  // 위에 테스트 게시물을 리스트로 불러오기 위해서 사용.

  App() {
    articlesLastId = 0;
    articles = new ArrayList<>();
  }


  void makeTestData() {
    for (int i = 1; i <= 100; i++) {
      articles.add(new Article(i, "제목" + i, "내용" + i));
    }
  }

  void run() {


    Scanner sc = Container.sc;

    //Article lastArticle = null; //

    // 텍스트 게시판 리팩토리하는 방법
    makeTestData();
    //  자바_텍스트_게시판_만들기();
    //위에 변수를 (ctrl + 1)을 사용하여 만들게 되면 public하고 생김 남들이 봤을 때, 더 한눈에 알아보기 위해서 사용

    // test게시물을 임의로 3개만 만들어 뒀기 때문에 다음 번호인 4번부터 받아와서 불러오기.
    if (articles.size() > 0) {
      articlesLastId = articles.get(articles.size() - 1).id;
    }


    System.out.println("== 게시판 v 0.1 == ");
    System.out.println("== 프로그램 시작== ");

//--------------------------------------whil문 시작 ------------------------------------------------------------------
    while (true) {
      System.out.printf("명령 : ");
      String cmd = sc.nextLine();

      Rq rq = new Rq(cmd);

      if (rq.getUrlPath().equals("exit")) {
        break;
      }
//--------------------------------------write------------------------------------------------------------------
      else if (rq.getUrlPath().equals("/usr/article/write")) {
        actionUsrArticleWrite();
      }
//--------------------------------------detail------------------------------------------------------------------
      else if (rq.getUrlPath().equals("/usr/article/detail")) {
        actionUsrArticleDetail(rq);
      }
//--------------------------------------modify------------------------------------------------------------------
      else if (rq.getUrlPath().equals("/usr/article/modify")) {
        actionUsrArticleModify(rq);
      }
//--------------------------------------delete------------------------------------------------------------------
      else if (rq.getUrlPath().equals("/usr/article/delete")) {
        actionUsrArticleDelete(rq);
      }
//--------------------------------------list------------------------------------------------------------------
      else if (rq.getUrlPath().equals("/usr/article/list")) {
        actionUsrArticleList(rq);
      }
//--------------------------------------종료문------------------------------------------------------------------
      else {
        System.out.println("잘못된 명령어 입니다");
      }
    }
    System.out.println("== 프로그램 종료 == ");
    sc.close();
  }

  //------------------------------------actionUsrArticleWrite()-----------------------------------------
  private void actionUsrArticleWrite() {
    System.out.println("==게시물 등록 ==");

    System.out.printf("제목 : ");
    String title = Container.sc.nextLine();

    System.out.printf("내용 : ");
    String content = Container.sc.nextLine();

    // 게시물 번호 하나씩 증가시키기
    int id = ++articlesLastId;
//    int id = articlesLastId + 1;
//    articlesLastId = id;

    // 게시물 입력한내용들이 new 객체내용대로  aticle(변수)로 들어옴.
    Article article = new Article(id, title, content);
    //lastArticle = article;
    //new 객체에 내용이 없으면 lastArticle은 null 아무것도 없기 대문에 존재하지 않는다고 뜸.

    //write 에 게시물 등록시 detail 새로 생성된 게시물을 article에 추가.
    //그래서 4번째 등록 게시물 생성.
    articles.add(article);

    System.out.println(("생성된 게시물 객체\n" + article));
    System.out.printf("%d번 게시물이 등록되었습니다.\n", article.id);
  }

  //---------------------------------------- actionUsrArticleDetail----------------------------------------------------------------------
  private void actionUsrArticleDetail(Rq rq) {
    Map<String, String> params = rq.getParams();

    if (params.containsKey("id") == false) {
      System.out.println("id를 입력해주세요");
      return;
    }
    // rq.getParams().get("id); 이렇게도 사용가능함. 이게 더 좋음

    // int id = Integer.parseInt(params.get("id"));// 형변환

    int id = 0;

    try { // exception 걸러내기  유효성 검사라고함.
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수 형태로 입력해주세요.");
      return;
    }

    // /usr/article/detail 입력 햇을시 내용물이 없으면 출력하고 다시 명령 하기.
    if (articles.isEmpty() /*lastArticle == null*/ || id > articles.size()) {
      // 게시물이 비어있거나 입력한 id가 article에 size를 넘을경우 출력
      // articles.size() == 0  ,
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

//***********delet,detail,modify에 3개에 모두 적용해줘야만 게시물이 완벽히 적용이됨.********
    Article foundArticle = null;
    for (Article article : articles) {
      if (article.id == id) {
        foundArticle = article;
        break;
      }
    }
    // 게시물을 찾지 못함
    if (foundArticle == null) {
      System.out.printf("해당 게시물은 존재하지 않습니다.\n");
      return;
    }
// ************************************************************************

    // 마지막 게시물 가져오기
    // lastArticle 변수 필요성을 제거.
    // Article article = lastArticle;

    System.out.printf("== 게시물 상세내용== \n");
    System.out.printf("번호 : %d\n", foundArticle.id);
    System.out.printf("제목 : %s\n", foundArticle.title);
    System.out.printf("내용 : %s\n", foundArticle.content);
  }

  //----------------------------actionUsrArticleModify-------------------------------------------
  private void actionUsrArticleModify(Rq rq) {
    Map<String, String> params = rq.getParams();

    if (params.containsKey("id") == false) {
      System.out.println("id를 입력해주세요");
      return;
    }
    // rq.getParams().get("id); 이렇게도 사용가능함. 이게 더 좋음

    // int id = Integer.parseInt(params.get("id"));// 형변환

    int id = 0;

    try { // exception 걸러내기  유효성 검사라고함.
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수 형태로 입력해주세요.");
      return;
    }

    // /usr/article/detail 입력 햇을시 내용물이 없으면 출력하고 다시 명령 하기.
    if (articles.isEmpty() /*lastArticle == null*/ || id > articles.size()) {
      // 게시물이 비어있거나 입력한 id가 article에 size를 넘을경우 출력
      // articles.size() == 0  ,
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }
//***********delet,detail,modify에 3개에 모두 적용해줘야만 게시물이 완벽히 적용이됨.********
    Article foundArticle = null;
    for (Article article : articles) {
      if (article.id == id) {
        foundArticle = article;
        break;
      }
    }
    // 게시물을 찾지 못함
    if (foundArticle == null) {
      System.out.printf("해당 게시물은 존재하지 않습니다.\n");
      return;
    }
// ************************************************************************
    System.out.printf("== %d번  게시물 수정== \n", foundArticle.id);
    System.out.printf("새 제목 :");
    foundArticle.title = Container.sc.nextLine();
    System.out.printf("새 내용 :");
    foundArticle.content = Container.sc.nextLine();

    System.out.printf("== %d번  게시물이 수정 되었습니다. == \n", foundArticle.id);
  }

  //----------------------------actionUsrArticleDelete-------------------------------------------
  private void actionUsrArticleDelete(Rq rq) {
    Map<String, String> params = rq.getParams();

    if (params.containsKey("id") == false) {
      System.out.println("id를 입력해주세요");
      return;
    }
    // rq.getParams().get("id); 이렇게도 사용가능함. 이게 더 좋음

    // int id = Integer.parseInt(params.get("id"));// 형변환

    int id = 0;

    try { // exception 걸러내기  유효성 검사라고함.
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수 형태로 입력해주세요.");
      return;
    }

    // /usr/article/detail 입력 햇을시 내용물이 없으면 출력하고 다시 명령 하기.
    if (articles.isEmpty() /*lastArticle == null*/ || id > articles.size()) {
      // 게시물이 비어있거나 입력한 id가 article에 size를 넘을경우 출력
      // articles.size() == 0  ,
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    //***********delet,detail,modify에 3개에 모두 적용해줘야만 게시물이 완벽히 적용이됨.********
    // 게시물을 찾은 경우 article에 넣어 불러와 삭제
    Article foundArticle = null;
    for (Article article : articles) {
      if (article.id == id) {
        foundArticle = article;
        break;
      }
    }
    // 게시물을 찾지 못함
    if (foundArticle == null) {
      System.out.printf("해당 게시물은 존재하지 않습니다.\n");
      return;
// ************************************************************************
    }
    System.out.printf("== %d번  게시물이 삭제 되었습니다. == \n", foundArticle.id);
    articles.remove(foundArticle);

  }

  //----------------------------actionUsrArticleList list문-------------------------------------------
  private void actionUsrArticleList(Rq rq) {

    System.out.println("== 게시물 리스트 ==");
    System.out.println("-------------------");
    System.out.println("번호 / 제목");
    System.out.println("-------------------");

    Map<String, String> params = rq.getParams();

    //==============검색시작================
    List<Article> filteredArticles = articles;

    if (params.containsKey("searchKeyword")) {
      String searchKeyword = params.get("searchKeyword");

      filteredArticles = new ArrayList<>();

      for (Article article : articles) {
        boolean matched = article.title.contains(searchKeyword) || article.content.contains(searchKeyword);

        if (matched) {
          filteredArticles.add(article);
        }
      }
    }
    // ************** 역순 정렬 코드****************
    List<Article> sortedArticles = filteredArticles; //filter들어오기전까지는 articles;가맞음

    boolean orderByIdDesc = true;

    if (params.containsKey("orderBy") && params.get("orderBy").equals("idAsc")) {
      orderByIdDesc = false;
    }

    //************** 정순 정렬 코드 *************
    // 저장되어있는 마지막 내용 부터 출력
    if (orderByIdDesc) { // 오름차순 4,3,2,1
      sortedArticles = Util.reverseList(sortedArticles);
    }
//          for (int i = articles.size() - 1; i >= 0; i--) {
//            Article article = articles.get(i);
//            System.out.printf("%d / %s \n", article.id, article.title);
//          }
//        }
//        else { // 내림차순 1,2,3,4
          /*  1. for문
        for(int i = 0; i < articles.size(); i++){
          Article article = articles.get(i);
          System.out.printf("%d / %s \n",article.id,article.title);
        } */
    // 2. 향상된 for문 사용
    for (Article article : sortedArticles) {
      System.out.printf("%d / %s \n", article.id, article.title);

      // 3. stream 사용한것
      //articles.stream().forEach(article -> System.out.printf("%d / %s \n", article.id, article.title));
    }
  }

}

