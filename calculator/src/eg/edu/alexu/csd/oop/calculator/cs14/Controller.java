package eg.edu.alexu.csd.oop.calculator.cs14;

import java.io.FileNotFoundException;
import java.lang.*;
import java.io.*;
import java.util.Scanner;

import eg.edu.alexu.csd.oop.calculator.cs14.calculator_id_14;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class Controller {
    calculator_id_14 operation = new calculator_id_14();


    private boolean dotchecker , operandchecker , equalchecker;
    @FXML private TextField equation;
    @FXML private Label label;
    @FXML private Tab memorytab;
    @FXML private Tab historytab;
    @FXML private TextArea History;
    @FXML private TextArea Memory;


    public void zero(){
        if(equalchecker == true){
            equalchecker = false;
            equation.clear();
        }
        label.setText(" ");
        equation.appendText("0");

    }

    public void one(){
        if(equalchecker == true){
            equalchecker = false;
            equation.clear();
        }
        label.setText(" ");
        equation.appendText("1");

    }

    public void two(){
        if(equalchecker == true){
            equalchecker = false;
            equation.clear();
        }
        label.setText(" ");
        equation.appendText("2");

    }

    public void three(){
        if(equalchecker == true){
            equalchecker = false;
            equation.clear();
        }
        label.setText(" ");
        equation.appendText("3");

    }

    public void four(){
        if(equalchecker == true){
            equalchecker = false;
            equation.clear();
        }
        label.setText(" ");
        equation.appendText("4");

    }

    public void five(){
        if(equalchecker == true){
            equalchecker = false;
            equation.clear();
        }
        label.setText(" ");
        equation.appendText("5");

    }

    public void six(){
        if(equalchecker == true){
            equalchecker = false;
            equation.clear();
        }
        label.setText(" ");
        equation.appendText("6");

    }

    public void seven(){
        if(equalchecker == true){
            equalchecker = false;
            equation.clear();
        }
        label.setText(" ");
        equation.appendText("7");

    }

    public void eight(){
        if(equalchecker == true){
            equalchecker = false;
            equation.clear();
        }
        label.setText(" ");
        equation.appendText("8");

    }

    public void nine(){
        if(equalchecker == true){
            equalchecker = false;
            equation.clear();
        }
        label.setText(" ");
        equation.appendText("9");

    }

    public void plus(){
        if(equalchecker == true){
            equalchecker = false;
            equation.clear();
        }
        label.setText(" ");
        if(operandchecker == false && !equation.getText().isEmpty()) {
            equation.appendText("+");
            operandchecker = true;
            dotchecker = false;
        }
    }

    public void minus(){
        if(equalchecker == true){
            equalchecker = false;
            equation.clear();
        }
        label.setText(" ");
        if(equation.getText().isEmpty()){
            equation.appendText("-");
        }
        else if(equation.getText().charAt(0) == '-' && equation.getText().length()>1 && operandchecker == false){
            equation.appendText("-");
            operandchecker = true;
            dotchecker = false;
        }

        else if(operandchecker == false && equation.getText().charAt(0) != '-' && equation.getText().length()>0) {
            equation.appendText("-");
            operandchecker = true;
            dotchecker = false;
        }

    }

    public void multiply(){
        if(equalchecker == true){
            equalchecker = false;
            equation.clear();
        }
        label.setText(" ");
        if(operandchecker == false && !equation.getText().isEmpty()) {
            equation.appendText("*");
            operandchecker = true;
            dotchecker = false;
        }

    }

    public void division(){
        if(equalchecker == true){
            equalchecker = false;
            equation.clear();
        }
        label.setText(" ");
        if(operandchecker == false && !equation.getText().isEmpty()) {
            equation.appendText("/");
            operandchecker = true;
            dotchecker = false;
        }

    }

    public void dot() {
        if(equalchecker == true){
            equalchecker = false;
            equation.clear();
        }
        label.setText(" ");

        if (dotchecker == false){
            equation.appendText(".");
            dotchecker = true;
        }
    }

    public void ans(){
        if(equalchecker == true){
            equation.clear();
        }
        label.setText(" ");
        if(operation.getResult().equals("invalid operation!")){
            equation.appendText("0");
        }
        else {
            equation.appendText(operation.getResult());
        }
    }


    public void c(){
        label.setText(" ");
        equation.clear();
        operandchecker = false;
        dotchecker = false;
    }

    public void delete(){
        if(!equation.getText().isEmpty()) {
            if (equalchecker == true) {
                equalchecker = false;
                equation.clear();
            }
            label.setText(" ");
            String E = equation.getText();
            if (E.charAt(E.length() - 1) == '.') {
                dotchecker = false;
            }
            else if (E.charAt(E.length() - 1) == '+' || E.charAt(E.length() - 1) == '-' || E.charAt(E.length() - 1) == '*' || E.charAt(E.length() - 1) == '/') {
                operandchecker = false;
            }
            equation.deleteText(E.length() - 1, E.length());
        }
    }

    public void equal(){
        if(!equation.getText().isEmpty()) {
            equalchecker = true;
            operandchecker = false;
            dotchecker = false;
            label.setText(" ");
            operation.input(equation.getText());
            label.setText(operation.getResult());
            if (operation.index > 4) {
                History.clear();
                for (int i = 0; i < 5; i++) {
                    History.appendText(operation.equations[i] + "\n");
                }
            } else {
                History.appendText(operation.equations[operation.index - 1] + "\n");
            }
        }
        historytab.getTabPane().getSelectionModel().select(historytab);
    }

    public void prev(){
        label.setText(" ");
        label.setText(operation.prev());
    }

    public void next(){
        label.setText(" ");
        label.setText(operation.next());
    }

    public void current(){
        label.setText(" ");
        label.setText(operation.current());
    }


    public void save() {
        operation.save();
        Scanner input = null;
        try {
            input = new Scanner(operation.myEq);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (input.hasNextLine()){
            Memory.appendText(input.nextLine()+"\n");

        }
        Memory.clear();

    }
    public void load() throws IOException {
        operation.load();
        Memory.clear();
        Scanner input = null;
        try {
            input = new Scanner(operation.myEq);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (input.hasNextLine()){
            Memory.appendText(input.nextLine()+"\n");
        }
        memorytab.getTabPane().getSelectionModel().select(memorytab);
    }


}










