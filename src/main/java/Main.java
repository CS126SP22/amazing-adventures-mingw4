import java.io.IOException;
import java.net.URL;

import student.adventure.Driver;

public class Main {
    private static String DEFAULT_RESOURCE_NAME = "siebel.json";

    public static void main(String[] args) throws IOException {
        // Wishing you good luck on your Adventure!
        String name = args.length == 0 ? DEFAULT_RESOURCE_NAME : args[0];
        URL src = Main.class.getResource(name);
        if (src == null) {
            System.out.println("No resource with the name \"" + name + "\" is found.");
            return;
        }
        Driver.run(src);
    }
}
