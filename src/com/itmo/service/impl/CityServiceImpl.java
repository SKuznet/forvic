package com.itmo.service.impl;

import com.itmo.info.exc.MusicException;
import com.itmo.info.exc.SingException;
import com.itmo.model.City;
import com.itmo.model.Musician;
import com.itmo.model.NewYork;
import com.itmo.service.CityService;

public class CityServiceImpl implements CityService {
    @Override
    public void acceptVisitors(City city) {
        System.out.println("welcome to our city " + city.getName() + " with concert!");
    }

    // если музыкант - Егор Крид - тогда доступ в наш город ему запрещен
    public City getCity(Musician infoAboutVisitor) throws MusicException {
        if (infoAboutVisitor.getName().equalsIgnoreCase("Egor Kreed")) {
            throw new MusicException("no party with Egor Kreed :'(");
        } else if (infoAboutVisitor.getName().equalsIgnoreCase("Timati")) {
            throw new SingException("He can't sing... sorry...");
        } else if (infoAboutVisitor.getName().equalsIgnoreCase("Agutin")) {
            return new NewYork();
        } else if (infoAboutVisitor.getName().equalsIgnoreCase("Kirkorov")) {
            return new SpecificCity();
        } else if (infoAboutVisitor.getName().equals("Kurt Cobain")) {
            return new LocalCity();
        } else if (infoAboutVisitor.getName().equals("Jony")) {
            return new PrimalCity();
        } else {
            return new City() {
                @Override
                public String getName() {
                    return "Anonymous city";
                }
            };
        }

    }


    static class PrimalCity extends City {
        @Override
        public String getName() {
            return "Atlantis";
        }
    }


    class SpecificCity extends City {

        @Override
        public String getName() {
            return "SpecificCity";
        }
    }
}


class LocalCity extends City {

    @Override
    public String getName() {
        return "CityInOurDreams";
    }
}
