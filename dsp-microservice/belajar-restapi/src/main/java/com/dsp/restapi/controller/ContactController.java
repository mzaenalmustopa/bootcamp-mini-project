package com.dsp.restapi.controller;

import com.dsp.restapi.model.request.AddressRequest;
import com.dsp.restapi.model.request.ContactRequest;
import com.dsp.restapi.model.response.Response;
import com.dsp.restapi.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactController {
    private ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody @Valid ContactRequest request){
        Response result = contactService.save(request).orElse(null);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable("id") String id,
                                           @RequestBody @Valid ContactRequest request){
        Response result = contactService.update(id, request).orElse(null);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{id}/address/{addressId}")
    public ResponseEntity<Response> updateAddress(@PathVariable("id") String id,
                                                  @PathVariable("addressId") String addressId,
                                                  @RequestBody @Valid AddressRequest request){
        Response result = contactService.updateAddress(id, addressId, request).orElse(null);
        return ResponseEntity.ok(result);
    }
}
