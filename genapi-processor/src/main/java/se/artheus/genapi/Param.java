package se.artheus.genapi;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by artheus on 24/09/16.
 */
public class Param {
  public String type;
  public String name;

  private Map<String, String> typeMapper = new HashMap<String, String>(){{
    put("java.lang.String", "string");
    put("int", "number");
    put("long", "number");
    put("boolean", "boolean");
    put("void", "void");
  }};

  private String getMappedType(String type) {
    String t = typeMapper.get(type);

    if(t == null) t = type;

    return t;
  }

  public void setType(String type) {
    this.type = getMappedType(type);
  }
}
