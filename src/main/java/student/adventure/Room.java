package student.adventure;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Room {
    private String name = "";
    private String description = "";
    private Direction[] directions = new Direction[0];
    private List<String> items = new ArrayList<>();

    public Room() {
    }

    public Room(String name, String description, Direction[] directions, List<String> items) {
        this.name = name;
        this.description = description;
        this.directions = directions;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Direction[] getDirections() {
        return directions;
    }

    public List<String> getItems() {
        return items;
    }

    /**
     * Attempt to remove the given item and return it back to the caller.
     */
    public String take(String itemName) {
        for (String item : items) {
            if (item.equalsIgnoreCase(itemName)) {
                items.remove(item);
                return item;
            }
        }
        return null;
    }

    /**
     * Place the given item in the room.
     */
    public void drop(String itemName) {
        items.add(itemName);
    }

    public String toString() {
        return description + "\nFrom here, you can go: "
                + String.join(", ", Stream.of(directions).map(direction -> direction.getDirectionName()).toList())
                + (items.isEmpty() ? "" : "\nItems visible: " + String.join(", ", items));
    }
}
