package cvut.omo.home_structure;

/**
 *
 */
public class Window {
    private boolean isOpened = false;

    /**
     * @return
     */
    public boolean isOpened() {
        return isOpened;
    }

    /**
     *
     */
    public void open() {
        isOpened = true;
    }

    /**
     *
     */
    public void close() {
        isOpened = false;
    }
}
