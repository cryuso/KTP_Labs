import java.awt.geom.Rectangle2D;

public class Tricorn extends FractalGenerator {
	
	public static final int MAX_ITERATIONS = 1000;
	
	public Tricorn() {
		
	}
	
	public void getInitialRange(Rectangle2D.Double range) {
		range.x = -2;
		range.y = -2;
		range.width = 4;
		range.height = 4;
	}
	
	/**
	* Если значение Z в рекуррентной формуле Z(n+1)=Z(n)^2+с стремится к бесконечности - 
	* точка находится за пределами фрактала.
	* Если значение Z колеблется в пределах фрактала: |Z|<2, значит точка принадлежит множеству Mandelbrot.
	* Представим формулу множества Mandelbrot как итеративную последовательность значений координат на плоскости, тогда
	* формула принимает следующий вид: X(n+1) X(n)^2 - Y(n)^2 + X(0); Y(n+1) = 2 *  X(n) * Y(n) + Y(0);
	* А ограничение примет вид: X(n)^2 + Y(n)^2 < 4
	**/
	
	public int numIterations(double x, double y) {
		int iteration = 0;
		double realPart = 0;
		double imaginaryPart = 0;
		
		while ((iteration < MAX_ITERATIONS) && ((realPart * realPart + imaginaryPart * imaginaryPart) < 4)) {
			double rp = realPart * realPart - imaginaryPart * imaginaryPart + x;
			double ip = -2 * realPart * imaginaryPart + y;
			realPart = rp;
			imaginaryPart = ip;
			iteration += 1;
		}
		
		// -1 - точка принадлежит последовательности, iteration - не принадлежит. Также определяем, на сколько она близко.
		if (iteration == MAX_ITERATIONS) 
			return -1;
		else 
			return iteration;
	}
	
	public String toString() {
		return "Tricorn";
	}
}
