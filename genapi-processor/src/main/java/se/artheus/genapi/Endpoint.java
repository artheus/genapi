package se.artheus.genapi;

import com.google.common.base.CaseFormat;

import java.util.List;

/**
 * @author artheus
 * @date 17/08/16
 * @package se.artheus.genapi
 */
public class Endpoint {
  public String name;

  public Path path;
  public List<Method> methods;
  public List<Param> params;
  public List<String> headers;
  public List<String> consumes;
  public List<String> produces;
  public Path enpointPathPrefix;

  public Endpoint() {}

  public String endpointPascalCaseName() {
    return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, name);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Path getPath() {
    return path;
  }

  public void setPath(Path path) {
    this.path = path;
  }

  public List<Method> getMethods() {
    return methods;
  }

  public void setMethods(List<Method> methods) {
    this.methods = methods;
  }

  public List<Param> getParams() {
    return params;
  }

  public void setParams(List<Param> params) {
    this.params = params;
  }

  public List<String> getHeaders() {
    return headers;
  }

  public void setHeaders(List<String> headers) {
    this.headers = headers;
  }

  public List<String> getConsumes() {
    return consumes;
  }

  public void setConsumes(List<String> consumes) {
    this.consumes = consumes;
  }

  public List<String> getProduces() {
    return produces;
  }

  public void setProduces(List<String> produces) {
    this.produces = produces;
  }
}
