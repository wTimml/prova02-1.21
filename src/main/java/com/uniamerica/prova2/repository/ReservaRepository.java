package com.uniamerica.prova2.repository;

import com.uniamerica.prova2.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva,Long> {
//    List<Reserva> findByStatus(String status);
}
