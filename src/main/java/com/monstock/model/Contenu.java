package com.monstock.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Contenu {
    @JsonProperty("type_message")
    private String typeMessage;
    @JsonProperty("id")
    private int id;
    @JsonProperty("fournisseur")
    private Fournisseur fournisseur;
    @JsonProperty("site_reception")
    private SiteReception siteReception;
    @JsonProperty("numero_commande")
    private String numeroCommande;
    @JsonProperty("numero_livraison")
    private String numeroLivraison;
    @JsonProperty("statut")
    private String statut;
    @JsonProperty("creation")
    private String creation;
    @JsonProperty("modification")
    private String modification;
    @JsonProperty("date_reception")
    private String dateReception;
    @JsonProperty("lignes")
    private List<Ligne> lignes;
    public int getId() {
        return id;
    }
    public Fournisseur getFournisseur() {
        return fournisseur;
    }
    public SiteReception getSiteReception() {
        return siteReception;
    }
    public String getTypeMessage() {
        return typeMessage;
    }
    public String getNumeroCommande() {
        return numeroCommande;
    }
    public String getCreation() {
        return creation;
    }
    public String getDateReception() {
        return dateReception;
    }
    public List<Ligne> getLignes() {
        return lignes;
    }


}
