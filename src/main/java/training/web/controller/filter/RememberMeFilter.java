package training.web.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class RememberMeFilter extends HttpFilter implements Filter {

    public RememberMeFilter() {
        super();
    }

    public void destroy() {
    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Cookie[] cookies = ((HttpServletRequest)request).getCookies();

        for(Cookie c : cookies) {
            if (c.getName().equals("remember-me")) {
                int userID = Integer.parseInt(c.getValue());
                // мозговой штурм
            }
        }

        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {

    }

}
