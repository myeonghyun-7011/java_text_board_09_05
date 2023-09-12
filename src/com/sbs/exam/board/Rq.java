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
}