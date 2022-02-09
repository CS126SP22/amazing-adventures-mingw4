package student.adventure;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Driver {
    private static String INPUT_QUIT = "quit";
    private static String INPUT_EXIT = "exit";
    private static String INPUT_GO = "go ";
    private static String INPUT_EXAMINE = "examine";
    private static String INPUT_TAKE = "take ";
    private static String INPUT_DROP = "drop ";

    /**
     * Accept input from the user in the console, and output data back to the console.
     */
    public static void run(URL src) throws IOException {
        Adventure adventure = new Adventure(new ObjectMapper().readValue(src, Layout.class));
        System.out.println(adventure.getCurrentRoom());
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.print("> ");
                String input = sc.nextLine().trim();
                if (input.toLowerCase().equals(INPUT_QUIT) || input.toLowerCase().equals(INPUT_EXIT)) {
                    return;
                } else if (input.toLowerCase().startsWith(INPUT_GO)) {
                    String directionName = input.substring(INPUT_GO.length()).trim();
                    if (adventure.go(directionName)) {
                        if (adventure.reachedEndingRoom()) {
                            System.out.println("You've made it on time to code review; you win!");
                            return;
                        }
                        System.out.println(adventure.getCurrentRoom());
                    } else {
                        System.out.println("I can't go \"" + directionName + "\"!");
                    }
                } else if (input.toLowerCase().equals(INPUT_EXAMINE)) {
                    System.out.println(adventure.getCurrentRoom());
                } else if (input.toLowerCase().startsWith(INPUT_TAKE)) {
                    String itemName = input.substring(INPUT_TAKE.length()).trim();
                    if (!adventure.take(itemName)) {
                        System.out.println("There is no item \"" + itemName + "\" in the room.");
                    }
                } else if (input.toLowerCase().startsWith(INPUT_DROP)) {
                    String itemName = input.substring(INPUT_DROP.length()).trim();
                    if (!adventure.drop(itemName)) {
                        System.out.println("You don't have \"" + itemName + "!\"");
                    }
                } else {
                    System.out.println("I don't understand \"" + input + "\"");
                }
            }
        }
    }
}
