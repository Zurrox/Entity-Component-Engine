package uk.ac.brighton.uni.ch629.ecengine.exceptions;

import uk.ac.brighton.uni.ch629.ecengine.component.Component;
import uk.ac.brighton.uni.ch629.ecengine.logic.World;

import java.util.UUID;

public class DuplicateComponentException extends Exception
{
    public DuplicateComponentException()
    {
    }

    public DuplicateComponentException(World world, UUID entityID, Class<? extends Component> comp)
    {
        super(String.format("Tried to add the Component: %s to the Entity: %s in the World: %s", comp.getSimpleName(), entityID, world.getID()));
    }
}