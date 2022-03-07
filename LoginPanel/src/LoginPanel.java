import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.print.attribute.AttributeSet;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class LoginPanel {
	
	private int[] tabPesel = new int[11];
	private int[] tabWeight = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3}; 
	private int totalCounter = 0;
	private int peselLenght, birthDay, birthMonth, birthYear;

	private JFrame frame;
	private JTextField textFirstName;
	private JTextField textLastName;
	private JTextField textPesel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPanel window = new LoginPanel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame("LoginPanel");
		frame.setBounds(100, 100, 484, 617);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblFirstName = new JLabel("Imie");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblFirstName.setBounds(43, 30, 116, 29);
		frame.getContentPane().add(lblFirstName);
		
		textFirstName = new JTextField();
		textFirstName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				String firstName = textFirstName.getText();
				int lenght = firstName.length();
				//System.out.println(lenght);
				char c = e.getKeyChar();
				// ------------------ warunek, textFirstName przyjmuje tylko litery, backspace i delete  
				if(!(Character.isAlphabetic(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE ) ){
						e.consume();						

				}
				
				
			}
		});
		textFirstName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textFirstName.setBounds(43, 70, 378, 42);
		frame.getContentPane().add(textFirstName);		
		textFirstName.setDocument(new JTextFieldLimit(20)); // textFirstName przyjmuje tylko 20 znakow
		
		
		
		JLabel lblLastName = new JLabel("Nazwisko");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblLastName.setBounds(43, 142, 139, 29);
		frame.getContentPane().add(lblLastName);
		
		textLastName = new JTextField();
		textLastName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textLastName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
				// ------------------ warunek, textLastName przyjmuje tylko litery, backspace i delete   -------------------
	            if (!(Character.isAlphabetic(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE )   ) { 
	            	
	               
	                	e.consume();
	               
	            }
				
			}
		});
		textLastName.setColumns(10);
		textLastName.setBounds(43, 173, 378, 42);
		frame.getContentPane().add(textLastName);
		textLastName.setDocument(new JTextFieldLimit(30)); // textFirstName przyjmuje tylko 30 znakow
		
		JLabel labelAutoData = new JLabel("");
		labelAutoData.setFont(new Font("Tahoma", Font.PLAIN, 22));
		labelAutoData.setBounds(43, 399, 193, 35);
		frame.getContentPane().add(labelAutoData);
		
		JLabel lblPesel = new JLabel("PESEL");
		lblPesel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPesel.setBounds(43, 240, 139, 29);
		frame.getContentPane().add(lblPesel);
		
		JLabel lblNewLabel = new JLabel("Data urodzenia");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(43, 351, 193, 35);
		lblNewLabel.setVisible(false);
		frame.getContentPane().add(lblNewLabel);
		
		textPesel = new JTextField();

		textPesel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String strPesel = textPesel.getText();
				int counter = 0;
				char c = e.getKeyChar();
				// ------------------ warunek, textLastName przyjmuje tylko CYFRY, backspace i delete  ----------------------------------------
			    if (!Character.isDigit(c)|| (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) 
			      {
			        
			        e.consume();
			        
			      }
	
			}
		});
		
		
		textPesel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textPesel.setColumns(10);
		textPesel.setBounds(43, 270, 378, 42);
		frame.getContentPane().add(textPesel);
		textPesel.setDocument(new JTextFieldLimit(11));
     
			
		
		JButton btnSave = new JButton("Wy\u015Blij");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				peselLenght = textPesel.getText().length();
				//---------- warunek, sprawdza czy uzupelniono wszystkie pola i czy dlugosc numeru pesel jest prawidlowa - 11  ------------------------------------
				if(textFirstName.getText().equals("") || textLastName.getText().equals("") || peselLenght != 11 ) {
					JOptionPane.showMessageDialog(frame, "Wprowadzileœ zle dane. Uzupelnij poprawnie");
					lblNewLabel.setVisible(false);
					labelAutoData.setVisible(false);
				}else {
					
					//System.out.println(textFirstName.getText());
					String strPesel = textPesel.getText();
					//System.out.println(strPesel);
					int i;
					
						for(i =0; i < 11; i++) {
							tabPesel[i] = strPesel.charAt(i) -'0';
							
					}
						
					birthYear = 10 * tabPesel[0] + tabPesel[1];
					birthMonth = 10 * tabPesel[2] + tabPesel[3];
					birthDay = 10 * tabPesel[4] + tabPesel[5];
					// -------- ustawia birthYear w zaleznosci czy wprowadzono rocznik 1900 czy 2000  ------------------------					
					if(birthMonth > 0 && birthMonth <= 12) {
						birthYear = 1900 + birthYear;
					}else if(birthMonth >= 21 && birthMonth <= 32) {
						birthYear = 2000 + birthYear;
					}
					
					// -------- ustawia birthMonth dla rocznika po  2000  ------------------------		
					if (birthMonth > 20 && birthMonth < 33) {
						birthMonth -= 20;
						}
					
					//TEST: 02070803628 ok 02070803626 Nok 14211206487 ok
					// ------ walidacja poprawnosci miesiaca w PESEL  ------------------------------						
					if(birthMonth > 12 || birthMonth == 0 || controlDigit() == false) {
						JOptionPane.showMessageDialog(frame, "Wprowadzileœ zly PESEL. Uzupelnij poprawnie");
						lblNewLabel.setVisible(false);
						labelAutoData.setVisible(false);
					}else {
					
						// -------- walidacja dni w miesiacu w PESEL, jesli miesiac parzysty to max 30 dni jesli miesiac nie parzysty to max 31 dni -----------------						
						if((birthMonth % 2) == 0 && birthMonth !=2 && controlDigit() == true ) {
							if(birthDay> 0 && birthDay <= 30) {
								//birthDay = 10 * tabPesel[4] + tabPesel[5];
								lblNewLabel.setVisible(true);
								labelAutoData.setVisible(true);
								labelAutoData.setText(birthYear + "-" + birthMonth + "-" + birthDay);
								JOptionPane.showMessageDialog(frame, "Sukces");
							}else {
								JOptionPane.showMessageDialog(frame, "Wprowadzileœ zly PESEL. Uzupelnij poprawnie");
								lblNewLabel.setVisible(false);
								labelAutoData.setVisible(false);
							}
							
						}
						if((birthMonth % 2) != 0 && birthMonth !=2 && controlDigit() == true) {
							if(birthDay> 0 && birthDay <= 31) {
								//birthDay = 10 * tabPesel[4] + tabPesel[5];
								lblNewLabel.setVisible(true);
								labelAutoData.setVisible(true);
								labelAutoData.setText(birthYear + "-" + birthMonth + "-" + birthDay);
								JOptionPane.showMessageDialog(frame, "Sukces");
							}else {
								JOptionPane.showMessageDialog(frame, "Wprowadzileœ zly PESEL. Uzupelnij poprawnie");
								lblNewLabel.setVisible(false);
								labelAutoData.setVisible(false);
							}
							
						}
						// ----------- walidacja, rzy rok przestepny, jesli tak to birthDay max 29 ---------------------------
						if(birthMonth == 2 && yearJump(birthYear) == true && controlDigit() == true) {
							if(birthDay> 0 && birthDay <= 29) {	
								lblNewLabel.setVisible(true);
								labelAutoData.setVisible(true);
								labelAutoData.setText(birthYear + "-" + birthMonth + "-" + birthDay);
								JOptionPane.showMessageDialog(frame, "Sukces");
							}else {
								JOptionPane.showMessageDialog(frame, "Wprowadzileœ zly PESEL. Uzupelnij poprawnie");
								lblNewLabel.setVisible(false);
								labelAutoData.setVisible(false);
							}
						}
						
						if(birthMonth == 2 && yearJump(birthYear) == false && controlDigit() == true) {
							if(birthDay> 0 && birthDay <= 28) {
								lblNewLabel.setVisible(true);
								labelAutoData.setVisible(true);
								labelAutoData.setText(birthYear + "-" + birthMonth + "-" + birthDay);
								JOptionPane.showMessageDialog(frame, "Sukces");
							}else {
								JOptionPane.showMessageDialog(frame, "Wprowadzileœ zly PESEL. Uzupelnij poprawnie");
								lblNewLabel.setVisible(false);
								labelAutoData.setVisible(false);
							}
						}
					}	
		
					
					
				}
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnSave.setBounds(114, 449, 193, 59);
		frame.getContentPane().add(btnSave);
		
		
		
		
	}
	// -------------  ustawienie ilosci przyjmowanych znakow w polach  ------------------- 
	public class JTextFieldLimit extends PlainDocument {
		  private int limit;

		  JTextFieldLimit(int limit) {
		   super();
		   this.limit = limit;
		   }

		  public void insertString( int offset, String  str, javax.swing.text.AttributeSet attr ) throws BadLocationException {
		    if (str == null) return;

		    if ((getLength() + str.length()) <= limit) {
		      super.insertString(offset, str, attr);
		    }
		  }
		}
	
	
	// ------------ sprawdza czy rok jest przestepny -----------------
	private boolean  yearJump(int year){
        return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0);
    }
	
	// --------- - funkcja do sprawdzania cyfry konrolnej w PESEL -----------------
	private boolean controlDigit() {
		int control = 0;
		for(int i =0; i < 10; i++) {
			control += tabWeight[i] + tabPesel[i];
			
		}
		control %= 10;
		control = 10 - control;
		control %= 10;
		 
		if (control == tabPesel[10]) {
			
		return true;		
		}else {
			return false;
		}
	}
}


