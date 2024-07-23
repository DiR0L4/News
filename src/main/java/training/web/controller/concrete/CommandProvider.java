package training.web.controller.concrete;

import training.web.controller.concrete.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider(){
        commands.put(CommandName.GO_TO_AUTH, new GoToAuth());
        commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
        commands.put(CommandName.DO_AUTH, new DoAuth());
        commands.put(CommandName.DO_REGISTRATION, new DoRegistration());
        commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
        commands.put(CommandName.GO_TO_INDEX_PAGE, new GoToIndexPage());
        commands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPage());
        commands.put(CommandName.DO_LOGOUT, new DoLogout());
        commands.put(CommandName.GO_TO_ADD_NEWS_PAGE, new GoToAddNewsPage());
        commands.put(CommandName.DO_ADD_NEWS, new DoAddNews());
        commands.put(CommandName.DO_CHANGE_LOCALE, new DoChangeLocale());
        commands.put(CommandName.GO_TO_ERROR_PAGE, new GoToErrorPage());
        commands.put(CommandName.GO_TO_FULL_NEWS_PAGE, new GoToFullNewsPage());
    }

    public Command takeCommand(String userCommand){
        CommandName commandName;
        Command command;

        try {
            commandName = CommandName.valueOf(userCommand.toUpperCase());
            command = commands.get(commandName);
        }catch (IllegalArgumentException | NullPointerException e){
            command = commands.get(CommandName.NO_SUCH_COMMAND);
        }

        return command;
    }
}
