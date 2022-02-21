package student.server;

import student.adventure.Adventure;
import student.adventure.Layout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Game {
    private static final String DEFAULT_RESOURCE_NAME = "siebel.json";
    private static final String COMMAND_NAME_GO = "go";
    private static final String COMMAND_NAME_EXAMINE = "examine";
    private static final String COMMAND_NAME_TAKE = "take";
    private static final String COMMAND_NAME_DROP = "drop";
    private static final String COMMAND_NAME_HISTORY = "history";

    private Adventure adventure;
    private int id;
    private String message;
    private List<String> history = new ArrayList<>();

    public Game(int id) throws AdventureException {
        try {
            adventure = new Adventure(new ObjectMapper().readValue(getClass().getClassLoader().getResource(DEFAULT_RESOURCE_NAME), Layout.class));
        } catch (IOException e) {
            throw new AdventureException(e.getMessage());
        }
        this.id = id;
        message = "" + adventure.getCurrentRoom();
        history.add(adventure.getCurrentRoom().getName());
    }

    public void executeCommand(Command command) {
        message = "";
        switch (command.getCommandName()) {
            case COMMAND_NAME_GO:
                adventure.go(command.getCommandValue());
                history.add(adventure.getCurrentRoom().getName());
                if (adventure.reachedEndingRoom()) {
                    message = "You've made it on time to code review; you win!";
                } else {
                    message = "" + adventure.getCurrentRoom();
                }
                break;
            case COMMAND_NAME_EXAMINE:
                message = "" + adventure.getCurrentRoom();
                break;
            case COMMAND_NAME_TAKE:
                adventure.take(command.getCommandValue());
                break;
            case COMMAND_NAME_DROP:
                adventure.drop(command.getCommandValue());
                break;
            case COMMAND_NAME_HISTORY:
                message = "Your history of traversed rooms: " + String.join(", ", history);
                break;
            default:
                break;
        }
    }

    public GameStatus getGame() {
        return new GameStatus(false, id, message, null, null, new AdventureState(String.join(", ", adventure.getInventory())), Map.of(
                COMMAND_NAME_GO, Stream.of(adventure.getCurrentRoom().getDirections()).map(direction -> direction.getDirectionName()).toList(),
                COMMAND_NAME_EXAMINE, List.of(""),
                COMMAND_NAME_TAKE, adventure.getCurrentRoom().getItems(),
                COMMAND_NAME_DROP, adventure.getInventory(),
                COMMAND_NAME_HISTORY, List.of("")));
    }
}
