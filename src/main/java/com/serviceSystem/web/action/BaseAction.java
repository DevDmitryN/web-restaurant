package com.serviceSystem.web.action;

import javax.servlet.http.HttpServletRequest;

public interface BaseAction {
    String execute(HttpServletRequest req);
}
