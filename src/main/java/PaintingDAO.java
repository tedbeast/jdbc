import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (data-access-object) is a style of a class that is designed to handle database interactions
 */
public class PaintingDAO {
    Connection conn;
    public PaintingDAO(){
        try {
            conn = DriverManager.getConnection("jdbc:h2:./h2/db");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void dropPaintingTable(){
        try{
            PreparedStatement ps = conn.prepareStatement("drop table painting");
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void createPaintingTable(){
        try{
            PreparedStatement ps = conn.prepareStatement("create table painting(title varchar(255), year_made int)");
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void insertPainting(Painting painting){
        try{
            PreparedStatement ps = conn.prepareStatement("insert into painting (title, year_made) values (?, ?)");
//            ps.setString, ps.setInt 'fills in' the question marks in a preparedStatement
//            this not only makes them easier to write but it also avoids a security issue called SQL injection
            ps.setString(1, painting.title);
            ps.setInt(2, painting.year_made);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public List<Painting> getAllPainting(){
        try{
            PreparedStatement ps = conn.prepareStatement("select * from painting");
//            because we want to retrieve data meaningfully in java, we have to expect data in the form of a 'resultset'
//            we also have to use executeQuery instead of executeUpdate, because executeQuery is expecting a resultSet
            ResultSet rs = ps.executeQuery();
            List<Painting> allPaintings = new ArrayList<>();
//            we have to loop through the entire resultset for every item it contains
            while(rs.next()){
//                we have to extract the DB column of each row into a meaningful java object
                Painting newPainting = new Painting(rs.getString("title"), rs.getInt("year_made"));
                allPaintings.add(newPainting);
            }
            return allPaintings;

        }catch(SQLException e){
            e.printStackTrace();
        }
//        in the event that we don't get to return allPaintings because a SQLException was thrown, just return null
        return null;
    }
}
