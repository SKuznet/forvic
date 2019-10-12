package com.itmo.model;

import com.itmo.info.Instruments;
import com.itmo.service.PlayMusicBehavior;
import com.itmo.service.SingBehavior;

import java.util.Comparator;
import java.util.Objects;


/**
 * Brass - it is musician instrument
 *
 * @author Viktoria
 */
public class Brass extends Musician implements PlayMusicBehavior, SingBehavior, Comparable<Brass> {
    private String name;

    public Brass(String name) {
        this.name = name;
    }

    public Brass() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Brass brass = (Brass) o;
        return Objects.equals(name, brass.name);
    }

    @Override
    public String toString() {
        return "'" + name + "'";
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Brass o) {
        return name.compareTo(o.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    void getInfo() {
        System.out.println("I am " + name);
    }

    @Override
    public void playMusic() {
        System.out.println("I play music with " + Instruments.Brass.toString());
    }

    @Override
    public void singSong() {
        System.out.println("I sing song as Gone.FLUUD");
    }
}
