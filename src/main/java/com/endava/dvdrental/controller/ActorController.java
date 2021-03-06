package com.endava.dvdrental.controller;

import com.endava.dvdrental.entity.Actor;
import com.endava.dvdrental.service.ActorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actor")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public ResponseEntity<List<Actor>> getAllActors() {
        List<Actor> actorList = actorService.getAllActors();
        return new ResponseEntity<>(actorList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable("id") Integer id) {
        Actor actor = actorService.getActorById(id);
        return new ResponseEntity<>(actor, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Actor> addActor(@RequestBody Actor actor) {
        Actor addedActor = actorService.addOneActor(actor);
        return new ResponseEntity<>(addedActor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actor> editActor(@RequestBody Actor actor, @PathVariable("id") Integer id) {
        Actor editedActor = actorService.editActorById(actor, id);

        return new ResponseEntity<>(editedActor, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteActorById(@PathVariable("id") Integer id) {
        String message = actorService.deleteActorById(id);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
