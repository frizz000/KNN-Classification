public class Irys {
    private final double v1;
    private final double v2;
    private final double v3;
    private final double v4;
    private final IrysEnum name;

    public Irys(double v1, double v2, double v3, double v4, IrysEnum name) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.v4 = v4;
        this.name = name;
    }

    public double[] getValuesTabe() {
        return new double[]{v1, v2, v3, v4};
    }

    public IrysEnum getName() {
        return name;
    }

    @Override
    public String toString() {
        return "v1 = " + v1 +
                ", v2 = " + v2 +
                ", v3 = " + v3 +
                ", v4 = " + v4 +
                ", name = " + name;
    }
}
