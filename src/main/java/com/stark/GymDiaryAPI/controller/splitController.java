package com.stark.GymDiaryAPI.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.stark.GymDiaryAPI.model.Split;

import com.stark.GymDiaryAPI.service.SplitService;


@RestController
@RequestMapping("/api/splits")
public class splitController {

    @Autowired
    private SplitService splitService;

    //Get all the splits

    @GetMapping
    public ResponseEntity<List<Split>>getAllSplits(){
        return new ResponseEntity<List<Split>>(splitService.getAllSplits(),HttpStatus.OK);
    }

    //get a split by its id

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Split>>getSingleSplit(@PathVariable String id){
        return new ResponseEntity<Optional<Split>>(splitService.getSplitByid(id),HttpStatus.OK);
    }

    //Create a new split

    @PostMapping("")
    public ResponseEntity<Split> createSplit(@RequestBody Split newSplit) {
        return new ResponseEntity<Split>(splitService.createSplit(newSplit),HttpStatus.OK);
    }
    

    //update a split
    
    @PutMapping("/{id}")
    public ResponseEntity<Optional<Split>>updateSplit(@PathVariable String id, @RequestBody Split updatedSplit){
        return new ResponseEntity<Optional<Split>>(splitService.updateSplit(id, updatedSplit), HttpStatus.ACCEPTED);
    }

    //delete a split
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean>deleteSplit(@PathVariable String id){
        return new ResponseEntity<Boolean>(splitService.deleteSplit(id), HttpStatus.ACCEPTED);
    }
}
