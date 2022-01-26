/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazegame;


public final class Stack <T>{
    
  private Node topNode; // References the first node in the chain
    
  public Stack() {
      
    topNode = null;
    
  } // end default constructor

  public void push(T newEntry) {
      
    Node newNode = new Node(newEntry, topNode);
    topNode = newNode;
  } 
  
  public T peek() {
      
    return topNode.getData();
  } 
  
  public T pop() {
    T top = peek(); 
    topNode = topNode.getNextNode(); 
    return top;
  } 
  
  public boolean isEmpty() {
      
    return topNode == null;
  } 
  
  public void clear() {
   
      topNode = null; 
  } 

  private class Node {    
      
    private T data; // Entry in stack 
    private Node next; // Link to next node
    
    private Node(T dataPortion) {
      
        this(dataPortion, null);
    } // end constructor
      
    private Node(T dataPortion, Node nextNode) {
      data = dataPortion;
      next = nextNode;
    } 
    
    private T getData() {
     
        return data;
    } 
    
    private void setData(T newData) {
        
      data = newData;
    } 
    
    private Node getNextNode() {
           
      return next;
    } 
    
    private void setNextNode(Node nextNode) {
      
        next = nextNode;
    } 
    
  } // end Node
  
}

