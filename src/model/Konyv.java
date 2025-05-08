/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author nagyg
 */
public class Konyv {
    private int id;
    private String szerzo;
    private String cim;
    private int oldalszam;
    private int tagId;

    public Konyv() {
    }

    public Konyv(String szerzo, String cim, int oldalszam, int tagId) {
        this.szerzo = szerzo;
        this.cim = cim;
        this.oldalszam = oldalszam;
        this.tagId = tagId;
    }

    public Konyv(int id, String szerzo, String cim, int oldalszam, int tagId) {
        this.id = id;
        this.szerzo = szerzo;
        this.cim = cim;
        this.oldalszam = oldalszam;
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        return id + ";" + szerzo + ";" + cim + ";" + oldalszam + ";" + tagId; 
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getSzerzo() {
        return szerzo;
    }

    public void setSzerzo(String szerzo) {
        this.szerzo = szerzo;
    }

    public int getOldalszam() {
        return oldalszam;
    }

    public void setOldalszam(int oldalszam) {
        this.oldalszam = oldalszam;
    }
    
    
}
