package com.seucontrolefinanceiro.adapters.inbound.dto.response;

import com.seucontrolefinanceiro.application.entities.Bill;
import com.seucontrolefinanceiro.application.entities.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserResponse {

        private final String id;
        private final String fullName;
        private final String email;
        private final String cpf;
        private List<Bill> bills = new ArrayList<>();

        public UserResponse(User user) {
            this.id = user.getId();
            this.fullName = user.getFullName();
            this.email = user.getEmail();
            this.cpf = user.getCpf();
            this.bills = user.getBills();
        }

        public static List<UserResponse> converter(List<User> users) {
            return users.stream().map(UserResponse::new).collect(Collectors.toList());
        }
}
