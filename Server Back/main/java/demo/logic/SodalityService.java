package demo.logic;

import java.util.List;

import demo.rest.boundaries.SodalityBoundary;

public interface SodalityService {

	public SodalityBoundary addSodality(String userId, SodalityBoundary sodality);

	public void updateSodality(String userId, SodalityBoundary sodality);

	public void deleteSodality(String userId, SodalityBoundary sodality);

	public SodalityBoundary getSodalityById(String userId, Long sodalityId);

	public List<SodalityBoundary> getAllSodalities(String userId, int page, int size);

}
