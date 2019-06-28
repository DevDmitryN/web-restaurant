package com.serviceSystem.web;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class ActionResolver {
    Logger logger = LoggerFactory.getLogger(ActionResolver.class);
    Action resolve(HttpServletRequest req){
        String command = req.getParameter("command");
        Action action = Action.ERROR;
        try{
            action = Action.valueOf(command.toUpperCase());
        }catch (Exception e){
            logger.warn("bad request");
            e.printStackTrace();
        }
        return action;
    }
}
