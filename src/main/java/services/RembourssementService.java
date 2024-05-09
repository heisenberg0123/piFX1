package services;

import cnx.Connexion;
import models.Pret;
import models.Rembourssement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RembourssementService implements IService<Rembourssement> {

    private Connection connection;

    public RembourssementService(){
        connection= Connexion.getInstance().getConnection();
    }
    public void ajouter(Rembourssement rmb,int id) throws SQLException {
        String req = "INSERT INTO remboursement (mois, montant_paye, periode_retard, status,rmb_id) " +
                "VALUES ('" + rmb.getMois() + "','" + rmb.getMontant_paye() + "','" + rmb.getPeriode_retard() + "','" +
                rmb.getStatus() + "','" + id + "')";

        Statement st = connection.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void ajouter(Rembourssement rembourssement) throws SQLException {
        
    }

    @Override
    public void modifier(Rembourssement rembourssement) throws SQLException {
        String req="UPDATE remboursement SET moois=?,payee=? ,retard= ? , status= ? , rmb_id=? WHERE id=? ";

        PreparedStatement ps=connection.prepareStatement(req);
        ps.setString(1,rembourssement.getMois());
        ps.setInt(2,rembourssement.getMontant_paye());
        ps.setString(3,rembourssement.getPeriode_retard());
        ps.setInt(4,rembourssement.getStatus());
        ps.setInt(5,rembourssement.getId());
        ps.setInt(6, rembourssement.getRmb_id());

        ps.executeUpdate();
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req=" DELETE FROM remboursement WHERE id=?";
       PreparedStatement pr= connection.prepareStatement(req);
        pr.setInt(1, id);
        pr.executeUpdate();

    }

    @Override
    public List<Rembourssement> recuperer() throws SQLException {
        List<Rembourssement> list = new ArrayList<>();
        String req = "SELECT * FROM remboursement  ";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Rembourssement pret = new Rembourssement();
            pret.setId(rs.getInt("id"));
            pret.setMois(rs.getString("mois"));
            pret.setStatus(rs.getInt("status"));
            pret.setMontant_paye(rs.getInt("montant_paye"));
            pret.setPeriode_retard(rs.getString("periode_retard"));
            pret.setRmb_id(rs.getInt("rmb_id"));

            list.add(pret);
        }
        return list;
    }
}
