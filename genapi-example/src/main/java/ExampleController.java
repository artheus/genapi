import org.json.simple.JSONArray;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author artheus
 * @date 16/08/16
 * @package PACKAGE_NAME
 */
@SuppressWarnings("unchecked")
@RequestMapping("/example")
@RestController
public class ExampleController {

  @RequestMapping(
      name = "numberRange",
      path = "/numberRange",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE
  )
  public String numberRangeAction(
      @RequestParam int start,
      @RequestParam int end
  ) {
    JSONArray numberRange = new JSONArray();

    for(int i = start; i <= end; i++) {
      numberRange.add(i);
    }

    return numberRange.toJSONString();
  }

  @RequestMapping(
      name = "greeting",
      path = "/greeting",
      method = RequestMethod.GET,
      produces = MediaType.TEXT_PLAIN_VALUE
  )
  public String greetingAction(
      @RequestParam String name
  ) {
    return String.format("Hello %s!", name);
  }
}
