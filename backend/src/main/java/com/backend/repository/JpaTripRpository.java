package com.backend.repository;

import com.backend.entity.Trip;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaTripRpository implements TripRepository {

    private final EntityManager em;

    public JpaTripRpository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Trip save(Trip trip) {
        em.persist(trip);
        return trip;
    }

    @Override
    public Optional<Trip> findById(Long id) {
        Trip trip = em.find(Trip.class, id);
        return Optional.ofNullable(trip);
    }

    @Override
    public List<Trip> findByTitle(String title) {
        return em.createQuery("select m from Trip m where m.title = :title", Trip.class)
                .setParameter("title", title)
                .getResultList();
    }

    @Override
    public List<Trip> findByDate(Date date) {
        return em.createQuery("select m from Trip m where m.date = :date", Trip.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<Trip> findByFullCost(Integer cost) {
        return em.createQuery("select m from Trip m where m.fullCost = :cost", Trip.class)
                .setParameter("cost", cost)
                .getResultList();
    }

    @Override
    public List<Trip> findAll() {
        return em.createQuery("select m from Trip m", Trip.class)
                .getResultList();
    }
}
