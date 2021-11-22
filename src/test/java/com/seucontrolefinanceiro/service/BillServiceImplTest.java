package com.seucontrolefinanceiro.service;

import com.seucontrolefinanceiro.application.entities.Bill;
import com.seucontrolefinanceiro.application.entities.User;
import com.seucontrolefinanceiro.application.ports.repositories.BillRepository;
import com.seucontrolefinanceiro.application.ports.repositories.UserRepository;
import com.seucontrolefinanceiro.application.services.BillServiceImpl;
import com.seucontrolefinanceiro.application.services.UserServiceImpl;
import com.seucontrolefinanceiro.feature.BillFactory;
import com.seucontrolefinanceiro.feature.UserFactory;
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
class BillServiceImplTest {

    @InjectMocks
    private BillServiceImpl service;

    @Mock
    private BillRepository repository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserServiceImpl userServiceImpl;

    @Test
    public void mustReturnAllBills_WhenCallFindAll() {
        List<Bill> bills = BillFactory.getBills();
        when(repository.findAll()).thenReturn(bills);
        assertFalse(service.findAll().isEmpty());
    }

    @Test
    public void mustReturnBill_WhenCallFindById() {
        Bill bill = BillFactory.getBill();
        when(repository.findById(anyString())).thenReturn(Optional.of(bill));
        assertNotNull(service.findById(anyString()));
    }

    @Test
    public void mustReturnBill_WhenCallSave() {
        Bill bill = BillFactory.getBillWithId();
        User user = UserFactory.getUserWithId();
        when(userServiceImpl.findById(anyString())).thenReturn(user);
        when(repository.findByUserId(anyString())).thenReturn(List.of(bill));
        assertNotNull(service.save(bill));
    }

    @Test
    public void mustReturnBillIsNotEveryMonth_WhenCallSave() {
        Bill bill = BillFactory.getBillIsNotEveryMonth();
        User user = UserFactory.getUserWithId();
        when(userServiceImpl.findById(anyString())).thenReturn(user);
        when(repository.findByUserId(anyString())).thenReturn(List.of(bill));
        assertNotNull(service.save(bill));
    }

    @Test
    public void mustDeleteBill_WhenCallDelete() {
        Bill bill = BillFactory.getBillWithId();
        when(repository.findById(anyString())).thenReturn(Optional.of(bill));
        service.delete(bill.getId());
        verify(repository, times(1)).deleteById(anyString());
    }

    @Test
    public void mustUpdateBill_WhenCallUpdate() {
        var expectedDescription = "Descricao nova";
        Bill newBill = BillFactory.getBillWithId();
        Bill oldBill = BillFactory.getBillWithId();
        List<Bill> bills = BillFactory.getChildrenBills();
        newBill.setParent(null);
        newBill.setBillDescription(expectedDescription);
        when(repository.findById(anyString())).thenReturn(Optional.of(oldBill));
        when(repository.findByUserId(anyString())).thenReturn(bills);
        var resultDescription = service.update(newBill).getBillDescription();
        assertEquals(expectedDescription, resultDescription);
    }

    @Test
    public void mustUpdateBillAndCreateChildren_WhenCallUpdate() {
        var installments = 10;
        Bill newBill = BillFactory.getBillIsEveryMonth();
        Bill oldBill = BillFactory.getBillIsNotEveryMonth();
        User user = UserFactory.getUserWithId();
        newBill.setPortion(installments);

        when(repository.findById(anyString())).thenReturn(Optional.of(oldBill));
        when(repository.findByUserId(anyString())).thenReturn(List.of(newBill));
        when(userServiceImpl.findById(anyString())).thenReturn(user);
        service.update(newBill);
        verify(repository, times(installments - 1)).insert((Bill) any());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void mustUpdateBillAndRemoveChildren_WhenCallUpdate() {
        var installments = 2;
        Bill newBill = BillFactory.getBillIsNotEveryMonth();
        Bill oldBill = BillFactory.getBillIsEveryMonth();
        List<Bill> bills = BillFactory.getChildrenBills();
        newBill.setPortion(installments);
        newBill.setParent(null);

        when(repository.findById(anyString())).thenReturn(Optional.of(oldBill));
        when(repository.findByUserId(anyString())).thenReturn(bills);
        service.update(newBill);
        verify(repository, times(installments)).delete(any());
    }

    @Test
    public void mustPayBill_WhenCallUpdate() {
        Bill bill = BillFactory.getBillWithId();
        bill.setPaid(true);
        service.update(bill);
        verify(repository, times(1)).save(any());
    }

    @Test
    public void mustThrowException_WhenCallUpdate() {
        Bill bill = BillFactory.getBillWithId();

        when(repository.findById(anyString())).thenReturn(Optional.of(bill));
        when(repository.findByUserId(anyString())).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> service.update(bill));
    }

    @Test
    public void mustDeleteAll_WhenCallDeleteAll() {
        Bill bill = BillFactory.getBillWithId();
        service.deleteAll(List.of(bill));
        verify(repository, times(1)).deleteAll(List.of(bill));

    }
}