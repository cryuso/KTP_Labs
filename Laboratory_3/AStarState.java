/**
 * Этот класс хранит базовое состояние, необходимое алгоритму A* для вычисления
 * пути по карте. Это состояние включает в себя набор "открытых" и "закрытых" путевых точек.
 * Кроме того, этот класс обеспечивает основные операции, необходимые алгоритму поиска пути 
 * для выполнения его обработки
 **/
 
 import java.util.*;
 
public class AStarState
{
    /** Ссылка на карту перемещения алгоритма A* **/
    private Map2D map;

	private HashMap<Location, Waypoint> openVertex = new HashMap<Location, Waypoint>();
	private HashMap<Location, Waypoint> closeVertex = new HashMap<Location, Waypoint>();


    /**
     * Инициализируем новый объект состояния для использования алгоритма поиска пути A*
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /** Возвращаем карту, по которой перемещается навигатор A* **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * Этот метод сканирует все открытые путевые точки и возвращает путевую точку
     * с минимальными общими затратами. Если нет открытых путевых точек, этот метод
     * возвращает <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint() {
		
		//System.out.println("getMinOpenWaypoint called!");
        if (openVertex.isEmpty()) return null;	
		
		float minCost = 3.4e+38f;
		Waypoint minCostObject = null;
		
		ArrayList<Waypoint> values = new ArrayList<Waypoint>(openVertex.values());
		for (Waypoint element : values) {
			if (element.getTotalCost() < minCost) {
				minCost = element.getTotalCost();
				minCostObject = element;
			}
		}
		
		//System.out.println("\tminWaypoint coords: " + minCostObject.getLocation().xCoord + ", " + minCostObject.getLocation().yCoord + ", cost = " + minCost);
		return minCostObject;
    }

    /**
     * Метод добавляет путевую точку К (или обновляет уже существующую точку
     * в коллекции "открытые" путевые точки). Если еще нет "открытых"
     * путевых точек на новом местоположении, то новая путевая точка просто
     * добавлеется в коллекцию. Но, если уже имеется путевая точка на
     * новом расположении, новая путевая точка заменяет старую,
     * если новое значение путевой точки (стоимость) меньше текущего.
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
		//System.out.println("addOpenWaypoint called!");

        // Получаем все ключи из HashMap
		ArrayList<Location> locations = new ArrayList<Location>(openVertex.keySet());

        // Получаем местоположение входящего Waypoint
		Location newLoc = newWP.getLocation();
		//System.out.println("\tnew waypoint coords: " + newLoc.xCoord + ", " + newLoc.yCoord);

        //Просмотр всех ключей из locations
		for (Location index : locations) {
			if (newLoc.equals(index)) {
                // Сравнение стоимостей, т.к. index == newLoc

                // Если стоимость пути до newWP меньше стоимости пути до вершины с таким же местоположением - заменяем
				Waypoint oldWP = openVertex.get(index);
				//System.out.println("\tthere is equal point: " + index.xCoord + ", " + index.yCoord);
				double oldCost = oldWP.getPreviousCost();
				double newCost = newWP.getPreviousCost();
				//System.out.println("\told cost: " + oldCost + ", new cost: " + newCost);
				
				if (newCost < oldCost) {
					openVertex.put(newLoc, newWP);
					return true;
				}

                // Если новая вершина не подходит
				return false;
				
			}
		}
		
		openVertex.put(newLoc, newWP);
		//System.out.println("\tnew point opened");
		return true;
    }


    /** Возвращаем текущее количество открытых путевых точек **/
    public int numOpenWaypoints()
    {
        return openVertex.size();
    }


    /**
     * Метод перемещает путевую точку в указанное место из
     ** "открытого" списока в "закрытый" список
     **/
    public void closeWaypoint(Location loc)
    {
		//System.out.println("Closing waypoint: " + loc.xCoord + ", " + loc.yCoord);
        Waypoint wp = openVertex.get(loc);
		openVertex.remove(loc);
		closeVertex.put(loc, wp);
    }

    /**
     * Возвращаем true, если коллекция "закрытых" путевых точек содержит путевую точку
     ** для указанного места
     **/
    public boolean isLocationClosed(Location loc)
    {
       return openVertex.containsKey(loc);
    }
}
