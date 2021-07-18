package DataBase;

import java.io.Serializable;
import java.time.LocalDate;

public class GameHistory implements Serializable {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";  // WHITE
    private String userName_1;
    private String userName_2;
    private String date;
    private String winner;

    public GameHistory(String userName_1, String userName_2, String winner) {
        this.userName_1 = userName_1;
        this.userName_2 = userName_2;
        this.date = LocalDate.now().toString();
        this.winner = winner;
    }

    public String getUserName_1() {
        return userName_1;
    }

    public String getUserName_2() {
        return userName_2;
    }

    public String getDate() {
        return date;
    }

    public String getWinner() {
        return winner;
    }

    @Override
    public String toString() {
        return userName_1 + " VS " + userName_2 + "\n Winner is : " + winner + "\n" + date;
    }
}
