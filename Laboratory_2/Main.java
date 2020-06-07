public class Main {
    public static void main (String[] args) {
        double x,y,z;
        DecimalFormat df = new DecimalFormat("###.##");
     /**ввод данных**/
    Scanner in = new Scanner(System.in);
    /**ввод координат для точки 1**/
        System.out.println("введите координаты первой точки");
    System.out.println("введите координату X");
    x=in.nextDouble();
    System.out.println("введите координату Y");
    y=in.nextDouble();
    System.out.println("введите координату Z");
    z=in.nextDouble();
    Point3d onepoint = new Point3d(x, y, z); //создание точки 1
 /**ввод координат для точки 2**/
     System.out.println("введите координаты второй точки");
     System.out.println("введите координату X");
     x=in.nextDouble();
     System.out.println("введите координату Y");
     y=in.nextDouble();
     System.out.println("введите координату Z");
     z=in.nextDouble();
     Point3d twopoint = new Point3d(x, y, z); //создание точки 2
 /**ввод координат точки 3**/
         System.out.println("введите координаты третьей точки");
     System.out.println("введите координату X");
     x=in.nextDouble();
     System.out.println("введите координату Y");
     y=in.nextDouble();
     System.out.println("введите координату Z");
     z=in.nextDouble();
     Point3d threepoint = new Point3d(x, y, z); //создание точки 3
 /**проверка точек на равенство**/
 if ((Point3d.Check3D(onepoint,twopoint) == false)
       &&(Point3d.Check3D(onepoint,threepoint) == false)
         &&(Point3d.Check3D(twopoint,threepoint) == false))
 {System.out.println("точки не равны между собой, продолжение работы");}
 else {System.out.println("точки имеют одинаковые координаты, остановка программы"); System.exit(0);}
 /**вычисление площади треугольника**/
 double P = Point3d.computeArea(onepoint,twopoint,threepoint);
 System.out.println("площадь треугольника равна = " + df.format(P));

    }
}
