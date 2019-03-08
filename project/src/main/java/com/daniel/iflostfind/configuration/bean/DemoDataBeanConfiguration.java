package com.daniel.iflostfind.configuration.bean;

import com.daniel.iflostfind.domain.Coordinate;
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

            //FIT
            Loss l1 = Loss.builder()
                    .name("FIT Iphone 3")
                    .description("Silver Iphone 3, Red case and spider-man as a wallpaper")
                    .type(LossType.GADGET)
                    .lossDate(LocalDate.now())
                    .coordinate(Coordinate.builder().latitude(50.45561968381101).longitude(30.480964645291124).build())
                    .build();

            Loss l2 = Loss.builder()
                    .name("FIT Iphone 6")
                    .description("Silver Iphone 6, Red case and spider-man as a wallpaper")
                    .type(LossType.GADGET)
                    .lossDate(LocalDate.now())
                    .coordinate(Coordinate.builder().latitude(50.45622762015983).longitude(30.475020870114122).build())
                    .build();

            Loss l3 = Loss.builder()
                    .name("FIT Iphone 7")
                    .description("Iphone 7, Red case and spider-man as a wallpaper")
                    .type(LossType.GADGET)
                    .lossDate(LocalDate.now())
                    .coordinate(Coordinate.builder().latitude(50.45522349636771).longitude(30.46928094282225).build())
                    .build();

            Loss l4 = Loss.builder()
                    .name("FIT Iphone 8")
                    .description("Silver Iphone 8, Red case and spider-man as a wallpaper")
                    .type(LossType.GADGET)
                    .lossDate(LocalDate.now())
                    .coordinate(Coordinate.builder().latitude(50.45471118009918).longitude(30.46505378141478).build())
                    .build();

            Loss l5 = Loss.builder()
                    .name("FIT Iphone X")
                    .description("Silver Iphone X, Red case and spider-man as a wallpaper")
                    .type(LossType.GADGET)
                    .lossDate(LocalDate.now())
                    .coordinate(Coordinate.builder().latitude(50.45424667522006).longitude(30.471405252362047).build())
                    .build();

            //IRPIN
            Loss l6 = Loss.builder()
                    .name("IRPIN Iphone 3")
                    .description("Silver Iphone 3, Red case and spider-man as a wallpaper")
                    .type(LossType.GADGET)
                    .lossDate(LocalDate.now())
                    .coordinate(Coordinate.builder().latitude(50.5183158271568).longitude(30.246850090707426).build())
                    .build();

            Loss l7 = Loss.builder()
                    .name("IRPIN  Iphone 6")
                    .description("Silver Iphone 6, Red case and spider-man as a wallpaper")
                    .type(LossType.GADGET)
                    .lossDate(LocalDate.now())
                    .coordinate(Coordinate.builder().latitude(50.51590087219747).longitude(30.252858238900785).build())
                    .build();

            Loss l8 = Loss.builder()
                    .name("IRPIN Iphone 7")
                    .description("Iphone 7, Red case and spider-man as a wallpaper")
                    .type(LossType.GADGET)
                    .lossDate(LocalDate.now())
                    .coordinate(Coordinate.builder().latitude(50.51767457997845 ).longitude(30.251613693917875).build())
                    .build();

            lossRepository.saveAll(Arrays.asList(l1, l2, l3, l4, l5));
            lossRepository.saveAll(Arrays.asList(l6, l7, l8));
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
