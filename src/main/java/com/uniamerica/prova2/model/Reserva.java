package com.uniamerica.prova2.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataRetirada;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataDevolucao;

    @ManyToOne
    private Carro carro;

    private String status;

    public Reserva() {
    }

    public Reserva(Long id, Date dataRetirada, Date dataDevolucao, Carro carro, String status) {
        this.id = id;
        this.dataRetirada = dataRetirada;
        this.dataDevolucao = dataDevolucao;
        this.carro = carro;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(Date dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
