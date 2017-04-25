/*

 */
package smartclimat2;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import static java.lang.Math.abs;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import static org.apache.commons.io.IOUtils.skip;
import static org.apache.commons.io.IOUtils.skip;

/**
 *
 * @author mammerly
 */
public class FXMLDocumentController implements Initializable {

    Hashtable ht = new Hashtable();
    
    @FXML
    private ImageView image,image2,ok;

    @FXML
    private Label label,ecartypetext;
    @FXML
    private Button parcourir, afficher_courbe, comparaison, telechargement,ecartype;

    @FXML
    private CheckBox temp_k, temp_c, humidite, nebulosite;
@FXML 
private Tab tab1;

  
    
    
    @FXML
    private TableView table_donne;

    @FXML
    private ComboBox T_annee, T_mois, T_jour, T_heur, c_annee1, c_annee2, c_mois1, c_mois2, c_jour1, c_jour2;

    @FXML
    private ComboBox choix_ville, annee, mois, jours, annee_v1, mois_v1, jour_v1, ville1, annee_v2, mois_v2, jour_v2, ville2, c_comparaison;

    @FXML
    private ListView liste;

    @FXML
    private LineChart courbe,courbe2;

    @FXML
    private NumberAxis xAxis,xAxis2;
    @FXML
    private NumberAxis yAxis,yaxis2;
    @FXML
    private RadioButton temp__c, temp__k, nubeliositee, humeditee;

    /**
     *
     */
    public List<Releve> climat = new ArrayList<>();

    /**
     *
     */
    public List<Stations> station = new ArrayList<>();

    @FXML
    private void comparaison() throws FileNotFoundException, IOException {
        
        int v1, v2;
        v1 = ville1.getSelectionModel().getSelectedIndex();v2 = ville2.getSelectionModel().getSelectedIndex();
int av1,av2,mv1,mv2,jv1,jv2;
av1=annee_v1.getSelectionModel().getSelectedIndex(); av2=annee_v2.getSelectionModel().getSelectedIndex();
xAxis2.setLabel("Mois");
if (mois_v1.isPressed()) {xAxis2.setLabel("jour");mv1=mois_v1.getSelectionModel().getSelectedIndex(); mv2=mois_v2.getSelectionModel().getSelectedIndex();}
if(jour_v1.isPressed()) {xAxis2.setLabel("heure");jv1=jour_v1.getSelectionModel().getSelectedIndex(); jv2=jour_v2.getSelectionModel().getSelectedIndex();}
//outil_telechargement o=new outil_telechargement();
//o.telechargement_gz(annee_v1.getSelectionModel().toString()+ mois_v1.getSelectionModel().toString());
//o.telechargement_gz(annee_v2.getSelectionModel().toString()+ mois_v2.getSelectionModel().toString());
        XYChart.Series series = new XYChart.Series();
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series seriescomparaison = new XYChart.Series();
       
        
         // a voir apres pour modification de couleur
        double heure = 0;
        double somme=0;
        int i = 0;
        if(nubeliositee.isSelected()){
            series.setName("comparaison de nubeliositè");
            courbe.setTitle("nubeliositè");
             yaxis2.setLabel("nubeliosite");
        InputStream ips = new FileInputStream("C:/Users/imed/Desktop/telechagement_Csv/nibuliosite.txt");
        InputStreamReader ipsr = new InputStreamReader(ips);
        BufferedReader br = new BufferedReader(ipsr);
        String ligne;
        heure = 0;
        int k = 0;
        int nb;
        while ((ligne = br.readLine()) != null) {
            for (String retval : ligne.split("/")) {
                 somme=somme+Double.parseDouble(retval);
                i=i+1;
                ligne = ligne + v1;
                series.getData().add(new XYChart.Data(heure, Double.parseDouble(retval)));
                ligne=ligne+v2;
                nb = (int) ((double) (Math.random() * ((Double.parseDouble(retval) + 10))) + (Double.parseDouble(retval) - 10));
                series1.getData().add(new XYChart.Data(heure, nb ));
                seriescomparaison.setName("comparaison");
               seriescomparaison.getData().add(new XYChart.Data(heure,((Double.parseDouble(retval) )+(nb ))/2));
                if (heure < 24) {
                    heure = heure + 0.5;
                }

            }}
            br.close();
            courbe.setVisible(true);
            courbe.getData().add(series);
            courbe.getData().add(series1);
            courbe.getData().add(seriescomparaison);
}
if(temp__c.isSelected()){
    
    series.setName("comparaison entre temperature en Celcus");
    courbe.setTitle("temperature en Celcus");
     yaxis2.setLabel("Temerature en celcus");
        InputStream ips = new FileInputStream("C:/Users/imed/Desktop/telechagement_Csv/temperature.txt");
        InputStreamReader ipsr = new InputStreamReader(ips);
        BufferedReader br = new BufferedReader(ipsr);
        String ligne;
        heure = 0;
        int k = 0;
        int nb;
        while ((ligne = br.readLine()) != null) {
            for (String retval : ligne.split("/")) {
                 somme=somme+Double.parseDouble(retval);
                 somme=somme-257;
                i=i+1;
                ligne = ligne + v1;
                series.getData().add(new XYChart.Data(heure, Double.parseDouble(retval) - 257));
                ligne=ligne+v2;
                nb = (int) ((double) (Math.random() * ((Double.parseDouble(retval) + 10) - 257)) + (Double.parseDouble(retval) - 10));
                series1.getData().add(new XYChart.Data(heure, nb - 257));
                
               seriescomparaison.getData().add(new XYChart.Data(heure,((Double.parseDouble(retval) - 257)+(nb - 257))/2));
                if (heure < 24) {
                    heure = heure + 0.5;
                }

            }}
        
            br.close();
            courbe.setVisible(true);
            courbe.getData().add(series);
            courbe.getData().add(series1);
            courbe.getData().add(seriescomparaison);
}
if(humeditee.isSelected()){
    series.setName("comparaison d'humedite");
    courbe.setTitle("humedite");
     yaxis2.setLabel("humedite");
        InputStream ips = new FileInputStream("C:/Users/imed/Desktop/telechagement_Csv/humedite.txt");
        InputStreamReader ipsr = new InputStreamReader(ips);
        BufferedReader br = new BufferedReader(ipsr);
        String ligne;
        heure = 0;
        int k,ii = 0;
        int nb;
        
        while ((ligne = br.readLine()) != null) {
             
            for (String retval : ligne.split("/")) {
                if (retval.equalsIgnoreCase("mq")) {
                        ii = 0;
                    }
                else{
                     somme=somme+Double.parseDouble(retval);
                i=i+1;
                ligne = ligne + v1;
                series.getData().add(new XYChart.Data(heure, Double.parseDouble(retval) ));
                ligne=ligne+v2;
                nb = (int) ((double) (Math.random() * ((Double.parseDouble(retval) + 10) )) + (Double.parseDouble(retval) - 10));
                series1.getData().add(new XYChart.Data(heure, nb ));
                
               seriescomparaison.getData().add(new XYChart.Data(heure,((Double.parseDouble(retval) )+(nb ))/2));
                if (heure < 24) {
                    heure = heure + 0.5;
                }

            }}
            br.close();
            courbe.setVisible(true);
            courbe.getData().add(series);
            courbe.getData().add(series1);
            courbe.getData().add(seriescomparaison);
}        
}
if(temp__k.isSelected())
{series.setName("comparaison entre temperature en Kelvin");
    courbe.setTitle("temperature en kelvin");
     yaxis2.setLabel("temperature en kelvin");
        InputStream ips = new FileInputStream("C:/Users/imed/Desktop/telechagement_Csv/temperature.txt");
        InputStreamReader ipsr = new InputStreamReader(ips);
        BufferedReader br = new BufferedReader(ipsr);
        String ligne;
        heure = 0;
        int k = 0;
        int nb;
        while ((ligne = br.readLine()) != null) {
            for (String retval : ligne.split("/")) {
                 somme=somme+Double.parseDouble(retval);
                i=i+1;
                ligne = ligne + v1;
                series.getData().add(new XYChart.Data(heure, Double.parseDouble(retval) ));
                ligne=ligne+v2;
                nb = (int) ((double) (Math.random() * ((Double.parseDouble(retval) + 10) )) + (Double.parseDouble(retval) - 10));
                series1.getData().add(new XYChart.Data(heure, nb));
                
               seriescomparaison.getData().add(new XYChart.Data(heure,((Double.parseDouble(retval) )+(nb))/2));
                if (heure < 24) {
                    heure = heure + 0.5;
                }

            }}
            br.close();
            courbe.setVisible(true);
            courbe.getData().add(series);
            courbe.getData().add(series1);
            courbe.getData().add(seriescomparaison);
}
 ecartypetext.setText("La differance = "+(somme/i));
    }

    @FXML
    private void affichercourbe(ActionEvent event) throws IOException {
      
        afficher_courbe.setDisable(true);
        String cville, jr, ms, ann = null;
        cville = choix_ville.getSelectionModel().toString();
        jr = jours.getSelectionModel().toString();
        ms = mois.getSelectionModel().toString();
        ann = annee.getSelectionModel().toString();
        afficher_courbe.setDisable(false);

        if (temp_c.isSelected()) {
            XYChart.Series series = new XYChart.Series();
            xAxis.setLabel("Heure");
            yAxis.setLabel("Temerature");
            courbe2.setTitle("Evolution de la temeparture de " + cville + "en " + jr + "/" + ms + "/" + ann);
            series.setName("Evolution de la temperature En Celcus"); // a voir apres pour modification de couleur
            double heure = 0;

            InputStream ips = new FileInputStream("C:/Users/imed/Desktop/telechagement_Csv/temperature.txt");
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            heure = 0;
            while ((ligne = br.readLine()) != null) {

                for (String retval : ligne.split("/")) {

                    series.getData().add(new XYChart.Data(heure, Double.parseDouble(retval) - 257));

                    if (heure < 24) {
                        heure = heure + 0.5;
                    }

                }

                br.skip(58);
            }
            br.close();

            courbe2.setVisible(true);
            courbe2.getData().add(series);
        }

        if (nebulosite.isSelected()) {

            XYChart.Series series = new XYChart.Series();
            xAxis.setLabel("Heure");
            yAxis.setLabel("Nubuliositè");
            courbe2.setTitle("Evolution ");
            series.setName("Evolution de nubuliositè "); // a voir apres pour modification de couleur
            double heure = 0;

            InputStream ips = new FileInputStream("C:/Users/imed/Desktop/telechagement_Csv/nibuliosite.txt");
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            heure = 0;
            while ((ligne = br.readLine()) != null) {

                for (String retval : ligne.split("/")) {

                    series.getData().add(new XYChart.Data(heure, Double.parseDouble(retval)));

                    if (heure < 24) {
                        heure = heure + 0.5;
                    }

                }

                br.skip(58);
            }
            br.close();

            courbe2.setVisible(true);
            courbe2.getData().add(series);
        }

        if (humidite.isSelected()) {

            XYChart.Series series = new XYChart.Series();
            xAxis.setLabel("Heure");
            yAxis.setLabel("humedite");
            courbe2.setTitle("Evolution ");
            series.setName("Evolution de humedite ");// a voir apres pour modification de couleur
            double heure = 0;

            InputStream ips = new FileInputStream("C:/Users/imed/Desktop/telechagement_Csv/humedite.txt");
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            heure = 0;
            int ii;
            while ((ligne = br.readLine()) != null) {

                for (String retval : ligne.split("/")) {
                    if (retval.equalsIgnoreCase("mq")) {
                        ii = 0;
                    } else {

                        series.getData().add(new XYChart.Data(heure, Double.parseDouble(retval)));

                        if (heure < 24) {
                            heure = heure + 0.5;
                        }

                        br.skip(58);
                    }
                }
            }
            br.close();

            courbe2.setVisible(true);
            courbe2.getData().add(series);
        }

        if (temp_k.isSelected()) {
            XYChart.Series series = new XYChart.Series();
            xAxis.setLabel("Heure");
            yAxis.setLabel("Temerature En Kalvin");
            courbe2.setTitle("Evolution ");
            series.setName("Evolution de temeparture en kalven de "); // a voir apres pour modification de couleur
            double heure = 0;

            InputStream ips = new FileInputStream("C:/Users/imed/Desktop/telechagement_Csv/temperature.txt");
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            heure = 0;
            while ((ligne = br.readLine()) != null) {

                for (String retval : ligne.split("/")) {

                    series.getData().add(new XYChart.Data(heure, Double.parseDouble(retval)));

                    if (heure < 24) {
                        heure = heure + 0.5;
                    }
                }

                br.skip(58);
            }
            br.close();

            courbe2.setVisible(true);
            courbe2.getData().add(series);

        }

    }

    @FXML
    private void reglage_Annee() {
        get_date d = new get_date();
        String Annee = d.get_annee_encour();
        for (int i = Integer.parseInt(Annee); i > 1995; i--) {
            ObservableList<String> t = FXCollections.observableArrayList(Integer.toString(i));
            T_annee.getItems().addAll(t);
            //      c_annee1.getItems().addAll(t);
            //    c_annee2.getItems().addAll(t);
        }

    }

    /**
     *
     * @param An
     */
    public void reglage_mois(String An) {
        get_date d = new get_date();
        String Mois = d.get_mois_encour();
        String Jour = d.get_jour_encour();
        if (d.get_annee_encour().equals(An)) {
            for (int i = 1; i < Integer.parseInt(Mois) + 1; i++) {
                ObservableList<String> t = FXCollections.observableArrayList(Integer.toString(i));
                T_mois.getItems().addAll(t);

            }

        } else {
            for (int i = 1; i < 13; i++) {
                ObservableList<String> t = FXCollections.observableArrayList(Integer.toString(i));
                T_mois.getItems().addAll(t);
//                  c_mois1.getItems().addAll(t);

                //       c_mois2.getItems().addAll(t);
            }
        }

    }

    /**
     *
     * @param mo
     */
    public void reglage_jour_heure(String mo) {

        get_date d = new get_date();
        String Jour = d.get_jour_encour();
        if (d.get_mois_encour().equals(mo)) {
            for (int i = 1; i < Integer.parseInt(Jour) + 1; i++) {
                ObservableList<String> t = FXCollections.observableArrayList(Integer.toString(i));
                T_jour.getItems().addAll(t);
            }

        } else {
            if (Integer.parseInt(mo) == 1 || Integer.parseInt(mo) == 3
                    || Integer.parseInt(mo) == 5 || Integer.parseInt(mo) == 10 || Integer.parseInt(mo) == 12
                    || Integer.parseInt(mo) == 7 || Integer.parseInt(mo) == 8) {
                for (int i = 1; i < 32; i++) {
                    ObservableList<String> t = FXCollections.observableArrayList(Integer.toString(i));
                    T_jour.getItems().addAll(t);
                }
            } else {
                for (int i = 1; i < 31; i++) {
                    ObservableList<String> t = FXCollections.observableArrayList(Integer.toString(i));
                    T_jour.getItems().addAll(t);
                }
            }
        }
        for (int j = 0; j < 22; j = j + 3) {
            ObservableList<String> t = FXCollections.observableArrayList(Integer.toString(j));
            T_heur.getItems().addAll(t);
        }

    }

    @FXML
    private void ecart_type() throws FileNotFoundException, IOException {
        double somme=0;
        
InputStream ips = new FileInputStream("C:/Users/imed/Desktop/telechagement_Csv/temperature.txt");
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            int i=0;
            while ((ligne = br.readLine()) != null) {

                for (String retval : ligne.split("/")) {
                    somme=somme+Double.parseDouble(retval);
                i=i+1;
                }

                br.skip(58);
            }
            br.close();

     ecartypetext.setText("ecart type = "+(somme/i));
    }

    @FXML
    private void importer_fichier(ActionEvent event) {
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Ouvrir un fichier");
        File file = filechooser.showOpenDialog(null);
        if (file != null) {
            String[] extensions = {"txt", "csv"};
            for (String extension : extensions) {
                if (file.getName().toLowerCase().endsWith("." + extension)) {
                    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        final String fileName = file.toURI().toString();
                        String fileNamelast = null;

                        for (String retval : fileName.split("/")) {
                            fileNamelast = retval;
                        }
                        liste.getItems().add(fileNamelast);

                        ObservableList<Releve> data = FXCollections.observableArrayList();
                        List<String> row = new ArrayList<>();
                        String line;
//                         donnes = new ArrayList<>();
                        boolean line_one = false;
                        while ((line = br.readLine()) != null) {
                            if (line_one) {
                                row.clear();
                                //   donnes.add(line);
                                int i = 0;
                                for (String retval : line.split(";")) {

                                    if (i == 0 || i == 1 || i == 7 || i == 9 || i == 14) {
                                        row.add(retval);
                                    }

                                    i++;
                                }

                                //              i_climat.add(new info_climat())
                                //Conversion Kelvin to celsius
                                //  Float kelvin = Float.parseFloat(row.get(0));
                                // Float celsius = kelvin - 273.15F;
                                data.add(new Releve(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4)));
                            }
                            line_one = true;
                        }
                        TableColumn c = new TableColumn("code ville");
                        c.setMinWidth(200);
                        c.setCellValueFactory(new PropertyValueFactory<Releve, String>("code_ville"));

                        TableColumn cdate = new TableColumn("date");
                        cdate.setMinWidth(200);
                        cdate.setCellValueFactory(new PropertyValueFactory<Releve, String>("date"));

                        TableColumn c1 = new TableColumn("Température ");
                        c1.setMinWidth(200);
                        c1.setCellValueFactory(new PropertyValueFactory<Releve, String>("temperature"));

                        TableColumn c2 = new TableColumn("Humidité %");
                        c2.setMinWidth(200);
                        c2.setCellValueFactory(new PropertyValueFactory<Releve, String>("humidite"));

                        TableColumn c3 = new TableColumn("Nebulosité %");
                        c3.setMinWidth(200);
                        c3.setCellValueFactory(new PropertyValueFactory<Releve, String>("nebulosite"));

                        table_donne.setItems(data);
                        table_donne.getColumns().addAll(c, cdate, c1, c2, c3);
                        File fs1 = new File("C:/Users/imed/Desktop/telechagement_Csv/temperature.txt");
                        File fs2 = new File("C:/Users/imed/Desktop/telechagement_Csv/nibuliosite.txt");
                        File fs3 = new File("C:/Users/imed/Desktop/telechagement_Csv/humedite.txt");// définir l'arborescence
                        if (fs1.exists() || fs2.exists() || fs3.exists()) {
                            fs1.delete();
                            fs2.delete();
                            fs3.delete();
                        }
                        File ff = new File("C:/Users/imed/Desktop/telechagement_Csv/temperature.txt"); // définir l'arborescence
                        File ff2 = new File("C:/Users/imed/Desktop/telechagement_Csv/nibuliosite.txt"); // définir l'arborescence
                        File ff3 = new File("C:/Users/imed/Desktop/telechagement_Csv/humedite.txt"); // définir l'arborescence

                        ff.createNewFile();
                        FileWriter ffw = new FileWriter(ff);
                        FileWriter ffw2 = new FileWriter(ff2);
                        FileWriter ffw3 = new FileWriter(ff3);

                        for (int i = 0; i < table_donne.getHeight(); i++) {

                            // System.out.println(table_donne.getHeight());
                            ffw.write(c1.getCellData(i).toString() + "/");  // écrire une ligne dans le fichier resultat.txt
                            ffw2.write(c2.getCellData(i).toString() + "/");
                            ffw3.write(c3.getCellData(i).toString() + "/");

                        }
                        ffw.close(); // fermer le fichier à la fin des traitements
                        ffw2.close();
                        ffw3.close();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);

                    }

                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //
       
       
       
//  Image image = new Image("meteo.gif");
  //this.image.setImage(image);

  //Image imageUbo=new Image ("téléchargement.png");
//  image2.setImage(imageUbo);

       temp__c.setOnAction((event)->{ temp__k.setDisable(true);humeditee.setDisable(true);nubeliositee.setDisable(true);});
        temp__k.setOnAction((event)->{temp__c.setDisable(true);humeditee.setDisable(true);nubeliositee.setDisable(true);});
         humeditee.setOnAction((event)->{temp__k.setDisable(true);temp__c.setDisable(true);nubeliositee.setDisable(true);});
          nubeliositee.setOnAction((event)->{temp__k.setDisable(true);humeditee.setDisable(true);temp__c.setDisable(true);});
       
        
        ////
        
        
        afficher_courbe.setDisable(true);
        annee.setDisable(true);
        choix_ville.setOnAction((event)->{
            annee.setDisable(false);
   
    });
        annee.setOnAction((event)->{
         afficher_courbe.setDisable(false);
        });
        
        
        
        
        comparaison.setDisable(true);
        ville2.setDisable(true);
        annee_v1.setDisable(true);
        annee_v2.setDisable(true);
        ville1.setOnAction((event)->{
        ville2.setDisable(false);
        annee_v1.setDisable(false);
        });
        
      annee_v1.setOnAction((event) -> {
          annee_v2.setDisable(false);
      comparaison.setDisable(false);
        });
        String[] villes = {"ABBEVILLE", "LILLE-LESQUIN", "PTE DE LA HAGUE", "CAEN-CARPIQUET", "ROUEN-BOOS", "REIMS-PRUNAY", "BREST-GUIPAVAS", "PLOUMANACH", "RENNES-ST JACQUES", "ALENCON", "ORLY", "TROYES-BARBEREY", "NANCY-OCHEY", "STRASBOURG-ENTZHEIM",
            "BELLE ILE-LE TALUT", "NANTES-BOUGUENAIS", "TOURS", "BOURGES", "DIJON-LONGVIC", "BALE-MULHOUSE", "PTE DE CHASSIRON",
            "POITIERS-BIARD", "LIMOGES-BELLEGARDE", "CLERMONT-FD", "LE PUY-LOUDES", "LYON-ST EXUPERY", "BORDEAUX-MERIGNAC",
            "GOURDON", "MILLAU", "MONTELIMAR", "EMBRUN", "MONT-DE-MARSAN", "TARBES-OSSUN", "ST GIRONS", "TOULOUSE-BLAGNAC",
            "MONTPELLIER", "MARIGNANE", "CAP CEPET", "NICE", "PERPIGNAN", "AJACCIO", "BASTIA", "GLORIEUSES", "JUAN DE NOVA",
            "EUROPA", "TROMELIN", "GILLOT-AEROPORT", "NOUVELLE AMSTERDAM", "CROZET", "KERGUELEN", "PAMANDZI", "ST-PIERRE",
            "LA DESIRADE METEO", "ST-BARTHELEMY METEO", "LE RAIZET AERO", "TRINITE-CARAVEL"};

        String[] idVilles = {"07005", "07015", "07020", "07027", "07037", "07072", "07110", "07117", "07130", "07139", "07149", "07168",
            "07181", "07190", "07207", "07222", "07240", "07255", "07280", "07299", "07314", "07335", "07434", "07460", "07471", "07481",
            "07510", "07535", "07558", "07577", "07591", "07607", "07621", "07627", "07630", "07643", "07650", "07661", "07690", "07747",
            "07761", "07790", "61968", "61976", "61980", "61996", "61998", "67005", "71805", "78897", "78925", "81401",
            "81405", "81408", "81415", "89642"};

        System.out.println(villes.length + " " + idVilles.length);
        for (int i = 0; i < idVilles.length; i++) {
            station.add(new Stations(idVilles[i], villes[i]));
        }

        choix_ville.getItems().addAll("ABBEVILLE", "LILLE-LESQUIN", "PTE DE LA HAGUE", "CAEN-CARPIQUET", "ROUEN-BOOS", "REIMS-PRUNAY", "BREST-GUIPAVAS", "PLOUMANACH", "RENNES-ST JACQUES", "ALENCON", "ORLY", "TROYES-BARBEREY", "NANCY-OCHEY", "STRASBOURG-ENTZHEIM",
                "BELLE ILE-LE TALUT", "NANTES-BOUGUENAIS", "TOURS", "BOURGES", "DIJON-LONGVIC", "BALE-MULHOUSE", "PTE DE CHASSIRON",
                "POITIERS-BIARD", "LIMOGES-BELLEGARDE", "CLERMONT-FD", "LE PUY-LOUDES", "LYON-ST EXUPERY", "BORDEAUX-MERIGNAC",
                "GOURDON", "MILLAU", "MONTELIMAR", "EMBRUN", "MONT-DE-MARSAN", "TARBES-OSSUN", "ST GIRONS", "TOULOUSE-BLAGNAC",
                "MONTPELLIER", "MARIGNANE", "CAP CEPET", "NICE", "PERPIGNAN", "AJACCIO", "BASTIA", "GLORIEUSES", "JUAN DE NOVA",
                "EUROPA", "TROMELIN", "GILLOT-AEROPORT", "NOUVELLE AMSTERDAM", "CROZET", "KERGUELEN", "PAMANDZI", "ST-PIERRE",
                "LA DESIRADE METEO", "ST-BARTHELEMY METEO", "LE RAIZET AERO", "TRINITE-CARAVEL");

        ville1.getItems().addAll("ABBEVILLE", "LILLE-LESQUIN", "PTE DE LA HAGUE", "CAEN-CARPIQUET", "ROUEN-BOOS", "REIMS-PRUNAY", "BREST-GUIPAVAS", "PLOUMANACH", "RENNES-ST JACQUES", "ALENCON", "ORLY", "TROYES-BARBEREY", "NANCY-OCHEY", "STRASBOURG-ENTZHEIM",
                "BELLE ILE-LE TALUT", "NANTES-BOUGUENAIS", "TOURS", "BOURGES", "DIJON-LONGVIC", "BALE-MULHOUSE", "PTE DE CHASSIRON",
                "POITIERS-BIARD", "LIMOGES-BELLEGARDE", "CLERMONT-FD", "LE PUY-LOUDES", "LYON-ST EXUPERY", "BORDEAUX-MERIGNAC",
                "GOURDON", "MILLAU", "MONTELIMAR", "EMBRUN", "MONT-DE-MARSAN", "TARBES-OSSUN", "ST GIRONS", "TOULOUSE-BLAGNAC",
                "MONTPELLIER", "MARIGNANE", "CAP CEPET", "NICE", "PERPIGNAN", "AJACCIO", "BASTIA", "GLORIEUSES", "JUAN DE NOVA",
                "EUROPA", "TROMELIN", "GILLOT-AEROPORT", "NOUVELLE AMSTERDAM", "CROZET", "KERGUELEN", "PAMANDZI", "ST-PIERRE",
                "LA DESIRADE METEO", "ST-BARTHELEMY METEO", "LE RAIZET AERO", "TRINITE-CARAVEL");

        ville2.getItems().addAll("ABBEVILLE", "LILLE-LESQUIN", "PTE DE LA HAGUE", "CAEN-CARPIQUET", "ROUEN-BOOS", "REIMS-PRUNAY", "BREST-GUIPAVAS", "PLOUMANACH", "RENNES-ST JACQUES", "ALENCON", "ORLY", "TROYES-BARBEREY", "NANCY-OCHEY", "STRASBOURG-ENTZHEIM",
                "BELLE ILE-LE TALUT", "NANTES-BOUGUENAIS", "TOURS", "BOURGES", "DIJON-LONGVIC", "BALE-MULHOUSE", "PTE DE CHASSIRON",
                "POITIERS-BIARD", "LIMOGES-BELLEGARDE", "CLERMONT-FD", "LE PUY-LOUDES", "LYON-ST EXUPERY", "BORDEAUX-MERIGNAC",
                "GOURDON", "MILLAU", "MONTELIMAR", "EMBRUN", "MONT-DE-MARSAN", "TARBES-OSSUN", "ST GIRONS", "TOULOUSE-BLAGNAC",
                "MONTPELLIER", "MARIGNANE", "CAP CEPET", "NICE", "PERPIGNAN", "AJACCIO", "BASTIA", "GLORIEUSES", "JUAN DE NOVA",
                "EUROPA", "TROMELIN", "GILLOT-AEROPORT", "NOUVELLE AMSTERDAM", "CROZET", "KERGUELEN", "PAMANDZI", "ST-PIERRE",
                "LA DESIRADE METEO", "ST-BARTHELEMY METEO", "LE RAIZET AERO", "TRINITE-CARAVEL");

//        c_comparaison.getItems().addAll("temperature_C", "temperature_k", "humiditè", "nebulosite");
        // pour le telechargement 
        reglage_Annee();
        T_mois.setDisable(true);
        T_jour.setDisable(true);
        T_heur.setDisable(true);
        telechargement.setDisable(true);

       
        telechargement.setOnAction(event -> {
            outil_telechargement telecharger = new outil_telechargement();
            get_date d = new get_date();
            String Annee = d.get_annee_encour();
            String moiss = T_mois.getSelectionModel().getSelectedItem().toString();
            if (Integer.parseInt(moiss) < 10) {
                moiss = "0" + moiss;
            }

            String jourr = T_jour.getSelectionModel().getSelectedItem().toString();
            if (Integer.parseInt(jourr) < 10) {
                jourr = "0" + jourr;
            }
            if (!T_annee.getSelectionModel().getSelectedItem().toString().equals(Annee)) {
                try {
                    telecharger.telechargement_gz(T_annee.getSelectionModel().getSelectedItem().toString()
                            + moiss);
                   
                } catch (IOException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    String heure = T_heur.getSelectionModel().getSelectedItem().toString();
                    if (Integer.parseInt(heure) < 10) {
                        heure = "0" + heure;
                    }
                    telecharger.telechargement_csv(
                            T_annee.getSelectionModel().getSelectedItem().toString()
                            + moiss
                            + jourr
                            + heure);
                } catch (IOException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        // pour le jour
        T_mois.setOnAction((event) -> {
            reglage_jour_heure(T_mois.getSelectionModel().getSelectedItem().toString());

            T_jour.setDisable(false);
            T_heur.setDisable(false);
            telechargement.setDisable(false);
        });

        // pour le mois 
        T_annee.setOnAction((event) -> {

            reglage_mois(T_annee.getSelectionModel().getSelectedItem().toString());
            T_mois.setDisable(false);
        });

        // a voir apres pour finalisation
        for (int i = 1; i < 32; i++) {
            if (i < 10) {
                jours.getItems().addAll('0' + String.valueOf(i));
                jour_v1.getItems().addAll('0' + String.valueOf(i));
                jour_v2.getItems().addAll('0' + String.valueOf(i));
            } else {
                jours.getItems().addAll(String.valueOf(i));
                jour_v1.getItems().addAll(String.valueOf(i));
                jour_v2.getItems().addAll(String.valueOf(i));
            }

        }

        for (int i = 1; i < 13; i++) {
            if (i < 10) {
                mois.getItems().addAll('0' + String.valueOf(i));
                mois_v1.getItems().addAll('0' + String.valueOf(i));
                mois_v2.getItems().addAll('0' + String.valueOf(i));
            } else {
                mois.getItems().addAll(String.valueOf(i));
                mois_v1.getItems().addAll(String.valueOf(i));
                mois_v2.getItems().addAll(String.valueOf(i));
            }

        }

        for (int i = 1996; i < 2018; i++) {
            annee.getItems().addAll(String.valueOf(i));
            annee_v1.getItems().addAll(String.valueOf(i));
            annee_v2.getItems().addAll(String.valueOf(i));
        }
    }

//    public float difference(ArrayList<Float> s, ArrayList<Float> n) {
//
//        return (abs(ecart_type(s) - ecart_type(n)));
//    }

    /**
     *
     * @param date
     * @return
     */

    public ArrayList<String> split_date(String date) {
        ArrayList<String> s = new ArrayList<>();

        String annee, mois, jour, heure;

        annee = date.substring(0, 4);
        mois = date.substring(4, 6);
        jour = date.substring(6, 8);
        heure = date.substring(8, 10);

        System.out.println("annee " + annee);
        System.out.println("\n mois " + mois);
        System.out.println("\n jour " + jour);
        System.out.println("\n heure " + heure);
        s.add(date);
        s.add(mois);
        s.add(jour);
        s.add(heure);

        return s;

        
       
    }

}
