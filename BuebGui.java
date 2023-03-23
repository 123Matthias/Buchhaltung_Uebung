import javax.swing.*;
import java.util.ArrayList;
import javafx.scene.control.TableColumn;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Point;
import javafx.beans.property.Property;
import javax.swing.table.*;
import java.awt.BorderLayout;
import javax.swing.event.TableModelListener;

public class BuebGui
{
    private JFrame fenster;
    private JTable tabelle;
    JButton zeileErzeugenButton;
    JButton summeBerechnenButton;
    JButton ktoHolenButton;
    ArrayList <String[][]> liste;
    Data d;
    DefaultTableModel meinModel;
    JPanel panel = new JPanel();
    double summe;
    private KontenplanReader kr = new KontenplanReader();
    ArrayList <String> kontenListe = new ArrayList<>();

    public BuebGui()
    {
        erzeugeFenster();
        erzeugeTabelle();
        erstelleLayout();
        erzeugeButton();
        this.kontenListe = kr.getKontenplanAsList();

    }

    public void erzeugeFenster()
    {
        fenster = new JFrame("BÜB (KostenArtenRechnung)");
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster.setLayout(new BorderLayout());

    }

    public void erzeugeTabelle(){
        this.meinModel =  new DefaultTableModel();
        this.meinModel.addColumn(" Konto_Nummer ");
        this.meinModel.addColumn("Kostenart");
        this.meinModel.addColumn("Aufwendung");
        this.meinModel.addColumn("-");
        this.meinModel.addColumn("+");
        this.meinModel.addColumn("Kosten");
        
        
        d = new Data();
        //  String[] col = {" Konto_Nummer "," Aufwendung "," - "," + "," Kosten "};

        /*    String[][] zeile = {{d.getKontonummer(), 
        d.getKonstenArt(),
        String.valueOf(d.getAufwendung()),
        String.valueOf(d.getMinus()),
        String.valueOf(d.getPlus()),
        String.valueOf(d.getKosten())}};

        liste = new ArrayList<>();

        liste.add(zeile); */
        this.meinModel.addRow(new String[]{null,null,null,null,null,null,});
        erzeugeSummeZeile();                              
        tabelle = new JTable(meinModel);
        
        JScrollPane scrollPane = new JScrollPane(tabelle);
        fenster.add(scrollPane,BorderLayout.CENTER);
    }

    public void erzeugeZeile(){
        this.meinModel.addRow(new String[4]);
        this.meinModel.moveRow(meinModel.getRowCount()-2, meinModel.getRowCount()-2, meinModel.getRowCount()-1);
    }

    public void erzeugeSummeZeile(){
        this.meinModel.addRow(new String[4]);
    }

    public void erzeugeButton(){
        this.zeileErzeugenButton = new JButton("neue Zeile");
        this.zeileErzeugenButton.setSize(200, 100);
        this.panel.add(this.zeileErzeugenButton);
        this.zeileErzeugenButton.addActionListener(e->{erzeugeZeile();});

        this.summeBerechnenButton = new JButton("Summe Berechnen");
        this.summeBerechnenButton.setSize(200, 100);
        this.panel.add(this.summeBerechnenButton);
        this.summeBerechnenButton.addActionListener(e->{setKosten();});

        this.ktoHolenButton = new JButton("Auto_füllen");
        this.summeBerechnenButton.setSize(200, 100);
        this.panel.add(this.ktoHolenButton);
        this.ktoHolenButton.addActionListener(e->{getKontoBezeichnung();});

    }

    public void erstelleLayout(){
        panel.setSize(200, 200);
        fenster.setSize(800, 600);
        fenster.add(panel, BorderLayout.SOUTH);
        fenster.setVisible(true);
    }

    public void getKontoBezeichnung(){
        String[] check = new String[1];
        int row = 0;
        int col = 0;
        for(int z = 0; z<meinModel.getRowCount()-1; z++){

            for (int i =0; i<kontenListe.size(); i++){
                if(kontenListe.get(i).contains(meinModel.getValueAt(z, col).toString())){
                    check = kontenListe.get(i).split(" ");
                    meinModel.setValueAt(check[1], z, 1);

                }

            }
        }

    }

    public void setKosten(){
        Double kosten;
        Double a;
        Double b;
        Double c;

        for(int i = 0; i<meinModel.getRowCount()-1; i++){
            a=0.0;
            b=0.0;
            c=0.0;
            kosten=0.0;
        
            try{a = Double.parseDouble(meinModel.getValueAt(i, 2).toString());}catch(Exception e){}; 
            try{b = Double.parseDouble(meinModel.getValueAt(i, 3).toString());}catch(Exception e){};
            try{c = Double.parseDouble(meinModel.getValueAt(i, 4).toString());}catch(Exception e){};  

            if(a>0.0 && b>0.0){
                kosten = a-b;
            } 
            else if(a>0.0 && c>0.0){
                kosten = a+c;
            } 
            else{
                kosten = a;
            } 
            meinModel.setValueAt(kosten, i, 5);
        }
        setSumme();
    }

    public void setSumme(){
        this.summe = 0.0;
        double s = 0.0;

        for(int i=0; i<this.meinModel.getRowCount()-1; i++){
            s = Double.parseDouble(meinModel.getValueAt(i, 5).toString());
            this.summe += s;
        }
        meinModel.setValueAt(this.summe, meinModel.getRowCount()-1, 5);

    }
}
