package com.uniamerica.prova2.model;

import javax.persistence.*;

@Entity
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placa;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Modelo modelo;

    private String reservaStatus;

    public String getReservaStatus() {
        return reservaStatus;
    }

    public void setReservaStatus(String reservaStatus) {
        this.reservaStatus = reservaStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
}
