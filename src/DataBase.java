import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/test1";
    private static final String password = "Mehr_12345";
    private static final String user = "admins";
    private static Statement st ;
    private static Connection con ;

    private DataBase()  {
        try {
            con = DriverManager.getConnection(url , user , password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            st = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String [] args) throws SQLException {
        new DataBase();
        String ste= "hevf";
        String insertion = "INSERT INTO tb1(col1, col2) VALUES (32, '"+ste+"');";
        st.execute(insertion);



    }
}
