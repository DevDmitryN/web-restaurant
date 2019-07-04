package com.serviceSystem.web.command;

import com.serviceSystem.DAO.builder.ColumnNames;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Command {
    public abstract void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
