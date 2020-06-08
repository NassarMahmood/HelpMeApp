package demo.logic;

import java.util.List;

import demo.rest.boundaries.IndigentBoundary;

public interface IndigentService {

	public IndigentBoundary addIndigent(String userId, IndigentBoundary newIndigent);

	public void updateIndigent(String userId, IndigentBoundary updateIndigent);

	public void deletedIndigent(String userId, IndigentBoundary deletedIndigent);

	public IndigentBoundary getIndigentByIdAndSodalityId(String userId, String indigentId, Long sodalityId);

	public List<IndigentBoundary> getIndigentsByFirstNameAndSodalityId(String userId, String indigentFisrtName, Long sodalityId, int size, int page);

	public List<IndigentBoundary> getAllIndigentsBySodalityId(String userId, Long sodalityId, int size, int page);

	public IndigentBoundary getIndigentIdById(String userId, String indigentId); // ADMIN

	public List<IndigentBoundary> getAllindIgents(String userId, int size, int page); // ADMIN


}
