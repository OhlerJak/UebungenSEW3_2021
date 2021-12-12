package todo.model;

import person.model.Person;
import serial.Catalog;

import java.io.Serializable;
import java.util.List;

public class ToDo implements Serializable {

    private static Catalog catalog;
    private Person verantwortlicher;
    private String text;

    public static void setCatalog(Catalog cat){
        if(cat!=null)
        catalog=cat;
    }

    public ToDo(String text, Person verantwortlicher){
        this.text=text;
        this.verantwortlicher=verantwortlicher;
    }

    public List findByPerson(Person person){
        return catalog.selectByPerson(person);
    }

    public void remove(){
    catalog.remove(this);
    }

    public void save(){
    catalog.save(this);
    }

    public Person getVerantwortlicher(){
        return verantwortlicher;
    }

   public String toString(){
        return text;
   }





}
