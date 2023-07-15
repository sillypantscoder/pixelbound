package com.sillypantscoder.pixelbound;

import java.awt.BorderLayout;
// import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
// import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
// import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/**
 * A panel that automatically redraws itself.
 */
public abstract class RepaintingPanel extends JPanel {
	private static final long serialVersionUID = 7148504528835036003L;
	protected static JFrame frame;
	public abstract Surface painter(int width, int height);
	/**
	* Called by the runtime system whenever the panel needs painting.
	*/
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Rectangle bounds = g.getClipBounds();
		g.drawImage(painter((int)(bounds.getWidth()), (int)(bounds.getHeight())).img, 0, 0, new DummyImageObserver());
	}
	public abstract void mouseClicked(MouseEvent e);
	public abstract void mouseMoved(MouseEvent e);
	public abstract void keyPressed(KeyEvent e);
	public static void startAnimation() {
		SwingWorker<Object, Object> sw = new SwingWorker<Object, Object>() {
			@Override
			protected Object doInBackground() throws Exception {
				while (true) {
					frame.revalidate();
					frame.getContentPane().repaint();
					Thread.sleep(16);
				}
			}
		};

		sw.execute();
	}
	public ArrayList<String> keys;

	/**
	* A little driver in case you want a stand-alone application.
	*/
	public void run() {
		//SwingUtilities.invokeLater(() -> {
			frame = new JFrame("pixelbound");
			frame.setSize(512, 512);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().add(this, BorderLayout.CENTER);
			frame.setVisible(true);
			startAnimation();
			// Start mouse listeners
			RepaintingPanelMouseListener ml = new RepaintingPanelMouseListener(this);
			this.addMouseListener(ml);
			RepaintingPanelMouseMotionListener mml = new RepaintingPanelMouseMotionListener(this);
			this.addMouseMotionListener(mml);
			// Add keyboard listener
			frame.addKeyListener(new RepaintingPanelKeyListener(this));
		//});
		this.keys = new ArrayList<String>();
	}
}
class RepaintingPanelMouseListener implements MouseListener {
	protected RepaintingPanel srcPanel;
	public RepaintingPanelMouseListener(RepaintingPanel p) {
		srcPanel = p;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) { }

	@Override
	public void mouseEntered(MouseEvent arg0) { }

	@Override
	public void mouseExited(MouseEvent arg0) { }

	@Override
	public void mousePressed(MouseEvent arg0) {
		srcPanel.mouseClicked(arg0);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) { }

}
class RepaintingPanelMouseMotionListener implements MouseMotionListener {
	protected RepaintingPanel srcPanel;
	public RepaintingPanelMouseMotionListener(RepaintingPanel p) {
		srcPanel = p;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) { }

	@Override
	public void mouseMoved(MouseEvent arg0) {
		srcPanel.mouseMoved(arg0);
	}
}
class RepaintingPanelKeyListener implements KeyListener {
	protected RepaintingPanel srcPanel;
	public RepaintingPanelKeyListener(RepaintingPanel p) {
		srcPanel = p;
	}

	@Override
	public void keyPressed(KeyEvent arg0) { String text = KeyEvent.getKeyText(arg0.getKeyCode()); srcPanel.keyPressed(arg0); if (srcPanel.keys.indexOf(text) == -1) { srcPanel.keys.add(text); } }

	@Override
	public void keyTyped(KeyEvent arg0) { }

	@Override
	public void keyReleased(KeyEvent arg0) { srcPanel.keys.remove(KeyEvent.getKeyText(arg0.getKeyCode())); }

}