package com.example.personnel.service;

import com.example.personnel.model.personnel;
import com.example.personnel.rabbitmq.MessageProducer;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class personnelService {

    private final Map<Long, personnel> personnelMap = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();
    private final MessageProducer messageProducer;

    public personnelService(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    public List<personnel> getAllPersonnel() {
        return new ArrayList<>(personnelMap.values());
    }

    public personnel getPersonnelById(Long id) {
        personnel personnel = personnelMap.get(id);
        if (personnel == null) {
            throw new NoSuchElementException("Personel bulunamadı: " + id);
        }
        return personnel;
    }

    public personnel createPersonnel(personnel personnel) {
        Long id = idCounter.incrementAndGet();
        personnel.setId(id);
        personnelMap.put(id, personnel);
        messageProducer.sendPersonnelUpdate(personnel); // RabbitMQ'ya mesaj gönder
        return personnel;
    }

    public personnel updatePersonnel(Long id, personnel personnelDetails) {
        personnel existingPersonnel = getPersonnelById(id);
        
        existingPersonnel.setFirstName(personnelDetails.getFirstName());
        existingPersonnel.setEmail(personnelDetails.getEmail());
        existingPersonnel.setDepartment(personnelDetails.getDepartment());

        personnelMap.put(id, existingPersonnel);
        messageProducer.sendPersonnelUpdate(existingPersonnel); // Güncellemeden sonra mesaj gönder
        return existingPersonnel;
    }

    public void deletePersonnel(Long id) {
        personnel personnel = getPersonnelById(id);
        personnelMap.remove(id);
        messageProducer.sendPersonnelDeletion(personnel); // Silmeden sonra mesaj gönder
    }
}
