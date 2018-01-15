public interface PressNotifier {
    void pressNotify(int row, int col);

    void addPressListener(PressListener pressListener);

    void removePressListener(PressListener pressListener);
}
