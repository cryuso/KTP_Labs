/**
 * Этот класс представляет конкретное местоположение на 2D-карте. 
 * Координаты являются целочисленными значениями
 **/
public class Location
{
    /** x координата данной локации **/
    public int xCoord;

    /** y координата данной локации **/
    public int yCoord;

    /** Реализация метода equals() **/
    public boolean equals(Object obj) {
    if (obj instanceof Location) {
        // Приводим другой объект к типу Location, затем сравниваем. 
        // Возвращаем true, если они равны
        Location other = (Location) obj;
        if (xCoord == other.xCoord && yCoord == other.yCoord) {
            return true;
        }
    }
       // Если мы попали сюда, то они не равны. Возвращаем false.
       return false;
   }
	
	public int hashCode() {
		
		return ((xCoord + 1) * 100 + yCoord); 
	}

    /** Создаем новое местоположение со специальными координатами **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Создаем новое местоположение с координатами (0.0) **/
    public Location()
    {
        this(0, 0);
    }
}
