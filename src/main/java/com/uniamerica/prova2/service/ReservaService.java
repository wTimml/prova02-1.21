package com.uniamerica.prova2.service;

import com.uniamerica.prova2.model.Reserva;
import com.uniamerica.prova2.repository.CarroRepository;
import com.uniamerica.prova2.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final CarroRepository carroRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository,CarroRepository carroRepository){
        this.reservaRepository = reservaRepository;
        this.carroRepository = carroRepository;
    }

    public Reserva criarReserva(Reserva reserva){

        if(checkIfCarroAvailable(reserva.getCarro().getId()).isEmpty()){
            reserva.setStatus("reservado");
            return reservaRepository.save(reserva);
        }

        return null;
    }

    public Reserva efetuarReserva(long id) throws Exception {
        try{
            Date date   = new Date(System.currentTimeMillis());

            Reserva reserva = getById(id);
            reserva.setDataRetirada(date); // Milliseconds?
            reserva.setStatus("em andamento");

            return reservaRepository.save(reserva);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    public Reserva finalizarReserva(long id) throws Exception {
        try{
            Date date   = new Date(System.currentTimeMillis());

            Reserva reserva = getById(id);
            reserva.setStatus("finalizado");

            return reservaRepository.save(reserva);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    public Reserva getById(long id){
        return reservaRepository.getOne(id);
    }

    public List<Reserva> findAll(){
        return reservaRepository.findAll();
    }

    public List<Reserva> checkIfCarroAvailable(long id){

        List<Reserva> reservas = reservaRepository.findAll();

        return reservas.stream()
                .filter(reservaFinalizado -> reservaFinalizado.getStatus().equals("em andamento") || reservaFinalizado.getStatus().equals("reservado"))
                .filter(reservado -> reservado.getCarro().getId().equals(id))
                .collect(Collectors.toList());
    }

    public List<Reserva> listCarrosAvailableByDate(Date dateToCheck){

        List<Reserva> reservas1 = reservaRepository.findAll();
        List<Reserva> reservas2 = reservaRepository.findAll();

        System.out.println(reservas1);

        reservas1.stream()
                .filter( reserva -> reserva.getStatus().equals("em andamento"))
                .filter( reserva -> reserva.getDataRetirada().before(dateToCheck) && reserva.getDataDevolucao().after(dateToCheck))
                .collect(Collectors.toList());

        reservas2.stream()
                .filter(reserva -> reserva.getStatus().equals("reservado")).collect(Collectors.toList());

         return Stream.concat(reservas1.stream(), reservas2.stream()).collect(Collectors.toList());

        //getCarros

//        List<Carro> carros = carroRepository.findAll();
//        return carros.stream()
//                .filter(carro -> carro.getId().)
    }

}
