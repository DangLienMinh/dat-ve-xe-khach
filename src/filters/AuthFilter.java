package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//Kiem tra trang nguoi dung vao co bat buoc dang nhap hay khong

//@WebFilter(filterName = "AuthFilter", urlPatterns = {"/admin/*"})
@WebFilter("/admin/*")
public class AuthFilter implements Filter {
    
    public AuthFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
         try {

            // Kiem tra bien session co duoc thiet lap hay k
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession ses = req.getSession(false);
            //  allow user to proccede if url is dangNhap.xhtml or user logged in or user is accessing any page in //public folder
            String reqURI = req.getRequestURI();
            if ( reqURI.indexOf("/admin/dangNhap.xhtml") >= 0 || (ses != null && ses.getAttribute("user") != null)
            		|| reqURI.contains("javax.faces.resource") )
                   chain.doFilter(request, response);
            else   // user didn't log in but asking for a page that is not allowed so take user to login page
                   res.sendRedirect(req.getContextPath() + "/admin/dangNhap.xhtml");  // Anonymous user. Redirect to login page
      }
     catch(Throwable t) {
         System.out.println( t.getMessage());
     }
    } //doFilter

    @Override
    public void destroy() {
        
    }
}