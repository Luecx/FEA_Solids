package solids_2d.visual.panel;

public enum RenderContent {


    VERTEX(0),
    FACE(1);


    private final int index;

    RenderContent(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

}
