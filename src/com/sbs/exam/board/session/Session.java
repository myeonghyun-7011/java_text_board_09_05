package com.sbs.exam.board.session;

import java.util.HashMap;
import java.util.Map;

public class Session { //session이라는 저장소
  private Map<String, Object> sessionStore;

  public Session() {
    sessionStore = new HashMap<>();
  }

  public Object getAttribute(String key) {
    return sessionStore.get(key);
  }

  public void setAttribute(String key, Object value) {
    sessionStore.put(key, value);
  }

  public void removeAttribute(String key) {
    sessionStore.remove(key);
  }
  public boolean hasAttribute(String key) { //key 유,무 확인
    return sessionStore.containsKey(key);
  }
}
