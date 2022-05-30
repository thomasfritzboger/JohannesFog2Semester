package dat.startcode.model.services;

public class SVG {
    StringBuilder svg = new StringBuilder();

    private int x;
    private int y;
    private String viewBox;
    private int width;
    private int height;

    private final String headerTemplate = "<svg height=\"%d%%\" " +
            "width=\"%d%%\" " +
            "viewBox=\"%s\" "+
            "x=\"%d\"   " +
            "y=\"%d\"   " +
            " preserveAspectRatio=\"xMinYMin\">";

    private final String rectTemplate = "<rect x=\"%d\" y=\"%d\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";

    private final String shedRectTemplate = "<rect x=\"%d\" y=\"%d\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill:#0069d9; fill-opacity:0.1;\" />";

    private final String lineTemplate = "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" style=\"stroke:#000000;\" />";

    private final String shedLineTemplate = "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" style=\"stroke:#000000; stroke-width:3 \"/>";

    private final String crossTemplate = "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" style=\"stroke:#000000; stroke-width:1.5; stroke-dasharray:5 5\"  />";

    private final String textTemplateY = "<text style=\"text-anchor: middle\" transform=\"translate(%d,%d) rotate(-90)\">%s cm</text>";

    private final String textTemplateX = "<text style=\"text-anchor: middle\" transform=\"translate(%d,%d)\">%s cm</text>";

    public SVG(int x, int y, String viewBox, int width, int height) {
        this.x = x;
        this.y = y;
        this.viewBox = viewBox;
        this.width = width;
        this.height = height;
        svg.append(String.format(headerTemplate, height, width, viewBox, x, y));
    }

    public void addRect(int x, int y, double height, double width)
    {
        svg.append(String.format(rectTemplate, x, y, height, width));
    }

    public void addShedRectTemplate(int x, int y, double height, double width) {
        svg.append(String.format(shedRectTemplate,x, y, height, width));
    }

    public void addLine(int x1, int y1, int x2, int y2) {
        svg.append(String.format(lineTemplate, x1,y1,x2,y2));
    }

    public void addSvg(SVG innerSVG) {
        svg.append(innerSVG.toString());
    }

    public void addCrossLine (int x1, int y1, int x2, int y2) { svg.append(String.format(crossTemplate,x1,y1,x2,y2));}

    public void addShedLine(int x1, int y1, int x2, int y2) {
        svg.append(String.format(shedLineTemplate,x1,y1,x2,y2));
    }

    public void addLineTextY(int locX, int locY, String carportWidth) {
        svg.append(String.format(textTemplateY, locX,locY,carportWidth));
    }
    public void addLineTextX(int locX, int locY, String carportLength) {
        svg.append(String.format(textTemplateX, locX,locY,carportLength));
    }

    @Override
    public String toString()
    {
        return svg.toString() + "</svg>" ;
    }
}
