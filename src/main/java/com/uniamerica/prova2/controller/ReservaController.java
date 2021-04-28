package com.uniamerica.prova2.controller;


import com.uniamerica.prova2.model.Reserva;
import com.uniamerica.prova2.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    @Autowired
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<?> criarReserva(@RequestBody Reserva reserva) throws Exception{
        Reserva reservaAdd;

            try {
                reservaAdd = reservaService.criarReserva(reserva);
            } catch (Exception e) {
                throw new Exception(e);
            }
            return new ResponseEntity<>(reservaAdd, null, HttpStatus.CREATED);
       }

    @PostMapping("/efetuar/{id}")
    public ResponseEntity<?> efetuarReserva(@PathVariable long id) throws Exception{
        Reserva reservaAdd;

        try {
            reservaAdd = reservaService.efetuarReserva(id);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return new ResponseEntity<>(reservaAdd, null, HttpStatus.OK);
    }
    @PostMapping("/finalizar/{id}")
    public ResponseEntity<?> finalizarReserva(@PathVariable long id) throws Exception{
        Reserva reserva;

        try {
            reserva = reservaService.finalizarReserva(id);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return new ResponseEntity<>(reserva, null, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity findAll() throws Exception{
        List<Reserva> reservas;
        try {
            reservas = reservaService.findAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
        return  new ResponseEntity<>(reservas,null, HttpStatus.OK);
    }

    @GetMapping("/disponiveis") //current date
    public ResponseEntity listCarrosAvailableByDate() throws Exception {
        List<Reserva> reservas;
        Date date   = new Date(System.currentTimeMillis());

        try {
            reservas = reservaService.listCarrosAvailableByDate(date);
        } catch (Exception e) {
            throw new Exception(e);
        }

        return new ResponseEntity<>(reservas,null, HttpStatus.OK);
    }
}
