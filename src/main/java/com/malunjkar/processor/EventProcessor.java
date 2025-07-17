package com.malunjkar.processor;


import com.malunjkar.model.Event;

/**
 *
 * @author Adesh Malunjkar
 */
public interface EventProcessor {
    void process(Event   event);
}
