package com.backend.repository;

import com.backend.entity.Image;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaImageRepository implements ImageRepository {

    private final EntityManager em;

    public JpaImageRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Image save(Image image) {
        em.persist(image);
        return image;
    }

    @Override
    public Optional<Image> findById(Long id) {
        Image image = em.find(Image.class, id);
        return Optional.ofNullable(image);
    }

    @Override
    public List<Image> findByLocationId(Long location_id) {
        return em.createQuery("select m from Image m where m.location = :location_id", Image.class)
                .setParameter("location_id", location_id)
                .getResultList();
    }

    @Override
    public List<Image> findAll() {
        return em.createQuery("select m from Image m", Image.class)
                .getResultList();
    }
}
