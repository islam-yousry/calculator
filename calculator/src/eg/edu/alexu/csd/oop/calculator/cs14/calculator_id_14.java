package eg.edu.alexu.csd.oop.calculator.cs14;

import eg.edu.alexu.csd.oop.calculator.cs14.Calculator;

import java.io.*;
import java.lang.*;



public class calculator_id_14  implements Calculator {
    public String[] equations = new String[5];
    public int current_equation = 0;
    int index = 0;



        String[] Equation = new String[3];

        File myEq = new File("C:\\Users\\dell\\IdeaProjects\\calculator\\src\\eg\\edu\\alexu\\csd\\oop\\calculator\\cs14\\history.txt");


        @Override
        public void input(String s) {
            if(s.charAt(s.length()-1) == '+' || s.charAt(s.length()-1) == '-' || s.charAt(s.length()-1) == '*' || s.charAt(s.length()-1) == '/'){
                Equation[0] = null;
            }
            if(s.contains("+")){
                Equation[0] = s.substring(0,s.indexOf('+',1));
                Equation[1] = "+";
                Equation[2] = s.substring(s.indexOf('+',1)+1);
            }
            else if(s.contains("*")){
                Equation[0] = s.substring(0,s.indexOf('*',1));
                Equation[1] = "*";
                Equation[2] = s.substring(s.indexOf('*',1)+1);
            }
            else if(s.contains("/")){
                Equation[0] = s.substring(0,s.indexOf('/',1));
                Equation[1] = "/";
                Equation[2] = s.substring(s.indexOf('/',1)+1);
            }
            else if(s.indexOf('-',1) != -1){
                Equation[0] = s.substring(0,s.indexOf('-',1));
                Equation[1] = "-";
                Equation[2] = s.substring(s.indexOf('-',1)+1);
            }
            else{
                Equation[0] = s;
                Equation[1] = "null";
            }

        }

        @Override
        public String getResult() {
            //check if invalid operation
            if(Equation[0] == null ){
                return "invalid operation!";
            }
            //check if no operands
            if(Equation[1].equals("null")){
                return Equation[0];
            }

            double ans = 0;
            double num1 = Double.parseDouble(Equation[0]);
            double num2 = Double.parseDouble(Equation[2]);

            if(Equation[1].equals("+")){
                 ans = num1+num2;
            }
            else if(Equation[1].equals("-")){
                 ans = num1-num2;
            }
            else if(Equation[1].equals("*")){
                 ans = num1*num2;
            }
            else if(Equation[1].equals("/")){
                if(num2 == 0){
                    return "invalid operation!";
                }
                 ans = num1/num2;
            }

            // save history of operations up to 5.
            if(index > 4){
                for(int i = 0 ; i < 4 ; i++){
                    equations[i] = (equations[i+1]);
                }
                equations[4] = Equation[0]+Equation[1]+Equation[2]+"="+ String.valueOf(ans);
            }
            else {
                equations[index] = Equation[0]+Equation[1]+Equation[2] + "=" + String.valueOf(ans);
                current_equation = index;
                index++;
            }




            return String.valueOf(ans);
        }

        @Override
        public String current() {
            return equations[current_equation];
        }

        @Override
        public String prev() {
            if(current_equation > 0){
                current_equation--;
                return equations[current_equation];
            }
            return "invalid operation!";
        }

        @Override
        public String next() {
            if(current_equation < 4){
                current_equation++;
                return equations[current_equation];
            }
            return "invalid operation";
        }

        @Override
        public void save() {

            try {
                FileWriter myWriter = new FileWriter("C:\\Users\\dell\\IdeaProjects\\calculator\\src\\eg\\edu\\alexu\\csd\\oop\\calculator\\cs14\\history.txt");
                if(index > 4) {
                    for (int i = 0; i < 5; i++) {
                        myWriter.write(equations[i]+"\n");
                    }
                }
                else {
                    for (int i = 0; i < index; i++) {
                        myWriter.write(equations[i]+"\n");
                    }
                }
                myWriter.close();
            }
                catch (IOException e) {
                    System.out.println("An error occurred.");
                }


        }

        @Override
        public void load() {

        }
    }


