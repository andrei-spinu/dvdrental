package com.endava.dvdrental.service;


import com.endava.dvdrental.entity.Actor;

import java.util.List;

public interface ActorService {
    List<Actor> getAllActors();
    Actor getActorById(Integer id);
    Actor editActorById(Actor actor, Integer id);
    Actor addOneActor(Actor actor);
    String deleteActorById(Integer id);
}
