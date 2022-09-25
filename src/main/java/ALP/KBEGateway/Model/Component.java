package ALP.KBEGateway.Model;

import java.io.Serializable;

/**
 * Representation of a component of a guitar
 */
public class Component implements Serializable {

    private String komponententyp;
    private String name;
    private String material;
    private String herkunft;
    private String farbe;
    private String hersteller;
    private String form;
    private String gebrauchszustand;
    private String preis;
    private String verfuegbarkeit;
    private String klangauswirkung;

    public Component() {
        this.komponententyp = "";
        this.name = "";
        this.material = "";
        this.herkunft = "";
        this.farbe = "";
        this.hersteller = "";
        this.form = "";
        this.gebrauchszustand = "";
        this.preis = "";
        this.verfuegbarkeit = "";
        this.klangauswirkung = "";
    }

    public Component(String komponententyp,
            String name,
            String material,
            String herkunft,
            String farbe,
            String hersteller,
            String form,
            String gebrauchszustand,
            String preis,
            String verfuegbarkeit,
            String klangauswirkung) {
        this.komponententyp = komponententyp;
        this.name = name;
        this.material = material;
        this.herkunft = herkunft;
        this.farbe = farbe;
        this.hersteller = hersteller;
        this.form = form;
        this.gebrauchszustand = gebrauchszustand;
        this.preis = preis;
        this.verfuegbarkeit = verfuegbarkeit;
        this.klangauswirkung = klangauswirkung;
    }

    public String getMaterial() {
        return this.material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHerkunft() {
        return this.herkunft;
    }

    public void setHerkunft(String herkunft) {
        this.herkunft = herkunft;
    }

    public String getFarbe() {
        return this.farbe;
    }

    public void setFarbe(String farbe) {
        this.farbe = farbe;
    }

    public String getHersteller() {
        return this.hersteller;
    }

    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    public String getForm() {
        return this.form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getGebrauchszustand() {
        return this.gebrauchszustand;
    }

    public void setGebrauchszustand(String gebrauchszustand) {
        this.gebrauchszustand = gebrauchszustand;
    }

    public String getPreis() {
        return this.preis;
    }

    public void setPreis(String preis) {
        this.preis = preis;
    }

    public String getVerfuegbarkeit() {
        return this.verfuegbarkeit;
    }

    public void setVerfuegbarkeit(String verfuegbarkeit) {
        this.verfuegbarkeit = verfuegbarkeit;
    }

    public String getKlangauswirkung() {
        return this.klangauswirkung;
    }

    public void setKlangauswirkung(String klangauswirkung) {
        this.klangauswirkung = klangauswirkung;
    }

    public String getKomponententyp() {
        return this.komponententyp;
    }

    public void setKomponententyp(String komponententyp) {
        this.komponententyp = komponententyp;
    }

    public void set(String key, String value) {
        switch (key) {
            case ("komponententyp"):
                setKomponententyp(value);
                break;
            case ("name"):
                setName(value);
                break;
            case ("material"):
                setMaterial(value);
                break;
            case ("herkunft"):
                setHerkunft(value);
                break;
            case ("farbe"):
                setFarbe(value);
                break;
            case ("hersteller"):
                setHersteller(value);
                break;
            case ("form"):
                setForm(value);
                break;
            case ("gebrauchszustand"):
                setGebrauchszustand(value);
                break;
            case ("preis"):
                setPreis(value);
                break;
            case ("verfuegbarkeit"):
                setVerfuegbarkeit(value);
                break;
            case ("klangauswirkung"):
                setKlangauswirkung(value);
                break;
        }
    }

    @Override
    public String toString() {
        return getKomponententyp() + ", " +
                getName() + ", " +
                getMaterial() + ", " +
                getHerkunft() + ", " +
                getFarbe() + ", " +
                getHersteller() + ", " +
                getForm() + ", " +
                getGebrauchszustand() + ", " +
                getPreis() + ", " +
                getVerfuegbarkeit() + ", " +
                getKlangauswirkung();
    }
}