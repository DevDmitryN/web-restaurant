package com.serviceSystem.web;

import com.serviceSystem.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontControllerServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req,resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        ActionResolver actionResolver = new ActionResolver();
        Action action = actionResolver.resolve(req);
        Command command = action.getCommand();
        command.execute(req,resp);
    }
}
