package Persons;

import Brewery.Brewery;
import Brewery.Kazan;
import Enums.TypeFruit;

public class Brewer extends Person implements Runnable{

    public Brewer(String name, int age, TypeFruit fruitToBrew, Brewery brewery) {
        super(name, age, fruitToBrew,brewery);
    }

    public void brewKazan(){
       Kazan kazan=brewery.getCompleteKazan(this.typeFruit);
       if (kazan==null){
           return;
       }
       kazan.brew();
    }
    @Override
    public void run() {
       while(true){
                brewKazan();
           if (brewery.getProducedRakiq().get()>=10){
               break;
           }
        }
    }
}
