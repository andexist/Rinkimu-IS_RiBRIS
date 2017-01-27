package lt.javainiai.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lt.javainiai.model.PollingDistrictEntity;

// TODO
// Jei veiks update - pridet atgal: implements RepositoryInterface<PollingDistrictEntity>

@Repository
public class PollingDistrictRepository {

    @Autowired
    private EntityManager em;

    @Transactional
    public PollingDistrictEntity save(PollingDistrictEntity pollingDistrict) {
        if (pollingDistrict.getId() == null) {
            em.persist(pollingDistrict);
            return pollingDistrict;
        } else {
            PollingDistrictEntity merged = em.merge(pollingDistrict);
            em.persist(merged);
            return merged;
        }
    }

    // @Transactional
    // @Override
    // public PollingDistrictEntity save(PollingDistrictEntity pollingDistrict)
    // {
    // em.persist(pollingDistrict);
    // return pollingDistrict;
    // }
    //
    // @Transactional
    // @Override
    // public PollingDistrictEntity update(Long id, PollingDistrictEntity
    // pollingDistrict) {
    // PollingDistrictEntity pollingDistrictToUpdate = findById(id);
    // pollingDistrictToUpdate.setName(pollingDistrict.getName());
    // return pollingDistrictToUpdate;
    // }

    // @Override
    public List<PollingDistrictEntity> findAll() {
        return em.createQuery("SELECT p FROM PollingDistrictEntity p").getResultList();
    }

    // @Override
    public PollingDistrictEntity findById(Long id) {
        return em.find(PollingDistrictEntity.class, id);
    }

    @Transactional
    // @Override
    public void deleteById(Long id) {
        PollingDistrictEntity pollingDistrictToRemove = em.find(PollingDistrictEntity.class, id);
        em.remove(pollingDistrictToRemove);
    }

}
