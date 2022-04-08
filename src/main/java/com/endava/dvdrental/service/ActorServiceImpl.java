package com.endava.dvdrental.service;

import com.endava.dvdrental.entity.Actor;
import com.endava.dvdrental.exception.EntityNotFoundException;
import com.endava.dvdrental.exception.NoDataFoundException;
import com.endava.dvdrental.repository.ActorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;

    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public List<Actor> getAllActors() {
        List<Actor> actorList = actorRepository.findAll();

        if (actorList.isEmpty()) {
            throw new NoDataFoundException();
        }

        return actorList;
    }

    @Override
    public Actor getActorById(Integer id) {
        return actorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public Actor editActorById(Actor actor, Integer id) {
        Optional<Actor> newActor = Optional.ofNullable(actor);

        Actor oldActor = actorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));

        oldActor.setFirstName(newActor
                .map(Actor::getFirstName)
                .orElse(oldActor.getFirstName())
        );
        oldActor.setLastName(newActor
                .map(Actor::getLastName)
                .orElse(oldActor.getLastName())
        );
        oldActor.setLastUpdate();

        return actorRepository.save(oldActor);
    }

    @Override
    public Actor addOneActor(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public String deleteActorById(Integer id) {
        actorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        actorRepository.deleteById(id);
        return "Actor with id " + id + " was deleted successfully!";
    }
}
