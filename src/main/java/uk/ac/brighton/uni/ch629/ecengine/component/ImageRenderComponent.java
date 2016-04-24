package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;
import uk.ac.brighton.uni.ch629.ecengine.types.Vector2i;

import java.awt.*;

@ComponentDetails(dependencies = TransformComponent.class, type = ComponentDetails.ComponentType.RENDER)
public class ImageRenderComponent extends Component {
    private Image image; //File file = new file(path); image = ImageIO.read(file);
    private int width = 1, height = 1;

    public ImageRenderComponent(Entity parent) {
        super(parent);
    }

    public void update(double deltaTime) {

    }

    public void render(Graphics graphics) {
        if (image != null) {
            Vector2i pos = parent.getTransform().getPos();
            graphics.drawImage(image, pos.x, pos.y, width, height, parent.getWorld().WINDOW.getContentPane());
        }
    }
}