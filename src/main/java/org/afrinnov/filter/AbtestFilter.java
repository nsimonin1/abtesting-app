package org.afrinnov.filter;

import lombok.extern.slf4j.Slf4j;
import org.ff4j.FF4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class AbtestFilter extends GenericFilterBean {

    private final FF4j getFF4j;

    public AbtestFilter(FF4j getFF4j) {
        this.getFF4j = getFF4j;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        if (Objects.isNull(session)) {
            session = request.getSession();
        }
        /*if (!path.contains("/modern")) {
            if (checkFeature("new_cars_list")) {
                response.sendRedirect(request.getContextPath() + "/modern" + path);
            }
        } else {
            log.info("Abtest: {}", request.getServletContext().getContextPath());
            chain.doFilter(request, response);
       }*/
        chain.doFilter(request, response);
    }

    private boolean checkFeature(String featureName) {
        return getFF4j.check(featureName);
    }
}
