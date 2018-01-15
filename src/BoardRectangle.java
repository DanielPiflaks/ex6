import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class BoardRectangle extends StackPane implements PressNotifier {

    private int row;
    private int col;
    private int recWidth;
    private int rechHeight;
    private javafx.scene.paint.Color color;
    private boolean isClicked;
    private javafx.scene.shape.Rectangle rectangle;

    public BoardRectangle(int recWidth, int recHeight,
                          javafx.scene.paint.Color color, int row, int col) {
        this.rechHeight = recHeight;
        this.recWidth = recWidth;
        this.color = color;
        this.rectangle = new Rectangle(recWidth, rechHeight, color);
        this.row = row;
        this.col = col;
        this.isClicked = false;
        setOnMouseClicked(event -> {
            this.isClicked = true;
        });
    }

    public boolean isClicked(){
        return isClicked;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public void pressNotify(int row, int col) {

    }

    @Override
    public void addPressListener(PressListener pressListener) {

    }

    @Override
    public void removePressListener(PressListener pressListener) {

    }
}
