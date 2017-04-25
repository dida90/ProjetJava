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
import smartclimat2.Releve;

/**
 *
 * @author imed
 */
public class teste_calsse_releve {
    
    public teste_calsse_releve() {
    }
    static Releve r;
    @BeforeClass
    public static void setUpClass() {
       r=new Releve("29200", "311020161800000", "56", "mq", "80");
    }
    
    @Test
    public void existecodeville(){
    Releve rAttendu=new Releve("29200", "311020161800000", "56", "mq", "80");
    String rObtenu=r.getCode_ville();
   String s= rAttendu.getCode_ville();
        assertTrue((s.equals(rObtenu)));
    }
    
     @Test
    public void existtemperature(){
    Releve rAttendu=new Releve("29200", "311020161800000", "56", "mq", "80");
    String rObtenu=r.getTemperature();
   String s= rAttendu.getTemperature();
        assertTrue((s.equals(rObtenu)));
    }
    
     @Test
    public void existhumedite(){
    Releve rAttendu=new Releve("29200", "311020161800000", "56", "mq", "80");
    String rObtenu=r.getHumidite();
   String s= rAttendu.getHumidite();
        assertTrue((s.equals(rObtenu)));
    }
 @Test
       public void exisnubeliosite(){
    Releve rAttendu=new Releve("29200", "311020161800000", "56", "mq", "80");
    String rObtenu=r.getNebulosite();
   String s= rAttendu.getNebulosite();
        assertTrue((s.equals(rObtenu)));
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
