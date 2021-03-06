import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JImageDisplay extends JPanel {
	
	// Состояния
	private int width;
	private int height;
	
	// Картинка в буффере (на ней будем рисовать)
	private BufferedImage bImg;
	
	// Рычаг для рисования
	private Graphics g;
	
	/**
	* Конструкторы
	**/
	public JImageDisplay() {
		
	}
	
	public JImageDisplay(int size) {
		this(size, size);
	}
	
	public JImageDisplay(int width, int height) {
		this.width = width;
		this.height = height;	
		
		// Создаем буферную картинку
		bImg = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
		
		// Получаем объект Graphics от объекта BufferedImage
		g = bImg.getGraphics();
		
		// Установка базового изображения
		this.setStartImage();
		this.repaint();
	}
	
	/**
	* Процесс рисования
	**/
	public void drawPixel(int x, int y, Color color) {
		// Устанавливаем необходимый цвет
		g.setColor(color);
		
		// Закрашиваем необходимый пиксель
		g.fillRect(x, y, 1, 1);
		
		this.repaint();
	}
	
	/**
	* Ответ на рисование на элементе, также вызывается при активации frame, и при вызове repaint()
	**/
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bImg, 0, 0, null);
    }
	
	/**
	* "Очистка" пикселей элемента - закрашиваение всего пространства чёрным прямоугольником
	**/
	public void clearImage() {
		g.setColor(Color.black);
		g.fillRect(0, 0, bImg.getWidth(), bImg.getHeight());
		this.repaint();
	}
	
	public void setStartImage() {
		g.setColor(Color.orange);
		g.fillRect(bImg.getWidth() / 2 - 51, bImg.getHeight() / 2 - 51, 100, 100);	
		g.setColor(Color.white);
		g.fillRect(bImg.getWidth() / 2 - 25, bImg.getHeight() / 2 - 25, 50, 50);
		
		g.setColor(Color.orange);
		g.fillRect(bImg.getWidth() / 2 - 13, bImg.getHeight() / 2 - 13, 25, 25);	
		g.setColor(Color.white);
		g.fillRect(bImg.getWidth() / 2 - 7, bImg.getHeight() / 2 - 7, 13, 13);
		this.repaint();
	}
}
