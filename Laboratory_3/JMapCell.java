import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;


/**
 * Этот класс представляет собой пользовательский компонент Swing для представления отдельной ячейки на 2D-карте.
 * Ячейка имеет несколько различных типов состояний, но основное состояние заключается в том, 
 * является ли ячейка проходимой или нет
 */
public class JMapCell extends JComponent
{
    private static final Dimension CELL_SIZE = new Dimension(12, 12);

    /** True значит, что ячейка является конечной точкой, т.е. началом или концом **/
    boolean endpoint = false;


    /** True значит, что ячейка проходима и наоборот **/
    boolean passable = true;

    /**
     * True значит, что данная ячейка является частью пути между началом и концом
     **/
    boolean path = false;

    /**
     * Построим новую ячейку карты с указанной «проходимостью». 
     * Ввод true значит, что ячейка проходима.
     **/
    public JMapCell(boolean pass)
    {
        // Установливаем предпочтительный размер ячейки, чтобы управлять начальным размером окна
        setPreferredSize(CELL_SIZE);

        setPassable(pass);
    }

    /** Создаем новую ячейку карты, которая по умолчанию проходима **/
    public JMapCell()
    {
        // Вызываем другой конструктор, указав true для «Passable»
        this(true);
    }

    /** Помечаем эту ячейку как начальную или конечную **/
    public void setEndpoint(boolean end)
    {
        endpoint = end;
        updateAppearance();
    }

    /**
     * Устанавливаем эту ячейку как проходимую или не проходимую. 
     * Ввод true помечает ячейку как проходимую; ввод false помечает как непроходимую.
     **/
    public void setPassable(boolean pass)
    {
        passable = pass;
        updateAppearance();
    }

    /** Возвращаем true, если эта ячейка проходима, или false в противном случае **/
    public boolean isPassable()
    {
        return passable;
    }

    /** Переключаем текущее «passible» состояние ячейки карты **/
    public void togglePassable()
    {
        setPassable(!isPassable());
    }

    /** Помечаем эту ячейку как часть пути, обнаруженного алгоритмом A* **/
    public void setPath(boolean path)
    {
        this.path = path;
        updateAppearance();
    }

    /**
     * Этот вспомогательный метод обновляет цвет фона, 
     * чтобы соответствовать текущему внутреннему состоянию ячейки
     **/
    private void updateAppearance()
    {
        if (passable)
        {
            // Проходимая клетка. Указываем её состояние с помощью границ.
            setBackground(Color.WHITE);

            if (endpoint)
                setBackground(Color.CYAN);
            else if (path)
                setBackground(Color.GREEN);
        }
        else
        {
            // Непроходиммая клетка. Делаем ее полностью красной.
            setBackground(Color.RED);
        }
    }

    /**
     * Реализация метода "рисования" для заполнения цвета фона в ячейке карты
     **/
    protected void paintComponent(Graphics g)
    {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
