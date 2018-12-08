

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.*;

public class Fractals extends JPanel {

    private final int width = 800; //frame width
    private final int height = 800; //frame height
    private double mapxMin; //x minimum mapping
    private double mapxMax; //x maximum in mapping
    private double mapyMin; //y minimum in mapping
    private double mapyMax; //y maxmum in mapping
    private double Cx, Cy; //for fixed complex number
    boolean status = true;
    private int iterations; //no of checking times 
    private static int temp = 1;
    private static int shape = 0;

    Mandelbrot manb = null;
    Julia jul = null;

    public static void main(String[] args) {
        double xMin = 0, xMax = 0, yMin = 0, yMax = 0, x = 0, y = 0;
        int n = 0;
        JFrame frame = null; //creating frame from JFrame

        if (args.length >= 1) {
            //for Mandelbrot
            if (args[0].equals("Mandelbrot")) {
                
                switch (args.length - 1) { 
                    case 0:                    //default values
                        xMin = -0.5;
                        xMax = 0.5;
                        yMin = -0.1;
                        yMax = 1;
                        n = 1000;
                        temp = 1;
                        break;
                    case 4:                 //set passed values from command line arguments
                        xMin = Double.parseDouble(args[1]);
                        xMax = Double.parseDouble(args[2]);
                        yMin = Double.parseDouble(args[3]);
                        yMax = Double.parseDouble(args[4]);
                        n = 1000;
                        temp = 1;
                        break;
                    case 5:                 //set passed values from command line arguments
                        xMin = Double.parseDouble(args[1]);
                        xMax = Double.parseDouble(args[2]);
                        yMin = Double.parseDouble(args[3]);
                        yMax = Double.parseDouble(args[4]);
                        n = Integer.parseInt(args[5]);
                        temp = 1;
                        break;
                    default:
                        System.out.println("Not enough arguments for Mandelbrot");
                        temp = 0;
                        break;
                }
                if(temp==1){
                    frame = new JFrame("Mandelbrot");
                    frame.setContentPane(new Fractals(xMin, xMax, yMin, yMax, n));
                }
               
            } else if (args[0].equals("Julia")) {  //for Julia set
                //shape=1;
                switch (args.length - 1) {
                    case 0:            //default values
                        x = -0.4;
                        y = 0.6;
                        n=1000;
                        temp=2;
                        break;
                    case 3:    //set passed values form command line arguments
                        x = Double.parseDouble(args[1]);
                        y = Double.parseDouble(args[2]);
                        n=Integer.parseInt(args[3]);
                        temp=2;
                        break;
                    default:
                        System.out.println("Not enough arguments for Julia set");
                        temp = 0;
                        break;
                }
                if(temp==2){
                    frame = new JFrame("Julia");
                    frame.setContentPane(new Fractals(x, y, n));  //call constructor
                }
            }else{
				System.out.println("Not a Fractal");
				return;
			}
            if(temp==1 || temp==2){
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
			
			
			
        } else {
            System.out.println("Enter a valid fractal name, Julia or Mandelbrot");
            temp = 0;
        }

    }

    //constructor
    public Fractals(double x1, double x2, double y1, double y2, int n) {
        this.mapxMin = x1;
        this.mapxMax = x2;
        this.mapyMin = y1;
        this.mapyMax = y2;
        this.iterations = n;
        this.shape = 0;
        manb = new Mandelbrot(this.width, this.height, this.mapxMin, this.mapxMax, this.mapyMin, this.mapyMax, this.iterations);
        setPreferredSize(new Dimension(width, height));
    }

       //constructor overloading
    public Fractals(double x1, double x2, int n) {
        this.Cx = x1;
        this.Cy = x2;
        this.iterations = n;
        this.shape = 1;
        jul = new Julia(this.width, this.height, this.Cx, this.Cy,this.iterations);
        setPreferredSize(new Dimension(width, height));
    }
    
    //paint points
    private static void printPoint(Graphics2D frame, Color c, double x, double y) {
        frame.setColor(c);
        frame.draw(new Line2D.Double(x, y, x, y));
    }

    @Override
    public void paintComponent(Graphics g) {
        // call paintComponent from parent class 
        super.paintComponent(g);
        Color color=null; //creating color object
        for (int i = 0; i < 800; i++) {   //checking for all pixels
            for (int j = 0; j < 800; j++) {
                if (temp == 1) {
                    manb.map(i, j);  //call the mapping function in mandelbrot
                    status = manb.checking(manb.getX(), manb.getY()); //check points are in mandelbrot set or not
                    color = Color.getHSBColor((float) manb.getIter() * 10.0f / (float) iterations, 0.8f, 0.8f);
                } else if(temp==2)  {
                    jul.map(i, j);  //call the mapping function in Julia
                    
                    status = jul.checking(i, j);  //check points are in Julia set or not
                    color = Color.getHSBColor((float) jul.getIter() * 10.0f / (float) iterations, 1.0f, 1.0f);
                }
                if (status) {
                    printPoint((Graphics2D) g, Color.BLACK, i, j); //colour the points in the set
                } else {
                    printPoint((Graphics2D) g, color, i, j); //colour the points outside the set
                }
            }
        }
    }

}
