package se.artheus.genapi;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * todo: well this should handle the fact that there is a possibility if multiple paths
 *  - needed? Uncertain, since all paths will point to the same method/endpoint
 */
public class Path {

  private String[] path;

  public Path(String[] path) {
    this.path = path;
  }

  public Path(String[] path, String[] value) {
    this.path = (String[]) ArrayUtils.addAll(path, value);
  }

  public Path(RequestMapping mapping) {
    this(mapping.path(), mapping.value());
  }

  public String path() {
    if(path.length > 0) {
      // this might not be needed, since `//` are usually well handled in urls by the major browsers.
      // Though, it does not look good with `//` in the api client script generated.
      if(path[0].equals("/"))
        path[0] = "";

      return path[0];
    }

    return "";
  }

  public boolean isEmpty() {
    return path.length == 0;
  }
}
