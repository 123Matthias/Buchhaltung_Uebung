
public class Data
{
    double aufwendung;
    double kosten;
    double plus;
    double minus;
    String kontonummer;
    String kostenArt;

    public Data()
    {
        aufwendung = 0.0;
        kosten = 0.0;
        plus = 0.0;
        minus = 0.0;
        kontonummer = "";
        kostenArt = "";
    }

    public double betragNeuBerechnen()
    {
        if(plus != 0){
            return aufwendung + plus;         
        }

        if(minus != 0){
            return aufwendung - minus;         
        }
        return 0.0;
    }

    public String getKontonummer(){
    return this.kontonummer;
    }
    
    public String getKonstenArt(){
        return this.kostenArt;
    }
    
    public double getAufwendung() {
        return this.aufwendung;
    }

    public double getKosten() {
        return this.kosten;
    }

    public double getPlus() {
        return this.plus;
    }

    public double getMinus() {
        return this.minus;
    }

    public void setBetragAlt(double betrag){
        this.aufwendung = betrag;
    }

    public Double[] getData() {
        Double[] d = new Double[3];
        d[0] = this.aufwendung;
        d[1] = this.minus;
        d[2] = this.plus;
        d[3] = this.kosten;
        return d;
    }
}
