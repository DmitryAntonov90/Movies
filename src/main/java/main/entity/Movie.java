package main.entity;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;


public class Movie implements Serializable {

    private Long id;
    private String title;
    private String director;
    private Long year;

    @JsonCreator
    public Movie() {

    }

    @JsonCreator
    public Movie(@JsonProperty("id") Long id,
                 @JsonProperty("title") String title,
                 @JsonProperty("director") String director,
                 @JsonProperty("year") Long year) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }
}
