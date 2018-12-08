# Fractals
Include Fractals like Mandelbrot and Julia which is based complex number operations.

The Mandelbrot set: In mathematics Mandelbrot set is defined as the set of complex numbers C such
that: Z n+1 = Z n2 +C, starting with Z 0 = 0 remains bounded when n reach infinity. In other words if for
some C the above equation remains bounded for any number of iterations then that C is in the
Mandelbrot set. There is a mathematical proof which shows that if ABS(Z n ) > 2 the above equation will
diverge hence C is not in the Mandelbrot set. Some complex numbers will be in the Mandelbrot set and
some are not. In the basic case one can assign one color (say black) to the complex numbers in the set
and another colour (say white) to the ones which are not.
In any case all Mandelbrot numbers are within a region of the complex plane; -1 < real part < 1 and -1<
complex part<1. We call this the region of interest which you should be able to set.

The Julia set: The Julia set is similar to the Mandelbrot set in that it uses the same equation Z n+1 =
Z n2 +C but Z 0 is the point in the complex plane corresponding to the pixel and C is a constant. The rest
of the computation is the same. If one types; java Fractal Julia -0.5 0.156 1000 then the Julia set for 
C = -0.5 + 0.156i with 1000 iterations for each point is plotted.
