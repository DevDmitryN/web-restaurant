package com.serviceSystem.web;

import com.serviceSystem.web.command.*;

public enum Action {
    CANCEL_ORDER(new CancelOrder()),
    SHOW_TABLES(new ShowTables()),
    CHANGE_TABLE_STATUS(new ChangeTableStatus()),
    SIGN_UP(new SignUp()),
    SHOW_ACTIVE_ORDERS(new ShowActiveOrders()),
    SHOW_ORDERS(new ShowOrders()),
    DELETE_ORDER(new DeleteOrder()),
    SHOW_DISHES_OF_ORDER(new ShowDishesOfOrder()),
    ERROR(new ErrorCommand()),
    AUTHORIZATION(new Authorization()),
    LOGOUT(new Logout());
    private Command command;

    Action(Command command){
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
