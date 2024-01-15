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
        final Stack<String> st = new ArrayStackDLA<>();
        
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
    }
}
