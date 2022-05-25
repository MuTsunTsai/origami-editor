package oriedita.editor.text;

import com.fasterxml.jackson.annotation.JsonIgnore;
import origami.crease_pattern.element.Point;

public class Text {
    private double x,y;
    private String text;

    private Text() {
        this(0,0,"");
    }

    public Text(double x, double y, String text) {
        this.x = x;
        this.y = y;
        this.text = text;
    }

    public Text(Text t) {
        this.x = t.getX();
        this.y = t.getY();
        this.text = t.getText();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @JsonIgnore
    public Point getPos() {
        return new Point(getX(), getY());
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
