package com.seucontrolefinanceiro.service;

import com.seucontrolefinanceiro.adapters.outbound.producer.KafkaProducer;
import com.seucontrolefinanceiro.application.entities.User;
import com.seucontrolefinanceiro.application.exception.ObjectNotFoundException;
import com.seucontrolefinanceiro.application.ports.repositories.UserRepository;
import com.seucontrolefinanceiro.application.services.UserServiceImpl;
import com.seucontrolefinanceiro.feature.UserFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private KafkaProducer kafkaProducer;

    @Test
    public void mustReturnAllUserus_WhenCallFindAll() {
        List<User> users = UserFactory.getUsers();
        when(repository.findAll()).thenReturn(users);
        assertFalse(service.findAll().isEmpty());
    }

    @Test
    public void mustReturnUser_WhenCallFindById() {
        User user = UserFactory.getDefaultUser();
        when(repository.findById(anyString())).thenReturn(Optional.of(user));
        assertNotNull(service.findById(anyString()));
    }

    @Test
    public void mustReturnObjectNotFoundException_WhenCallFindById() {
        when(repository.findById(anyString())).thenThrow(ObjectNotFoundException.class);
        assertThrows(ObjectNotFoundException.class, () -> service.findById(anyString()));
    }

    @Test
    public void mustReturnUser_WhenFindByEmail() {
        User user = UserFactory.getDefaultUser();
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(user));
        assertNotNull(service.findByEmail(anyString()));
    }

    @Test
    public void mustReturnUser_WhenSave() {
        User user = UserFactory.getDefaultUser();
        when(repository.save(user)).thenReturn(user);
        assertNotNull(service.save(user));
        verify(kafkaProducer, times(1)).send(anyString());
    }

    @Test
    public void mustDeleteUser_WhenCallDelete() {
        User user = UserFactory.getUserWithId();
        when(repository.findById(anyString())).thenReturn(Optional.of(user));
        service.delete(user.getId());
        verify(repository, times(1)).deleteById(user.getId());
    }

    @Test
    public void mustReturnObjectNotFoundException_WhenCallDelete() {
        when(repository.findById(anyString())).thenThrow(ObjectNotFoundException.class);
        assertThrows(ObjectNotFoundException.class, () -> service.delete(anyString()));
    }

    @Test
    public void mustReturnUser_WhenCallUpdate() {
        User user = UserFactory.getUserWithId();
        when(repository.findById(anyString())).thenReturn(Optional.of(user));
        when(repository.save(user)).thenReturn(user);
        assertNotNull(service.update(user));
    }

    @Test
    public void mustReturnObjectNotFoundException_WhenCallUpdate() {
        User user = UserFactory.getUserWithId();
        when(repository.findById(anyString())).thenThrow(ObjectNotFoundException.class);
        assertThrows(ObjectNotFoundException.class, () -> service.update(user));
    }
}