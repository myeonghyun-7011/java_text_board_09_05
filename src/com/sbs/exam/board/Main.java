package com.sbs.exam.board;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int articlesLastId = 0;
    Article lastArticle = null;


    System.out.println("== 게시판 v 0.1 == ");
    System.out.println("== 프로그램 시작== ");
    while (true) {
      System.out.printf("명령 : ");
      String cmd = sc.nextLine();

      if (cmd.equals("exit")) {
        break;
      }
      else if (cmd.equals("/usr/article/write")) {
        System.out.println("==게시물 등록 ==");

        System.out.printf("제목 : ");
        String title = sc.nextLine();

        System.out.printf("내용 : ");
        String content = sc.nextLine();

        int id = articlesLastId + 1;
        articlesLastId = id;

        Article article = new Article(id, title, content);
        lastArticle = article;


        System.out.println(("생성된 게시물 객체\n" +  article));
        System.out.printf("%d번 게시물이 등록되었습니다.\n",article.id);

      }
      else if(cmd.equals("/usr/article/detail")) {

        if(lastArticle == null){
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        Article article = lastArticle;

        System.out.printf("== 게시물 상세내용== \n");
        System.out.printf(  "번호 : %d\n", article.id);
        System.out.printf(  "제목 : %s\n", article.title);
        System.out.printf("내용 : %s\n", article.content);

      }
      else {
        System.out.printf("입력된 명령어 : %s \n", cmd);
      }
    }
    System.out.println("== 프로그램 종료 == ");
    sc.close();
  }

}
class Article{
  int id;
  String title;
  String content;

  Article(int id, String title, String content){
    this.id = id;
    this.title = title;
    this.content = content;
  }

  @Override // 통째로 외우기 !!!!!!!!!!!
  public String toString(){
    return String.format("{id : \"%d\", title :\"%s\", content : \"%s\" }" ,id,title,content);
    //return "안녕  %d".formatted(10); // 위에랑 같으 표현
  }
}

