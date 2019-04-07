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
                    .coordinate(new Coordinate(50.45561968381101, 30.480964645291124))
                    .build();

            Loss l2 = Loss.builder()
                    .name("FIT Iphone 6")
                    .description("Silver Iphone 6, Red case and spider-man as a wallpaper")
                    .type(LossType.GADGET)
                    .lossDate(LocalDate.now())
                    .coordinate(new Coordinate(50.45622762015983, 30.475020870114122))
                    .build();

            Loss l3 = Loss.builder()
                    .name("FIT Iphone 7")
                    .description("Iphone 7, Red case and spider-man as a wallpaper")
                    .type(LossType.GADGET)
                    .lossDate(LocalDate.now())
                    .coordinate(new Coordinate(50.45522349636771, 30.46928094282225))
                    .build();

            Loss l4 = Loss.builder()
                    .name("FIT Iphone 8")
                    .description("Silver Iphone 8, Red case and spider-man as a wallpaper")
                    .type(LossType.GADGET)
                    .lossDate(LocalDate.now())
                    .coordinate(new Coordinate(50.45471118009918, 30.46505378141478))
                    .build();

            Loss l5 = Loss.builder()
                    .name("FIT Iphone X")
                    .description("Silver Iphone X, Red case and spider-man as a wallpaper")
                    .type(LossType.GADGET)
                    .lossDate(LocalDate.now())
                    .coordinate(new Coordinate(50.45424667522006, 30.471405252362047))
                    .build();

            //IRPIN
            Loss i_gadget_1 = Loss.builder()
                    .name("Gadget 1")
                    .description("Lorem ipsum dolor sit amet.")
                    .type(LossType.GADGET)
                    .lossDate(LocalDate.now())
                    .coordinate(new Coordinate(50.5186818939805, 30.251842895770324))
                    .build();

            Loss i_gadget_2 = Loss.builder()
                    .name("Gadget 2")
                    .description("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eum, facilis?")
                    .type(LossType.GADGET)
                    .lossDate(LocalDate.now())
                    .coordinate(new Coordinate(50.51860685544913, 30.252862135195983))
                    .build();

            Loss i_gadget_3 = Loss.builder()
                    .name("Gadget 3")
                    .description("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Asperiores atque consectetur eligendi eos harum laborum nemo nobis possimus reprehenderit rerum.")
                    .type(LossType.GADGET)
                    .lossDate(LocalDate.now())
                    .coordinate(new Coordinate(50.51796561222457, 30.253366390490783))
                    .build();

            Loss i_animal_1 = Loss.builder()
                    .name("Animal 1")
                    .description("Lorem ipsum dolor sit amet.")
                    .type(LossType.ANIMAL)
                    .lossDate(LocalDate.now())
                    .coordinate(new Coordinate(50.518279413188885, 30.25017992618109))
                    .build();

            Loss i_animal_2 = Loss.builder()
                    .name("Animal 2")
                    .description("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eum, facilis?")
                    .type(LossType.ANIMAL)
                    .lossDate(LocalDate.now())
                    .coordinate(new Coordinate(50.51881832736828, 30.25145665767218))
                    .build();

            Loss i_animal_3 = Loss.builder()
                    .name("Animal 3")
                    .description("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Asperiores atque consectetur eligendi eos harum laborum nemo nobis possimus reprehenderit rerum.")
                    .type(LossType.ANIMAL)
                    .lossDate(LocalDate.now())
                    .coordinate(new Coordinate(50.51929584111981, 30.250362316394103))
                    .build();

            Loss i_doc_1 = Loss.builder()
                    .name("Doc 1")
                    .description("Lorem ipsum dolor sit amet.")
                    .type(LossType.DOCUMENT)
                    .lossDate(LocalDate.now())
                    .coordinate(new Coordinate(50.51716745689227, 30.250137010836852))
                    .build();

            Loss i_doc_2 = Loss.builder()
                    .name("Doc 2")
                    .description("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eum, facilis?")
                    .type(LossType.DOCUMENT)
                    .lossDate(LocalDate.now())
                    .coordinate(new Coordinate(50.51627377977825, 30.249858061099303))
                    .build();

            Loss i_doc_3 = Loss.builder()
                    .name("Doc 3")
                    .description("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Asperiores atque consectetur eligendi eos harum laborum nemo nobis possimus reprehenderit rerum.")
                    .type(LossType.DOCUMENT)
                    .lossDate(LocalDate.now())
                    .coordinate(new Coordinate(50.51681953880584, 30.251081148410094))
                    .build();

            Loss i_bag_1 = Loss.builder()
                    .name("Bag 1")
                    .description("Lorem ipsum dolor sit amet.")
                    .type(LossType.BAG)
                    .lossDate(LocalDate.now())
                    .coordinate(new Coordinate(50.519186695545685, 30.248989025378478))
                    .build();

            Loss i_bag_2 = Loss.builder()
                    .name("Bag 2")
                    .description("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eum, facilis?")
                    .type(LossType.BAG)
                    .lossDate(LocalDate.now())
                    .coordinate(new Coordinate(50.51850452998712, 30.24919287326361))
                    .build();

            Loss i_bag_3 = Loss.builder()
                    .name("Bag 3")
                    .description("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Asperiores atque consectetur eligendi eos harum laborum nemo nobis possimus reprehenderit rerum.")
                    .type(LossType.BAG)
                    .lossDate(LocalDate.now())
                    .coordinate(new Coordinate(50.519691491711754, 30.2499117052796))
                    .build();


            lossRepository.saveAll(Arrays.asList(l1, l2, l3, l4, l5));
            lossRepository.saveAll(Arrays.asList(i_animal_1, i_animal_2, i_animal_3));
            lossRepository.saveAll(Arrays.asList(i_doc_1, i_doc_2, i_doc_3));
            lossRepository.saveAll(Arrays.asList(i_bag_1, i_bag_2, i_bag_3));
            lossRepository.saveAll(Arrays.asList(i_gadget_1, i_gadget_2, i_gadget_3));
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
