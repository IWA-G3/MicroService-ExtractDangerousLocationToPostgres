package polytech.group3.iwa.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity(name="users")
@Access(AccessType.FIELD)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_keycloak;

    @ManyToMany
    @JoinTable(name="user_locations",
            joinColumns = @JoinColumn(name="id_keycloak"),
            inverseJoinColumns = @JoinColumn(name="id_location"))
    @JsonIgnore
    private List<Location> locations;


    public int getId_keycloak() {
        return id_keycloak;
    }

    public void setId_keycloak(int id_keycloak) {
        this.id_keycloak = id_keycloak;
    }

    public List<Location> getLocations() {
        return locations;
    }
    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

}
