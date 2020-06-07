public class Point2d {
            /**x координата**/
            private double xCoord;
            /**y координата**/
            private double yCoord;
    /**конструктор инициализации**/
    public Point2d (double x, double y){
        xCoord = x;
        yCoord = y;
    }
    /**конструктор по умолчанию**/
    public Point2d(){
        this (0,0);
    }
        /**возвращение координаты x**/
    public double getX2(){
        return xCoord;
    }
        /**возвращение координаты y**/
        public double getY2(){
            return yCoord;
        }
    /**установка значения координаты x**/
    public void setX2(double val){
        xCoord = val;
    }
    public void setY2(double val) {
        yCoord = val;
    }
    public static boolean Check2D(Point2d first, Point2d two){
        if ((first.getX2() == two.getX2()) && (first.getY2() == two.getY2())){
            return true;
        }
        else {return false;}
    }
}
