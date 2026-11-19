package tasca_jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List; 

public class AtletaPgDAO implements DAO<Atleta> {
    private Connection conn;

    public AtletaPgDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean add(Atleta atleta) {
        // TODO Auto-generated method stub
        String atletaAdd = "INSERT INTO deportistas (nombre, cod_deporte) VALUES ('" + atleta.name + "', '" + atleta.esport + "')";

        if (conn == null)
            return false;
        
        try (PreparedStatement stmt = conn.prepareStatement(atletaAdd)) {
            int rs = stmt.executeUpdate();
            // System.out.println("Atleta nou afegit" + rs);
            return true;

        } catch (SQLException e) {
            System.err.println("Error al afegir atletes: " + e.getMessage());
            return false;
        }

        //throw new UnsupportedOperationException("Unimplemented method 'add'");

    }

    @Override
    public List<Atleta> getAll() {
        List<Atleta> atletas = new ArrayList<>();

          String sql = "SELECT d.nombre AS Deportista, CASE WHEN d.cod_deporte IS NULL THEN 'Sin deporte' ELSE de.nombre END AS Esport FROM DEPORTISTAS d LEFT JOIN DEPORTES de ON d.cod_deporte = de.cod;";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                atletas.add(new Atleta(
                        rs.getString("Deportista"),
                        rs.getString("Esport")
                        ));
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar atletas: " + e.getMessage());
        }

        return atletas;
    }

    @Override
    public List<Atleta> getAllChosen(int codEsport) {
        List<Atleta> atletas = new ArrayList<>();

          String sql = "SELECT d.nombre AS Deportista, de.nombre AS Esport FROM DEPORTISTAS d LEFT JOIN DEPORTES de ON d.cod_deporte = de.cod WHERE d.cod_deporte =" +  codEsport + ";";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                atletas.add(new Atleta(
                        rs.getString("Deportista"),
                        rs.getString("Esport")
                        ));
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar atletas: " + e.getMessage());
        }

        return atletas;
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

}
