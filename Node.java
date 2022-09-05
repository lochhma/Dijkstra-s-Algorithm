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
    boolean selectStartEnd;
    public Node(int x, int y, boolean selected, boolean selectStartEnd)
    {
        this.x=x;
        this.y=y;
        this.selected=selected;
        this.selectStartEnd=selectStartEnd;
    }
}
