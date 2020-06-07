public class Main {
    public static void main (String[] args) {
        double x,y,z;
        DecimalFormat df = new DecimalFormat("###.##");
    Scanner in = new Scanner(System.in);
        System.out.println("введите координаты первой точки");
    System.out.println("Введите координату X");
    x=in.nextDouble();
    System.out.println("Введите координату Y");
    y=in.nextDouble();
    System.out.println("Введите координату Z");
    z=in.nextDouble();
    Point3d onepoint = new Point3d(x, y, z);//создание точки

     System.out.println("введите координаты второй точки");
     System.out.println("введите координату x");
     x=in.nextDouble();
     System.out.println("введите координату y");
     y=in.nextDouble();
     System.out.println("введите координату z");
     z=in.nextDouble();
     Point3d twopoint = new Point3d(x, y, z);//создание второй точки

         System.out.println("введите координаты третьей точки");
     System.out.println("Введите координату X");
     x=in.nextDouble();
     System.out.println("Введите координату Y");
     y=in.nextDouble();
     System.out.println("Введите координату Z");
     z=in.nextDouble();
     Point3d threepoint = new Point3d(x, y, z);//создание третьей точки

if ((Point3d.Check3D(onepoint,twopoint) == false)
       &&(Point3d.Check3D(onepoint,threepoint) == false)
         &&(Point3d.Check3D(twopoint,threepoint) == false))
 {System.out.println("точки не равны, программа продолжает работать");}
 else {System.out.println("точки имеют одинаковые координаты, работа программы остановлена"); System.exit(0);}

double P = Point3d.computeArea(onepoint,twopoint,threepoint);
 System.out.println("площадь треугольника равна = " + df.format(P));

    }
}
