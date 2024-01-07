package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<EventDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(EventDTO::new);
    }

    @Transactional
    public EventDTO insert(EventDTO dto) {
        Event entity = new Event();
        entity.setName(dto.getName());
        entity.setDate(dto.getDate());
        entity.setUrl(dto.getUrl());
        entity.setCity(new City(dto.getCityId() , null));
        entity = repository.save(entity);
        return new EventDTO(entity);
    }
}
