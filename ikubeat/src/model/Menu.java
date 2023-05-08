package model;

import java.sql.Time;

public class Menu {

    private Integer id;
    private Integer restaurantId;
    private String name;
    private String description;
    private Time activeFrom;
    private Time activeUntil;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Time getActiveFrom() {
        return activeFrom;
    }

    public void setActiveFrom(Time activeFrom) {
        this.activeFrom = activeFrom;
    }

    public Time getActiveUntil() {
        return activeUntil;
    }

    public void setActiveUntil(Time activeUntil) {
        this.activeUntil = activeUntil;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", restaurantId=" + restaurantId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", activeFrom=" + activeFrom +
                ", activeUntil=" + activeUntil +
                '}';
    }
}
