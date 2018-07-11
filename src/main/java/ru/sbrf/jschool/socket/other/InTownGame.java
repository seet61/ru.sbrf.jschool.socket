package ru.sbrf.jschool.socket.other;

import ru.sbrf.jschool.socket.tcp.MulticastResultListener;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class InTownGame {
    public static final int USER_ATTEMPT = 3;

    final Map<String, Set<String>> cities = new HashMap<>();
    final LinkedList<String> myCities = new LinkedList<>();
    final LinkedList<String> userCities = new LinkedList<>();
    int userFails = 0;
    int stepCount = 0;
    final List<StepListener> listeners = new ArrayList<>();

    public InTownGame() {
        final URL citiesFileUrl = getClass().getResource("/cities.txt");
        List<String> cityList;
        try {
            cityList = Files.readAllLines(Paths.get(citiesFileUrl.toURI()));
        } catch (Exception e) {
            throw new InTownGameException("Ошибка иницаиализации");
        }

        cityList.stream().forEach(s -> {
            final String firstChar = s.substring(0,1).toUpperCase();
            Set<String> sameBeginCitySet = cities.get(firstChar);

            if(sameBeginCitySet==null){
                sameBeginCitySet = new HashSet<>();
                cities.put(firstChar,sameBeginCitySet);
            }
            sameBeginCitySet.add(s);

        });
    }

    public String step(String cityName){
        if(cityName==null) return "Назовите город";

        if(!checkUserAnswer(cityName)){
            return failAnswer();
        }

        userCities.add(cityName.toLowerCase());

        String city = getCity(lastChar(cityName));
        if(city==null){
            throw new InTownGameException("Вы выйграли");
        }
        myCities.add(city);
        stepCount++;
        fireListener();
        return String.format("%s вам на %s", city, lastChar(city).toUpperCase());
    }

    private String getCity(String firstChar){
        Set<String> availableCities = cities.get(firstChar.toUpperCase());
        return availableCities.stream()
                .filter(city -> !myCities.contains(city))
                .findFirst()
                .orElse(null);
    }

    private boolean checkUserAnswer(String cityName){
        if(!myCities.isEmpty() &&
                !lastChar(myCities.getLast()).equalsIgnoreCase(firstChar(cityName))){
              return false;
        }
        return !userCities.contains(cityName.toLowerCase());
    }

    private String lastChar(String s){
        String ch = s.substring(s.length()-1);
        if("ьъы".contains(ch)){
            return lastChar(s.substring(0,s.length()-1));
        }
        return ch;
    }

    private String failAnswer(){
        if(USER_ATTEMPT>userFails){
            userFails++;
            return String.format("Неверно, осталось %d попыток", USER_ATTEMPT-userFails);
        }else {
            throw new InTownGameException("Вы проиграли");
        }
    }

    private void fireListener(){
        for(StepListener listener:listeners){
            listener.stepPerformed(stepCount,userCities.getLast(), myCities.getLast());
        }
    }

    private String firstChar(String s){
        return s.substring(0,1);
    }


    public static void main(String[] args) throws IOException, URISyntaxException {
        new InTownGame();
    }

    public void addListener(StepListener listener) {
        listeners.add(listener);
    }
}
