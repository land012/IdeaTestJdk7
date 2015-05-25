package com.umbrella.grammar.serialize.composite;

import com.umbrella.grammar.serialize.composite.Country;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by 大洲 on 14-12-29.
 */
public class Person implements Serializable {
    private long id;
    private String Name;
    private transient Country country;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    private void writeObject(ObjectOutputStream out) {
        try {
            out.defaultWriteObject();
            out.writeUTF(country.getName());
            out.writeLong(country.getId());
            out.writeUTF(country.getNationalFlag());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readObject(ObjectInputStream in) {
        try {
            in.defaultReadObject();
            String name = in.readUTF();
            long id = in.readLong();
            String nationalFlag = in.readUTF();
            country = new Country();
            country.setId(id);
            country.setName(name);
            country.setNationalFlag(nationalFlag);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
