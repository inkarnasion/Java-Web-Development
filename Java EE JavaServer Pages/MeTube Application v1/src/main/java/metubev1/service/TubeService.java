package metubev1.service;

import metubev1.domain.models.service.TubeServiceModel;

import java.util.List;

public interface TubeService {

    void saveTube(TubeServiceModel tubeServiceModel);

    TubeServiceModel findTubeByName(String name);

    List<TubeServiceModel> findAllTubes();

}
