package student.server;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * A class to represent values in a game state.
 *
 * Note: these fields should be JSON-serializable values, like Strings, ints, floats, doubles, etc.
 * Please don't nest objects, as the frontend won't know how to display them.
 *
 * Good example:
 * private String shoppingList;
 *
 * Bad example:
 * private ShoppingList shoppingList;
 */
@JsonSerialize
public class AdventureState {
    private String inventory;

    public AdventureState(String inventory) {
        this.inventory = inventory;
    }

    public String getInventory() {
        return inventory;
    }
}
