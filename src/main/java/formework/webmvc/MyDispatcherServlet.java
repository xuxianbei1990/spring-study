package formework.webmvc;

import formework.annotation.MyController;
import formework.annotation.MyRequestMapping;
import formework.context.MyApplicationContext;
import formework.webmvc.servlet.MyHandlerMapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Name
 *
 * @author xuxb
 * Date 2019-12-21
 * VersionV1.0
 * @description
 */
public class MyDispatcherServlet extends HttpServlet {

    private MyApplicationContext myApplicationContext;

    private final String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";

    private List<MyHandlerMapping> handlerMappings = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDispatcher(req, resp);
    }

    private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 初始化 applicaitonContext
        myApplicationContext = new MyApplicationContext(config.getInitParameter(CONTEXT_CONFIG_LOCATION));
        //初始化Spring MVC
        initStrategies(myApplicationContext);
    }

    protected void initStrategies(MyApplicationContext context) {
        initMultipartResolver(context);
        initLocaleResolver(context);
        initThemeResolver(context);
        //
        initHandlerMappings(context);
        //
        initHandlerAdapters(context);
        initHandlerExceptionResolvers(context);
        initRequestToViewNameTranslator(context);
        //
        initViewResolvers(context);
        initFlashMapManager(context);
    }

    private void initFlashMapManager(MyApplicationContext context) {

    }

    private void initViewResolvers(MyApplicationContext context) {

    }

    private void initRequestToViewNameTranslator(MyApplicationContext context) {

    }

    private void initHandlerExceptionResolvers(MyApplicationContext context) {

    }

    private void initHandlerAdapters(MyApplicationContext context) {

    }

    private void initHandlerMappings(MyApplicationContext context) {
        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            Object controller = context.getBean(beanName);
            if (!controller.getClass().isAnnotationPresent(MyController.class)) {
                continue;
            }
            Class<?> clazz = controller.getClass();
            String baseUrl = "";
            if (clazz.isAnnotationPresent(MyRequestMapping.class)) {
                MyRequestMapping requestMapping = clazz.getAnnotation(MyRequestMapping.class);
                baseUrl = requestMapping.value();
            }

            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (!method.isAnnotationPresent(MyRequestMapping.class)) {
                    continue;
                }
                MyRequestMapping requestMapping = method.getAnnotation(MyRequestMapping.class);
                String regex = ("/" + baseUrl + "/" + requestMapping.value().replaceAll("\\*", ".*"))
                        .replaceAll("/+", "/");
                Pattern pattern = Pattern.compile(regex);
                handlerMappings.add(new MyHandlerMapping(controller, method, pattern));
            }
        }
    }

    private void initThemeResolver(MyApplicationContext context) {

    }

    private void initLocaleResolver(MyApplicationContext context) {

    }

    private void initMultipartResolver(MyApplicationContext context) {

    }
}
