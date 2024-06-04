/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

public class Queue<T> {
  Node<T> front, rear;

  public Queue() {
    this.front = null;
    this.rear = null;
  }

  public boolean isEmpty() {
    return front == null;
  }

  public void enqueue(T data) {
    Node<T> newNode = new Node<>(data);
    if (isEmpty()) {
      front = rear = newNode;
    } else {
      rear.next = newNode;
      rear = newNode;
    }
  }

  public T dequeue() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue vacia");
    }
    T data = front.data;
    front = front.next;
    if (front == null) {
      rear = null;
    }
    return data;
  }
}

