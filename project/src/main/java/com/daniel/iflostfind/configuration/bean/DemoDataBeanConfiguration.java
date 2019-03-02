package com.daniel.iflostfind.configuration.bean;

import com.daniel.iflostfind.domain.Location;
import com.daniel.iflostfind.domain.Loss;
import com.daniel.iflostfind.domain.LossType;
import com.daniel.iflostfind.domain.User;
import com.daniel.iflostfind.repository.LossRepository;
import com.daniel.iflostfind.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class DemoDataBeanConfiguration {

    private final UserRepository userRepository;
    private final LossRepository lossRepository;

    @Autowired
    public DemoDataBeanConfiguration(UserRepository userRepository, LossRepository lossRepository) {
        this.userRepository = userRepository;
        this.lossRepository = lossRepository;
    }

    @Bean
    public CommandLineRunner persistDemoLosses() {
        return args -> {

            Loss l1 = Loss.builder()
                    .name("Iphone 3")
                    .description("Silver Iphone 3, Red case and spider-man as a wallpaper")
                    .type(LossType.GADGET)
                    .lossDate(LocalDate.now())
                    .location(Location.builder().latitude(50.518063999999995).longitude(30.251570600000036).build())
                    .build();

            Loss l2 = Loss.builder()
                    .name("Iphone 6")
                    .description("Silver Iphone 6, Red case and spider-man as a wallpaper")
                    .type(LossType.GADGET)
                    .lossDate(LocalDate.now())
                    .location(Location.builder().latitude(50.5180662).longitude(30.25157230000002).build())
                    .build();

            Loss l3 = Loss.builder()
                    .name("Iphone 7")
                    .description("Iphone 7, Red case and spider-man as a wallpaper")
                    .type(LossType.GADGET)
                    .lossDate(LocalDate.now())
                    .location(Location.builder().latitude(50.518072).longitude(30.25157409999997).build())
                    .build();

            Loss l4 = Loss.builder()
                    .name("Iphone 8")
                    .description("Silver Iphone 8, Red case and spider-man as a wallpaper")
                    .type(LossType.GADGET)
                    .lossDate(LocalDate.now())
                    .location(Location.builder().latitude(50.5180647).longitude(30.251550899999984).build())
                    .build();

            Loss l5 = Loss.builder()
                    .name("Iphone X")
                    .description("Silver Iphone X, Red case and spider-man as a wallpaper")
                    .type(LossType.GADGET)
                    .lossDate(LocalDate.now())
                    .location(Location.builder().latitude(50.5180588).longitude(30.251554599999963).build())
                    .build();

            lossRepository.saveAll(Arrays.asList(l1, l2, l3, l4, l5));
        };
    }

    @Bean
    public CommandLineRunner persistDemoData() {
        return args -> {

            User dan = new User();
            dan.setName("d");
            dan.setLastName("v");
            dan.setEmail("test@test.com");
            dan.setPassword("Qwerty1234567");
            dan.setCountry("Ukraine");
            dan.setCity("Irpin");

            User kir = new User();
            kir.setName("k");
            kir.setLastName("a");
            kir.setEmail("d@m.com");
            kir.setPassword("dddddd");
            kir.setCountry("Ukraine");
            kir.setCity("Irpin");

            userRepository.saveAll(Arrays.asList(dan, kir));
        };
    }
}
