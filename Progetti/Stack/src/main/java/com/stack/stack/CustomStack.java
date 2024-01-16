/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.stack.stack;

/**
 *
 * @author Administrator
 */
public class CustomStack {

    public static void main(String[] args) 
    {
        final Stack<String> st = new ArrayStackDLA<>(1);
        
        st.push("123");
        st.push("456");
        st.push("789");
        
        System.out.println(st);
        
        
        final Stack<Integer> st2 = new ArrayStackDLA<>();
        
        st2.push(123);
        st2.push(456);
        st2.push(789);
        
        System.out.println(st);
        System.out.println(st2);
        
        st.pop();
        st2.pop();
        
        System.out.println(st);
        System.out.println(st2);
        
        final Stack<Integer> st3 = new ArrayStackFLA<>(st2);
        final Stack<Integer> st4 = new ArrayStackDLA<>(st3);
        
        System.out.println(st2);
        
        try 
        {
            st3.push(100);
        }
        catch (final StackOperationException ex)
        {
            System.err.println(ex.getMessage());
        }
        
        st4.push(10);
        
        System.out.println(st3);
        System.out.println(st4);
    }
}
