package com.sbs.exam.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {
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