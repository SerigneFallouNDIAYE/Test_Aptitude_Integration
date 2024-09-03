package com.monstock.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ClientOrder {
    @JsonProperty("id")
    private int id;
    @JsonProperty("message_type")
    private String messageType;
    @JsonProperty("creation")
    private String creation;
    @JsonProperty("exported")
    private int exported;
    @JsonProperty("contenu")
    private Contenu contenu;

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getCreation() {
        return creation;
    }

    public void setCreation(String creation) {
        this.creation = creation;
    }

    public int getExported() {
        return exported;
    }

    public void setExported(int exported) {
        this.exported = exported;
    }

    public Contenu getContenu() {
        return contenu;
    }

    public void setContenu(Contenu contenu) {
        this.contenu = contenu;
    }

    public class Ligne {
    }
}
