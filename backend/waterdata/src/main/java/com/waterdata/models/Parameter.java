package com.waterdata.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Parameter")
public class Parameter {
    @Id
    @Column(name = "IdParameter")
    private int idParameter;

    @Column(name = "ParameterName", length=50)
    private String parameterName;

    @Column(name = "Value", length=10)
    private String value;

    @Column(name = "Unit", length=10)
    private String unit;

    //Getters
    public int getIdParameter() { return idParameter; }
    public String getParameterName() { return parameterName; }
    public String getValue() { return value; }
    public String getUnit() { return unit; }

    //Setters
    public void setIdParameter(int idParameter) { this.idParameter = idParameter; }
    public void setParameterName(String parameterName) { this.parameterName = parameterName; }
    public void setValue(String value) { this.value = value; }
    public void setUnit(String unit) { this.unit = unit; }
}
