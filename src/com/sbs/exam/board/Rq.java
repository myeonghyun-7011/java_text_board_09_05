package com.sbs.exam.board;

import java.util.Map;

public class Rq {
  public String url;
  public Map<String, String> params; // params = 0; 이랑 같음.

  public String urlPath;

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

  //rq.getParams // rq에서 Params들을 다 가져와라
  //int id = rq.getIntParam("id",0); // rq에서 가져와라 Param을 정수화해서 가져와라
  //
  //if(id == 0){}
  ////실행이 안되는경우
  //1.param중에 id가 없는 경우
  //2.params에 id가 있더라도 정수가 아닌경우
  public int getIntParam(String paramName, int defaultValue) {
    if (params.containsKey(paramName) == false) {
      return defaultValue;
    }

      try {
        return Integer.parseInt(params.get(paramName));
      } catch (NumberFormatException e) {
        return defaultValue;
      }
    }
    public String getParam (String paramName, String defaultValue){ //paramName = id

      if (params.containsKey(paramName) == false) { // 값이 없을경우 실행
        return defaultValue;
      }
      return params.get(paramName);
    }
  }


/*
    try { // 예외처리
      return (params.get(paramName)); // params는 String이기때문에 int로 형변환시켜줌
    } catch (NumberFormatException e) {
      return defaultValue;
    }
    */

