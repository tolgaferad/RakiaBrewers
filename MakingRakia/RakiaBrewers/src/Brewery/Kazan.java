package Brewery;

import Enums.TypeFruit;
import util.Randomizator;

import java.util.concurrent.atomic.AtomicInteger;

public class Kazan {
    private AtomicInteger kilogram=new AtomicInteger();
    private String name="Казан_";
    private static int suffix=1;
    private TypeFruit fruitToBrew;
    private volatile boolean flagUsing;
    private static final int maxKilogram=10;
    private Brewery brewery;
    public Kazan(Brewery brewery){
        name+=suffix++;
        kilogram.set(0);
        this.flagUsing=false;
        this.brewery=brewery;
    }
    public void setFruitToBrew(TypeFruit fruitToBrew){
        if (this.fruitToBrew==null) {
            this.fruitToBrew = fruitToBrew;
        }
    }
    public synchronized void addFruit(){
      if(kilogram.get()<maxKilogram) {
              try {
                  kilogram.getAndIncrement();
                  Thread.sleep(200);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          System.out.println(Thread.currentThread().getName() + " напълни " + this.name + " с " + fruitToBrew + ",сега в " + this.name + " има " + kilogram + "kg " + fruitToBrew);

          if (this.kilogram.get() == 10) {
              brewery.addFullKazan(this);
          }
        }
    }
    public synchronized void brew(){
        this.flagUsing=true;
        System.out.println(Thread.currentThread().getName()+" вари "+this.name+" пълен с "+fruitToBrew);
        int brewedLitres= Randomizator.randomFromTo(1,10);
        brewery.addProducedRakiq(brewedLitres);
        if (brewery.getProducedRakiq().get()>=10){
            //one brewer see that produced liters are enough, than noticifate other brewers to see that
            //in the brewery produced liters are enough than they go home too..
           brewery.notificateBrewersToGoHome();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.name+" е изварен полученото количество е: "+brewedLitres);
        this.flagUsing=false;
        kilogram.set(0);
        fruitToBrew=null;
        notifyAll();
    }
    public boolean isFlagUsing() {
        return flagUsing;
    }
    public TypeFruit getTypeFruit() {
        return fruitToBrew;
    }
}
