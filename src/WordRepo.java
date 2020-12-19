import java.util.ArrayList;

public class WordRepo {
    private static final ArrayList<String> gameWords = new ArrayList<>();
    static {
        gameWords.add("car");
        gameWords.add("coffee");
        gameWords.add("hat");
        gameWords.add("semestrovaya");
        gameWords.add("glasses");
        gameWords.add("floor lamp");
        gameWords.add("");

    }
    public static String generateWord(){
        int a = (int)(Math.random()*gameWords.size());
        return gameWords.get(a);
    }
}
