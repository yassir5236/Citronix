package org.yassir.citronix.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yassir.citronix.Dto.Tree.TreeRequestDTO;
import org.yassir.citronix.Dto.Tree.TreeResponseDTO;
import org.yassir.citronix.Service.ITreeService;

import java.util.List;

@RestController
@RequestMapping("/api/trees")
public class TreeController {

    private final ITreeService treeService;

    @Autowired
    public TreeController(ITreeService treeService) {
        this.treeService = treeService;
    }

    @PostMapping
    public ResponseEntity<TreeResponseDTO> createTree(@Valid @RequestBody TreeRequestDTO treeRequestDTO) {
        TreeResponseDTO createdTree = treeService.createTree(treeRequestDTO);
        return new ResponseEntity<>(createdTree, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreeResponseDTO> getTreeById(@PathVariable Long id) {
        TreeResponseDTO tree = treeService.getTreeById(id);
        return ResponseEntity.ok(tree);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreeResponseDTO> updateTree(
            @PathVariable Long id,
            @Valid @RequestBody TreeRequestDTO treeRequestDTO) {
        TreeResponseDTO updatedTree = treeService.updateTree(id, treeRequestDTO);
        return ResponseEntity.ok(updatedTree);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTree(@PathVariable Long id) {
        treeService.deleteTree(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TreeResponseDTO>> getAllTrees() {
        List<TreeResponseDTO> trees = treeService.getAllTrees();
        return ResponseEntity.ok(trees);
    }




}
