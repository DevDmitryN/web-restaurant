package com.serviceSystem.web.command;



import com.serviceSystem.web.filter.SessionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Logout extends Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionHandler.removeUser(req.getSession());
        resp.sendRedirect("authorization.jsp");
    }
}
