package com.optivem.kata.banking.infra.real.inmemory;

import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Queue;


@Component
public class EventQueue<T> {

    private final Queue<T> queue;

    public EventQueue(){
        this.queue = new ArrayDeque<>();
    }

    public void addEvent(T event){
           queue.add(event);
    }

    public T next(){
        if(queue.isEmpty()){
            throw new NoSuchElementException();
        }
        return queue.remove();
    }

}
