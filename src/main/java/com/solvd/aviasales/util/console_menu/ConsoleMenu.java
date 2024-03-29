package com.solvd.aviasales.util.console_menu;

import com.solvd.aviasales.domain.actions.CollectorActions;
import com.solvd.aviasales.domain.actions.ConsoleMenuActions;
import com.solvd.aviasales.domain.actions.UserActions;
import com.solvd.aviasales.domain.session.ResultCollector;
import com.solvd.aviasales.domain.session.RouteCollector;
import com.solvd.aviasales.util.console_menu.menu_enums.*;

import static com.solvd.aviasales.util.Printers.*;

public class ConsoleMenu {
    protected static EntityActionsService ENTITY_ACTIONS_SERVICE = EntityActionsService.getInstance();
    private static final ResultCollector RESULT = new ResultCollector();

    public ConsoleMenu runMainMenu() {
        int answer = drawAnyMenuAndChooseMenuItem("MAIN MENU:", MainMenu.values());
        switch (answer) {
            case (1) -> {
                return runUserMenu();
            }
            case (2) -> {
                return authentication();
            }
            default -> {
                return tearDown();
            }
        }
    }

    private ConsoleMenu runUserMenu() {
        int answer = drawAnyMenuAndChooseMenuItem("USER MENU:", UserMenu.values());
        switch (answer) {
            case (1) -> {
                RouteCollector collector = UserActions.getRouteCollectionFromConsole();
                RESULT.addRouteCollectionToResult(collector);
                CollectorActions.showRouteCollection(collector);
                return runUserMenu();
            }
            case (2) -> {
                UserActions.chooseFinishRouteCollection(RESULT);
                return runUserMenu();
            }
            case (3) -> {
                return runMainMenu();
            }
            default -> {
                return tearDown();
            }
        }
    }

    private ConsoleMenu runAdminMenu() {
        int answer = drawAnyMenuAndChooseMenuItem("ADMIN MENU:", AdminMenu.values());
        switch (answer) {
            case (1) -> {
                ENTITY_ACTIONS_SERVICE.assignEntry("COMPANY");
                return runAdminCompanyMenu();
            }
            case (2) -> {
                ENTITY_ACTIONS_SERVICE.assignEntry("AIRLINE");
                return runAdminAirlineMenu();
            }
            case (3) -> {
                ENTITY_ACTIONS_SERVICE.assignEntry("ROUTE");
                return runAdminRouteMenu();
            }
            case (4) -> {
                return runMainMenu();
            }
            default -> {
                return tearDown();
            }
        }
    }

    private ConsoleMenu runAdminCompanyMenu() {
        int answer = drawAnyMenuAndChooseMenuItem("ADMIN COMPANY MENU:", AdminCompanyMenu.values());
        switch (answer) {
            case (1) -> {
                ENTITY_ACTIONS_SERVICE.getEntityActions().showEntityEntries();
                return runAdminCompanyMenu();
            }
            case (2) -> {
                ENTITY_ACTIONS_SERVICE.getEntityActions().registerEntityEntry();
                return runAdminCompanyMenu();
            }
            case (3) -> {
                ENTITY_ACTIONS_SERVICE.getEntityActions().updateEntityEntry();
                return runAdminCompanyMenu();
            }
            case (4) -> {
                ENTITY_ACTIONS_SERVICE.getEntityActions().removeEntityEntry();
                return runAdminCompanyMenu();
            }
            case (5) -> {
                return runAdminMenu();
            }
            default -> {
                return tearDown();
            }
        }
    }

    private ConsoleMenu runAdminAirlineMenu() {
        int answer = drawAnyMenuAndChooseMenuItem("ADMIN AIRLINE MENU:", AdminAirlineMenu.values());
        switch (answer) {
            case (1) -> {
                ENTITY_ACTIONS_SERVICE.getEntityActions().showEntityEntries();
                return runAdminAirlineMenu();
            }
            case (2) -> {
                ENTITY_ACTIONS_SERVICE.getEntityActions().registerEntityEntry();
                return runAdminAirlineMenu();
            }
            case (3) -> {
                ENTITY_ACTIONS_SERVICE.getEntityActions().updateEntityEntry();
                return runAdminAirlineMenu();
            }
            case (4) -> {
                ENTITY_ACTIONS_SERVICE.getEntityActions().removeEntityEntry();
                return runAdminAirlineMenu();
            }
            case (5) -> {
                return runAdminMenu();
            }
            default -> {
                return tearDown();
            }
        }
    }

    private ConsoleMenu runAdminRouteMenu() {
        int answer = drawAnyMenuAndChooseMenuItem("ADMIN ROUTE MENU:", AdminRouteMenu.values());
        switch (answer) {
            case (1) -> {
                ENTITY_ACTIONS_SERVICE.getEntityActions().showEntityEntries();
                return runAdminRouteMenu();
            }
            case (2) -> {
                ENTITY_ACTIONS_SERVICE.getEntityActions().registerEntityEntry();
                return runAdminRouteMenu();
            }
            case (3) -> {
                ENTITY_ACTIONS_SERVICE.getEntityActions().updateEntityEntry();
                return runAdminRouteMenu();
            }
            case (4) -> {
                ENTITY_ACTIONS_SERVICE.getEntityActions().removeEntityEntry();
                return runAdminRouteMenu();
            }
            case (5) -> {
                return runAdminMenu();
            }
            default -> {
                return tearDown();
            }
        }
    }

    private int drawAnyMenuAndChooseMenuItem(String title, IMenu[] menuItems) {
        int index = 1;
        PRINT2LN.info(title);
        for (IMenu item : menuItems) {
            PRINTLN.info(String.format("[%d]: %s", index, item.getTitle()));
            index++;
        }
        return RequestMethods.getNumberFromChoice("menu item number", index - 1);
    }

    private ConsoleMenu tearDown() {
        ConsoleMenuActions.tearDownActions(RESULT);
        RequestMethods.closeScanner();
        PRINTLN.info("GOOD BYE!");
        return null;
    }

    private ConsoleMenu authentication() {
        PRINT2LN.info("AUTHENTICATION");
        if (ConsoleMenuActions.authenticationActions()) {
            return runAdminMenu();
        } else {
            return runMainMenu();
        }
    }
}