/*
 * This document is part of the lab material for the subject:
 * Gestion de Sistemas de Informacion
 * to be released at the
 * Universidad Publica de Navarra
 * during the first semester of the Academic Year 2015-2016
 */

package GSILabs.BSystem;

import GSILabs.BModel.Event;
import GSILabs.BModel.Event;

/**
 * This interface represents the functionality of finding an event given a partial
 *  description of it.
 * @author carlos.lopez
 * @version 1.0 (04/09/2015)    
 */
public interface EventLookupService {

    /**
     * Retrieves the list of events that match the information given as argument. Note that 
     *  the concatenation of the information is to be performed as an "and". This meaning
     *  that the inquirer is looking for events that have a name that looks like the eventName,
     *  held at a location whose name is similar to locationName and a performer like performerName.
     * Hence, coincidences in all of the fields must be produced in order an event to be
     * listed in the return value.
     * Invokers are free to use an asterisk to represent the "any value". Of course,
     *  if all of the arguments are asterisks, the return value will be the complete list
     *  of events in the system.
     * @param eventName The name of the event, or "*" if interested in all of them.
     * @param locationName The name of the location, or "*" if interested in all of them.
     * @param performerName The name of the performer, , or "*" if interested in all of them
     * @return The list of events that match all of the information given in the arguments.
     */
    Event[] searchEvent(String eventName, String locationName, String performerName);
    
}
