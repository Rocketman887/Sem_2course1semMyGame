package models;

public class User {
    int id;
    String name;
    boolean isDraw=false;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }
    void willDraw(){
        this.isDraw = true;
    }
}
