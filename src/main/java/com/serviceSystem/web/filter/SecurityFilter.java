package com.serviceSystem.web.filter;

import com.serviceSystem.service.ClientService;
import com.serviceSystem.service.WorkerService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SecurityFilter implements Filter {
//    private Set<String> unsecuredAccess = new HashSet<>();
    private Set<String> clientAccess = new HashSet<>();
    private Set<String> workerAccess = new HashSet<>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        String[] arr = filterConfig.getInitParameter("unsecuredAccess")
//                .replaceAll("\n","")
//                .replaceAll(" ","")
//                .split(",");
//        unsecuredAccess.addAll(Set.of(arr));

//        clientAccess.addAll(unsecuredAccess);
        clientAccess.addAll(Set.of(filterConfig.getInitParameter("clientAccess").split(",")));

//        workerAccess.addAll(unsecuredAccess);
        workerAccess.addAll(Set.of(filterConfig.getInitParameter("workerAccess").split(",")));
    }

    @Override
    public void destroy() {
//        unsecuredAccess = null;
        clientAccess = null;
        workerAccess = null;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;

        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/html; charset=UTF-8");

        HttpSession session = req.getSession();

        final String command = req.getParameter("command");
        final String requestPath = req.getServletPath().replaceFirst("/view/","");

        boolean isUserAuthorized = SessionHandler.isUserAuthorized(session);

//        if((requestPath.equals("frontController") && unsecuredAccess.contains(command)) || unsecuredAccess.contains(requestPath)){
        if(!(clientAccess.contains(command) || workerAccess.contains(command))
                && !(clientAccess.contains(requestPath) || workerAccess.contains(requestPath))){
            filterChain.doFilter(req,resp);
        }else if(isUserAuthorized && SessionHandler.getRole(session).equals("client") &&
                (clientAccess.contains(requestPath) || clientAccess.contains(command))){
            filterChain.doFilter(req,resp);
        }else if(isUserAuthorized && SessionHandler.getRole(session).equals("worker") &&
                (workerAccess.contains(requestPath) || workerAccess.contains(command))){
            filterChain.doFilter(req,resp);
        }else{
            if(!isUserAuthorized){
                resp.sendRedirect("/view/authorization.jsp");
            }else{
                resp.sendRedirect("frontController?command=error");
            }
        }
//        if(session.getAttribute("user") != null){
//            filterChain.doFilter(req,resp);
//        }else if(clientService.isExist(email,password)){
//            SessionHandler.setUser(session,clientService.getByEmail(email),"client");
//            filterChain.doFilter(req,resp);
//        }else if(workerService.isExist(email,password)){
//            SessionHandler.setUser(session,workerService.getByEmail(email),"worker");
//            filterChain.doFilter(req,resp);
//        }else{
//            req.getRequestDispatcher("/view/authorization.jsp").forward(req,resp);
//        }
    }
}
