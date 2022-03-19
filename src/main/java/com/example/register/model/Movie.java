package com.example.register.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table (name ="movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer likes;
    private Integer dislikes;
    private String year;
    private String [] genre;

    public Movie() {}

    public Movie(Integer id, String name, Integer likes, Integer dislikes, String year, String[] genre) {
        this.id = id;
        this.name = name;
        this.likes = likes;
        this.dislikes = dislikes;
        this.year = year;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String[] getGenre() {
        return genre;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) && Objects.equals(name, movie.name) && Objects.equals(likes, movie.likes) && Objects.equals(dislikes, movie.dislikes) && Objects.equals(year, movie.year) && Arrays.equals(genre, movie.genre);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, likes, dislikes, year);
        result = 31 * result + Arrays.hashCode(genre);
        return result;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                ", year='" + year + '\'' +
                ", genre=" + Arrays.toString(genre) +
                '}';
    }
}
