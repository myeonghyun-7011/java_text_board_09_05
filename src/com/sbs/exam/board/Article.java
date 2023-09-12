package com.sbs.exam.board;

public class Article {
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
