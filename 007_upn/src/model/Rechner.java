package model;

import java.util.Stack;

public class Rechner {

    private Stack<Double> numberstack;


    public Rechner(){
        numberstack = new Stack<>();
    }
    public void addNumber(double number){
        numberstack.push(number);
    }



    public double addition() throws RechnerException {
        if(numberstack.size()<=1){
            throw new RechnerException("Keine Zahl zum Rechnen");
        }
        double erg =numberstack.pop()+numberstack.pop();

        numberstack.push(erg);

        return erg;
    }
    public double subtraction() throws RechnerException {
        if(numberstack.size()<=1){
            throw new RechnerException("Keine Zahl zum Rechnen");
        }


        double erg= -numberstack.pop() +numberstack.pop();


        numberstack.push(erg);
        return erg;
    }
    public double multiplication () throws RechnerException {
        if(numberstack.size()<=1){
            throw new RechnerException("Keine Zahl zum Rechnen");
        }


        double erg= numberstack.pop() *numberstack.pop();


        numberstack.push(erg);
        return erg;
    }
    public double division() throws RechnerException {
        if(numberstack.size()<=1){
            throw new RechnerException("Keine Zahl zum Rechnen");
        }

        double divisor = numberstack.pop();
        double erg=  numberstack.pop() / divisor;

        numberstack.push(erg);
        return erg;
    }




}
