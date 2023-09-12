package com.sbs.exam.board.controller;

import com.sbs.exam.board.Article;
import com.sbs.exam.board.Rq;
import com.sbs.exam.board.Util;
import com.sbs.exam.board.container.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsrArticleController {
  int ArticlesLastId;
  private int articlesLastId; //공유자원

  //private써주는건 관례 App에서만 사용하기때문에 사용권장
  private List<Article> articles; // Article 전용 리스트 생성
  // 위에 테스트 게시물을 리스트로 불러오기 위해서 사용.

  public UsrArticleController() {
    articlesLastId = 0;
    articles = new ArrayList<>();

    makeTestData(); // testdata 생성하기위한 함수

    if (articles.size() > 0) //size 마지막을 구해오기위한함수

    {
      articlesLastId = articles.get(articles.size() - 1).id;
    }
  }

  public void makeTestData() {
    for (int i = 1; i <= 100; i++) {
      articles.add(new Article(i, "제목" + i, "내용" + i));
    }
  }


  public void actionWrite() {
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

  public void showDetail(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if (id == 0) {
      System.out.println("id를 올바르게 입력해주세요");
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

  public void actionModify(Rq rq) {
    int id = rq.getIntParam("id", 0);
    if (id == 0) {
      System.out.println("id를 올바르게 입력해주세요");
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
  public void actionDelete(Rq rq) {
    int id = rq.getIntParam("id", 0);
    if (id == 0) {
      System.out.println("id를 올바르게 입력해주세요");
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

  public void showList(Rq rq) {
    String searchKeyword = rq.getParam("searchKeyword", "");

    //==============검색시작================
    List<Article> filteredArticles = articles;

    if (searchKeyword.length() > 0) {

      filteredArticles = new ArrayList<>();

      for (Article article : articles) {
        boolean matched = article.title.contains(searchKeyword) || article.content.contains(searchKeyword);

        if (matched) {
          filteredArticles.add(article);
        }
      }
    }
    //=====================검새 끝=========

    // ************** 역순 정렬 코드****************
    List<Article> sortedArticles = filteredArticles; //filter들어오기전까지는 articles;가맞음

    String orderBy = rq.getParam("orderBy", "idDesc");
    boolean orderByIdDesc = orderBy.equals("idDesc");

    //************** 정순 정렬 코드 *************
    // 저장되어있는 마지막 내용 부터 출력
    if (orderByIdDesc) { // 오름차순 4,3,2,1
      sortedArticles = Util.reverseList(sortedArticles);
    }
    System.out.println("== 게시물 리스트 ==");
    System.out.println("-------------------");
    System.out.println("번호 / 제목");
    System.out.println("-------------------");
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


