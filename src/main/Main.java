/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import main.model.Klient;
import main.model.Zamowienie;
import main.model.Sklep;
import main.model.Adres;
import main.model.Produkt;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.eclipse.persistence.expressions.ExpressionBuilder;
import org.eclipse.persistence.jpa.JpaQuery;
import org.eclipse.persistence.queries.JPAQueryBuilder;

/**
 *
 * @author Daniel
 */
public class Main {
    public static EntityManager em;

    public static EntityManager getEntityManager() 
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TestyBazyPU");
        EntityManager em = entityManagerFactory.createEntityManager();
        return em;
    }
    public static void main(String[] args) {
        //loadRecords();
        printRecords();
    }
     public static void loadRecords()
    {
        em = getEntityManager();
        em.getTransaction().begin();
        Random random = new Random();
        //KLIENCI
        String[] names = new String[]{"Daniel","Marek","Konrad","Przemek","Rafal","Kamil","Michal","Marek"};
        String[] surnames = new String[]{"Kowalski","Macierewicz","Palikot","Miller","Schetyna","Stoch","Zyla","Kot"};
        
        //ADRESY
        String[] miasta = new String[]{"Koluszki","Zyrardow","Lodz","Stalowa Wola","Warszawa","Kutno","Konin","Poznan","Gdansk","Sopot","Gdynia","Zakopane","Trzcianka","Jastarnia"};
        String[] numery = new String[50];
        for (int i = 0; i < numery.length; i++) {
            numery[i]=String.valueOf(random.nextInt(100));
        }
        String[] ulice = new String[]{"Brzezinska","Lodzka","Rokicinska","Radwanska","Mickiewicza","Natolinska","3 maja","Narutowicza","Slowackiego"};
        String[] zip = new String[]{"95-040","90-534","91-021","95-305"};
        
        
        //SKLEP
        String[] sklepy = new String[]{"Auchan","Carrefour","Biedronka","Zabka","Obi","Leroy costam","Selgros","Spar","Dino","U Darka"};
        List<Sklep> sklepyList = new ArrayList<>();
        for (int i = 0; i < sklepy.length; i++) {
            Sklep sklep = new Sklep();
            sklep.setNazwa(sklepy[i]);
            em.persist(sklep);
            sklepyList.add(sklep);
        }
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        
        //PRODUKT
        String[] marki = new String[]{"Adidas","Puma","Nike","Ferrari","Rolex","Lamborhini"};
        String[] modele = new String[]{"Pro 1","Aventador","ApachePro","Ducato","Panda"};
        String[] nazwy = new String[]{"Zegarek","Samochod","Laptop","Smartfon","Telewizor","Komputer"};
       
        
        //ZAMOWIENIE
        //auto persist dla klient stworzy adres etc sklep produkt i dla produktu setSklep by relacja produkt_sklep powstala
        
        for (int i = 0; i < 150; i++) {
            Zamowienie z = new Zamowienie();
            em.persist(z);
            em.getTransaction().commit();
            em.getTransaction().begin();
            z = em.merge(z);
            Klient k = new Klient();
            
            //ADRES KLIENTA
            Adres a = new Adres();
            a.setZip(zip[random.nextInt(zip.length)]);
            a.setMiasto(miasta[random.nextInt(miasta.length)]);
            a.setUlica(ulice[random.nextInt(ulice.length)]);
            a.setNrDomu(String.valueOf(random.nextInt(300)));
            em.persist(a);
            k.setAdres(a);
            
            //KLIENT
            k.setImie(names[random.nextInt(names.length)]);
            k.setNazwisko(surnames[random.nextInt(surnames.length)]);
            em.persist(k); ///////////////////////////////////////////////PERSIST
            //PRODUKTY
            int ileProduktow = random.nextInt(5);
            int randomSklep = random.nextInt(sklepyList.size());
            for (int j = 0; j < ileProduktow; j++) {
                Produkt p = new Produkt();
                em.persist(p);
                em.getTransaction().commit();
                em.getTransaction().begin();
                p = em.merge(p);
                p.setMarka(marki[random.nextInt(marki.length)]);
                p.setModel(modele[random.nextInt(modele.length)]);
                p.setNazwa(nazwy[random.nextInt(nazwy.length)]);
                p.addSklep(sklepyList.get(randomSklep));
                em.persist(p);
                z.addProdukt(p,random.nextInt(150) );
            }
            
            //ilosc dla produktow problem bo powinien byc w zamowienie produkt a teraz nie ma miejsca na to ;fff
            z.setKlient(k);
            
            
            z.setSklep(sklepyList.get(randomSklep));
            //em.persist(z);
           
        }

    em.getTransaction().commit();
    em.close();
    }

    private static void printRecords() {
        em = getEntityManager();
       List<Produkt> allProducts = em.createNamedQuery("Produkt.findAll").getResultList();
        System.out.println("ID"+"\t"+"|"+"\t"+"Nazwa"+"\t"+"|"+"\t"+"Model"+"\t"+"|"+"\t"+"Marka"+"\t"+"|");
      allProducts.forEach((t) -> {
          System.out.println(t.getProduktId() +"\t\t"+ t.getNazwa() +"\t\t"+ t.getModel() +"\t\t"+ t.getMarka());
      });
      
        System.out.println("Produkty ktorych ilosc w zamowieniu jest wieksza niz 100");
        //List<Produkt> specificProducts = (List<Produkt>) em.createQuery("SELECT p from Produkt p JOIN p.zamowienie_produkt zp ON p.produktId=zp.produktId").getResultList();
        List<Produkt> specificProducts = em.createNativeQuery("SELECT * from produkt left join zamowienie_produkt using(produkt_id) where ilosc>100",Produkt.class).getResultList();
        
        System.out.println("ID"+"\t"+"|"+"\t"+"Marka"+"\t"+"|"+"\t"+"Model"+"\t"+"|"+"\t"+"Nazwa"+"\t"+"|");
        specificProducts.forEach((Produkt t) -> {
            System.out.println(t.getProduktId()+"\t\t"+t.getMarka()+"\t\t"+t.getModel()+"\t\t"+t.getNazwa());
            //List<Produkt> specificProducts1 = (List<Produkt>) em.createQuery("SELECT p FROM Produkt p JOIN ZamowienieProdukt zp ON p.produktId=zp.zamowienieProduktPK.produktId").getResultList();
        });
        
    }
    
}
