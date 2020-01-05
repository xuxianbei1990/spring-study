package formework.webmvc.servlet;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * Name
 *
 * @author xuxb
 * Date 2019-12-21
 * VersionV1.0
 * @description
 */
@Data
@AllArgsConstructor
public class MyHandlerMapping {
    private Object controller;
    private Method method;
    private Pattern pattern;
}
