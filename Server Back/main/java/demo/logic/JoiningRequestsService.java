package demo.logic;

import java.util.List;

import demo.rest.boundaries.JoiningRequestsBoundary;

public interface JoiningRequestsService {

	public JoiningRequestsBoundary addJioningRequest(String userId, JoiningRequestsBoundary request);

	public void updateJioningRequest(String userId, JoiningRequestsBoundary request);

	public void deleteJioningRequest(String userId, JoiningRequestsBoundary request);

	public List<JoiningRequestsBoundary> getAllJioningRequest(String userId, int page, int size);

}
