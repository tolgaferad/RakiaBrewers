package Persons;

import Brewery.Brewery;
import Brewery.Kazan;
import Enums.TypeFruit;

public class Picker extends Person implements Runnable{

    public Picker(String name, int age, TypeFruit fruitToPick, Brewery brewery) {
        super(name, age, fruitToPick,brewery);
    }
    public void fillingKazan(){
        Kazan kazan=brewery.getRandomFreeKazan(this.typeFruit);
        if (kazan==null){
            return;
        }
        kazan.setFruitToBrew(this.typeFruit);
        if (kazan.getTypeFruit()!=this.typeFruit){
            return;
        }
        kazan.addFruit();
    }
    @Override
    public void run() {
        while (true){
                fillingKazan();
                if (brewery.getProducedRakiq().get()>=10){
                    break;
                }
        }

    }
}
