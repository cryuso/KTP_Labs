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

                // Если "следующее местоположение" находится за пределами карты, пропускаем его
                if (!map.contains(nextLoc))
                    continue;

                // Если «следующее местоположение» - текщее местоположение, пропускаем его
                if (nextLoc == loc)
                    continue;

                // Если это местоположение уже находится в "закрытом" наборе, 
                // тогда переходим к "следующему местоположению"
                if (state.isLocationClosed(nextLoc))
                    continue;

                // Делаем путевую точку для "следующего местоположения"

                Waypoint nextWP = new Waypoint(nextLoc, currWP);

                // "Обманываем" и используем оценку стоимости, чтобы вычислить фактическую стоимость из предыдущей ячейки. 
                // Затем мы добавляем стоимость из ячейки карты, на которую мы наступаем, для включения барьеров и тд
                float prevCost = currWP.getPreviousCost() +
                        estimateTravelCost(currWP.getLocation(),
                                nextWP.getLocation());

                prevCost += map.getCellValue(nextLoc);

                // Пропускаем "следующее местоположение", если оно "слишком дорогое"
                if (prevCost >= COST_LIMIT)
                    continue;

                nextWP.setCosts(prevCost,
                        estimateTravelCost(nextLoc, map.getFinish()));

                // Добавляем путевую точку в набор "открытых" путевых точек. 
                // Если для этого местоположения уже есть путевая точка, новая путевая точка заменяет эту старую путевую точку, 
                // если она "дешевле", чем старая.
                state.addOpenWaypoint(nextWP);
            }
        }
    }

    /**
     * Оценивает "стоимость" передвижения между двумя указанными точками
     * Фактическая стоимость - это прямое расстояние между двумя точками
     **/
    private static float estimateTravelCost(Location currLoc, Location destLoc)
    {
        int dx = destLoc.xCoord - currLoc.xCoord;
        int dy = destLoc.yCoord - currLoc.yCoord;

        return (float) Math.sqrt(dx * dx + dy * dy);
    }
}
