package com.mycompany.calcolatricegrafica;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class Controller 
{
    private Operazione op;
    private Double a;
    
    @FXML
    private Text result;
    
    private void setText(String text) 
    { 
        try
        {
            result.setText(Double.valueOf(result.getText()).equals(0.0) ? text : (result.getText() + text));
        }
        catch (NumberFormatException e) { result.setText(text); }
    }
    private Double execCalc(Double lhs, Double rhs) throws ArithmeticException
    {
        op.setA(lhs);
        op.setB(rhs);

        return op.calcola();
    }
    private void recursiveOperations()
    {
        if (a == null) 
        {
            a = Double.valueOf(result.getText());
            result.setText("0");
            return;
        }
        try 
        {
            a = this.execCalc(a, Double.valueOf(result.getText()));  
            result.setText("0");
        }
        catch (ArithmeticException e) { result.setText(e.getMessage()); }
    }
    public void calculate(ActionEvent e) 
    {
       if (a == null || op == null) return;
       
       try 
       {
            result.setText(this.execCalc(a, Double.valueOf(result.getText())).toString());  
       }
       catch (ArithmeticException exception) { result.setText(exception.getMessage()); }
       a = null;
    }
    public void delete(ActionEvent e)
    {
        String text = result.getText();
                
        try
        {
            if (text.length() == 1 || Double.valueOf(text).equals(0.0)) 
            {
                result.setText("0");
                return;
            }
            
            result.setText(text.substring(0, text.length() - 1));
        }
        catch (NumberFormatException ex) { result.setText("0"); }
        
    }
    
    public void button_add(ActionEvent e) 
    {        
        this.recursiveOperations();
        if (!(op instanceof Addizione)) op = new Addizione();
    }
    public void button_subtract(ActionEvent e) 
    {
        this.recursiveOperations();
        if (!(op instanceof Sottrazione)) op = new Sottrazione();
    }
    public void button_multiply(ActionEvent e) 
    {
        this.recursiveOperations();
        if (!(op instanceof Moltiplicazione)) op = new Moltiplicazione();
    }
    public void button_divide(ActionEvent e) 
    {
        this.recursiveOperations();
        if (!(op instanceof Divisione)) op = new Divisione();
    }
    public void button_square(ActionEvent e) 
    {
        if (Double.valueOf(result.getText()).equals(0.0)) return;
        result.setText(new Potenza(Double.valueOf(result.getText()), Double.valueOf(2)).calcola().toString());
    }
    public void button_sqrt(ActionEvent e) 
    {
        if (Double.valueOf(result.getText()).equals(0.0)) return;
        result.setText(new Radice(Double.valueOf(result.getText())).calcola().toString());
    }
    public void cancelAll(ActionEvent e)
    {
        result.setText("0");
        op = null;
        a = null;
    }
    
    public void button_1(ActionEvent e) { this.setText("1"); }
    public void button_2(ActionEvent e) { this.setText("2"); }
    public void button_3(ActionEvent e) { this.setText("3"); }
    public void button_4(ActionEvent e) { this.setText("4"); }
    public void button_5(ActionEvent e) { this.setText("5"); }
    public void button_6(ActionEvent e) { this.setText("6"); }
    public void button_7(ActionEvent e) { this.setText("7"); }
    public void button_8(ActionEvent e) { this.setText("8"); }
    public void button_9(ActionEvent e) { this.setText("9"); }
    public void button_0(ActionEvent e) { if (result.getText().equals("0")) return; this.setText("0"); }
}
