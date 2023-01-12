import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
//        menu to interact with the DB
        PaintingDAO paintingDAO = new PaintingDAO();
        paintingDAO.dropPaintingTable();
        paintingDAO.createPaintingTable();
        Scanner sc = new Scanner(System.in);
        while(true){
            List<Painting> allPaintings = paintingDAO.getAllPainting();
            System.out.println(allPaintings);
            System.out.println("add a new painting: ");
            String p_title = sc.nextLine();
            String p_year_input = sc.nextLine();
            int p_year = Integer.parseInt(p_year_input);
            Painting p = new Painting(p_title, p_year);
            paintingDAO.insertPainting(p);

        }

    }
}
