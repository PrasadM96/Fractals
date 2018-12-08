
class Mandelbrot {

    private int width, height;
    private double scale=400;
    double x, y;
    double mapxMin;
    double mapxMax;
    double mapyMin;
    double mapyMax;
    boolean status = true;
    int iteration;

    private int iter;

    //constructor
    public Mandelbrot(int width, int height, double x1, double x2, double y1, double y2, int iterations) {
        this.width = width;
        this.height = height;
        this.mapxMin = x1;
        this.mapxMax = x2;
        this.mapyMin = y1;
        this.mapyMax = y2;
        this.iteration = iterations;
    }

    //mapping function
    public void map(int p, int q) {
         this.x=(double)((p-width/2.0)*(mapxMax-mapxMin)/scale);//-Math.abs(mapxMin);
         this.y=(double)((q-height/2.0)*(mapyMax-mapyMin)/scale);//-Math.abs(mapyMin);
    }
    
    //check whether points are in mandelbrot set or not
    public boolean checking(double x, double y) {
        iter = 0;
        double z_x = 0, z_y = 0;
        iteration = 1000;
        while (iteration-- > 0) {
            double Zx = (z_x * z_x) - (z_y * z_y) + x;
            double Zy = (2 * z_x * z_y) + y;
            z_x = Zx;
            z_y = Zy;
            iter++;

            if ((z_x * z_x + z_y * z_y) > 4) {
                return false;
            }
        }
        return true;
    }

    //return the x values
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    //return the no of iterations excuted
    public int getIter() {
        return iter;
    }
}
