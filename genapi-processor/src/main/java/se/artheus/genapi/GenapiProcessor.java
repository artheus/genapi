package se.artheus.genapi;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.google.auto.service.AutoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import javax.tools.StandardLocation;
import java.io.Writer;
import java.util.*;

/**
 * Generation of TypeScript code for api modules in AngularJS.
 * This will make AngularJS -> Spring Backend communication a lot simpler (hopefully)
 *
 * @author artheus
 * @date 13/08/16
 * @package se.artheus.genapi
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({
    "se.artheus.genapi.ApiGenerationConfig",
    "se.artheus.genapi.ApiEndpoint"
})
@AutoService(Processor.class)
public class GenapiProcessor extends AbstractProcessor {

  Filer filer;
  Messager messager;
  Elements elements;
  Types types;

  Set<? extends Element> cachedConfigSet;

  @Override
  public synchronized void init(ProcessingEnvironment processingEnv) {
    super.init(processingEnv);

    filer = processingEnv.getFiler();
    messager = processingEnv.getMessager();
    elements = processingEnv.getElementUtils();
    types = processingEnv.getTypeUtils();
  }

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    Set<? extends Element> configSet = roundEnv.getElementsAnnotatedWith(ApiGenerationConfig.class);

    if (configSet.isEmpty()) {
      return false;
    }

    Element config = configSet.iterator().next();

    ApiGenerationConfig configAnnotation = config.getAnnotation(ApiGenerationConfig.class);

    String className = configAnnotation.className();
    String host = configAnnotation.host();
    Path path = new Path(configAnnotation.path());
    int port = configAnnotation.port();
    String protocol = configAnnotation.protocol();

    ApiConfiguration apiConfig = new ApiConfiguration(className, host, path, port, protocol);

    List<Endpoint> endpoints = new ArrayList<>();

    for (Element e : roundEnv.getElementsAnnotatedWith(RequestMapping.class)) {
      if(e.getKind() != ElementKind.METHOD) continue;

      RequestMapping classReqMapping = e.getEnclosingElement().getAnnotation(RequestMapping.class);
      RequestMapping endpoint = e.getAnnotation(RequestMapping.class);

      String name = endpoint.name();
      Path endpointPath = new Path(endpoint);
      List<Method> methods = new ArrayList<>();
      List<Param> params = new ArrayList<>();
      List<String> headers = arrayToArrayList(endpoint.headers());
      List<String> consumes = arrayToArrayList(endpoint.consumes());
      List<String> produces = arrayToArrayList(endpoint.produces());

      for (RequestMethod m: endpoint.method()){
        methods.add(new Method(m.name()));
      }

      ExecutableElement elm = (ExecutableElement) e;

      for (VariableElement p: elm.getParameters()) {
        Param par = new Param();

        if(p.getAnnotation(RequestParam.class) == null) continue;

        par.name = p.getSimpleName().toString();
        par.setType(p.asType().toString());

        params.add(par);
      }

      Endpoint ep = new Endpoint();

      ep.setName(name);
      ep.setPath(endpointPath);
      ep.setMethods(methods);
      ep.setParams(params);
      ep.setHeaders(headers);
      ep.setConsumes(consumes);
      ep.setProduces(produces);

      if(classReqMapping instanceof RequestMapping) {
          ep.enpointPathPrefix = new Path(classReqMapping);
      }

      endpoints.add(ep);
    }

    ApiModule module = new ApiModule(apiConfig, endpoints);

    // If the conf root path of the api is / it is unnecessary
    //if(module.configuration.path().equals("/")) module.configuration.path = "";

    MustacheFactory mf = new DefaultMustacheFactory();
    Mustache mustache = mf.compile("templates/template.mustache");

    try {
      Writer fileWriter = filer.createResource(StandardLocation.CLASS_OUTPUT, "web", "apiConfig.ts").openWriter();

      mustache.execute(fileWriter, module).flush();
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }

    return true;
  }

  private ArrayList<String> arrayToArrayList(Object[] strings) {
    ArrayList<String> list = new ArrayList<String>();

    for (Object s : strings) {
      list.add(s.toString());
    }

    return list;
  }

  public void info(String msg) {
    messager.printMessage(Diagnostic.Kind.NOTE, msg);
  }

  public void error(String msg) {
    messager.printMessage(Diagnostic.Kind.ERROR, msg);
  }
}
