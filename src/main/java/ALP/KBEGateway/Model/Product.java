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
                    Component decke){
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
                        createMap();
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

    private void createMap(){
        if(components == null){
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
    
    @Override
    public String toString() {
        String s = "";
        for(Component component : components.values()){
            s = s + "<-| ";
            s = s + component.toString();
            s = s + " |->";
        }
        return s;
    }
}