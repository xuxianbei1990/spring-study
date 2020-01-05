package security.token;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;


/**
 * 令牌认证过滤器
 * 
 * @author dingwl
 *
 */
@Component
@PropertySource("classpath:security.properties")
public class TokenAuthenticationFilter extends GenericFilterBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

    private static final String HEADER_TOKEN = "token";

    @Autowired
    private Environment env;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest httpRequest = this.getHttpRequest(request);

        String accessToken = this.extractAccessTokenFromRequest(httpRequest);
        if (null != accessToken) {
            System.out.println("#######################################################");
            Token token = this.parseToken(accessToken);
            if (token != null) {
                
                List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                User user = new User(token.getUsername(), token.getPassword(), authorities);
                //System.out.println(token.getUsername());
                //System.out.println(token.getPassword());
                if (null != user) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,
                            null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * 解析token
     * 
     * @param accessToken
     * @return
     */
    private Token parseToken(String accessToken) {
        Token token = null;
        return token;
    }

    /**
     * 获取HTTP请求
     * 
     * @param request
     *            Servlet请求
     * @return
     */
    private HttpServletRequest getHttpRequest(ServletRequest request) {
        if (!(request instanceof HttpServletRequest)) {
            throw new RuntimeException("Expecting an HTTP request");
        }

        return (HttpServletRequest) request;
    }

    /**
     * 从请求中提取token
     * 
     * @param httpRequest
     *            HTTP请求
     * @return
     */
    private String extractAccessTokenFromRequest(HttpServletRequest httpRequest) {
        // 从请求头中获取token
        String authToken = httpRequest.getHeader(HEADER_TOKEN);

        // 从请求参数中获取token
        if (authToken == null) {
            authToken = httpRequest.getParameter(HEADER_TOKEN);
        }

        return authToken;
    }

}
