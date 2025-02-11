package com.waterdata.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "")
public class Station {
    @Id
    @Column(name = "IdStation")
    private int idStation;

    @Column(name = "PosX", precision = 6)
    private double posX;

    @Column(name = "PosY", precision = 6)
    private double posY;

    @Column(name = "Location", length = 50)
    private String location;

    //Getters
    public int getIdStation() { return idStation; }
    public double getPosX() { return posX; }
    public double getPosY() { return posY; }
    public String getLocation() { return location; }

    //Setters
    public void setIdStation(int idStation) { this.idStation = idStation; }
    public void setPosX(double posX) { this.posX = posX; }
    public void setPosY(double posY) { this.posY = posY; }
    public void setLocation(String location) { this.location = location; }
}