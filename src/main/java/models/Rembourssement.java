package models;

public class Rembourssement {
    private int id,montant_paye,status,rmb_id;
    private String mois,periode_retard;


    public Rembourssement() {
    }

    public Rembourssement(int id, int montant_paye, int status, String mois, String periode_retard,int rmb_id) {
        this.id = id;
    this.rmb_id=rmb_id;
        this.montant_paye = montant_paye;
        this.status = status;
        this.mois = mois;
        this.periode_retard = periode_retard;
    }

    public Rembourssement(int montant_paye, int status, String mois, String periode_retard,int rmb_id) {
        this.montant_paye = montant_paye;
        this.status = status;
        this.mois = mois;
        this.periode_retard = periode_retard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMontant_paye() {
        return montant_paye;
    }

    public void setMontant_paye(int montant_paye) {
        this.montant_paye = montant_paye;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public String getPeriode_retard() {
        return periode_retard;
    }

    public void setPeriode_retard(String periode_retard) {
        this.periode_retard = periode_retard;
    }

    public int getRmb_id() {
        return rmb_id;
    }

    public void setRmb_id(int rmb_id) {
        this.rmb_id = rmb_id;
    }

    @Override
    public String toString() {
        return "Rembourssement{" +
                "id=" + id +
                ", montant_paye=" + montant_paye +
                ", status=" + status +
                ", rmb_id=" + rmb_id +
                ", mois='" + mois + '\'' +
                ", periode_retard='" + periode_retard + '\'' +
                '}';
    }
}
