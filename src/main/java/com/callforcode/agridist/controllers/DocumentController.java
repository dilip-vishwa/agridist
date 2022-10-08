package com.callforcode.agridist.controllers;

import com.callforcode.agridist.entities.Document;
import com.callforcode.agridist.entities.User;
import com.callforcode.agridist.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @GetMapping("/document")
    public ResponseEntity<List<Document>> getAllDocument( ) throws InterruptedException, ExecutionException{
        List<Document> documents = documentService.getDocuments();
        if(documents.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.of(Optional.of(documents));
    }

    @GetMapping("/document/{id}")
    public ResponseEntity<Optional<Document>> getDocument(@PathVariable String id ) throws InterruptedException, ExecutionException{
        Optional<Document> user = documentService.getDocumentDetails(id);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(user));
    }

    @PostMapping("/document")
    public ResponseEntity<Document> createDocument(@RequestBody Document document ) throws InterruptedException, ExecutionException {
        try{
            Document user1 = documentService.saveDocumentDetails(document);
            return ResponseEntity.status(HttpStatus.CREATED).body(user1);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/document/{id}")
    public ResponseEntity<Document> updateDocument(@RequestBody Document document , @PathVariable String id ) throws InterruptedException, ExecutionException {
        try {
            documentService.updateDocumentDetails(document, id);
            return ResponseEntity.ok().body(document);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/document/{id}")
    public ResponseEntity<String> deleteDocument(@PathVariable String id){
        try {
            documentService.deleteDocument(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}