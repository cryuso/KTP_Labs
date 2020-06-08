import java.awt.geom.Rectangle2D;

/**
 * Этот класс предоставляет общий интерфейс и операции для генераторов фракталов, 
 * которые можно просмотреть в FractalExplorer.
 **/
public abstract class FractalGenerator {

    /**
     * Эта статическая вспомогательная функция принимает целочисленную координату 
     * и преобразует ее в значение двойной точности, соответствующее определенному диапазону. 
     * Она используется для преобразования координат пикселей в значения 
     * двойной точности для вычисления фракталов и тд.
     *
     * @param rangeMin минимальное значение диапазона с плавающей запятой
     * @param rangeMax максимальное значение диапазона с плавающей запятой
     *
     * @param size размер измерения, из которого берется пиксельная координата
     *
     * @param coord координата, чтобы вычислить значение двойной точности
     * Координата должна находиться в диапазоне [0, размер]
     **/
    public static double getCoord(double rangeMin, double rangeMax,
        int size, int coord) {

        assert size > 0;
        assert coord >= 0 && coord < size;

        double range = rangeMax - rangeMin;
        return rangeMin + (range * (double) coord / (double) size);
    }


    /**
     * Устанавливаем указанный прямоугольник, который будет содержать начальный диапазон, подходящий
     * для генерируемого фрактала
     **/
    public abstract void getInitialRange(Rectangle2D.Double range);


    /**
     * Обновляем текущий диапазон с центром в указанных координатах, 
     * а также увеличиваем или уменьшаем с помощью указанного коэффициента масштабирования
     **/
    public void recenterAndZoomRange(Rectangle2D.Double range,
        double centerX, double centerY, double scale) {

        double newWidth = range.width * scale;
        double newHeight = range.height * scale;

        range.x = centerX - newWidth / 2;
        range.y = centerY - newHeight / 2;
        range.width = newWidth;
        range.height = newHeight;
    }


    /**
     * Учитывая координату <em>x</em>+<em>iy</em> в комплексной плоскости,
     * вычисляем и возвращаем количество итераций, прежде чем фрактальная
     * функция покинет ограничивающую область для этой точки. Точка, которая не 
     * доходит до достижения предела итерации, указывается с результатом -1
     **/
    public abstract int numIterations(double x, double y);
}
