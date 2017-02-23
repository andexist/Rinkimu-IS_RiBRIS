package lt.javainiai.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lt.javainiai.model.CandidatesResultsSingleMandateEntity;

@Repository
public class CandidatesResultsSingleMandateRepository implements 
				RepositoryInterface<CandidatesResultsSingleMandateEntity>{

    @Autowired
    private EntityManager em;

    @Override
    @Transactional
    public CandidatesResultsSingleMandateEntity saveOrUpdate(CandidatesResultsSingleMandateEntity inputResult) {
    	
    	List<CandidatesResultsSingleMandateEntity> allResults = findAll();
    	boolean resultExists = false;
    	CandidatesResultsSingleMandateEntity newResult = new CandidatesResultsSingleMandateEntity();
    	
    	for (CandidatesResultsSingleMandateEntity resultInList : allResults) {
	        if (resultInList.equals(inputResult)) {
	        	resultExists = true;
	        	newResult = resultInList;
	            if (( inputResult.getNumberOfVotes() != null) && ( inputResult.getNumberOfVotes() == 0 )) {
	            	newResult.setNumberOfVotes( inputResult.getNumberOfVotes() );
	            } else if ((inputResult.getNumberOfVotes() != null) && (inputResult.getNumberOfVotes() != 0)) {
	            	newResult.setNumberOfVotes( inputResult.getNumberOfVotes() + resultInList.getNumberOfVotes() );
	            } else {
	            	newResult.setNumberOfVotes( inputResult.getNumberOfVotes() );
	            }
	        }
    	}
        if (!resultExists) {
            em.persist(inputResult);
            return inputResult;
        } else {
        	CandidatesResultsSingleMandateEntity merged = em.merge(newResult);
            em.persist(merged);
            return merged;
        }
    }

	@SuppressWarnings("unchecked")
	@Override
    public List<CandidatesResultsSingleMandateEntity> findAll() {
        return em.createQuery("SELECT c FROM CandidatesResultsSingleMandateEntity c").getResultList();
    }

    @Override
    public CandidatesResultsSingleMandateEntity findById(Long id) {
        return em.find(CandidatesResultsSingleMandateEntity.class, id);
    }

    @Override
    public void deleteById(Long id) {
    	CandidatesResultsSingleMandateEntity candidateResultsToRemove = 
		em.find(CandidatesResultsSingleMandateEntity.class, id);
			em.remove(candidateResultsToRemove);
    }
}