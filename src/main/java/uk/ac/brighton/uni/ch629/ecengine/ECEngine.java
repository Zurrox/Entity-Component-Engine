package uk.ac.brighton.uni.ch629.ecengine;

import uk.ac.brighton.uni.ch629.ecengine.game.GameWindow;

import java.awt.*;

public class ECEngine extends GameWindow {
    public ECEngine() {
        super("ECEngine Test", 1024, 768);
    }

    public static void main(String[] args) {
        new ECEngine();
    }

    public void update(double deltaTime) {

    }

    public void render(Graphics g) {
    }

    public void initialize() {

    }
}
//TODO: Game States (Main menu state, game state etc)
//TODO: Maybe something in JSON to automate creation of some Entities
//TODO: Multithreading with events
//TODO: Localization?
//TODO: JavaDocs
//TODO: Networking
//TODO: Maybe some sort of blueprint for Entities to be created using.
//TODO: Find some elegant way to create blueprint entities & also create any entity with some set values (Right now need to define either all as defaults or all as non-defaults)
//TODO: Overlays? Pause Menu -> Needs to pause main game when opened.
//TODO: Static Components (Stuff like a Score Component doesn't need to be updated constantly, just needs to be called to update the score then tell a TextRenderComponent to render it)
//TODO: In event's just give direct Entities rather than their UUID's, makes life a lot easier and means every event I don't have to do parent.getWorld().entities.get(event.entity);
//TODO: Maybe a messaging system between Components rather than just Events, Events can be used for things like updating score, but some Components just need to directly talk with another easily(ScoreComponent needs to communicate with the TextRenderComponent to update the text on screen)
//TODO: Different Components(Render Components, Update Components and both, not all components need to be rendered, not all need to be updated)
//TODO: Remove this class for final build, this does nothing.
//TODO: Do something with the Event System, triggers being collided with with use onHit as well as throw CollisionEvents which is doing double the work it probably needs to.
//TODO: The Event system could just use the ActionListener System, which would simplify a lot. Too much redesigning though for the deadline, this is just a priority for later on.
//TODO: Need to look up the differences between an EventBus system and a typical java event system, currently using EventBus mainly, but I think I could migrate almost everything to use the typical java way...
//TODO: I guess the EventBus system is more universal, and can just be left, and will probably work for any addition to it, where a lot of things may want to listen on some actions, for example the EntityKillEvent which is needed for score as well as loading the next level. But the normal Java Event system can be used for things, that work directly onto Components, like onCollide as not all components care about other collisions in the world; But some things may want to listen to collisions still, as something like hovering damage text whenever one object collides with another.