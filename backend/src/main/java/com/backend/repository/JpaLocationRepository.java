package com.backend.repository;

import com.backend.entity.Location;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaLocationRepository implements LocationRepository {

    private final EntityManager em;

    public JpaLocationRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Location save(Location location) {
        em.persist(location);
        return location;
    }

    @Override
    public Optional<Location> findById(Long id) {
        Location location = em.find(Location.class, id);
        return Optional.ofNullable(location);
    }

    @Override
    public List<Location> findByTripId(Long trip_id) {
        return em.createQuery("select m from Location m where m.trip = :trip_id", Location.class)
                .setParameter("trip_id", trip_id)
                .getResultList();
    }

    @Override
    public List<Location> findAll() {
        return em.createQuery("select m from Location m", Location.class)
                .getResultList();
    }
}
