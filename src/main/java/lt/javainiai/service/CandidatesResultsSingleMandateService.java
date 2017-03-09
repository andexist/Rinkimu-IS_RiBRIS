package lt.javainiai.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.javainiai.model.CandidateEntity;
import lt.javainiai.model.CandidatesResultsSingleMandateEntity;
import lt.javainiai.model.ConstituencyEntity;
import lt.javainiai.model.PollingDistrictEntity;
import lt.javainiai.repository.CandidatesResultsSingleMandateRepository;
import lt.javainiai.utils.SingleMandateCandidateResults;
import lt.javainiai.utils.UtilityMethods;

@Service
public class CandidatesResultsSingleMandateService {

	@Autowired
	private CandidatesResultsSingleMandateRepository candidatesResultsRepository;
	@Autowired
	private PollingDistrictService pollingDistrictService;
	@Autowired
	private ConstituencyService constituencyService;

	public CandidatesResultsSingleMandateEntity saveOrUpdate(CandidatesResultsSingleMandateEntity candidatesResults) {
		return candidatesResultsRepository.saveOrUpdate(candidatesResults);
	}

	public List<CandidatesResultsSingleMandateEntity> findAll() {
		return candidatesResultsRepository.findAll();
	}

	public CandidatesResultsSingleMandateEntity findById(Long id) {
		return candidatesResultsRepository.findById(id);
	}

	public void deleteById(Long id) {
		this.candidatesResultsRepository.deleteById(id);
	}

	// Single-mandate results in District
	public List<SingleMandateCandidateResults> getSingleMandateResultsInDistrict(Long districtId) {

		List<SingleMandateCandidateResults> districtResultsList = new ArrayList<SingleMandateCandidateResults>();
		PollingDistrictEntity district = pollingDistrictService.findById(districtId);
		List<CandidateEntity> candidates = district.getConstituency().getCandidates();
		Long validVotes = 0L;
		Long allBallots = pollingDistrictService.getVotersActivityInUnitsInDistrict(districtId);

		// Count all valid single-member votes in district
		List<CandidatesResultsSingleMandateEntity> results = findAll();
		for (CandidatesResultsSingleMandateEntity result : results) {
			if (result.getDistrict() == district) {
				validVotes += result.getNumberOfVotes();
			}
		}

		// Fill districtResultsList with results of every candidate in district
		for (CandidateEntity candidate : candidates) {
			Long candidateVotes = 0L;
			Double percentOfValidBallots = null;
			Double percentOfAllBallots = null;

			// Get result in units in one district
			List<CandidatesResultsSingleMandateEntity> candidateResultsList = candidate
					.getCandidatesResultsSingleMandate();
			for (CandidatesResultsSingleMandateEntity result : candidateResultsList) {
				if (result.getDistrict() == district) {
					candidateVotes = result.getNumberOfVotes();
					break;
				}
			}

			// Get result in percent of valid ballots in one district
			percentOfValidBallots = (candidateVotes.doubleValue() / validVotes.doubleValue()) * 100.0d;
			percentOfValidBallots = UtilityMethods.round(percentOfValidBallots, 2);

			// Get result in percent of all ballots in one district
			percentOfAllBallots = (candidateVotes.doubleValue() / allBallots.doubleValue()) * 100.0d;
			percentOfAllBallots = UtilityMethods.round(percentOfAllBallots, 2);

			SingleMandateCandidateResults candidateResults = new SingleMandateCandidateResults(candidate,
					candidateVotes, percentOfValidBallots, percentOfAllBallots);

			districtResultsList.add(candidateResults);
		}
		return districtResultsList;
	}

	// Single-mandate results in Constituency
	public List<SingleMandateCandidateResults> getSingleMandateResultsInConstituency(Long constituencyId) {
		List<SingleMandateCandidateResults> constituencyResultsList = new ArrayList<SingleMandateCandidateResults>();
		ConstituencyEntity constituency = constituencyService.findById(constituencyId);
		List<PollingDistrictEntity> districts = constituency.getPollingDistricts();
		List<CandidateEntity> candidates = constituencyService.findById(constituencyId).getCandidates();
		Long validVotes = 0L;
		Long spoiledBallots = 0L;
		Long allBallots = 0L;

		// Count all valid single-member votes in constituency
		List<CandidatesResultsSingleMandateEntity> results = findAll();
		for (CandidatesResultsSingleMandateEntity result : results) {
			if (result.getDistrict().getConstituency() == constituency) {
				validVotes += result.getNumberOfVotes();
			}
		}

		// Count all ballots in single-member constituency
		for (PollingDistrictEntity district : districts) {
			spoiledBallots += district.getSpoiledSingleMandateBallots();
		}
		allBallots = validVotes + spoiledBallots;

		// Fill List of single-member candidates with their results
		for (CandidateEntity candidate : candidates) {
			Long candidateVotes = 0L;
			Double percentOfValidBallots = null;
			Double percentOfAllBallots = null;

			// Count candidate results in Constituency
			List<CandidatesResultsSingleMandateEntity> candidateResultsList = candidate
					.getCandidatesResultsSingleMandate();
			for (CandidatesResultsSingleMandateEntity result : candidateResultsList) {
				if (result.getDistrict().getConstituency() == constituency) {
					candidateVotes += result.getNumberOfVotes();
				}
			}

			// Count percent of valid ballots
			percentOfValidBallots = (candidateVotes.doubleValue() / validVotes.doubleValue()) * 100.0d;
			percentOfValidBallots = UtilityMethods.round(percentOfValidBallots, 2);

			// Count percent of all ballots
			percentOfAllBallots = (candidateVotes.doubleValue() / allBallots.doubleValue()) * 100.0d;
			percentOfAllBallots = UtilityMethods.round(percentOfAllBallots, 2);

			// Create candidate result object and add to List
			SingleMandateCandidateResults candidateResults = new SingleMandateCandidateResults(candidate,
					candidateVotes, percentOfValidBallots, percentOfAllBallots);
			constituencyResultsList.add(candidateResults);
		}
		return constituencyResultsList;
	}

}