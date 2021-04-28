package com.uniamerica.prova2.service;

import com.uniamerica.prova2.model.Carro;
import com.uniamerica.prova2.model.Reserva;
import com.uniamerica.prova2.repository.CarroRepository;
import com.uniamerica.prova2.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final CarroRepository carroRepository;
    private final CarroService carroService;


    @Autowired
    public ReservaService(ReservaRepository reservaRepository,CarroService carroService,CarroRepository carroRepository){
        this.reservaRepository = reservaRepository;
        this.carroService = carroService;
        this.carroRepository = carroRepository;
    }
    public Reserva insereReserva(Reserva reserva){
        if (checkCarroStatus(reserva.getCarro().getId())) {
            Carro updateCarro = reserva.getCarro();
            updateCarro.setReservaStatus("em andamento");
            carroRepository.save(updateCarro);
            return reservaRepository.save(reserva);
        }
        return null;
    }

    public boolean checkCarroStatus(long id){
        String reservaStatus = carroService.getCarroStatus(id);
        if(reservaStatus.equals("em andamento") || reservaStatus.equals("reservado")){
            return false;
        }
        return true;
    }

    void efetuaReserva(long id){
        Date retirada = new Date();
        System.out.println(retirada.getTime());
        System.out.println(retirada.getDate());
        System.out.println(retirada.getClass());

    }
}
