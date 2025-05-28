package com.example.egy_tour.controller;

import com.example.egy_tour.dto.CreateProgramDTO;
import com.example.egy_tour.dto.ProgramResponseDTO;
import com.example.egy_tour.dto.UpdateProgramDTO;
import com.example.egy_tour.model.Program;
import com.example.egy_tour.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programs")
public class ProgramController {

    @Autowired
    private ProgramService programService;


    @PostMapping("/user/{id}")
    public ResponseEntity<Program> createProgram(
            @PathVariable Long id,
            @RequestBody CreateProgramDTO programDTO) {
        Program createdProgram = programService.createProgram(id, programDTO);
        return ResponseEntity.ok(createdProgram);
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<List<ProgramResponseDTO>> getProgramsByUserId(@PathVariable Long id) {
        List<ProgramResponseDTO> programs = programService.getProgramByUserId(id);
        return ResponseEntity.ok(programs);
    }



    @PutMapping("/{programId}")
    public ResponseEntity<Program> updateProgram(
            @PathVariable Long programId,
            @RequestBody UpdateProgramDTO updateProgramDTO) {
        Program updatedProgram = programService.updateProgram(programId, updateProgramDTO);
        return ResponseEntity.ok(updatedProgram);
    }


    @DeleteMapping("/{programId}")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long programId) {
        programService.deleteProgram(programId);
        return ResponseEntity.noContent().build();
    }
}
