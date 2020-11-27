package polytech.group3.iwa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import polytech.group3.iwa.models.Location;
import polytech.group3.iwa.kafka_location_model.LocationKafka;
import polytech.group3.iwa.models.User;
import polytech.group3.iwa.models.UserLocation;
import polytech.group3.iwa.models.UserLocationId;
import polytech.group3.iwa.repositories.LocationRepository;
import polytech.group3.iwa.repositories.UserLocationRepository;
import polytech.group3.iwa.repositories.UserRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Component
@EnableKafka
class KafkaReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaReceiver.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    UserLocationRepository userLocationRepository;

    public KafkaReceiver() {
        super();
    }


    @KafkaListener(topics = "dangerous_location")
    public void receive(@Payload LocationKafka location) {
        System.out.println("localisation dangereuse reçue");
        LOGGER.info("received dangerous location='{}'", location.toString());

        User user = userRepository.getOne(location.getUserid());
        System.out.println(location.getLocation_date());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime localDate =  LocalDateTime.parse(location.getLocation_date(),dateTimeFormatter);
        List userList = new ArrayList<User>();
        userList.add(user);
        Location postgresLocation = new Location(location.getLatitude(),location.getLongitude(), localDate, userList);
        postgresLocation = locationRepository.saveAndFlush(postgresLocation);

        userLocationRepository.saveAndFlush(new UserLocation(new UserLocationId(user.getId_keycloak(), postgresLocation.getId_location())));
        LOGGER.info("dangerous location post to postgres");
    }
}
