package com.serviceSystem.web.filter;

import com.serviceSystem.entity.Client;
import com.serviceSystem.entity.User;
import com.serviceSystem.entity.Worker;

import javax.servlet.http.HttpSession;

public class SessionHandler {
    private final static String USER = "user";
    private final static String ROLE = "role";

    public static Client getClient(HttpSession session){
        return (Client) session.getAttribute(USER);
    }
    public static Worker getWorker(HttpSession session){
        return (Worker) session.getAttribute(USER);
    }
    public static void setUser(HttpSession session, User user,String role){
        switch (role){
            case "client":
                session.setAttribute(USER,(Client) user);
                session.setAttribute(ROLE,role);
                break;
            case "worker":
                session.setAttribute(USER,(Worker) user);
                session.setAttribute(ROLE,role);
                break;
        }
    }
    public static void removeUser(HttpSession session){
        if(session.getAttribute(USER) != null){
            session.removeAttribute(USER);
            session.removeAttribute(ROLE);
        }
    }
    public static String getWorkerRole(HttpSession session){
        return getWorker(session).getRole().name();
    }

    public static boolean isUserAuthorized(HttpSession session){
        return session.getAttribute(USER) != null;
    }
    public static String getRole(HttpSession session){
        return (String) session.getAttribute(ROLE);
    }
}
