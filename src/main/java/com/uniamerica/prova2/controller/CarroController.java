package com.uniamerica.prova2.controller;


import com.uniamerica.prova2.model.Carro;
import com.uniamerica.prova2.model.Marca;
import com.uniamerica.prova2.service.CarroService;
import com.uniamerica.prova2.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {

    private final CarroService carroService;

    private final MarcaService marcaService;


    @Autowired
    public CarroController(CarroService carroService, MarcaService marcaService) {
        this.carroService = carroService;
        this.marcaService = marcaService;
    }

    @PostMapping
    public ResponseEntity<?> insertCarro(@RequestBody Carro carro) throws Exception {
        Carro add;
        try {
            add = carroService.insereCarro(carro);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return new ResponseEntity<>(add, null, HttpStatus.CREATED);
    }

    @GetMapping("/marca/{id}")
    public ResponseEntity getChannelsByRecloser(@PathVariable long id) {

        Marca marca = marcaService.getById(id);

        List<Carro> list = carroService.getCarrosByMarca(marca);

        if (!list.isEmpty()) {
            return new ResponseEntity<>(list, null, HttpStatus.OK);
        }
        return new ResponseEntity<>(list, null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/status")
    public ResponseEntity getCarrosStatus(@PathVariable long id) {

        List<Carro> list = carroService.getStatusDisponiveis();

        if (!list.isEmpty()) {
            return new ResponseEntity<>(list, null, HttpStatus.OK);
        }
        return new ResponseEntity<>(list, null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/status/{id}")
    public ResponseEntity getReservaStatus(@PathVariable long id) {

        String reservaStatus = carroService.getCarroStatus(id);

        return new ResponseEntity<>(reservaStatus, null, HttpStatus.NO_CONTENT);
    }

}