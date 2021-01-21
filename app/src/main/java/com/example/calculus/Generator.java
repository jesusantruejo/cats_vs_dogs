package com.example.calculus;

import java.util.Random;

public class Generator implements java.io.Serializable {
    private int a;
    private int b;
    private int result;
    private String operator;
    private static String operators="+-*";


    //LAS DIFICULTADES ESCALAN DE 1 A 3. EN 1 SON SUMAS Y RESTAS DE 1 A 5,
    // 2 SE AÑADEN MULTIPLICACIONES DE 1 A 7, 3 SE AÑADEN DIVISIONES DE 1 A 10

    public Generator(int dificultad) {
        this.a = a;
        this.b = b;
        this.operator = operator;

        if(dificultad==1){
            this.a= getRandomNumberInRange(1,5);
            this.b= getRandomNumberInRange(1,5);
            this.operator=operators.charAt(0)+"";
        }

        else if(dificultad==2){
            this.a= getRandomNumberInRange(1,7);
            this.b= getRandomNumberInRange(1,7);
            this.operator=operators.charAt(getRandomNumberInRange(0,1))+"";
        }

        else if(dificultad==3){
            this.a= getRandomNumberInRange(1,9);
            this.b= getRandomNumberInRange(1,9);
            this.operator=operators.charAt(getRandomNumberInRange(0,2))+"";
        }


        if(operator.equals("+")){
            this.result=this.a+this.b;
        }
        else if(operator.equals("-")){
            this.result=this.a-this.b;
        }
        else if(operator.equals("*")){
            this.result=this.a*this.b;
        }
        else if(operator.equals("/")){
            this.result=this.a/this.b;
        }

    }

    public boolean checkAnswer(int input){
        if(input==this.result){
            return true;
        }
        else{
            return false;
        }
    }

    public int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }





}
