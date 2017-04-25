/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartclimat2;

/**
 *
 * @author Lenovo
 */
class Heure {
    
    private String heure;
    private Releve releveclimatique;

    public Heure(String heure, Releve releveclimatique) {
        this.heure = heure;
        this.releveclimatique = releveclimatique;
    }

    public String getHeure() {
        return heure;
    }

    public Releve getReleveclimatique() {
        return releveclimatique;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public void setReleveclimatique(Releve releveclimatique) {
        this.releveclimatique = releveclimatique;
    }
    
    
    
}
