package models;

public class Pret {
    private  int id,montant,periode;
    private String interet,type,description;
    private int status,accepte,refuse;

    public Pret( int montant, int periode, String interet, String type, String description, int status, int accepte, int refuse) {

        this.montant = montant;
        this.periode = periode;
        this.interet = interet;
        this.type = type;
        this.description = description;
        this.status = status;
        this.accepte = accepte;
        this.refuse = refuse;
    }
    public Pret(int id ,int montant, int periode, String interet, String type, String description, int status, int accepte, int refuse) {
this.id=id;
        this.montant = montant;
        this.periode = periode;
        this.interet = interet;
        this.type = type;
        this.description = description;
        this.status = status;
        this.accepte = accepte;
        this.refuse = refuse;
    }

    public Pret() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public int getPeriode() {
        return periode;
    }

    public void setPeriode(int periode) {
        this.periode = periode;
    }

    public String getInteret() {
        return interet;
    }

    public void setInteret(String interet) {
        this.interet = interet;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAccepte() {
        return accepte;
    }

    public void setAccepte(int accepte) {
        this.accepte = accepte;
    }

    public int getRefuse() {
        return refuse;
    }

    public void setRefuse(int refuse) {
        this.refuse = refuse;
    }

    @Override
    public String toString() {
        return "Pret{" +
                "id=" + id +
                ", montant=" + montant +
                ", periode=" + periode +
                ", interet='" + interet + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", accepte=" + accepte +
                ", refuse=" + refuse +
                '}';
    }

}
