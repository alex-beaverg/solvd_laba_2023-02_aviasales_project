package com.solvd.aviasales.domain.structure;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

import java.util.List;

@JsonIgnoreProperties(value = { "routes" })
@XmlRootElement(name = "airline")
@XmlAccessorType(XmlAccessType.FIELD)
public class Airline {
    private Long id;
    private String title;
    private Company company;
    @XmlTransient
    private List<Route> routes;

    public Airline() { }

    public Airline(String title,
                   Company company) {
        this.title = title;
        this.company = company;
    }

    public Airline(Long id,
                   String title,
                   Company company) {
        this.id = id;
        this.title = title;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    @Override
    public String toString() {
        return String.format("Airline:[%s, %s]", title, company);
    }
}
