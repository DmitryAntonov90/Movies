package main.service.Impl;


import main.entity.Actor;
import main.repository.ActorRepository;
import main.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository actorRepository;

    @Override
    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    @Override
    public Actor findByName(String name) {
        Actor actor = actorRepository.getByName(name);
        return actor;
    }

    @Override
    public Actor findById(Long id) {
        Actor actor = actorRepository.getById(id);
        return actor;
    }

    @Override
    public Actor addActor(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public boolean isActorExist(String name) {
        Actor actor = findByName(name);
        return actor != null ? true : false;
    }

    @Override
    public boolean isActorExist(Long id) {
        Actor actor = findById(id);
        return actor != null ? true : false;
    }
}
