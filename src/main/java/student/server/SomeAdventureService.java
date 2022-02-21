package student.server;

import java.util.SortedMap;
import java.util.TreeMap;

public class SomeAdventureService implements AdventureService {
    private TreeMap<Integer, Game> map = new TreeMap<>();

    @Override
    public void reset() {
        map.clear();
    }

    /**
     * Creates a new Adventure game and stores it.
     * @return the id of the game.
     */
    @Override
    public int newGame() throws AdventureException {
        int id = map.isEmpty() ? 0 : map.lastKey() + 1;
        map.put(id, new Game(id));
        return id;
    }

    /**
     * Returns the state of the game instance associated with the given ID.
     * @param id the instance id
     * @return the current state of the game
     */
    @Override
    public GameStatus getGame(int id) {
        return map.get(id).getGame();
    }

    /**
     * Removes & destroys a game instance with the given ID.
     * @param id the instance id
     * @return false if the instance could not be found and/or was not deleted
     */
    @Override
    public boolean destroyGame(int id) {
        if (!map.containsKey(id)) {
            return false;
        }
        map.remove(id);
        return true;
    }

    /**
     * Executes a command on the game instance with the given id, changing the game state if applicable.
     * @param id the instance id
     * @param command the issued command
     */
    @Override
    public void executeCommand(int id, Command command) {
        map.get(id).executeCommand(command);
    }

    /**
     * Returns a sorted leaderboard of player "high" scores.
     * @return a sorted map of player names to scores
     */
    @Override
    public SortedMap<String, Integer> fetchLeaderboard() {
        return new TreeMap<>();
    }
}
