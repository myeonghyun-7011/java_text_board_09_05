package com.sbs.exam.board.session;

import java.util.HashMap;
import java.util.Map;

public class Session { //session이라는 저장소
  private Map<String, Object> sessionStore;
// 키 값 , 키 값을 사용 할수 잇는 map사용
  public Session() {
    sessionStore = new HashMap<>();
  }

  public Object getAttribute(String key) { //가져오기
    return sessionStore.get(key);
  }

  public void setAttribute(String key, Object value) { //저장하기
    sessionStore.put(key, value); // sessionStore에 key와 value  값 put해서저장
  }

  public void removeAttribute(String key) { //제거하기 다음아이디를 로그인하기 위한
    sessionStore.remove(key);
  }
  public boolean hasAttribute(String key) { // 로그인 멤버key 유,무 확인
    return sessionStore.containsKey(key);
  }
}
