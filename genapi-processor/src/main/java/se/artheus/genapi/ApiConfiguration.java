package se.artheus.genapi;

/**
 * @author artheus
 * @date 17/08/16
 * @package se.artheus.genapi
 */
public class ApiConfiguration {
  public String className;
  public String host;
  public Path path;
  public int port;
  public String protocol;

  public ApiConfiguration(String className, String host, Path path, int port, String protocol) {
    this.className = className;
    this.host = host;
    this.path = path;
    this.port = port;
    this.protocol = protocol;
  }
}
