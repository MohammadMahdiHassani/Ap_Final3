package DataBase;

import java.time.LocalDate;

public class GameHistory {
    private String userName_1 ;
    private String userName_2 ;
    private String date ;
    private String winner;

    public GameHistory(String userName_1 , String userName_2,String winner) {
        this.userName_1 = userName_1;
        this.userName_2 = userName_2;
        this.date = LocalDate.now().toString();
        this.winner = winner;
    }
}
