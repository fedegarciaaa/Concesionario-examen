package concesionario;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class main extends JFrame {

	private JPanel contentPane;

	Connection conexion2;
	Connection conexion;
	Statement objetoStatement;
	ResultSet resultado;
	private JTextField NumeroDeSerie;
	private JTextField marca;
	private JTextField modelo;
	private JTextField plazas;
	private JTextField color;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	
	public main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 478, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		NumeroDeSerie = new JTextField();
		NumeroDeSerie.setToolTipText("Numero de serie");
		NumeroDeSerie.setBounds(109, 49, 86, 20);
		contentPane.add(NumeroDeSerie);
		NumeroDeSerie.setColumns(10);
		
		marca = new JTextField();
		marca.setToolTipText("Marca");
		marca.setBounds(109, 89, 86, 20);
		contentPane.add(marca);
		marca.setColumns(10);
		
		modelo = new JTextField();
		modelo.setToolTipText("Modelo");
		modelo.setBounds(109, 129, 86, 20);
		contentPane.add(modelo);
		modelo.setColumns(10);
		
		plazas = new JTextField();
		plazas.setToolTipText("Plazas");
		plazas.setText("");
		plazas.setBounds(109, 170, 86, 20);
		contentPane.add(plazas);
		plazas.setColumns(10);
		
		color = new JTextField();
		color.setToolTipText("Color");
		color.setBounds(109, 211, 86, 20);
		contentPane.add(color);
		color.setColumns(10);
		
		JLabel lblNumeroDeSerie = new JLabel("  Numero de serie:");
		lblNumeroDeSerie.setBounds(0, 52, 107, 14);
		contentPane.add(lblNumeroDeSerie);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(59, 92, 39, 14);
		contentPane.add(lblMarca);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setBounds(53, 132, 46, 14);
		contentPane.add(lblModelo);
		
		JLabel lblPlazas = new JLabel("Plazas:");
		lblPlazas.setBounds(59, 173, 40, 14);
		contentPane.add(lblPlazas);
		
		JLabel lblColor = new JLabel("Color:");
		lblColor.setBounds(65, 214, 34, 14);
		contentPane.add(lblColor);
		
		JRadioButton rdbtnCoches = new JRadioButton("COCHES");
		rdbtnCoches.setBounds(39, 7, 81, 23);
		contentPane.add(rdbtnCoches);
		
		JRadioButton rdbtnMotos = new JRadioButton("MOTOS");
		rdbtnMotos.setBounds(146, 7, 72, 23);
		contentPane.add(rdbtnMotos);
		
		ButtonGroup buttonGroup = new ButtonGroup();

		buttonGroup.add(rdbtnCoches);
		buttonGroup.add(rdbtnMotos);
		
				
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.setBounds(256, 129, 115, 33);
		contentPane.add(btnModificar);
		
		JButton btnExportar = new JButton("EXPORTAR");
		btnExportar.setBounds(233, 189, 168, 35);
		contentPane.add(btnExportar);
		
		JLabel error_de_id = new JLabel("ERROR DE ID ");
		error_de_id.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 11));
		error_de_id.setForeground(Color.RED);
		error_de_id.setBounds(109, 256, 86, 14);
		contentPane.add(error_de_id);
		
		error_de_id.setVisible(false);
		
		JLabel lblSeleccionaCochesO = new JLabel("SELECCIONA COCHES O MOTOS");
		lblSeleccionaCochesO.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 11));
		lblSeleccionaCochesO.setForeground(Color.RED);
		lblSeleccionaCochesO.setBounds(15, 256, 208, 14);
		contentPane.add(lblSeleccionaCochesO);
		
		lblSeleccionaCochesO.setVisible(false);
		
		JLabel lblDebeRellenarTodos = new JLabel("DEBE RELLENAR TODOS LOS CAMPOS");
		lblDebeRellenarTodos.setForeground(Color.RED);
		lblDebeRellenarTodos.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		lblDebeRellenarTodos.setBounds(10, 254, 222, 20);
		contentPane.add(lblDebeRellenarTodos);
		
		lblDebeRellenarTodos.setVisible(false);
		
		JLabel lblDatosIntroducidos = new JLabel("DATOS INTRODUCIDOS");
		lblDatosIntroducidos.setForeground(Color.RED);
		lblDatosIntroducidos.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 11));
		lblDatosIntroducidos.setBounds(53, 256, 165, 14);
		contentPane.add(lblDatosIntroducidos);
		
		lblDatosIntroducidos.setVisible(false);
		
		JButton importar = new JButton("IMPORTAR");
		importar.setBounds(233, 235, 168, 35);
		contentPane.add(importar);
		
		JButton btnConsultar = new JButton("CONSULTAR");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
				
				
				
				try {
					
					if(rdbtnCoches.isSelected()==true) {
					open1();
					
					error_de_id.setVisible(false);
					lblSeleccionaCochesO.setVisible(false);


					String numerodeserie = NumeroDeSerie.getText();
					String sentencia = "SELECT * FROM vehiculos WHERE numero_de_serie='"+numerodeserie+"'";
					resultado = objetoStatement.executeQuery(sentencia);
					
					System.out.println(sentencia);
					
					if (resultado.next()) {
						 
						 marca.setText(resultado.getString("marca"));
						 modelo.setText(resultado.getString("modelo"));
						 plazas.setText(Integer.toString(resultado.getInt("plazas")));
						 color.setText(resultado.getString("color"));
						 }else {
							 error_de_id.setVisible(true);
						 }
					}else if(rdbtnMotos.isSelected()==true) {
						
						open2();
						
						error_de_id.setVisible(false);
						lblSeleccionaCochesO.setVisible(false);


						String numerodeseriemoto = NumeroDeSerie.getText();
						String sentenciamoto = "SELECT * FROM vehiculos WHERE numero_de_serie='"+numerodeseriemoto+"'";
						resultado = objetoStatement.executeQuery(sentenciamoto);
						
						System.out.println(sentenciamoto);
						
						if (resultado.next()) {
							 
							 marca.setText(resultado.getString("marca"));
							 modelo.setText(resultado.getString("modelo"));
							 plazas.setText(Integer.toString(resultado.getInt("plazas")));
							 color.setText(resultado.getString("color"));
							 }else {
								 error_de_id.setVisible(true);
							 }
						
					}else {
						lblSeleccionaCochesO.setVisible(true);
					 }
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				close();
				
			
			}
		});

		btnConsultar.setBounds(256, 7, 115, 33);
		contentPane.add(btnConsultar);
		
		JButton btnInsertar = new JButton("INSERTAR");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					
					if(rdbtnCoches.isSelected()==true) {
						
						open1();
											
						String numerodeserie = NumeroDeSerie.getText();
						String sentencia = "SELECT numero_de_serie FROM vehiculos WHERE numero_de_serie=' "+numerodeserie+"'";
						resultado = objetoStatement.executeQuery(sentencia);
						
						if(marca.getText().isEmpty()==true||modelo.getText().isEmpty()==true||plazas.getText().isEmpty()==true||color.getText().isEmpty()==true) {
	
							lblDatosIntroducidos.setVisible(false);
							lblSeleccionaCochesO.setVisible(false);
							lblDebeRellenarTodos.setVisible(true);

						
						}else if(!resultado.next()) {
							
							lblDebeRellenarTodos.setVisible(false);
							error_de_id.setVisible(false);
							lblSeleccionaCochesO.setVisible(false);


							
							
							String introducirNumserie = NumeroDeSerie.getText();
							String introducirMarca = marca.getText();
							String introducirModelo = modelo.getText();
							String introducirPlazas = plazas.getText();
							String introducirColor = color.getText();
							
							
							String insertar = "INSERT INTO vehiculos (numero_de_serie, marca, modelo, plazas, color) VALUES ('"+introducirNumserie+"'"
									+",'"+introducirMarca+"'"
									+", '"+introducirModelo+"'"
									+",'"+introducirPlazas+"'"
									+",'"+introducirColor+"')";
							
							
							System.out.println(insertar);
							objetoStatement.executeUpdate(insertar);
							lblDatosIntroducidos.setVisible(true);
							
							
						}else {
						
							 error_de_id.setVisible(true);										
	
						}
					}else if(rdbtnMotos.isSelected()==true) {
						
						open2();
						
						String numerodeseriemoto = NumeroDeSerie.getText();
						String sentenciamoto = "SELECT numero_de_serie FROM vehiculos WHERE numero_de_serie=' "+numerodeseriemoto+"'";
						resultado = objetoStatement.executeQuery(sentenciamoto);
						
						if(marca.getText().isEmpty()==true||modelo.getText().isEmpty()==true||plazas.getText().isEmpty()==true||color.getText().isEmpty()==true) {
	
							lblDatosIntroducidos.setVisible(false);
							lblSeleccionaCochesO.setVisible(false);
							lblDebeRellenarTodos.setVisible(true);

						
						}else if(!resultado.next()) {
							
							lblDebeRellenarTodos.setVisible(false);
							error_de_id.setVisible(false);
							lblSeleccionaCochesO.setVisible(false);


							
							
							String introducirNumserie = NumeroDeSerie.getText();
							String introducirMarca = marca.getText();
							String introducirModelo = modelo.getText();
							String introducirPlazas = plazas.getText();
							String introducirColor = color.getText();
							
							
							String insertar = "INSERT INTO vehiculos (numero_de_serie, marca, modelo, plazas, color) VALUES ('"+introducirNumserie+"'"
									+",'"+introducirMarca+"'"
									+", '"+introducirModelo+"'"
									+",'"+introducirPlazas+"'"
									+",'"+introducirColor+"')";
							
							
							System.out.println(insertar);
							objetoStatement.executeUpdate(insertar);
							lblDatosIntroducidos.setVisible(true);
							
							
						}else {
						
							 error_de_id.setVisible(true);										
	
						}
						
					}else {

						lblSeleccionaCochesO.setVisible(true);

					}
					
					
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
				close();
				
				
			}
		});
		btnInsertar.setBounds(256, 49, 115, 33);
		contentPane.add(btnInsertar);
		
		
		
		JButton btnBorrar = new JButton("BORRAR");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		btnBorrar.setBounds(256, 89, 115, 33);
		contentPane.add(btnBorrar);
		
	}
	
	
	
	public void open1() {
		

		 try {
			Class.forName("org.sqlite.JDBC");
			 		 
			 conexion = DriverManager.getConnection("jdbc:sqlite:concesionario.db","","");

			 objetoStatement = conexion.createStatement();
			 
			 
			 System.out.println("Abriendo conexion");
		} catch (ClassNotFoundException ex) {
			 System.out.println("Error al registrar el driver de SQLite: " + ex);
		} catch (SQLException sqle) {
			 System.out.println("Error SQL: " + sqle.getMessage() +
			sqle.getSQLState());
		} catch (Exception e) {
			 System.out.println("Error de conexión: " + e);
		}
		
	}
	
	public void open2() {
		

		 try {
			Class.forName("org.sqlite.JDBC");
			 		 
			 
			 conexion2 = DriverManager.getConnection("jdbc:sqlite:concesionariomotos.db","","");

			 objetoStatement = conexion2.createStatement();
			 
			 System.out.println("Abriendo conexion");
		} catch (ClassNotFoundException ex) {
			 System.out.println("Error al registrar el driver de SQLite: " + ex);
		} catch (SQLException sqle) {
			 System.out.println("Error SQL: " + sqle.getMessage() +
			sqle.getSQLState());
		} catch (Exception e) {
			 System.out.println("Error de conexión: " + e);
		}
		
	}
	
	public void close() {
		
		try {
			if (resultado != null) { 
				 resultado.close();
				 }
				 if (objetoStatement != null) { 
				 objetoStatement.close();
				 }
				 if (conexion != null) { 
				 conexion.close();
				 }if (conexion2 != null) { 
					 conexion2.close();
				 }
				 System.out.println("Cerrando conexion");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
