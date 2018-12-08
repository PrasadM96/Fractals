
class Julia {

    private final int width;
    private final int height;
    private double scale=800;
    double Cx, Cy,x,y;
    double mapxMin;
    double mapxMax;
    double mapyMin;
    double mapyMax;
    int iteration;
    private int iter;

   //constructor
    public Julia(int width, int height, double Cx, double Cy,int iterations) {
        this.width = width;
        this.height = height;
        this.mapxMin = -1;
        this.mapxMax = 1;
        this.mapyMin = -1;
        this.mapyMax = 1;
        this.Cx=Cx;
        this.Cy=Cy;
        this.iteration = iterations;
    }
    
    //mapping function
   public void map(int p, int q) {
      this.x = (double)(p - width / 2f) * (mapxMax - mapxMin) / scale ;
      this.y = (double)(q - height / 2f) * (mapyMax - mapyMin) /scale;
      
   }
   //check whether points are in mandelbrot set or not
    public boolean checking(double x, double y) {
        iter = 0;
        double z_x = this.x,z_y = this.y;
        iteration = 1000;
        while (iteration-- > 0) {
            double Zx = (z_x * z_x) - (z_y * z_y) +this.Cx;
            double Zy = (2 * z_x * z_y) + this.Cy;
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
    
    //return the y coordinates
    public double getY() {
        return y;
    }
    
    //return the no of iterations excuted
    public int getIter() {
        return iter;
    }

}
