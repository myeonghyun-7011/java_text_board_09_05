package com.sbs.exam.board;

import java.util.*;

import static java.lang.Integer.*;

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

    List<Article> articles = new ArrayList<>();// Article 전용 리스트 생성
    // 위에 테스트 게시물을 리스트로 불러오기 위해서 사용.


    //Article lastArticle = null; // 84번줄 // lastArticle 변수 필요성을 제거.

    // 텍스트 게시판 리팩토리하는 방법
    makeTestData(articles);
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
      Map<String, String> params = rq.getParams();

      if (rq.getUrlPath().equals("exit")) {
        break;
      }
//--------------------------------------write------------------------------------------------------------------

      else if (rq.getUrlPath().equals("/usr/article/write")) {
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
        //lastArticle = article;
        //new 객체에 내용이 없으면 lastArticle은 null 아무것도 없기 대문에 존재하지 않는다고 뜸.

        //write 에 게시물 등록시 detail 새로 생성된 게시물을 article에 추가.
        //그래서 4번째 등록 게시물 생성.
        articles.add(article);

        System.out.println(("생성된 게시물 객체\n" + article));
        System.out.printf("%d번 게시물이 등록되었습니다.\n", article.id);
      }


//--------------------------------------detail------------------------------------------------------------------

      else if (rq.getUrlPath().equals("/usr/article/detail")) {

        if (params.containsKey("id") == false) {
          System.out.println("id를 입력해주세요");
          continue;
        }
        // rq.getParams().get("id); 이렇게도 사용가능함. 이게 더 좋음

        // int id = Integer.parseInt(params.get("id"));// 형변환

        int id = 0;

        try { // exception 걸러내기  유효성 검사라고함.
          id = Integer.parseInt(params.get("id"));
        } catch (NumberFormatException e) {
          System.out.println("id를 정수 형태로 입력해주세요.");
          continue;
        }

        // /usr/article/detail 입력 햇을시 내용물이 없으면 출력하고 다시 명령 하기.
        if (articles.isEmpty() /*lastArticle == null*/ || id > articles.size()) {
          // 게시물이 비어있거나 입력한 id가 article에 size를 넘을경우 출력
          // articles.size() == 0  ,
          System.out.println("게시물이 존재하지 않습니다.");
          continue; // 위로 다시 돌려보내기.
        }

        Article article = articles.get(id - 1);

        // 마지막 게시물 가져오기
        // lastArticle 변수 필요성을 제거.
        // Article article = lastArticle;

        System.out.printf("== 게시물 상세내용== \n");
        System.out.printf("번호 : %d\n", article.id);
        System.out.printf("제목 : %s\n", article.title);
        System.out.printf("내용 : %s\n", article.content);

      }

//--------------------------------------list------------------------------------------------------------------

      else if (rq.getUrlPath().equals("/usr/article/list")) {
        System.out.println("== 게시물 리스트 ==");
        System.out.println("-------------------");
        System.out.println("번호 / 제목");
        System.out.println("-------------------");

        List<Article> sortedArticles = articles; // 정렬이 안된 articles(1,2,3..순) 리모컨을

        // ***************정순 정렬 코드****************
        //stream 방식 forEach사용 3번째 방법
        boolean orderByIdDesc = true;

        if (params.containsKey("orderBy") && params.get("orderBy").equals("idAsc")) {
          orderByIdDesc = false;
        }
        if (orderByIdDesc) {
          sortedArticles = Util.reverseList((sortedArticles)); //정렬이 안된 리모컨을 가져와 정렬 시켜주는 리모컨만들기
        }

        //************** 역순 정렬 코드 *************
        // 저장되어있는 마지막 내용 부터 출력
//        if (orderByIdDesc) { // 오름차순 4,3,2,1
//          for (int i = articles.size() - 1; i >= 0; i--) {
//            Article article = articles.get(i);
//            System.out.printf("%d / %s \n", article.id, article.title);
//          }
//        }
        // else { // 내림차순 1,2,3,4
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

//--------------------------------------종료문------------------------------------------------------------------
      else{
      System.out.println("잘못된 명령어 입니다");
    }
  }
    System.out.println("== 프로그램 종료 == ");
    sc.close();
}
}

//--------------------------------------Artice 객체 생성------------------------------------------------------------------
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

//--------------------------------------Rq 객체 생성------------------------------------------------------------------

class Rq {
  private String url;
  private Map<String, String> params; // params = 0; 이랑 같음.

  private String urlPath;

  Rq(String url) {
    this.url = url;
    urlPath = Util.getUrlPathFromUrl(this.url);
    params = Util.getParamsFromUrl(this.url);
  }

  public Map<String, String> getParams() {

    return params;
  }

  public String getUrlPath() {

    return urlPath;
  }
}

//--------------------------------------Util 객체 생성------------------------------------------------------------------

class Util {
  static Map<String, String> getParamsFromUrl(String url) {
    Map<String, String> params = new HashMap<>();
    String[] urlBits = url.split("\\?", 2); //2개 이상 나뉘는건 원치 않는다.

    if (urlBits.length == 1) { // 나뉘지 않았다면 '?' 가 없다는 뜻, 더이상 할일이 없음.
      return params;
    }
    String queryString = urlBits[1];

    for (String bit : queryString.split(("&"))) {
      String[] bits = bit.split("=", 2); //2개이상 나뉘는건 원치않음.

      if (bits.length == 1) { // 나뉘지 않았다면 = 가 없다는뜻. 즉 잘못된 파라미터라는뜻.
        continue; // for문 상단으로 돌아간다. 죽 아래 코드 스킵
      }
      params.put(bits[0], bits[1]);

    }
    return params;
  }

  static String getUrlPathFromUrl(String url) {
    return url.split("\\?", 2)[0];
  }

  //이해하면됨. revers 뒤집다.

  // 이 함수는 원본리스트를 훼손하지 않고, 새 리스트를 만든다.
  // 즉 정렬이 반대인 복사본 리스트를 반환한다.
  public static <T> List<T> reverseList(List<T> list) {
    List<T> reverse = new ArrayList<>(list.size());

    for (int i = list.size() - 1; i >= 0; i--) {
      reverse.add(list.get(i));
    }
    return reverse;
  }
}
