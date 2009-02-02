package eksperimenter.gui;



import java.awt.BorderLayout;

import java.awt.Dimension;

import java.awt.Graphics;

import java.awt.Toolkit;



import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;



import javax.swing.JCheckBox;

import javax.swing.JFrame;

import javax.swing.JPanel;

import javax.swing.SwingUtilities;





public class CellAut {

	public static void main (String args[]){

		SwingUtilities.invokeLater(new Runnable(){

											public void run(){

												new C();

											}

										});



	}

}









class C {

	

	static final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

	

	static ActionListener[] knappeAction = new CelleListener[8];

	

	final static int WIDTH = d.width;

	final static int HEIGHT = d.height-50;

	

	static boolean[][] FIELD = new boolean[WIDTH][HEIGHT];

	static boolean pattern[] = {false, false, false, false, false, false, false, false};

	static CellWindow gui = new CellWindow("Cellular Automata Hari");

	

	public static void updateField(){

		C.FIELD[WIDTH/2][0] = true; 

		

		for (int j = 1; j < C.FIELD[0].length-1; j++) {

			for (int i = 1; i < C.FIELD.length-1; i++) {

				

				C.FIELD[i][j] = false;

				

				if(C.pattern[0] == true){ // 000

					if(			C.FIELD[i-1][j-1] == false

							&&	C.FIELD[i][j-1] == false

							&&	C.FIELD[i+1][j-1] == false

					){

						C.FIELD[i][j] = true;

						

					}

				}

				if(C.pattern[1] == true){ // 001

					if(			C.FIELD[i-1][j-1] == false

							&&	C.FIELD[i][j-1] == false

							&&	C.FIELD[i+1][j-1] == true

					){

						C.FIELD[i][j] = true;

					}

				}

				if(C.pattern[2] == true){ // 010

					if(			C.FIELD[i-1][j-1] == false

							&&	C.FIELD[i][j-1] == true

							&&	C.FIELD[i+1][j-1] == false

					){

						C.FIELD[i][j] = true;

					}

				}

				if(C.pattern[3] == true){ // 011

					if(			C.FIELD[i-1][j-1] == false

							&&	C.FIELD[i][j-1] == true

							&&	C.FIELD[i+1][j-1] == true

					){

						C.FIELD[i][j] = true;

					}

				}

				if(C.pattern[4] == true){ // 100

					if(			C.FIELD[i-1][j-1] == true

							&&	C.FIELD[i][j-1] == false

							&&	C.FIELD[i+1][j-1] == false

					){

						C.FIELD[i][j] = true;

					}

				}

				if(C.pattern[5] == true){ // 101

					if(			C.FIELD[i-1][j-1] == true

							&&	C.FIELD[i][j-1] == false

							&&	C.FIELD[i+1][j-1] == true

					){

						C.FIELD[i][j] = true;

					}

				}

				if(C.pattern[6] == true){ // 110

					if(			C.FIELD[i-1][j-1] == true

							&&	C.FIELD[i][j-1] == true

							&&	C.FIELD[i+1][j-1] == false

					){

						C.FIELD[i][j] = true;

					}

				}

				if(C.pattern[7] == true){ // 111

					if(			C.FIELD[i-1][j-1] == true

							&&	C.FIELD[i][j-1] == true

							&&	C.FIELD[i+1][j-1] == true

					){

						C.FIELD[i][j] = true;

					}

				}

			}

		}

	}



	public static void update() {

		gui.update();

		

	}

}





class CelleListener implements ActionListener {

	private int bit;

	

	public CelleListener(int i) {

		bit = i;

	}



	

	public void actionPerformed(ActionEvent e) {

		boolean b = C.pattern[bit];

		C.pattern[bit] = !b;

		

		SwingUtilities.invokeLater(new Runnable(){

			public void run(){

				C.update();

			}

			

		});

		

	}

	

}



class CellWindow extends JFrame{

	/**

	 * 

	 */

	private static final long serialVersionUID = 5454294975696551471L;

	CellDrawWindow cdw;

	

	

	public CellWindow(String s) {

		setTitle(s);

		

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		

		JPanel p = new JPanel();

		p.setLayout(new BorderLayout());

		

		

		cdw = new CellDrawWindow();

		CellCheckRow ccr = new CellCheckRow();

		





		

		p.add(ccr, BorderLayout.NORTH);

		p.add(cdw, BorderLayout.CENTER);

		

		

		add(p);

		

		

		pack();

		

		setVisible(true);

	}





	public void update() {

		cdw.update();

		

	}	

}



class CellCheckRow extends JPanel{

	/**

	 * 

	 */

	private static final long serialVersionUID = -5163975998098542489L;

	/**

	 * 

	 */

	JCheckBox[] boxrow = new JCheckBox[8];

	CellCheckRow() {

		for(int i = boxrow.length-1; i >= 0; i--){

			boxrow[i] = new JCheckBox(toBinWithLeadingZeros(i), C.pattern[i]);

			boxrow[i].addActionListener(new CelleListener(i));

			add(boxrow[i]);

		}

		

	}

	

	String toBinWithLeadingZeros(int i){

		String s = Integer.toBinaryString(i);

		

		while(s.length()<3){

			s = new String(0 + s);

		}

		return s;

	}

}



class CellDrawWindow extends JPanel{

	private static final long serialVersionUID = 8712002530360204636L;





	public CellDrawWindow() {

	setPreferredSize(new Dimension(C.WIDTH, C.HEIGHT));

	

	}



	public void paintComponent(Graphics g) {

		C.updateField();

		super.paintComponent(g);

		

		for (int i = 0; i < C.FIELD.length; i++) {

			for (int j = 0; j < C.FIELD[i].length; j++) {

				if(C.FIELD[i][j] == true){

					g.drawLine(i, j, i, j);

					

				}

			}

			

		}

		

	}

	

	public void update(){

		C.updateField();

		this.updateUI();

	}

}
