import org.json.simple.JSONArray;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author artheus
 * @date 16/08/16
 */
@RequestMapping("/another/example")
public class AnotherExampleController {

  @RequestMapping(
      name = "anotherGreeting",
      path = "/greeting",
      method = RequestMethod.GET,
      produces = MediaType.TEXT_PLAIN_VALUE
  )
  public String numberRangeAction(
      @RequestParam String name
  ) {
    return String.format("Hey %s.", name);
  }
}
