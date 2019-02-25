package com.daniel.iflostfind.configuration.bean;

import com.daniel.iflostfind.domain.Location;
import com.daniel.iflostfind.domain.Loss;
import com.daniel.iflostfind.domain.LossType;
import com.daniel.iflostfind.domain.User;
import com.daniel.iflostfind.repository.MissingItemRepository;
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
    private final MissingItemRepository itemRepository;


    @Autowired
    public DemoDataBeanConfiguration(UserRepository userRepository, MissingItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    @Bean
    public CommandLineRunner persistDemoData(){
        return args -> {

            //Missing items
            Loss i1 = new Loss();
            i1.setName("Iphone X");
            i1.setDescription("Silver Iphone X, Red case and spider-man as a wallpaper");
            i1.setType(LossType.GADGET);
            i1.setLocation(new Location(50.519196, 30.251947));
            i1.setLossDate(LocalDate.now());

            Loss i2 = new Loss();
            i2.setName("Iphone XS");
            i2.setDescription("Silver Iphone XS, Red case and spider-man as a wallpaper");
            i2.setType(LossType.GADGET);
            i2.setLocation(new Location(50.522713, 30.247875));
            i2.setLossDate(LocalDate.now());

            //Users
            User dan = new User();
            dan.setName("d");
            dan.setLastName("v");
            dan.setEmail("test@test.com");
            dan.setPassword("Qwerty1234567");
            dan.setCountry("Ukraine");
            dan.setCity("Irpin");
            dan.addLoss(i1);
            dan.addLoss(i2);

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
