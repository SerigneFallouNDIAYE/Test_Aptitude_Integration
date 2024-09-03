package com.monstock.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ligne {
    @JsonProperty("id")
    private String id;
    @JsonProperty("code_produit")
    private String codeProduit;
    @JsonProperty("libelle_fr")
    private String libelleFr;
    @JsonProperty("quantite")
    private int quantite;
    @JsonProperty("unite")
    private String unite;
    @JsonProperty("lieu")
    private int lieu;
    @JsonProperty("nb_jour_dlc_apres_decongelation")
    private int nbJourDlcApresDecongelation;
    @JsonProperty("nb_jour_dlv")
    private int nbJourDlv;
    @JsonProperty("nb_jour_blocage")
    private int nbJourBlocage;
    @JsonProperty("fragile")
    private boolean fragile;
    @JsonProperty("numero_lot")
    private String numeroLot;
    @JsonProperty("dlc")
    private String dlc;
    @JsonProperty("categorie")
    private String categorie;

    public String getId() {
        return id;
    }
    public String getCodeProduit() {
        return codeProduit;
    }
    public int getQuantite() {
        return quantite;
    }



}
