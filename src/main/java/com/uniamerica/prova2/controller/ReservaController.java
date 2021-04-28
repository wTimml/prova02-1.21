package com.uniamerica.prova2.controller;


import com.uniamerica.prova2.model.Reserva;
import com.uniamerica.prova2.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    @Autowired
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<?> insertReserva(@RequestBody Reserva reserva) throws Exception{
        Reserva reservaAdd;

            try {
                reservaAdd = reservaService.insereReserva(reserva);

            } catch (Exception e) {
                throw new Exception(e);
            }
            return new ResponseEntity<>(reservaAdd, null, HttpStatus.CREATED);
       }

}
