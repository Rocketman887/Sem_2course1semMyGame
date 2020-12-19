package room;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Room {
        public static final List<Connection> list = new ArrayList<>();
        public int id;

    public Room(int id) {
        this.id = id;
    }
}
