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
