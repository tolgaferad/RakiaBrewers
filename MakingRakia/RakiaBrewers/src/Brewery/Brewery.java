package Brewery;

import Enums.TypeFruit;
import Exceptions.NoFreeKazanException;
import Exceptions.NoFullKazansException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class Brewery {
    private ArrayList<Kazan> kazans;
    private HashMap<TypeFruit, ArrayList<Kazan>> fullKazans = new HashMap<>();
    private AtomicInteger producedRakiq =new AtomicInteger();
    public Brewery() {
        addKazans();
        producedRakiq.set(0);
    }
    public synchronized void addFullKazan(Kazan kazan) {
        if (!fullKazans.containsKey(kazan.getTypeFruit())) {
            fullKazans.put(kazan.getTypeFruit(), new ArrayList<Kazan>());
            fullKazans.get(kazan.getTypeFruit()).add(kazan);
        } else {
            fullKazans.get(kazan.getTypeFruit()).add(kazan);
        }
        notifyAll();
    }

    public void addKazans() {
        kazans = new ArrayList<>();
        kazans.add(new Kazan(this));
        kazans.add(new Kazan(this));
        kazans.add(new Kazan(this));
        kazans.add(new Kazan(this));
        kazans.add(new Kazan(this));
    }
    public synchronized void notificateBrewersToGoHome(){// notificate other brewers to wake up when litres are more than 10
                                                        //
        notifyAll();
    }
    public synchronized Kazan getRandomFreeKazan(TypeFruit typeFruit) {

        List<Kazan> list = kazans.stream().filter(kazan -> !kazan.isFlagUsing())
                .collect(Collectors.toList());
        if (list.size() == 0) {
            try {
                throw new NoFreeKazanException("Няма свободни казани, изчакай да се освободи");
            } catch (NoFreeKazanException e) {
                System.out.println(e.getMessage());
            }
            try {
                wait();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            return null;
        }
        return list.get(new Random().nextInt(list.size()));
    }

    public synchronized Kazan getCompleteKazan(TypeFruit fruit) {

        if (!fullKazans.containsKey(fruit)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        if (fullKazans.get(fruit).size() == 0) {
            try {
                throw new NoFullKazansException("Няма пълни казани от твоя тип, изчакай да се напълнят");
            } catch (NoFullKazansException e) {
                System.out.println(e.getMessage());
            }
            try {
                wait();
            } catch (InterruptedException interruptedException) {
                System.out.println(interruptedException.getMessage());
            }
            return null;
        }
        Kazan kazanToReturn = fullKazans.get(fruit).get(new Random().nextInt(fullKazans.get(fruit).size()));
        fullKazans.get(fruit).remove(kazanToReturn);
        return kazanToReturn;
    }

    public AtomicInteger getProducedRakiq() {
        return producedRakiq;
    }
    public void addProducedRakiq(int litres){
        producedRakiq.addAndGet(litres);
    }
}

