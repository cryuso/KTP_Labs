import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.event.*;

public class FractalExplorer {
	
	private int width;
	private int height;
	
	private JImageDisplay display;
	private Rectangle2D.Double range;
	private JFrame frame;
	private JButton button;
	private Mandelbrot mandelbrot;
	
	/*
	* Классы-слушатели событий кнопки и мыши
	*/
	private class resetButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
			// Сбрасываем границы фрактала и вызоваем функции отрисовки
			mandelbrot.getInitialRange(range);
			FractalExplorer.this.drawFractal();
		}
	}
	
	private class mouseClickListener implements MouseListener {
		
		// Событие нажатия на кнопку мыши
		public void mouseClicked(MouseEvent e) {
			//System.out.println("Mouse button clicked!");
			
			// Координаты нажатия мыши
			int x = e.getX();
			int y = e.getY();
			
			// Переводим координаты в комплексную плоскость
			double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, display.getWidth(), x);
			double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, display.getHeight(), y);
			
			// Нажатие левой кнопкой мыши
			if (e.getButton() == MouseEvent.BUTTON1) {
				// Масштабирование
				mandelbrot.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
			}
			
			// Нажатие правой кнопкой мыши
			if (e.getButton() == MouseEvent.BUTTON3) {
				// Масштабирование
				mandelbrot.recenterAndZoomRange(range, xCoord, yCoord, 1.5);
			}
			
			// Перерисовываем фрактал
			FractalExplorer.this.drawFractal();	
		}
		
	
		public void mouseEntered(MouseEvent e) {}
 
        public void mouseExited(MouseEvent e) {}
 
        public void mousePressed(MouseEvent e) {}
 
        public void mouseReleased(MouseEvent e) {}
	}
	
	
	/*
	* Конструкторы
	*/
	public FractalExplorer() {
		this(600);
	}
	
	public FractalExplorer(int size) {
		this(size, size);
	}
	
	public FractalExplorer(int width, int height) {
		this.width = width;
		this.height = height;
		
		// Создаем объект, содержащий диапазон
		this.range = new Rectangle2D.Double();
		
		// Создаем объект Mandelbrot
		this.mandelbrot = new Mandelbrot();
		
		// Задаем предел фрактала
		mandelbrot.getInitialRange(range);	
	}
	
	/*
	* Создаем форму с компонентами
	*/
	public void createAndShowGUI() {
		// Создание формы
		this.frame = new JFrame("Fraktalz");
		this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.frame.setSize(this.width, this.height);
		this.frame.setResizable(false); 
		
		// Добавляем кнопки
		this.button = new JButton("Reset display");
		frame.getContentPane().add(BorderLayout.SOUTH, this.button);
		
		// Добавляем слушателя нажатия на кнопку
		button.addActionListener(new resetButtonListener());
		
		// Создаем панель рисования
		this.display = new JImageDisplay(this.frame.getWidth(), this.frame.getHeight());
		frame.getContentPane().add(BorderLayout.CENTER, this.display);
		
		// Добавляем слушателя нажатия мыши по элементу
		display.addMouseListener(new mouseClickListener());
		
		
		frame.setVisible(true);
	}
	
	/*
	* Отрисовываем фрактал.
	* В цикле идёт проход по всем пикселям и определяется, входит ли он в площадь фрактала.
	* Степень входа определяется цветом пикселя.
	*/
	public void drawFractal() {
		
		//System.out.println("Range = " + range.x + ", " + range.y + ", " + range.width + ", " + range.height + "\n");
		
		for (int x = 0; x < this.width; x++) {
			for (int y = 0; y < this.height; y++) {
				
				// Преобразование координат плоскости пикселей в координаты мнимой плоскости
				double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, display.getWidth(), x);
				double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, display.getHeight(), y);
				
				// Определяем вход точки в множество Мандельброта
				int numOfIter = mandelbrot.numIterations(xCoord, yCoord);
				
				// Логируем количество итераций каждой точки
				//if (numOfIter > 50)
					//System.out.println("x = " + x + ", y = " + y + ", xCoord = " + xCoord + ", yCoord = " + yCoord + ", iteration = " + numOfIter);
				
				int rgbColor;
				if (numOfIter != -1) {
					float hue = 0.7f + (float) numOfIter / 200f; 
					rgbColor = Color.HSBtoRGB(hue, 1f, 1f); 
          
				} 
				else {
					rgbColor = Color.HSBtoRGB(0, 0, 0); 
				}
				
				
				display.drawPixel(x, y, new Color(rgbColor));
				
			}
		}
	}
	
	/*
	* Точка входа
	*/
	public static void main(String[] args) {
		FractalExplorer explorer = new FractalExplorer(700);
		explorer.createAndShowGUI();
		explorer.drawFractal();
	}
}
