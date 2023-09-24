package com.mycompany.calcolatricegrafica;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class Controller 
{
    private Operazione op;
    private String a = null;
    private String b = null;
    
    @FXML
    private Text result;
    
    private void setText(String text)
    {
        result.setText(result.getText().equals("0") ? text : (result.getText() + text));
    }
    
    public void calculate(ActionEvent e) 
    {
        if (op.getB() == null) op.setB(Double.valueOf(result.getText()));
        if (op != null && op.getA() != null && op.getB() != null)
        {
            Double value = op.calcola();
            
            result.setText(value.toString());
            op.setA(value);
            op.setB(null);
        }
    }
    public void button_1(ActionEvent e) 
    {
        this.setText("1");  
    }
    public void delete(ActionEvent e)
    {
        String text = result.getText();
        
        if (text.length() == 1) result.setText("0");
        else result.setText(text.substring(0, text.length() - 1));
    }
    public void button_2(ActionEvent e) 
    {
        this.setText("2");
    }
    public void button_3(ActionEvent e) 
    {
        this.setText("3");
    }
    public void button_4(ActionEvent e) 
    {
        this.setText("4");
    }
    public void button_5(ActionEvent e) 
    {
        this.setText("5");
    }
    public void button_6(ActionEvent e) 
    {
        this.setText("6");
    }
    public void button_7(ActionEvent e) 
    {
        this.setText("7");
    }
    public void button_8(ActionEvent e) 
    {
        this.setText("8");
    }
    public void button_9(ActionEvent e) 
    {
        this.setText("9");
    }
    public void button_0(ActionEvent e) 
    {
        if (result.equals("0")) return;
        this.setText("0");
    }
    public void button_add(ActionEvent e) 
    {
        if (op == null) op = new Addizione();
        
        if (op.getA() == null) 
        {
            op.setA(Double.valueOf(result.getText()));
            result.setText("0");
        }
        else if (op.getB() == null && result.getText().equals("0")) this.calculate(e);
        else 
        {
            op.setB(Double.valueOf(result.getText()));
            result.setText("0");    
        }
        
        System.out.println("A: " + op.getA());
        System.out.println("B: " + op.getB());
        
    }
    public void button_subtract(ActionEvent e) 
    {
        
    }
    public void button_multiply(ActionEvent e) 
    {
        
    }
    public void button_divide(ActionEvent e) 
    {
        
    }
    public void button_square(ActionEvent e) 
    {
        
    }
    public void button_sqrt(ActionEvent e) 
    {
        
    }
}
