import se.artheus.genapi.ApiGenerationConfig;

@ApiGenerationConfig(
    className = "exampleApiClient",
    host = "example.com",
    protocol = "https",
    path = "/api",
    port = 443
)
public class Application {

  public static void main(String[] args) {
    // Here something should be done in a project which is not an example!
  }
}
