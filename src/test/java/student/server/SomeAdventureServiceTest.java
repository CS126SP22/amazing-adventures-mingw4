package student.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class SomeAdventureServiceTest {
    private SomeAdventureService service;

    @Before
    public void setUp() {
        // This is run before every test.
        service = new SomeAdventureService();
    }

    @Test
    public void testReset() throws AdventureException {
        assertEquals(0, service.newGame());
        service.reset();
        assertEquals(0, service.newGame());
    }

    @Test
    public void testNewGame() throws AdventureException {
        assertEquals(0, service.newGame());
        assertEquals(1, service.newGame());
    }

    @Test
    public void testGetGame() throws AdventureException {
        assertEquals(0, service.newGame());
        assertNotNull(service.getGame(0));
    }

    @Test
    public void testDestroyGame() throws AdventureException {
        assertEquals(0, service.newGame());
        service.reset();
        assertEquals(0, service.newGame());
    }

    @Test
    public void testFetchLeaderboard() throws AdventureException {
        assertEquals(Map.of(), service.fetchLeaderboard());
    }
}
