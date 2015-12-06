package main.service;


import main.entity.Actor;

import java.util.List;

public interface ActorService {
    List<Actor> findAll();
    Actor findByName(String name);
    Actor findById(Long id);
    Actor addActor(Actor actor);
    boolean isActorExist(String name);
    boolean isActorExist(Long id);
}
