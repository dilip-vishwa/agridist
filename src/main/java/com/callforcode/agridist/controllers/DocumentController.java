package com.callforcode.agridist.controllers;

import com.callforcode.agridist.entities.Document;
import com.callforcode.agridist.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @GetMapping("/document")
    public List<Document> getAllDocument( ) throws InterruptedException, ExecutionException{
        return documentService.getDocuments();
    }

    @GetMapping("/document/{id}")
    public Document getDocument(@PathVariable String id ) throws InterruptedException, ExecutionException{
        return documentService.getDocumentDetails(id);
    }

    @PostMapping("/document")
    public String createDocument(@RequestBody Document document ) throws InterruptedException, ExecutionException {
        return documentService.saveDocumentDetails(document);
    }

    @PutMapping("/document/{id}")
    public String updateDocument(@RequestBody Document document , @PathVariable String id ) throws InterruptedException, ExecutionException {
        return documentService.updateDocumentDetails(document, id);
    }

    @DeleteMapping("/document/{id}")
    public String deleteDocument(@PathVariable String id){
        return documentService.deleteDocument(id);
    }
}