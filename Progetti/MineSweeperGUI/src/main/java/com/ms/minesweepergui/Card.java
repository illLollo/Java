/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ms.minesweepergui;

import java.util.Iterator;
import javafx.scene.Node;

/**
 *
 * @author Administrator
 */
public class Card
{
    private final Node node;
    private final int row;
    private final int col;
    
    public Card(final Node node, final int row, final int col)
    {
        this.node = node;
        this.row = row;
        this.col = col;
    }
    public Node getNode() { return this.node; }
    public int getRow() { return this.row; }
    public int getCol() { return this.col; }

    
    
}
