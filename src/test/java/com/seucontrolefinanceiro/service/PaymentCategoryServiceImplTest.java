package com.seucontrolefinanceiro.service;

import com.seucontrolefinanceiro.application.entities.PaymentCategory;
import com.seucontrolefinanceiro.application.ports.repositories.PaymentCategoryRepository;
import com.seucontrolefinanceiro.application.services.PaymentCategoryServiceImpl;
import com.seucontrolefinanceiro.application.exception.ObjectNotFoundException;
import com.seucontrolefinanceiro.feature.PaymentCategoryFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PaymentCategoryServiceImplTest {

    @InjectMocks
    private PaymentCategoryServiceImpl service;

    @Mock
    private PaymentCategoryRepository repository;

    @Test
    public void mustReturnAllCategories_WhenCallFindAll() {
        List<PaymentCategory> paymentCategory = PaymentCategoryFactory.getAllCategories();
        when(repository.findAll()).thenReturn(paymentCategory);
        assertFalse(service.findAll().isEmpty());
    }

    @Test
    public void mustReturnCategory_WhenCallFindById() {
        PaymentCategory paymentCategory = PaymentCategoryFactory.getCategory();
        when(repository.findById(anyString())).thenReturn(Optional.of(paymentCategory));
        assertNotNull(service.findById(anyString()));
    }

    @Test
    public void mustReturnObjectNotFoundException_WhenCallFindById() {
        when(repository.findById(anyString())).thenThrow(ObjectNotFoundException.class);
        assertThrows(ObjectNotFoundException.class, () -> service.findById(anyString()));
    }

    @Test
    public void mustReturnCategory_WhenCallInsert() {
        PaymentCategory paymentCategory = PaymentCategoryFactory.getCategoryWithId();
        when(repository.insert(paymentCategory)).thenReturn(paymentCategory);
        assertNotNull(service.insert(paymentCategory));
    }

    @Test
    public void mustDeleteCategory_WhenCallDelete() {
        PaymentCategory paymentCategory = PaymentCategoryFactory.getCategoryWithId();
        when(repository.findById(paymentCategory.getId())).thenReturn(Optional.of(paymentCategory));
        service.delete(paymentCategory.getId());
        verify(repository, times(1)).findById(paymentCategory.getId());
    }

    @Test
    public void mustReturnObjectNotFoundException_WhenCallDelete() {
        PaymentCategory paymentCategory = PaymentCategoryFactory.getCategoryWithId();
        when(repository.findById(anyString())).thenThrow(ObjectNotFoundException.class);
        assertThrows(ObjectNotFoundException.class, () -> service.delete(paymentCategory.getId()));
    }

    @Test
    public void mustReturnCategory_WhenCallUpdate() {
        PaymentCategory paymentCategory = PaymentCategoryFactory.getCategoryWithId();
        when(repository.findById(anyString())).thenReturn(Optional.of(paymentCategory));
        when(service.update(paymentCategory)).thenReturn(paymentCategory);
        assertNotNull(service.update(paymentCategory));
    }

    @Test
    public void mustReturnObjectNotFoundException_WhenCallUpdate() {
        PaymentCategory paymentCategory = PaymentCategoryFactory.getCategoryWithId();
        when(repository.findById(anyString())).thenThrow(ObjectNotFoundException.class);
        assertThrows(ObjectNotFoundException.class, () -> service.update(paymentCategory));
    }

    @Test
    public void mustReturnCategory_WhenCallFindByDescription() {
        var description = "ACADEMIA";
        List<PaymentCategory> paymentCategory = PaymentCategoryFactory.getCategoryContainingDescription();
        when(repository.findByDescriptionContainingIgnoreCase(description)).thenReturn(paymentCategory);
        assertFalse(service.findByDescriptionContainingIgnoreCase(description).isEmpty());
    }

}