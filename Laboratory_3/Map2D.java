/**
 * Этот класс представляет собой простую двумерную карту, составленную из квадратных ячеек. 
 * Каждая ячейка определяет "стоимость" прохождения этой ячейки.
 **/
public class Map2D
{
    /** Ширина карты **/
    private int width;

    /** Высота карты **/
    private int height;

    /**
     * Фактические данные карты, по которым должен ориентироваться алгоритм поиска пути
     **/
    private int[][] cells;

    /** Начальное местоположение для выполнения поиска пути A* **/
    private Location start;

    /** Конечное местоположение для выполнения поиска пути A* **/
    private Location finish;


    /** Создание новой 2D карты с указанными шириной и высотой **/
    public Map2D(int width, int height)
    {
        if (width <= 0 || height <= 0)
        {
            throw new IllegalArgumentException(
                    "width and height must be positive values; got " + width +
                            "x" + height);
        }

        this.width = width;
        this.height = height;

        cells = new int[width][height];

        // Составляем некоторые координаты для начала и конца
        start = new Location(0, height / 2);
        finish = new Location(width - 1, height / 2);
    }


    /**
     * Этот вспомогательный метод проверяет указанные координаты, чтобы увидеть, находятся ли они
     * в пределах карты. Если координаты находятся за пределами карты, метод генерирует исключение 
     * <code>IllegalArgumentException</code>
     **/
    private void checkCoords(int x, int y)
    {
        if (x < 0 || x > width)
        {
            throw new IllegalArgumentException("x must be in range [0, " +
                    width + "), got " + x);
        }

        if (y < 0 || y > height)
        {
            throw new IllegalArgumentException("y must be in range [0, " +
                    height + "), got " + y);
        }
    }

    /** Возвращаем ширину карты **/
    public int getWidth()
    {
        return width;
    }

    /** Возвращаем длину карты **/
    public int getHeight()
    {
        return height;
    }

    /**
     * Возвращаем true, если указанные координаты содержатся в области карты
     **/
    public boolean contains(int x, int y)
    {
        return (x >= 0 && x < width && y >= 0 && y < height);
    }


    /** Возвращаем true, если местоположение содержится в области карты **/
    public boolean contains(Location loc)
    {
        return contains(loc.xCoord, loc.yCoord);
    }

    /** Возвращаем сохраненное значение стоимости для указанной ячейки **/
    public int getCellValue(int x, int y)
    {
        checkCoords(x, y);
        return cells[x][y];
    }

    /** Возвращаем сохраненное значение стоимости для указанной ячейки **/
    public int getCellValue(Location loc)
    {
        return getCellValue(loc.xCoord, loc.yCoord);
    }

    /** Устанавливаем значение стоимости для указанной ячейки **/
    public void setCellValue(int x, int y, int value)
    {
        checkCoords(x, y);
        cells[x][y] = value;
    }

    /**
     * Возвращаем начальное местоположение карты. 
     * Это точка, где начинается сгенерированный путь.
     **/
    public Location getStart()
    {
        return start;
    }

    public void setStart(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc cannot be null");

        start = loc;
    }

    /**
     * Возвращаем конечное местоположение карты. 
     * Это точка, где сгенерированный путь заканчивается
     **/
    public Location getFinish()
    {
        return finish;
    }

    public void setFinish(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc cannot be null");

        finish = loc;
    }
}
