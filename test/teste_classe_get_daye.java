/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import smartclimat2.get_date;

/**
 *
 * @author imed
 */
public class teste_classe_get_daye {
    
    public teste_classe_get_daye() {
    }
   static get_date gt;
    @BeforeClass
    public static void setUpClass() {
        gt=new get_date();
    }
    
    
    @Test
    public void testeget_dat_annee_encours(){
    get_date gt1=new get_date();
   
        assertTrue(gt1.get_annee_encour().equals(gt.get_annee_encour()));
    }
    
    @Test
    public void testdat_mois_en_cours(){
    get_date gt1=new get_date();
    assertTrue(gt1.get_mois_encour().equals(gt.get_mois_encour()));
    }
   
    
     
    @Test
    public void testdat_jours_en_cours(){
    get_date gt1=new get_date();
    assertTrue(gt1.get_jour_encour().equals(gt.get_jour_encour()));
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
