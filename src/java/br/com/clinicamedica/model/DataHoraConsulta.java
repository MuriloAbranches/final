package br.com.clinicamedica.model;

public class DataHoraConsulta {

    private int id;
    private String dataConsulta;
    private String horaConsulta;
    private int flagAtivo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getHoraConsulta() {
        return horaConsulta;
    }

    public void setHoraConsulta(String horaConsulta) {
        this.horaConsulta = horaConsulta;
    }

    public int getFlagAtivo() {
        return flagAtivo;
    }

    public void setFlagAtivo(int flagAtivo) {
        this.flagAtivo = flagAtivo;
    }

    public DataHoraConsulta() {
    }

    public DataHoraConsulta(int id, String dataConsulta, String horaConsulta, int flagAtivo) {
        this.id = id;
        this.dataConsulta = dataConsulta;
        this.horaConsulta = horaConsulta;
        this.flagAtivo = flagAtivo;
    }

}
