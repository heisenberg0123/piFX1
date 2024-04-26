package services;

import cnx.Connexion;
import models.Pret;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PretService implements IService<Pret>{



    private Connection connection;

    public PretService(){
        connection=Connexion.getInstance().getConnection();

    }
    @Override
    public void ajouter(Pret pret) throws SQLException {
        String req = "INSERT INTO pret (montant, periode, interet, type, description, status, accepte, refuse) " +
                "VALUES ('" + pret.getMontant() + "','" + pret.getPeriode() + "','" + pret.getInteret() + "','" +
                pret.getType() + "','" + pret.getDescription() + "','" + pret.getStatus() + "','" +
                pret.getAccepte() + "','" + pret.getRefuse() + "')";

        Statement st = connection.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(Pret pret) throws SQLException {
String req="UPDATE pret SET montant=?,periode=? ,interet= ? , type= ?, description=?, status=?, accepte= ?, refuse= ?  WHERE id=? ";

        PreparedStatement ps=connection.prepareStatement(req);
        ps.setInt(1,pret.getMontant());
        ps.setInt(2,pret.getPeriode());
        ps.setString(3,pret.getInteret());
        ps.setString(4,pret.getType());
        ps.setString(5,pret.getDescription());
        ps.setInt(6,pret.getStatus());
        ps.setInt(7,pret.getAccepte());
        ps.setInt(8,pret.getRefuse());
        ps.setInt(9,pret.getId());

        ps.executeUpdate();
    }

    @Override
    public void supprimer(int id) throws SQLException {

String req=" DELETE FROM pret WHERE id=?";
PreparedStatement pr =connection.prepareStatement(req);
pr.setInt(1,id);
pr.executeUpdate();

    }

    @Override
    public List<Pret> recuperer() throws SQLException {

        List<Pret> list =new ArrayList<>();
        String req ="SELECT * FROM pret";
        Statement st =connection.createStatement();
       ResultSet rs = st.executeQuery(req);
       while(rs.next()){
           Pret pret =new Pret();
           pret.setId(rs.getInt("id"));
           pret.setMontant(rs.getInt("montant"));
           pret.setInteret(rs.getString("interet"));
           pret.setPeriode(rs.getInt("periode"));
           pret.setDescription(rs.getString("description"));
           pret.setType(rs.getString("type"));
           pret.setStatus(rs.getInt("status"));
           pret.setAccepte(rs.getInt("accepte"));
           pret.setRefuse(rs.getInt("refuse"));
           list.add(pret);
       }

return list;

    }
}
