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
public class Mois {
    
    private String mois;
    private List<Jour> list_jour;

    public Mois(String mois, List<Jour> list_jour) {
        this.mois = mois;
        this.list_jour = list_jour;
    }

    
    public String getMois() {
        return mois;
    }

    public List<Jour> getList_jour() {
        return list_jour;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public void setList_jour(List<Jour> list_jour) {
        this.list_jour = list_jour;
    }
    
    public Releve CalculMoyenneMois()
    {
     Releve releveclimatique = new Releve("0", "0", "0", "0","0");
        float temperature_C = 0, temperature_k=0;
        int humidite = 0,nebolosite = 0;
        for(int i=0; i<getList_jour().size(); i++)
        {
            temperature_k =  Float.parseFloat(getList_jour().get(i).CalculMoyenneJour().getTemperature());
         
            humidite += Integer.parseInt(getList_jour().get(i).CalculMoyenneJour().getHumidite());
            nebolosite += Integer.parseInt(getList_jour().get(i).CalculMoyenneJour().getNebulosite());
        }
        
        releveclimatique.setTemperature(String.valueOf(temperature_k/getList_jour().size()));
        releveclimatique.setTemperature(String.valueOf(temperature_C/getList_jour().size()));
        releveclimatique.setNebulosite(String.valueOf(nebolosite/getList_jour().size()));
        releveclimatique.setHumidite(String.valueOf(humidite/getList_jour().size()));
        
        return releveclimatique;
    }
}
