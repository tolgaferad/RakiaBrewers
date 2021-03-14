import Brewery.Brewery;
import Enums.TypeFruit;
import Persons.Brewer;
import Persons.Picker;

public class Demo {
    public static void main(String[] args) {
        Brewery brewery=new Brewery();
        Thread picker1=new Thread(new Picker("Sashko",20, TypeFruit.GRAPE,brewery), "Sashko");
        Thread picker2=new Thread(new Picker("Tolga",21,TypeFruit.APRICOT,brewery),"Tolga");
        Thread picker3=new Thread(new Picker("Vladimir",22,TypeFruit.PLUM,brewery),"Vladimir");
        Thread picker4=new Thread(new Picker("Valentin",32,TypeFruit.GRAPE,brewery),"Valentin");
        Thread picker5=new Thread(new Picker("Presiqna",22,TypeFruit.APRICOT,brewery),"Presiqna");
        Thread picker6=new Thread(new Picker("Esra",18,TypeFruit.PLUM,brewery),"Esra");

        Thread brewer1=new Thread(new Brewer("Krasimir",35,TypeFruit.PLUM,brewery),"Krasimir");
        Thread brewer2=new Thread(new Brewer("Radi,Romanski",65,TypeFruit.APRICOT,brewery),"Radkata");
        Thread brewer3=new Thread(new Brewer("Pesho",44,TypeFruit.GRAPE,brewery),"Peshkata");

        picker1.start();
        picker2.start();
        picker3.start();
        picker4.start();
        picker5.start();
        picker6.start();
        brewer1.start();
        brewer2.start();
        brewer3.start();
        try {
            picker1.join();
            picker2.join();
            picker3.join();
            picker4.join();
            picker5.join();
            picker6.join();
            brewer1.join();
            brewer2.join();
            brewer3.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Килограмите ракия са повече от 10, стига толкова, защото всяка вечер ще съм турбо пиян..");
    }
}
