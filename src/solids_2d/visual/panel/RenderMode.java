package solids_2d.visual.panel;

public enum RenderMode {


    NONE(-1,RenderContent.FACE),
    STRESS(0,RenderContent.FACE),
    E_MODUL(1,RenderContent.FACE),
    DISPLACEMENT(2,RenderContent.VERTEX),
    STRESS_X(3,RenderContent.FACE),
    STRESS_Y(4,RenderContent.FACE),
    STRESS_XY(5,RenderContent.FACE),
    DISPLACEMENT_X(6,RenderContent.VERTEX),
    DISPLACEMENT_Y(7,RenderContent.VERTEX),
    STRAIN(8,RenderContent.FACE);


    private final int index;
    private final RenderContent content;

    RenderMode(int index, RenderContent content) {
        this.index = index;
        this.content = content;
    }

    public int getIndex() {
        return index;
    }

    public RenderContent getContent() {
        return content;
    }
}
