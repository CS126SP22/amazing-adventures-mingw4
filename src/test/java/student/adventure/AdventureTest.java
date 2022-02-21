package student.adventure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

//All tests are passed.
public class AdventureTest {
    private Room[] rooms = {
        new Room("MatthewsStreet", "You are on Matthews, outside the Siebel Center",
                new Direction[] { new Direction("East", "SiebelEntry") }, new ArrayList<>(List.of("coin"))),
        new Room("SiebelEntry", "You are in the west entry of Siebel Center. You can see the elevator, the ACM office, and hallways to the north and east.",
                new Direction[] { new Direction("West", "MatthewsStreet"), new Direction("East", "SiebelEastHallway") }, List.of("sweatshirt", "key")),
        new Room("SiebelEastHallway", "You are in the east hallway.  You can see Einstein Bros' Bagels and a stairway.",
                new Direction[] { new Direction("West", "SiebelEntry"), new Direction("South", "Siebel1314") }, List.of("coin")),
        new Room("Siebel1314", "You are in Siebel 1314.  There are happy CS 126 students doing a code review.",
                new Direction[] { new Direction("North", "SiebelEastHallway") }, List.of()),
    };
    private Adventure adventure;

    @Before
    public void setUp() {
        // This is run before every test.
        adventure = new Adventure(new Layout("MatthewsStreet", "Siebel1314", rooms));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorIllegalStartingRoom() {
        adventure = new Adventure(new Layout("AcmOffice", "Siebel1314", rooms));
    }

    @Test
    public void testGetCurrentRoomInitial() {
        assertEquals("MatthewsStreet", adventure.getCurrentRoom().getName());
    }

    @Test
    public void testGoValidDirection() {
        assertTrue(adventure.go("East"));
        assertEquals("SiebelEntry", adventure.getCurrentRoom().getName());
    }

    @Test
    public void testGoValidDirectionIgnoreCase() {
        assertTrue(adventure.go("EaSt"));
        assertEquals("SiebelEntry", adventure.getCurrentRoom().getName());
    }

    @Test
    public void testGoInvalidDirection() {
        assertFalse(adventure.go("Eastward"));
        assertEquals("MatthewsStreet", adventure.getCurrentRoom().getName());
    }

    @Test
    public void testReachedEndingRoomFalse() {
        assertFalse(adventure.reachedEndingRoom());
    }

    @Test
    public void testReachedEndingRoomTrue() {
        adventure.go("East");
        adventure.go("East");
        adventure.go("South");
        assertTrue(adventure.reachedEndingRoom());
    }

    @Test
    public void testTakeExistingItem() {
        assertTrue(adventure.take("coin"));
        assertEquals(List.of(), adventure.getCurrentRoom().getItems());
        assertEquals(List.of("coin"), adventure.getInventory());
    }

    @Test
    public void testTakeExistingItemIgnoreCase() {
        assertTrue(adventure.take("CoIn"));
        assertEquals(List.of(), adventure.getCurrentRoom().getItems());
        assertEquals(List.of("coin"), adventure.getInventory());
    }

    @Test
    public void testTakeNonExistingItem() {
        assertFalse(adventure.take("sweatshirt"));
        assertEquals(List.of("coin"), adventure.getCurrentRoom().getItems());
        assertEquals(List.of(), adventure.getInventory());
    }

    @Test
    public void testDropExistingItem() {
        adventure.getInventory().add("sweatshirt");
        assertTrue(adventure.drop("sweatshirt"));
        assertEquals(List.of("coin", "sweatshirt"), adventure.getCurrentRoom().getItems());
        assertEquals(List.of(), adventure.getInventory());
    }

    @Test
    public void testDropExistingItemIgnoreCase() {
        adventure.getInventory().add("sweatshirt");
        assertTrue(adventure.drop("SwEaTsHiRt"));
        assertEquals(List.of("coin", "sweatshirt"), adventure.getCurrentRoom().getItems());
        assertEquals(List.of(), adventure.getInventory());
    }

    @Test
    public void testDropNonExistingItem() {
        assertFalse(adventure.drop("sweatshirt"));
        assertEquals(List.of("coin"), adventure.getCurrentRoom().getItems());
        assertEquals(List.of(), adventure.getInventory());
    }

    @Test
    public void testDropDuplicateItem() {
        adventure.getInventory().add("coin");
        assertTrue(adventure.drop("coin"));
        assertEquals(List.of("coin", "coin"), adventure.getCurrentRoom().getItems());
        assertEquals(List.of(), adventure.getInventory());
    }
}
