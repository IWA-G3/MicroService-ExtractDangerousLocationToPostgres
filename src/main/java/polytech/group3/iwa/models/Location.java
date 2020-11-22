package polytech.group3.iwa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name="locations")
@Access(AccessType.FIELD)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_location;

    private double latitude;

    private double longitude;

    private LocalDateTime location_date;

    @ManyToMany(mappedBy = "locations")
    @JsonIgnore
    private List<User> users;

    public Location(double latitude, double longitude, LocalDateTime location_date, List<User> users) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.location_date = location_date;
        this.users = users;
    }

    public Location() {

    }

    public int getId_location() {
        return id_location;
    }

    public void setId_location(int id_location) {
        this.id_location = id_location;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getLocation_date() {
        return location_date;
    }

    public void setLocating_date(LocalDateTime locating_date) {
        this.location_date = locating_date;
    }

    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }

}
