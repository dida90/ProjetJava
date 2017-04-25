/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartclimat2;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//import smarte_climate.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui représente le jour d'une donnée climatique
 * @author LPR
 */
public class Jour {

    private String jour_value;
    private List<Heure> list_heure;

    /**
     * Constructeur du jour
     * @param jour_value la valeur du jour
     * @param heure l'heure du jour 
     */
    public Jour(String jour_value) {
      //  this.list_heure = new ArrayList<>();
        //this.list_heure.addAll(heure);
        this.jour_value = jour_value;
    }

    /**
     * getter la valeur du jour
     * @return retourne la valeur du jour
     */
    public String getJour_value() {
        return jour_value;
    }

    /**
     * setter valeur du jour
     * @param jour_valuen valeur du jour
     */
    public void setJour_value(String jour_value) {
        this.jour_value = jour_value;
    }

    /**
     * getter la liste des heures associé à ce jour 
     * @return la liste des heures associé à ce jour 
     */
    
    public List<Heure> getList_heure() {
        return list_heure;
    }

    /**
     * setter liste heure
     * @param list_heure la liste des heures associé à ce jour
     */
    
    public void setList_heure(List<Heure> list_heure) {
        this.list_heure.addAll(list_heure);
    }
    
     /**
     * setter liste heure
     * @param list_heure la liste des heures associé à ce jour
     */
   public void addHeure(Heure heure) {
        this.list_heure.add(heure);
    }

    /**
     * Methode qui retourne la moyenne du jour en faisant la moyenne des heures de la journée
     * @return l'objet Releve qui contient la moyenne de chaque type de données climatique
     */
    public Releve CalculMoyenneJour()
    {
        Releve releveclimatique = new Releve("0", "0", "0", "0","0");
        float temperature_C = 0, temperature_k=0;
        int humidite = 0,nebolosite = 0;
        for(int i=0; i<getList_heure().size(); i++)
        {
            temperature_k +=  Float.parseFloat(getList_heure().get(i).getReleveclimatique().getTemperature());
           
            humidite += Integer.parseInt(getList_heure().get(i).getReleveclimatique().getHumidite());
            nebolosite += Integer.parseInt(getList_heure().get(i).getReleveclimatique().getNebulosite());
        }
        
        releveclimatique.setTemperature(String.valueOf(temperature_k/getList_heure().size()));
        ;
        releveclimatique.setNebulosite(String.valueOf(nebolosite/getList_heure().size()));
        releveclimatique.setHumidite(String.valueOf(humidite/getList_heure().size()));
        
        return releveclimatique;
    }
    

}