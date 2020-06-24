package control;

import model.cliente.ClienteBean;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns = {"/admin/*", "/user/*"})
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //Cast da ServletRequest a HTTPserveltrequest perché serve l'uri
        HttpServletRequest hrequest = (HttpServletRequest) req;
        HttpServletResponse hresponse = (HttpServletResponse) resp;

        //Controllo se l'uri appartiene a /login /admin o /user
        String uri = hrequest.getRequestURI();
        if (uri.contains("/admin/")) {
            HttpSession session = hrequest.getSession(false);
            checkLogic(session, req, resp, chain, hresponse, hrequest, "admin");
        } else if (uri.contains("/user/")) {
            HttpSession session = hrequest.getSession(false);
            checkLogic(session, req, resp, chain, hresponse, hrequest, "user");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }
    private void checkLogic(HttpSession session, ServletRequest req, ServletResponse resp, FilterChain chain, HttpServletResponse hresponse, HttpServletRequest hrequest, String tipo) throws ServletException, IOException {
        if (session != null) {
            ClienteBean bean = (ClienteBean) session.getAttribute("user");
            if (check(bean, tipo)) {
                chain.doFilter(req, resp);
            } else hresponse.sendRedirect(hrequest.getContextPath() + "/login.jsp");
        } else hresponse.sendRedirect(hrequest.getContextPath() + "/login.jsp");
    }

    private boolean check (ClienteBean bean, String tipo) {
        if(tipo.equals("admin")){
            return bean != null && bean.isAdmin();
        } else if (tipo.equals("user")) {
            return bean != null && (bean.isAdmin() || !bean.isAdmin());
        } else return false;
    }

}
