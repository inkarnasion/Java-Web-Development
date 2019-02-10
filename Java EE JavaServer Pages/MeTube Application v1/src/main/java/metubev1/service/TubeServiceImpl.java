package metubev1.service;

import metubev1.domain.entities.Tube;
import metubev1.domain.models.service.TubeServiceModel;
import metubev1.repository.TubeRepository;
import metubev1.util.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class TubeServiceImpl implements TubeService {
    private final TubeRepository tubeRepository;
    private final ModelMapper modelMapper;

    @Inject
    public TubeServiceImpl(TubeRepository tubeRepository, ModelMapper modelMapper) {
        this.tubeRepository = tubeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveTube(TubeServiceModel tubeServiceModel) {
        this.tubeRepository.save(this.modelMapper.map(tubeServiceModel, Tube.class));

    }

    @Override
    public TubeServiceModel findTubeByName(String name) {
        Tube tube = this.tubeRepository.findByName(name).orElse(null);
        TubeServiceModel tubeServiceModel = this.modelMapper.map(tube, TubeServiceModel.class);

        return tubeServiceModel;
    }

    @Override
    public List<TubeServiceModel> findAllTubes() {
        List<TubeServiceModel> tubeServiceModels = this.tubeRepository.findAll().stream().map(t -> this.modelMapper.map(t, TubeServiceModel.class)).collect(Collectors.toList());

        return tubeServiceModels;
    }
}
