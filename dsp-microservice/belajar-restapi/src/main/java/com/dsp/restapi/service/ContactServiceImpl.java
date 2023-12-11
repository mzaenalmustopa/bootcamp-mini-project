package com.dsp.restapi.service;

import com.dsp.restapi.exception.CommonApiException;
import com.dsp.restapi.model.entity.AddressEntity;
import com.dsp.restapi.model.entity.ContactEntity;
import com.dsp.restapi.model.entity.UserEntity;
import com.dsp.restapi.model.request.AddressRequest;
import com.dsp.restapi.model.request.ContactRequest;
import com.dsp.restapi.model.response.AddressResponse;
import com.dsp.restapi.model.response.ContactResponse;
import com.dsp.restapi.model.response.Response;
import com.dsp.restapi.repository.AddressRepository;
import com.dsp.restapi.repository.ContactRepository;
import com.dsp.restapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class ContactServiceImpl implements ContactService{
    private UserRepository userRepository;
    private ContactRepository contactRepository;
    private AddressRepository addressRepository;

    @Autowired
    public ContactServiceImpl(UserRepository userRepository, ContactRepository contactRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.contactRepository = contactRepository;
        this.addressRepository = addressRepository;
    }

    @Transactional
    @Override
    public Optional<Response> save(ContactRequest request) {
        UserEntity user = userRepository.findById(request.getUserId()).orElse(null);
        if(user == null){
            //return Optional.empty();
            throw new CommonApiException("User not found", HttpStatus.BAD_REQUEST);
        }

        ContactEntity contact = new ContactEntity();
        BeanUtils.copyProperties(request, contact);
        // set user at contact
        contact.setUser(user);
        contact.setId(UUID.randomUUID().toString());

        return saveContactWithAddress(request, contact);
    }

    @Override
    public Optional<Response> update(String id, ContactRequest request) {
        if(id.isEmpty()){
            throw new CommonApiException("Id is empty", HttpStatus.BAD_REQUEST);
        }

        ContactEntity contact = contactRepository.findById(id).orElse(null);
        if(contact == null){
            throw new CommonApiException("Contact not found", HttpStatus.BAD_REQUEST);
        }

        //1. remove address
        for(AddressEntity addressEntity : contact.getAddress()){
            addressEntity.setContact(null);
        }
        contact.getAddress().clear();
        // contact save
        this.contactRepository.save(contact);

        BeanUtils.copyProperties(request, contact);
        return saveContactWithAddress(request, contact);
    }

    @Override
    public Optional<Response> updateAddress(String id, String addressId, AddressRequest request) {
        AddressEntity result = addressRepository.findByIdAndContactId(addressId, id).orElse(null);
        if(result == null){
            throw new CommonApiException("Contact not found", HttpStatus.BAD_REQUEST);
        }

        BeanUtils.copyProperties(request, result);
        try{
            this.addressRepository.save(result);
            AddressResponse response = this.setAddressResponse(result);
            return Optional.of(new Response(200,"Success", response));
        }catch (Exception e){
            throw new CommonApiException("Save contact is failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Optional<Response> saveContactWithAddress(ContactRequest request, ContactEntity contact) {
        for(AddressRequest addressRequest: request.getAddress()){
            AddressEntity addressEntity = new AddressEntity();
            // copy property
            BeanUtils.copyProperties(addressRequest, addressEntity);
            addressEntity.setId(UUID.randomUUID().toString());
            // call method yang ada di contact entity
            contact.addAddress(addressEntity);
        }

        try{
            contactRepository.save(contact);
            // convert to response
            ContactResponse contactResponse = this.setContactResponse(contact);
            // send to response
            Response response = new Response(200, "Success", contactResponse);
            return Optional.of(response);
        }catch (Exception e) {
            throw new CommonApiException("Save contact is failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private AddressResponse setAddressResponse(AddressEntity address){
        AddressResponse result = new AddressResponse();
        BeanUtils.copyProperties(address, result);
        return result;
    }

    private ContactResponse setContactResponse(ContactEntity contact){
        ContactResponse result = new ContactResponse();
        BeanUtils.copyProperties(contact, result);

        if(!contact.getAddress().isEmpty()) {
            List<AddressResponse> address = contact.getAddress().stream()
                    .map(this::setAddressResponse)
                    .toList();
            result.setAddress(address);
        }
        return result;
    }
}
