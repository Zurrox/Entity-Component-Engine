package uk.ac.brighton.uni.ch629.ecengine.exceptions;

import uk.ac.brighton.uni.ch629.ecengine.logic.World;

import java.util.UUID;

public class DuplicateEntityException extends Exception
{
    public DuplicateEntityException()
    {
    }

    public DuplicateEntityException(World world, UUID entityID)
    {
        super(String.format("Tried to add an entity to World: %s with the ID %s", world.getID(), entityID));
    }
}