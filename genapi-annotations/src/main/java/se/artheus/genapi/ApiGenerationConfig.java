package se.artheus.genapi;

/**
 * Configuration Annotation for generating api correctly.
 * Per project there should only exist one of these annotations. (eg. on the main class)
 *
 * @date 13/08/16
 * @package se.artheus.genapi
 */
public @interface ApiGenerationConfig {
  String className();
  String protocol() default "http";
  String host() default "localhost";
  String[] path() default "";
  int port() default 80;
}
