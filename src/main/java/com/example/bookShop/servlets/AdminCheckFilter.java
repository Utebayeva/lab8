package com.example.bookShop.servlets;

import com.example.bookShop.models.User;
import com.example.bookShop.models.UserAdmin;
import com.example.bookShop.models.UserJust;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AdminCheckFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");

        if(user == null){
            System.out.println("User dont authorized");
            resp.sendRedirect("/index.jsp");
        } else if (user instanceof UserJust) {
            System.out.println("User is not Admin");
            req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        } else if (user instanceof UserAdmin) {
            System.out.println("User is Admin");
            req.getServletContext().getRequestDispatcher("/admin").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        System.out.println("Filter destroy");
    }
}
