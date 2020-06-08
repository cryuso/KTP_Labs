import java.util.HashMap;
import java.util.HashSet;


/**
 * Этот класс содержит реализацию алгоритма поиска пути A*. 
 * Алгоритм реализован как статический метод, поскольку алгоритм поиска пути не должен поддерживать 
 * никакого состояния между вызовами алгоритма.
 */
public class AStarPathfinder
{
    /**
     * Эта константа содержит максимальный предел отсечки для стоимости путей. 
     * Если конкретная путевая точка превышает этот предел стоимости, 
     * путевая точка отбрасывается.
     **/
    public static final float COST_LIMIT = 1e6f;


    /**
     * Попытки вычислить путь перемещения, указанный между начальным и конечным местоположениями карты. 
     * Если путь может быть найден, то возвращается путевая точка шага <em>final</em>; 
     * эта точка может быть использована для перехода назад к начальной точке. Если путь не может быть найден, 
     * возвращается <code>null</code>.
     **/
    public static Waypoint computePath(Map2D map)
    {
        // Переменные, необходимые для поиска А*
        AStarState state = new AStarState(map);
        Location finishLoc = map.getFinish();

        // Установка начальной точки, чтобы начать поиск A*
        Waypoint start = new Waypoint(map.getStart(), null);
        start.setCosts(0, estimateTravelCost(start.getLocation(), finishLoc));
        state.addOpenWaypoint(start);

        Waypoint finalWaypoint = null;
        boolean foundPath = false;

        while (!foundPath && state.numOpenWaypoints() > 0)
        {
            // Поиск "лучшей" точки на данный момент
            Waypoint best = state.getMinOpenWaypoint();

            // Если "лучшая" точка - финишная, то мы закончили
            if (best.getLocation().equals(finishLoc))
            {
                finalWaypoint = best;
                foundPath = true;
            }

            // Добавление/обновление всех соседних точек текущего "лучшего" местоположения. 
            // Это равносильно выполнению всех «следующих шагов» из этого места
            takeNextStep(best, state);

            // Перемещение этого места из "открытого" списка в "закрытый"
            state.closeWaypoint(best.getLocation());
        }

        return finalWaypoint;
    }

    /**
     * Этот статический вспомогательный метод берет путевую точку и генерирует все 
     * действительные «последующие шаги» из этой путевой точки. Новые путевые точки 
     * добавляются в коллекцию «открытых путевых точек» переданного объекта состояния A*
     **/
    private static void takeNextStep(Waypoint currWP, AStarState state)
    {
        Location loc = currWP.getLocation();
        Map2D map = state.getMap();

        for (int y = loc.yCoord - 1; y <= loc.yCoord + 1; y++)
        {
            for (int x = loc.xCoord - 1; x <= loc.xCoord + 1; x++)
            {
                Location nextLoc = new Location(x, y);

                // If "next location" is outside the map, skip it.
                if (!map.contains(nextLoc))
                    continue;

                // If "next location" is this location, skip it.
                if (nextLoc == loc)
                    continue;

                // If this location happens to already be in the "closed" set
                // then continue on with the next location.
                if (state.isLocationClosed(nextLoc))
                    continue;

                // Make a waypoint for this "next location."

                Waypoint nextWP = new Waypoint(nextLoc, currWP);

                // OK, we cheat and use the cost estimate to compute the actual
                // cost from the previous cell.  Then, we add in the cost from
                // the map cell we step onto, to incorporate barriers etc.

                float prevCost = currWP.getPreviousCost() +
                        estimateTravelCost(currWP.getLocation(),
                                nextWP.getLocation());

                prevCost += map.getCellValue(nextLoc);

                // Skip this "next location" if it is too costly.
                if (prevCost >= COST_LIMIT)
                    continue;

                nextWP.setCosts(prevCost,
                        estimateTravelCost(nextLoc, map.getFinish()));

                // Add the waypoint to the set of open waypoints.  If there
                // happens to already be a waypoint for this location, the new
                // waypoint only replaces the old waypoint if it is less costly
                // than the old one.
                state.addOpenWaypoint(nextWP);
            }
        }
    }

    /**
     * Estimates the cost of traveling between the two specified locations.
     * The actual cost computed is just the straight-line distance between the
     * two locations.
     **/
    private static float estimateTravelCost(Location currLoc, Location destLoc)
    {
        int dx = destLoc.xCoord - currLoc.xCoord;
        int dy = destLoc.yCoord - currLoc.yCoord;

        return (float) Math.sqrt(dx * dx + dy * dy);
    }
}
