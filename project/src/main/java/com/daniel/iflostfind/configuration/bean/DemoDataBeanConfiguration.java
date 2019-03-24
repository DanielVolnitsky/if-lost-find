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

            Loss l9 = Loss.builder()
                    .name("Park druzhbi")
                    .description("Iphone 7, Red case and spider-man as a wallpaper")
                    .type(LossType.GADGET)
                    .lossDate(LocalDate.now())
                    .coordinate(Coordinate.builder().latitude(50.52432833288968).longitude(30.238248476525428).build())
                    .build();

            Loss l10 = Loss.builder()
                    .name("Park Pravika")
                    .description("Iphone 7, Red case and spider-man as a wallpaper")
                    .type(LossType.GADGET)
                    .lossDate(LocalDate.now())
                    .coordinate(Coordinate.builder().latitude(50.526327206757394).longitude(30.238197159577226).build())
                    .build();

            Loss l11 = Loss.builder()
                    .name("EKO")
                    .description("Iphone 7, Red case and spider-man as a wallpaper")
                    .type(LossType.GADGET)
                    .lossDate(LocalDate.now())
                    .coordinate(Coordinate.builder().latitude(50.518481247806264).longitude(30.238570579531824).build())
                    .build();

            Loss l12 = Loss.builder()
                    .name("Tonus")
                    .description("Iphone 7, Red case and spider-man as a wallpaper")
                    .type(LossType.GADGET)
                    .lossDate(LocalDate.now())
                    .coordinate(Coordinate.builder().latitude(50.52173174720019).longitude(30.23883194276118).build())
                    .build();

            lossRepository.saveAll(Arrays.asList(l1, l2, l3, l4, l5));
            lossRepository.saveAll(Arrays.asList(l6, l7, l8, l9, l10, l11, l12));
        };
    }

    @Bean
    public CommandLineRunner persistDemoData() {
        return args -> {

            User dan = new User();
            dan.setName("d");
            dan.setLastName("v");
            dan.setEmail("d@d");
            dan.setPassword("d");
            dan.setDefaultLocation("Kvitkova Street, Irpin', Киевская область, Украина");

            User bos = new User();
            bos.setName("k");
            bos.setLastName("a");
            bos.setEmail("b@b");
            bos.setPassword("b");
            bos.setDefaultLocation("Bohdana Havrylyshyna Street, Киев, Украина");

            userRepository.saveAll(Arrays.asList(dan, bos));
        };
    }
}
