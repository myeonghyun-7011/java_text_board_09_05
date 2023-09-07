package com.sbs.exam.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  static void makeTestData(List<Article> articles) {
    // 테스트 데이터 3개 등록 시작
    articles.add(new Article(1, "제목1", "내용1"));
    articles.add(new Article(2, "제목2", "내용2"));
    articles.add(new Article(3, "제목3", "내용3"));
    //테스트 데이터 3개 등록 끝
  }


  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int articlesLastId = 0;
    Article lastArticle = null; //
    List<Article> articles = new ArrayList<>();// Article 전용 리스트 생성
    // 위에 테스트 게시물을 리스트로 불러오기 위해서 사용.

    // 텍스트 게시판 리팩토리하는 방법
    makeTestData(articles);
   //  자바_텍스트_게시판_만들기();
    //위에 변수를 (ctrl + 1)을 사용하여 만들게 되면 public하고 생김 남들이 봤을 때, 더 한눈에 알아보기 위해서 사용

    // test게시물을 임의로 3개만 만들어 뒀기 때문에 다음 번호인 4번부터 받아와서 불러오기.
    if(articles.size() > 0){
      articlesLastId = articles.get(articles.size() - 1).id;
    }


    System.out.println("== 게시판 v 0.1 == ");
    System.out.println("== 프로그램 시작== ");
    while (true) {
      System.out.printf("명령 : ");
      String cmd = sc.nextLine();

      if (cmd.equals("exit")) {
        break;
      } else if (cmd.equals("/usr/article/write")) {
        System.out.println("==게시물 등록 ==");

        System.out.printf("제목 : ");
        String title = sc.nextLine();

        System.out.printf("내용 : ");
        String content = sc.nextLine();

        // 게시물 번호 하나씩 증가시키기
        int id = articlesLastId + 1;
        articlesLastId = id;

        // 게시물 입력한내용들이 new 객체내용대로  aticle(변수)로 들어옴.
        Article article = new Article(id, title, content);
        lastArticle = article;
        //new 객체에 내용이 없으면 lastArticle은 null 아무것도 없기 대문에 존재하지 않는다고 뜸.

        //write 에 게시물 등록시 detail 새로 생성된 게시물을 article에 추가.
        //그래서 4번째 등록 게시물 생성.
        articles.add(article);


        System.out.println(("생성된 게시물 객체\n" + article));
        System.out.printf("%d번 게시물이 등록되었습니다.\n", article.id);

      } else if (cmd.equals("/usr/article/detail")) {

        // /usr/article/detail 입력 햇을시 내용물이 없으면 출력하고 다시 명령 하기.
        if (lastArticle == null) {
          System.out.println("게시물이 존재하지 않습니다.");
          continue; // 위로 다시 돌려보내기.
        }

        Article article = lastArticle;

        System.out.printf("== 게시물 상세내용== \n");
        System.out.printf("번호 : %d\n", article.id);
        System.out.printf("제목 : %s\n", article.title);
        System.out.printf("내용 : %s\n", article.content);

      }
      else if (cmd.equals("/usr/article/list")) {
        System.out.println("== 게시물 리스트 ==");
        System.out.println("-------------------");
        System.out.println("번호 / 제목");
        System.out.println("-------------------");
        // 정순 정렬 코드

        // 향상된 for문 사용하여 2번재 방법
//        for (Article article : articles) {
//          System.out.printf("%d / %s \n", article.id, article.title);
//        }
        /*  for문 첫번째 방법
        for(int i = 0; i < articles.size(); i++){
          Article article = articles.get(i);
          System.out.printf("%d / %s \n",article.id,article.title);
        } */
        //stream 방식
        //articles.stream().forEach(article ->  System.out.printf("%d / %s \n",article.id,article.title));

        // 역순 정렬 코드
        for (int i = articles.size() - 1; i >= 0; i--) {
          Article article = articles.get(i);
          System.out.printf("%d / %s \n", article.id, article.title);
        }

      }
      else {
        System.out.println("잘못된 명령어 입니다");
      }
    }
    System.out.println("== 프로그램 종료 == ");
    sc.close();
  }


}

class Article {
  int id;
  String title;
  String content;

  Article(int id, String title, String content) {
    this.id = id;
    this.title = title;
    this.content = content;
  }

  @Override // 통째로 외우기 !!!!!!!!!!!
  public String toString() {
    return String.format("{id : \"%d\", title :\"%s\", content : \"%s\" }", id, title, content);
    //return "안녕  %d".formatted(10); // 위에랑 같으 표현
  }
}

