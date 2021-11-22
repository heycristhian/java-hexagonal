package com.seucontrolefinanceiro.controller;

import com.seucontrolefinanceiro.domain.dto.request.UserRequest;
import com.seucontrolefinanceiro.domain.dto.response.UserResponse;
import com.seucontrolefinanceiro.domain.model.Bill;
import com.seucontrolefinanceiro.domain.model.User;
import com.seucontrolefinanceiro.exception.ObjectNotFoundException;
import com.seucontrolefinanceiro.service.BillService;
import com.seucontrolefinanceiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("scf-service/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private BillService billService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> find() {
        List<User> users = service.findAll();
        return ResponseEntity.ok().body(UserResponse.converter((users)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable String id) {
        User user = service.findById(id);
        return ResponseEntity.ok().body(new UserResponse(user));
    }

    @PostMapping
    public ResponseEntity<UserResponse> insert(@RequestBody @Valid UserRequest request, UriComponentsBuilder uriBuilder) {
        User user = request.converter();
        user = service.save(user);
        URI uri = uriBuilder.path("scf-service/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserResponse(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/resetAccount/{id}")
    public ResponseEntity<String> resetAccount(@PathVariable String id) {
        try {
            User user = service.findById(id);
            billService.deleteAll(user.getBills());
            return ResponseEntity.ok("Account reset successfully!");
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<UserResponse> update(@RequestBody @Valid UserRequest request) {
        User user = request.converter();
        service.update(user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{user}/bills")
    public ResponseEntity<List<Bill>> findBills(@PathVariable String user) {
        User userFound = service.findByEmail(user)
                .orElseThrow(() -> new ObjectNotFoundException("User not found referring to the email sent"));
        return ResponseEntity.ok().body(userFound.getBills());
    }
}
