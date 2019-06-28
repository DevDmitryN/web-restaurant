package com.serviceSystem.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req,resp);
    }
    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String command = req.getParameter("command");
        if(command != null){
            System.out.println(command);
        }
        resp.sendRedirect("/front-controller");
    }
}
