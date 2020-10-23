package GUI_Sudoku;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Sudoku_Logica.Celda;
import Sudoku_Logica.CeldaReloj;
import Sudoku_Logica.Reloj;
import Sudoku_Logica.Sudoku;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Component;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;


@SuppressWarnings("serial")
public class GUISudoku extends JFrame {

	private JPanel contentPane;
	private Sudoku Juego;
	private Reloj reloj;
	private Timer timer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUISudoku frame = new GUISudoku();
					frame.setVisible(true);
				} catch (Exception e) { e.printStackTrace(); }
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUISudoku() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		 
		Juego= new Sudoku();
		reloj=new Reloj();
		timer=new Timer();
		
		JPanel Menu = new JPanel();
		Menu.setBounds(10, 10, 240, 492);
		
		JPanel Tablero = new JPanel();
		Tablero.setBounds(260, 10, 492, 492);
		Tablero.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		contentPane.setLayout(null);
		contentPane.add(Menu);
		Menu.setLayout(null);
		Tablero.setVisible(false);
		Tablero.setEnabled(false);
		contentPane.add(Tablero);
		Tablero.setLayout(new GridLayout(Juego.getcantFilas(), 0, 0, 0));
		
		JLabel Cronometro = new JLabel("Tiempo:");
		Cronometro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Cronometro.setHorizontalAlignment(SwingConstants.CENTER);
		Cronometro.setBounds(10, 11, 90, 27);
		Menu.add(Cronometro);
		Cronometro.setBorder(null);
		
		JPanel PanelCronometro= new JPanel();
		PanelCronometro.setBounds(10, 49, 220, 91);
		PanelCronometro.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		Menu.add(PanelCronometro);
		PanelCronometro.setLayout(new GridLayout(1, 0, 0, 0));
		
		for (int i=0;i<8;i++) {
			MiLabelReloj labeldigito=new MiLabelReloj(reloj.getcelda(i));
			ImageIcon ImagenReloj=reloj.getcelda(i).getimagen().getim();
			PanelCronometro.add(labeldigito);
			labeldigito.addComponentListener(new ComponentAdapter() {
				public void componentResized(ComponentEvent e) {
					Redimensionar(labeldigito,ImagenReloj);
					labeldigito.setIcon(ImagenReloj);
				}
			});
		}
		
		TimerTask tarea=new TimerTask() {
			@Override
			public void run() {
				String s=Juego.getTiempo();
				char [] arr=s.toCharArray();
				char caracter;
				int i=0;
				for (Component l:PanelCronometro.getComponents()) {
					if (i<arr.length) {
						MiLabelReloj label=(MiLabelReloj) l;
						caracter=arr[i];
						if (!Character.toString(caracter).equals(":")) {
							int dig=Integer.parseInt(Character.toString(caracter));
							CeldaReloj celda=reloj.getcelda(i);
							reloj.actualizar(dig, i);
							ImageIcon imagen=celda.getimagen().getim();
							Redimensionar(label,imagen);
						}
						i++;
					}
				}
			}	
		};
		
		JButton btnJugar = new JButton("Jugar");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Juego.getestadoinicialcorrecto()) {
					Actualizar(Juego,Tablero);
					Juego.reiniciarHora();
					timer.schedule(tarea, 0, 1000);
					Tablero.setVisible(true);
					Tablero.setEnabled(true);
					btnJugar.setEnabled(false);
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "Error al inicializar el estado inicial");
					System.exit(0);
				}
			}
		});
		
		btnJugar.setBounds(10, 151, 220, 59);
		Menu.add(btnJugar);
		
		for (int i=0;i<Juego.getcantFilas();i++) {
			for (int j=0;j<Juego.getcantFilas();j++) {
				Celda c=Juego.getCelda(i, j);
				c.setfila(i);
				c.setcolumna(j);
				
				ImageIcon ImagenNumero= c.getimage().getim();
				MiLabel label=new MiLabel(c);
				Tablero.add(label);
				label.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
				
				
				label.addComponentListener(new ComponentAdapter() {
					public void componentResized(ComponentEvent e) {
						Redimensionar(label,ImagenNumero);
						label.setIcon(ImagenNumero);
					}
				});
				 // Si es una celda cuyo estado inicial es distinto de cero, corresponde al estado inical del tablero, no se deberia modificar y los marca con borde verde
				if (Juego.getCelda(i, j).getValor()!=0) {
					label.setBorder(BorderFactory.createLineBorder(Color.GREEN,2));
				}
				else {
					label.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							String cadena=JOptionPane.showInputDialog("Numero: ");
							if (cadena!=null) {
								try {
									int num=Integer.parseInt(cadena);
									if (num>9 || num<1) {
										JOptionPane.showMessageDialog(contentPane, "El numero no esta entre 1 y 9");
									}
									else {
										Juego.accionar(c, num);
										Actualizar(Juego,Tablero);
										if (Juego.gano()) {
											timer.cancel();
											JOptionPane.showMessageDialog(contentPane, "USTED A GANADO; su tiempo fue: "+Juego.getTiempo());
											Tablero.setEnabled(false);
											System.exit(0);
										}
									}
								} catch (NumberFormatException exception) { JOptionPane.showMessageDialog(contentPane, "Lo escrito no es un numero que esta entre 1 y 9");}
							}
						}
					});
				}
			}
		}
	}
	
	private void Actualizar(Sudoku Juego,JPanel Tablero) {
		for (Component l:Tablero.getComponents()) {
			MiLabel label=(MiLabel) l;
			ImageIcon imagen=label.getcelda().getimage().getim();
			Redimensionar(label,imagen);
		}
	}
	
	private void Redimensionar(JLabel label, ImageIcon imagen) {
		Image i=imagen.getImage();
		if(i!=null) {
			Image nuevai=i.getScaledInstance(label.getWidth(), label.getHeight(), java.awt.Image.SCALE_SMOOTH);
			imagen.setImage(nuevai);
			label.repaint();
		}
	}
}