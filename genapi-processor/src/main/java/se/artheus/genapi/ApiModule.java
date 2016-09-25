package se.artheus.genapi;

import java.util.List;

/**
 * @author artheus
 * @date 17/08/16
 * @package se.artheus.genapi
 */
public class ApiModule {

  public ApiConfiguration configuration;
  public List<Endpoint> endpoints;

  public ApiModule(ApiConfiguration configuration, List<Endpoint> endpoints) {
    this.configuration = configuration;
    this.endpoints = endpoints;
  }

  public ApiConfiguration configuration() {
    return configuration;
  }

  public List<Endpoint> endpoints() {
    return endpoints;
  }
}
