package com.hitbtvn_b5.controller;

import com.hitbtvn_b5.dto.URLDTO;
import com.hitbtvn_b5.exception.NotFoundException;
import com.hitbtvn_b5.model.URL;
import com.hitbtvn_b5.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/urls")
public class URLControllerAPI {

    @Autowired
    private URLService urlService;

    @GetMapping
    public ResponseEntity<?> getAllURLs() {
        List<URL> urls = urlService.findAllUrls();
        return ResponseEntity.status(200).body(urls);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        URL url = urlService.findByURLId(id);
        if(url == null) {
            throw new NotFoundException("Not found url with id: " + id);
        }
        return ResponseEntity.status(200).body(url);
    }

    @PostMapping
    public ResponseEntity<?> createNewUser(@RequestBody URLDTO urlDTO) {
        List<URL> urls = urlService.findAllUrls();

        if (urlService.checkNull(urlDTO) && !urlService.checkDuplicateShortenURL(urls, urlDTO.getShortenURL())) {
            URL url = new URL();
            url.setOriginalURL(urlDTO.getOriginalURL());
            url.setShortenURL(urlDTO.getShortenURL());

            urlService.save(url);
            return ResponseEntity.status(HttpStatus.CREATED).body("Created.");
        }
        throw new NotFoundException("Create failure.");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteURLById(@PathVariable(name = "id") Long id) {
        URL url = urlService.findByURLId(id);
        if(url == null) {
            throw new NotFoundException("Not found URL with id: " + id);
        }
        urlService.delete(url);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }

}
