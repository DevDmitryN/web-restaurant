package com.serviceSystem.web.filter;

import com.serviceSystem.service.ClientService;
import com.serviceSystem.service.ScheduledService;
import com.serviceSystem.service.WorkerService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SecurityFilter implements Filter {
    private Set<String> clientAccess = new HashSet<>();
    private Set<String> workerAccess = new HashSet<>();
    private Set<String> adminAccess = new HashSet<>();
    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    @Override
    public void init(FilterConfig filterConfig) {
        clientAccess.addAll(Set.of(filterConfig.getInitParameter("clientAccess").split(",")));
        workerAccess.addAll(Set.of(filterConfig.getInitParameter("workerAccess").split(",")));
        adminAccess.addAll(Set.of(filterConfig.getInitParameter("adminAccess").split(",")));
        adminAccess.addAll(workerAccess);
        executorService.scheduleAtFixedRate(new ScheduledService(), 0, 20, TimeUnit.MINUTES);
    }

    @Override
    public void destroy() {
        executorService.shutdown();
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
        final String requestPath = req.getServletPath().replaceFirst("/view/", "");

        boolean isUserAuthorized = SessionHandler.isUserAuthorized(session);

        if (!(clientAccess.contains(command) || adminAccess.contains(command))
                && !(clientAccess.contains(requestPath) || adminAccess.contains(requestPath))) {
            filterChain.doFilter(req, resp);
        } else if (isUserAuthorized && SessionHandler.getRole(session).equals("client")
                && (clientAccess.contains(requestPath) || clientAccess.contains(command))) {
            filterChain.doFilter(req, resp);
        } else if (isUserAuthorized && SessionHandler.getRole(session).equals("worker")
                && (workerAccess.contains(requestPath) || workerAccess.contains(command))) {
            filterChain.doFilter(req, resp);
        } else if (isUserAuthorized && SessionHandler.getRole(session).equals("admin")
                && (adminAccess.contains(requestPath) || adminAccess.contains(command))) {
            filterChain.doFilter(req,resp);
        } else {
            if (!isUserAuthorized) {
                resp.sendRedirect("/view/authorization.jsp");
            } else {
                resp.sendRedirect("frontController?command=error");
            }
        }
    }
}
