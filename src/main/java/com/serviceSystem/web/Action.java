package com.serviceSystem.web;

import com.serviceSystem.web.command.*;

public enum Action {
    SHOW_ORDERS(new ShowOrders()),
    DELETE_ORDER(new DeleteOrder()),
    SHOW_DISHES_OF_ORDER(new ShowDishesOfOrder()),
    ERROR(new ErrorCommand());

    private Command command;

    Action(Command command){
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
