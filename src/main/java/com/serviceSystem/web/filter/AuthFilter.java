package com.serviceSystem.web.filter;

import com.serviceSystem.entity.Client;
import com.serviceSystem.service.ClientService;
import com.serviceSystem.service.WorkerService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse res = (HttpServletResponse) servletResponse;

        final String phoneNumber = req.getParameter("phone_number");
        final String password = req.getParameter("password");

        final HttpSession session = req.getSession();

        ClientService clientService = ClientService.getInstance();
        WorkerService workerService = WorkerService.getInstance();

        if(nonNull(session) && nonNull(phoneNumber) && nonNull(password)){

        }else if(clientService.isExist(phoneNumber,password)){

        }else if(workerService.isExist(phoneNumber,password)){

        }else{

        }
    }
}
