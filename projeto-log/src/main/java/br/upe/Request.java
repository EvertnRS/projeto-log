package br.upe;

public class Request {
    private String IP_Adress;
    private int year;
    private String month;
    private String typeRequest;
    private String solicitation;
    private int status;
    private int length;
    private String OperationalSystem;

    public String getIP_Adress() {
        return IP_Adress;
    }

    public String getTypeRequest() {
        return typeRequest;
    }

    public String getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String getSolicitation() {
        return solicitation;
    }

    public int getStatus() {
        return status;
    }

    public int getLength() {
        return length;
    }

    public String getOperationalSystem() {
        return OperationalSystem;
    }

    public void setIP_Adress(String ip) {
        this.IP_Adress = ip;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setYear(String year) {
        this.year = Integer.parseInt(year);
    }

    public void setSolicitation(String solicitation) {
        this.solicitation = solicitation;
    }

    public void setTypeRequest(String typeRequest) {
        this.typeRequest = typeRequest;
    }

    public void setStatus(String status) {
        this.status = Integer.parseInt(status);
    }

    public void setLength(String length) {
        this.length = Integer.parseInt(length);
    }

    public void setOperationalSystem(String operationalSystem) {
        OperationalSystem = operationalSystem;
    }
}
