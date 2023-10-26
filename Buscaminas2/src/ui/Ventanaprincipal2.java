package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utily.Casilla;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Dimension;

public class Ventanaprincipal2 extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
    private Buscaminas buscaminas = new Buscaminas();
    private Casilla casilla = new Casilla(rootPaneCheckingEnabled);
    public boolean _tienebombaa = casilla.getTieneBomba();
    private JPanel panel_1;
    private JComboBox<Integer> comboBox;
    private JButton botonAceptar;
    private JPanel panel_2;
    private JTextField txtBombas;
    private JLabel lblBomba;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					Ventanaprincipal2 frame = new Ventanaprincipal2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ventanaprincipal2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 623, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanel(), BorderLayout.CENTER);
		contentPane.add(getPanel_1(), BorderLayout.SOUTH);
		contentPane.add(getPanel_2(), BorderLayout.NORTH);
		crearBotones();
	}
	
	public void inicializar() {
		buscaminas = new Buscaminas(buscaminas.getTablero().getTablero().length);
		crearBotones();
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(3, 3, 0, 0));
			
		}
		return panel;
	}
	public void crearBotones() {
		panel.removeAll();
		panel.setLayout(new GridLayout(buscaminas.getTablero().getTablero().length,Math.max(1,buscaminas.getTablero().getTablero().length), 0, 0));

		for(int i = 0;i < buscaminas.getTablero().getTablero().length; i++) {
			for(int j = 0;j < buscaminas.getTablero().getTablero()[i].length; j++) {
				panel.add(CrearBoton(i,j));
			}
		panel.repaint();
		this.setSize(getWidth()+1, getHeight() +1);
		this.setSize(getWidth()-1, getHeight() -1);
		}
	}
	
	private JButton CrearBoton(int fila, int columna) {
	            JButton boton = new JButton("");
	            boton.setFont(new Font("Tahoma", Font.BOLD, 20));
	            boton.setName(fila + "" + columna );
	            boton.setBackground(Color.WHITE);
				boton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						accionBoton(fila,columna,boton);
					}
						
			
	});
				return boton;
	}
	//setImagenAdaptada(boton,"./rsc/bomb.png");
	private void accionBoton(int fila, int columna, JButton boton) {
        int result = buscaminas.clickOn(fila, columna);
        if (result < 0) {
            setImagenAdaptada(boton, "./rsc/bomb.png");
            int value = JOptionPane.showConfirmDialog(this,
                    "Ha seleccionado una bomba, ¿Desea iniciar una nueva partida?");
            if (value == JOptionPane.OK_OPTION) {
                inicializar();
            } else {
                dispose();
            }
        } else {

            if (result == 0) {
                boton.setForeground(Color.GRAY);
                boton.setBackground(Color.GRAY);
                clickBoton(fila,columna);
            } else if (result == 1) {
                boton.setForeground(Color.GREEN);
                boton.setText(result + "");
            } else if (result == 2) {
                boton.setForeground(Color.CYAN);
                boton.setText(result + "");
            } else if (result == 3) {
                boton.setForeground(Color.BLUE);
                boton.setText(result + "");
            } else if (result == 4) {
                boton.setForeground(Color.RED);
                boton.setText(result + "");
            }
            //buscaminas.getTablero().imprimirtablero();
            if(buscaminas.setermino()) {
                int value = JOptionPane.showConfirmDialog(this,
                        "Has GANADO, ¿Desea iniciar una nueva partida?");
                if (value == JOptionPane.OK_OPTION) {
                    inicializar();
                } else {
                    dispose();
                }
            }
        }
    }




	
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.add(getComboBox());
			panel_1.add(getBotonAceptar());
		}
		return panel_1;
	}
	private JComboBox<Integer> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<Integer>();
			comboBox.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {3, 4, 5, 6, 7, 8, 9, 10}));
		}
		return comboBox;
	}
	private JButton getBotonAceptar() {
		if (botonAceptar == null) {
			botonAceptar = new JButton("Aceptar");
			
			botonAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int value = ((Integer)getComboBox().getSelectedItem()).intValue();;
					buscaminas = new Buscaminas(value);
	                crearBotones();
	                getTxtBombas().setText(buscaminas.getTablero().getNumeroBombas()+"");
				}
			});
		}
		return botonAceptar;
			
		}
	
	private void setImagenAdaptada(JButton boton, String rutaImagen) {
        Image imgOriginal = new ImageIcon(rutaImagen).getImage();
        Image imgEscalada = imgOriginal.getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(imgEscalada);
        boton.setIcon(icon);
    }
	private void setImagenAdaptada(JLabel label, String rutaImagen) {
        Image imgOriginal = new ImageIcon(rutaImagen).getImage();
        Image imgEscalada = imgOriginal.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(imgEscalada);
        label.setIcon(icon);
    }
	
	public Font getBotonAceptarFont() {
		return getBotonAceptar().getFont();
	}
	public void setBotonAceptarFont(Font font) {
		getBotonAceptar().setFont(font);
	}
	
	private void clickBoton(int fila, int columna) {
        Component[] componentes = panel.getComponents();
        for(int i = fila-1; i<= fila+1; i++) { //Recorre las filas
            for(int j = columna-1; j<= columna+1; j++) { // Recorre las columnas
                 if(i>=0 && i< buscaminas.getTablero().getTablero().length && j>=0 && j< buscaminas.getTablero().getTablero().length && (i != fila || j != columna )) {
                     JButton boton = null;
                     for(int k = 0; k<componentes.length; k++) {
                         if(componentes[k].getName().equals(i+""+j)) {
                             boton = (JButton)componentes[k];
                             int result = buscaminas.clickOn(i,j);
                             buscaminas.getTablero().getTablero()[i][j].incrementarVisitados();
                             if (result == 0) {
                                    boton.setForeground(Color.GRAY);
                                    boton.setBackground(Color.GRAY);
                                    if(buscaminas.getTablero().getTablero()[i][j].getNumVisitado() <= 1) {
                                        clickBoton(i,j);
}
                                    boton.setEnabled(false);
                            } else if (result == 1) {
                                    boton.setForeground(Color.GREEN);
                                    boton.setText(result + "");
                            } else if (result == 2) {
                                    boton.setForeground(Color.CYAN);
                                    boton.setText(result + "");
                            } else if (result == 3) {
                                    boton.setForeground(Color.BLUE);
                                    boton.setText(result + "");
                            } else if (result == 4) {
                                    boton.setForeground(Color.RED);
                                    boton.setText(result + "");
                            } else if (result == 5) {
                                boton.setForeground(Color.ORANGE);
                                boton.setText(result + "");
                            }else if (result == 6) {
                                boton.setForeground(Color.MAGENTA);
                                boton.setText(result + "");
                            }
                         }
                     }
                 }
            }
        }
    }

	
	
	
	
	
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.add(getLblBomba());
			panel_2.add(getTxtBombas());
		}
		return panel_2;
	}
	private JTextField getTxtBombas() {
		if (txtBombas == null) {
			txtBombas = new JTextField();
			txtBombas.setForeground(Color.BLACK);
			txtBombas.setHorizontalAlignment(SwingConstants.CENTER);
			txtBombas.setEditable(false);
			txtBombas.setColumns(2);
			getTxtBombas().setText(buscaminas.getTablero().getNumeroBombas()+"");
		}
		return txtBombas;
	}
	private JLabel getLblBomba() {
		if (lblBomba == null) {
			lblBomba = new JLabel("");
			lblBomba.setSize(new Dimension(20, 20));
			lblBomba.setMinimumSize(new Dimension(20, 20));
			setImagenAdaptada(lblBomba,"./rsc/bomb.png");
		}
		return lblBomba;
	}
}


