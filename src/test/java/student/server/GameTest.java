package student.server;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GameTest {
    private Game game;

    @Before
    public void setUp() throws AdventureException {
        // This is run before every test.
        game = new Game(0);
    }

    @Test
    public void testExecuteCommandGo() {
        game.executeCommand(new Command("go", "East"));
        assertEquals("You are in the west entry of Siebel Center. You can see the elevator, the ACM office, and hallways to the north and east.\nFrom here, you can go: West, Northeast, North, East\nItems visible: sweatshirt, key", game.getGame().getMessage());
        assertEquals(List.of("West", "Northeast", "North", "East"), game.getGame().getCommandOptions().get("go"));
        assertEquals(List.of("sweatshirt", "key"), game.getGame().getCommandOptions().get("take"));
        assertEquals(List.of(), game.getGame().getCommandOptions().get("drop"));
    }

    @Test
    public void testExecuteCommandExamine() {
        game.executeCommand(new Command("examine", ""));
        assertEquals("You are on Matthews, outside the Siebel Center\nFrom here, you can go: East\nItems visible: coin", game.getGame().getMessage());
        assertEquals(List.of("East"), game.getGame().getCommandOptions().get("go"));
        assertEquals(List.of("coin"), game.getGame().getCommandOptions().get("take"));
        assertEquals(List.of(), game.getGame().getCommandOptions().get("drop"));
    }

    @Test
    public void testExecuteCommandTake() {
        game.executeCommand(new Command("take", "coin"));
        assertEquals("", game.getGame().getMessage());
        assertEquals(List.of("East"), game.getGame().getCommandOptions().get("go"));
        assertEquals(List.of(), game.getGame().getCommandOptions().get("take"));
        assertEquals(List.of("coin"), game.getGame().getCommandOptions().get("drop"));
    }

    @Test
    public void testExecuteCommandDrop() {
        game.executeCommand(new Command("take", "coin"));
        game.executeCommand(new Command("drop", "coin"));
        assertEquals("", game.getGame().getMessage());
        assertEquals(List.of("East"), game.getGame().getCommandOptions().get("go"));
        assertEquals(List.of("coin"), game.getGame().getCommandOptions().get("take"));
        assertEquals(List.of(), game.getGame().getCommandOptions().get("drop"));
    }

    @Test
    public void testExecuteCommandHistory() {
        game.executeCommand(new Command("history", ""));
        assertEquals("Your history of traversed rooms: MatthewsStreet", game.getGame().getMessage());
        assertEquals(List.of("East"), game.getGame().getCommandOptions().get("go"));
        assertEquals(List.of("coin"), game.getGame().getCommandOptions().get("take"));
        assertEquals(List.of(), game.getGame().getCommandOptions().get("drop"));
    }

    @Test
    public void testGetGame() {
        game.executeCommand(new Command("examine", ""));
        assertEquals("You are on Matthews, outside the Siebel Center\nFrom here, you can go: East\nItems visible: coin", game.getGame().getMessage());
        assertEquals(List.of("East"), game.getGame().getCommandOptions().get("go"));
        assertEquals(List.of("coin"), game.getGame().getCommandOptions().get("take"));
        assertEquals(List.of(), game.getGame().getCommandOptions().get("drop"));
    }
}
