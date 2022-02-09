package student.adventure;

import java.util.ArrayList;
import java.util.List;

public class Adventure {
    private Layout layout;
    private Room currentRoom;
    private List<String> inventory = new ArrayList<>();

    public Adventure(Layout layout) {
        this.layout = layout;
        updateCurrentRoom(layout.getStartingRoom());
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public List<String> getInventory() {
        return inventory;
    }

    /**
     * Change the current room that the user is in to the room in the given direction.
     * Return false if the direction is invalid.
     */
    public boolean go(String directionName) {
        for (Direction direction : currentRoom.getDirections()) {
            if (direction.getDirectionName().equalsIgnoreCase(directionName)) {
                updateCurrentRoom(direction.getRoom());
                return true;
            }
        }
        return false;
    }

    /**
     * Reached the ending room.
     */
    public boolean reachedEndingRoom() {
        return currentRoom.getName().equalsIgnoreCase(layout.getEndingRoom());
    }

    /**
     * Attempt to give the given ite to the user. Return false if no such item exists in the room.
     */
    public boolean take(String itemName) {
        String item = currentRoom.take(itemName);
        if (item == null) {
            return false;
        }
        inventory.add(item);
        return true;
    }

    /**
     * Remove the given item from the inventory and place it in the room.
     * Return false if the user does not have the item.
     */
    public boolean drop(String itemName) {
        for (String item : inventory) {
            if (item.equalsIgnoreCase(itemName)) {
                inventory.remove(item);
                currentRoom.drop(item);
                return true;
            }
        }
        return false;
    }

    private void updateCurrentRoom(String name) {
        for (Room room : layout.getRooms()) {
            if (room.getName().equalsIgnoreCase(name)) {
                currentRoom = room;
                return;
            }
        }
        throw new IllegalArgumentException("No room with the name \"" + name + "\" is found.");
    }
}
