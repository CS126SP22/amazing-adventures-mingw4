package student.adventure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class RoomTest {
    private Room room;

    @Before
    public void setUp() {
        // This is run before every test.
        room = new Room("MatthewsStreet", "You are on Matthews, outside the Siebel Center",
                new Direction[] { new Direction("East", "SiebelEntry") }, new ArrayList<>(List.of("coin")));
    }

    @Test
    public void testTakeExistingItem() {
        assertEquals("coin", room.take("coin"));
        assertEquals(List.of(), room.getItems());
    }

    @Test
    public void testTakeExistingItemIgnoreCase() {
        assertEquals("coin", room.take("CoIn"));
        assertEquals(List.of(), room.getItems());
    }

    @Test
    public void testTakeNonExistingItem() {
        assertNull(room.take("sweatshirt"));
        assertEquals(List.of("coin"), room.getItems());
    }

    @Test
    public void testDrop() {
        room.drop("sweatshirt");
        assertEquals(List.of("coin", "sweatshirt"), room.getItems());
    }

    @Test
    public void testDropDuplicateItems() {
        room.drop("coin");
        assertEquals(List.of("coin", "coin"), room.getItems());
    }

    @Test
    public void testToStringWithoutItems() {
        room = new Room("MatthewsStreet", "You are on Matthews, outside the Siebel Center",
                new Direction[] { new Direction("East", "SiebelEntry") }, List.of());
        assertEquals("You are on Matthews, outside the Siebel Center\nFrom here, you can go: East", room.toString());
    }

    @Test
    public void testToStringWithItems() {
        assertEquals("You are on Matthews, outside the Siebel Center\nFrom here, you can go: East\nItems visible: coin",
                room.toString());
    }
}
