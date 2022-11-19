package com.helpdeskeditor.application.data.service;

import com.helpdeskeditor.application.data.entity.SampleAddress;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleAddressRepository extends JpaRepository<SampleAddress, UUID> {

}