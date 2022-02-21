import java.io.IOException;
import java.net.URL;

import student.adventure.Driver;
import student.server.AdventureResource;
import student.server.AdventureServer;

public class Main {
    private static String DEFAULT_RESOURCE_NAME = "siebel.json";

    public static void main(String[] args) throws IOException {
        // Wishing you good luck on your Adventure!
        AdventureServer.createServer(AdventureResource.class);

        String name = args.length == 0 ? DEFAULT_RESOURCE_NAME : args[0];
        URL src = Main.class.getResource(name);
        if (src == null) {
            System.out.println("No resource with the name \"" + name + "\" is found.");
            return;
        }
        Driver.run(src);
    }
}
