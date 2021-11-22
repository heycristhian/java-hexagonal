package com.seucontrolefinanceiro.adapters.configuration;

import com.seucontrolefinanceiro.SeuControleFinanceiroApplication;
import com.seucontrolefinanceiro.adapters.outbound.producer.KafkaProducer;
import com.seucontrolefinanceiro.application.ports.repositories.BillRepository;
import com.seucontrolefinanceiro.application.ports.repositories.PaymentCategoryRepository;
import com.seucontrolefinanceiro.application.ports.repositories.UserRepository;
import com.seucontrolefinanceiro.application.ports.services.BillService;
import com.seucontrolefinanceiro.application.ports.services.PaymentCategoryService;
import com.seucontrolefinanceiro.application.ports.services.UserService;
import com.seucontrolefinanceiro.application.services.BillServiceImpl;
import com.seucontrolefinanceiro.application.services.PaymentCategoryServiceImpl;
import com.seucontrolefinanceiro.application.services.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = SeuControleFinanceiroApplication.class)
public class BeanConfiguration {

    @Bean
    UserService userService(UserRepository userRepository, KafkaProducer producer) {
        return new UserServiceImpl(userRepository, producer);
    }

    @Bean
    BillService billService(BillRepository billRepository, UserServiceImpl userService, UserRepository userRepository) {
        return new BillServiceImpl(billRepository, userService, userRepository);
    }

    @Bean
    PaymentCategoryService paymentCategoryService(PaymentCategoryRepository repository) {
        return new PaymentCategoryServiceImpl(repository);
    }
}
