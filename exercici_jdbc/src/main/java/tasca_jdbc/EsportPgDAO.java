package tasca_jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List; 

public class EsportPgDAO implements DAO<Esport> {
    private Connection conn;

    public EsportPgDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean add(Esport esport) {
        // TODO Auto-generated method stub
        String esportAdd = "INSERT INTO deportes (nombre) VALUES ('" + esport.esport +"')";

        if (conn == null)
            return false;
        
        try (PreparedStatement stmt = conn.prepareStatement(esportAdd)) {
            int rs = stmt.executeUpdate();
            // System.out.println("Atleta nou afegit" + rs);
            return true;

        } catch (SQLException e) {
            System.err.println("Error al afegir esports: " + e.getMessage());
            return false;
        }

        //throw new UnsupportedOperationException("Unimplemented method 'add'");

    }

    @Override
    public List<Esport> getAll() {
        List<Esport> esports = new ArrayList<>();

          String sql = "SELECT * FROM deportes;";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                esports.add(new Esport(
                        rs.getString("cod"),
                        rs.getString("nombre")
                        ));
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar atletas: " + e.getMessage());
        }

        return esports;
    }

    public void callFunction(String p1, String p2) {
        CallableStatement cStmt = null;
        try {
            cStmt = conn.prepareCall    ("{call get_user_name(?,?)}");
            cStmt.setString(1, p1);
            cStmt.setString(2, p2);
            cStmt.execute();
            cStmt.registerOutParameter(1, Types.INTEGER);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    

    }

    public boolean userExists(String name) {
        return false;
    }

    @Override
    public List<Esport> getAllChosen(int item) {
        throw new UnsupportedOperationException("Funcio nomes per a Atletes");
    }

}
