/**
 * A script to allow multiple nodes
 *
 * lochhma
 * @v1
 */
public class Node {
    int x;
    int y;//the x and y coords on screen

    boolean selected;
    public Node(int x, int y, boolean selected)
    {
        this.x=x;
        this.y=y;
        this.selected=selected;
    }
}
