package ALP.KBEGateway.Model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Representation of a product of a guitar
 */
public class Product implements Serializable {

    private String name;
    private Component hals;
    private Component steg;
    private Component korpus;
    private Component griffbrett;
    private Component tonabnehmer;
    private Component schallloch;
    private Component kopf;
    private Component wirbel;
    private Component sattel;
    private Component decke;
    private String additionalInfo;
    private HashMap<String, Component> components;

    public Product(String name,
            Component hals,
            Component steg,
            Component korpus,
            Component griffbrett,
            Component tonabnehmer,
            Component schallloch,
            Component kopf,
            Component wirbel,
            Component sattel,
            Component decke,
            String additionalInfo) {
        this.name = name;
        this.hals = hals;
        this.steg = steg;
        this.korpus = korpus;
        this.griffbrett = griffbrett;
        this.tonabnehmer = tonabnehmer;
        this.schallloch = schallloch;
        this.kopf = kopf;
        this.wirbel = wirbel;
        this.sattel = sattel;
        this.decke = decke;
        this.additionalInfo = additionalInfo;
        createMap();
    }

    public Product() {
        
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Component getHals() {
        return this.hals;
    }

    public void setHals(Component hals) {
        this.hals = hals;
    }

    public Component getSteg() {
        return this.steg;
    }

    public void setSteg(Component steg) {
        this.steg = steg;
    }

    public Component getKorpus() {
        return this.korpus;
    }

    public void setKorpus(Component korpus) {
        this.korpus = korpus;
    }

    public Component getGriffbrett() {
        return this.griffbrett;
    }

    public void setGriffbrett(Component griffbrett) {
        this.griffbrett = griffbrett;
    }

    public Component getTonabnehmer() {
        return this.tonabnehmer;
    }

    public void setTonabnehmer(Component tonabnehmer) {
        this.tonabnehmer = tonabnehmer;
    }

    public Component getSchallloch() {
        return this.schallloch;
    }

    public void setSchallloch(Component schallloch) {
        this.schallloch = schallloch;
    }

    public Component getKopf() {
        return this.kopf;
    }

    public void setKopf(Component kopf) {
        this.kopf = kopf;
    }

    public Component getWirbel() {
        return this.wirbel;
    }

    public void setWirbel(Component wirbel) {
        this.wirbel = wirbel;
    }

    public Component getSattel() {
        return this.sattel;
    }

    public void setSattel(Component sattel) {
        this.sattel = sattel;
    }

    public Component getDecke() {
        return this.decke;
    }

    public void setDecke(Component decke) {
        this.decke = decke;
    }

    public HashMap<String, Component> getComponents() {
        return this.components;
    }

    public String getAdditionalInfo(){
        return this.additionalInfo;
    }

    private void createMap() {
        if (components == null) {
            components = new HashMap<>();
        }
        components.put("hals", hals);
        components.put("steg", steg);
        components.put("korpus", korpus);
        components.put("griffbrett", griffbrett);
        components.put("tonabnehmer", tonabnehmer);
        components.put("schalloch", schallloch);
        components.put("kopf", kopf);
        components.put("wirbel", wirbel);
        components.put("sattel", sattel);
        components.put("decke", decke);
    }

    public String Stringify() {
        return this.getName() + "; " +
                this.getHals().Stringify() + "; " +
                this.getSteg().Stringify() + "; " +
                this.getKorpus().Stringify() + "; " +
                this.getGriffbrett().Stringify() + "; " +
                this.getTonabnehmer().Stringify() + "; " +
                this.getSchallloch().Stringify() + "; " +
                this.getKopf().Stringify() + "; " +
                this.getWirbel().Stringify() + "; " +
                this.getSattel().Stringify() + "; " +
                this.getDecke().Stringify();
    }

    public static Product Parse(String product) {
        Product prod = new Product();
        String[] parts = product.split(", ");
        if(parts.length == 11){
            prod.setName(parts[0]);
            prod.setHals(Component.Parse(parts[1]));
            prod.setSteg(Component.Parse(parts[2]));
            prod.setKorpus(Component.Parse(parts[3]));
            prod.setGriffbrett(Component.Parse(parts[4]));
            prod.setTonabnehmer(Component.Parse(parts[5]));
            prod.setSchallloch(Component.Parse(parts[6]));
            prod.setKopf(Component.Parse(parts[7]));
            prod.setWirbel(Component.Parse(parts[8]));
            prod.setSattel(Component.Parse(parts[9]));
            prod.setDecke(Component.Parse(parts[10]));
        }
        return prod;
    }

    @Override
    public String toString() {
        String s = "";
        for (Component component : components.values()) {
            s = s + "<-| ";
            s = s + component.toString();
            s = s + " |->";
        }
        return s;
    }
}