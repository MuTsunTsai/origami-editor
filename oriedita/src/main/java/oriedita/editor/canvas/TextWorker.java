package oriedita.editor.canvas;

import oriedita.editor.drawing.tools.Camera;
import oriedita.editor.save.TextSave;
import oriedita.editor.text.Text;
import origami.crease_pattern.element.Point;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class TextWorker {
    private final List<Text> texts;

    @Inject
    public TextWorker() {
        this.texts = new ArrayList<>();
    }

    public void draw(Graphics2D g2, Camera camera) {
        for (Text text : texts) {
            Point pt = camera.object2TV(text.getPos());
            g2.drawString(text.getText(), (int) pt.getX(), (int) pt.getY());
        }
    }

    public void getSave(TextSave save) {
        texts.forEach(t -> save.addText(new Text(t)));
    }

    public void setSave(TextSave memo1) {
        texts.clear();
        texts.addAll(memo1.getTexts());
    }
}
