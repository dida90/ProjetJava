/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartclimat2;

import java.util.List;

/**
 *
 * @author Lenovo
 */
public class Annee {
    private String annee;
    private List<Mois> mois_list;

    public Annee(String annee, List<Mois> mois_list) {
        this.annee = annee;
        this.mois_list = mois_list;
    }

    public Releve CalculMoyenneParMois()
    {
     Releve releveclimatique = new Releve("0", "0", "0", "0","0");
        float temperature_C = 0, temperature_k=0;
        int humidite = 0,nebolosite = 0;
        for(int i=0; i<getMois_list().size(); i++)
        {
            temperature_k +=  Float.parseFloat(getMois_list().get(i).CalculMoyenneMois().getTemperature());
           
            humidite += Integer.parseInt(getMois_list().get(i).CalculMoyenneMois().getHumidite());
            nebolosite += Integer.parseInt(getMois_list().get(i).CalculMoyenneMois().getNebulosite());
        }
        
        releveclimatique.setTemperature(String.valueOf(temperature_k/getMois_list().size()));
        releveclimatique.setTemperature(String.valueOf(temperature_C/getMois_list().size()));
        releveclimatique.setNebulosite(String.valueOf(nebolosite/getMois_list().size()));
        releveclimatique.setHumidite(String.valueOf(humidite/getMois_list().size()));
        
        return releveclimatique;
    }
    
    
    public String getAnnee() {
        return annee;
    }

    public List<Mois> getMois_list() {
        return mois_list;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public void setMois_list(List<Mois> mois_list) {
        this.mois_list = mois_list;
    }
    
    
}
