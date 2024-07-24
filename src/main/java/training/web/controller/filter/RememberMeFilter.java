package training.web.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import training.web.bean.User;
import training.web.service.exception.ServiceException;
import training.web.service.ServiceProvider;
import training.web.service.UserService;

import java.io.IOException;

public class RememberMeFilter extends HttpFilter implements Filter {

    private final UserService userService = ServiceProvider.getInstance().getUserService();

    public RememberMeFilter() {
        super();
    }

    public void destroy() {
    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpSession session = ((HttpServletRequest) request).getSession(false);

            if (session == null || session.getAttribute("user") == null) {
                Cookie[] cookies = ((HttpServletRequest) request).getCookies();

                if (cookies != null) {
                    for (Cookie c : cookies) {
                        if (c.getName().equals("remember-me")) {
                            String token = c.getValue();
                            User user = userService.rememberMe(token);
                            session.setAttribute("user", user);
                        }
                    }
                }
            }
            chain.doFilter(request, response);

        } catch (ServiceException e) {
            ((HttpServletResponse) response)
                    .sendRedirect("MyController?command=go_to_auth&authError=Wrong login or password!");
        }

    }


    public void init(FilterConfig fConfig) throws ServletException {

    }

}
