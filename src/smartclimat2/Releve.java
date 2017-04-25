/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartclimat2;

/**
 *
 * @author mammerly
 */
public class Releve {
    String date;

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
    String code_ville;

    public void setCode_ville(String code_ville) {
        this.code_ville = code_ville;
    }

    public String getCode_ville() {
        return code_ville;
    }
    String temperature;
    String humidite;
    String nebulosite;

   

    public String getTemperature() {
        return temperature;
    }

    public String getHumidite() {
        return humidite;
    }

    public String getNebulosite() {
        return nebulosite;
    }

  

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setHumidite(String humidite) {
        this.humidite = humidite;
    }

    public void setNebulosite(String nebulosite) {
        this.nebulosite = nebulosite;
    }

    public Releve(String code_ville, String date,String temperature, String humidite, String nebulosite) {
        this.date=date;
        this.code_ville=code_ville;
        this.temperature = temperature;
        this.humidite = humidite;
        this.nebulosite = nebulosite;
    }

    public Releve getCode_ville(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
