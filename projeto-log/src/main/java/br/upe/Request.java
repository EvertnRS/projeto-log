package br.upe;

public class Request {
    private String IP_Adress;
    private String dateHour;
    private String solicitation;
    private String status;
    private String length;
    private String OperationalSystem;

    public String getIP_Adress() {
        return IP_Adress;
    }

    public String getDateHour() {
        return dateHour;
    }

    public String getSolicitation() {
        return solicitation;
    }

    public String getStatus() {
        return status;
    }

    public String getLength() {
        return length;
    }

    public String getOperationalSystem() {
        return OperationalSystem;
    }

    // Set Methods - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public void setIP_Adress(String ip) {
        this.IP_Adress = ip;
    }

    public void setDateHour(String dateHour) {
        this.dateHour = dateHour;
    }

    public void setSolicitation(String solicitation) {
        this.solicitation = solicitation;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public void setOperationalSystem(String operationalSystem) {
        OperationalSystem = operationalSystem;
    }
}
