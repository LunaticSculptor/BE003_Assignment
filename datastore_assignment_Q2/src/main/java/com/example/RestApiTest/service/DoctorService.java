// src/main/java/com/example/demo/service/DoctorService.java
package com.example.RestApiTest.service;

import com.example.RestApiTest.model.Doctor;
import com.example.RestApiTest.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import org.springframework.cache.annotation.Cacheable;
import java.util.Optional;
@EnableCaching
@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    @Autowired

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
    @Cacheable(value = "doctors", key= "#id")
    public Optional<Doctor> getDoctorById(Long id) {
        System.out.println(id);
        return doctorRepository.findById(id);
    }

    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }
    @CacheEvict(value = "doctors", key = "#id")
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}

