package com.helpdeskeditor.application.util;

import com.helpdeskeditor.application.util.SampleAddress;
import java.util.Optional;
import java.util.UUID;

import com.helpdeskeditor.application.util.SampleAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SampleAddressService {

    private final SampleAddressRepository repository;

    @Autowired
    public SampleAddressService(SampleAddressRepository repository) {
        this.repository = repository;
    }

    public Optional<SampleAddress> get(UUID id) {
        return repository.findById(id);
    }

    public SampleAddress update(SampleAddress entity) {
        return repository.save(entity);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public Page<SampleAddress> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public int count() {
        return (int) repository.count();
    }

}
