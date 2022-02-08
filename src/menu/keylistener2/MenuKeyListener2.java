
package menu.keylistener2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * 
 * @author pawelstradomski
 */

//Wlasne menu - FocusListener, KeyListener, VirtualKeys, GridLayout
//FocusListener not working on Mac. I dont not why! Somebody know?

public class MenuKeyListener2 extends JFrame
{
    MenuKeyListener2() 
    {
        super("Wlasne menu2");
        this.setBounds(600, 300, 200, 300);
        initComponent();
        this.setDefaultCloseOperation(3);
    }
    void initComponent()
    {
        kontener.add(panel);
        panel.add(menu1);
        panel.add(menu2);
        panel.add(menu3);
        panel.setLayout(new GridLayout(3, 1));
        
    }
    private final JPanel panel = new JPanel();
    private final Container kontener = this.getContentPane();
    private final MenuButton menu1 = new MenuButton("Dodaj");
    private final MenuButton menu2 = new MenuButton("Usun");
    private final MenuButton menu3 = new MenuButton("Zmien");
    private int i =0;
    
    private class MenuButton extends JButton implements FocusListener, ActionListener
    {

        private MenuButton(String nazwa) {
            super(nazwa);
            this.addFocusListener(this); //FocusListener not working on Mac
            this.addActionListener(this);
            this.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    int dlugoscMenu = panel.getComponentCount();
                    if(i==0)i=dlugoscMenu;//aby nie wywalalo bledu jak pojawia sie liczby minusowe i
                    if(e.getKeyCode()==KeyEvent.VK_DOWN)
                    {
//                        System.out.println(++i); //tak lub tak:
                        panel.getComponent(++i%dlugoscMenu).requestFocus();
                        
                    }
                    else if(e.getKeyCode()==KeyEvent.VK_UP)
                    {
//                        System.out.println(--i);
                        panel.getComponent(--i%dlugoscMenu).requestFocus();
                    }
                    else if(e.getKeyCode()==KeyEvent.VK_ENTER)
                    {
//                        MenuButton tmp = (MenuButton)e.getSource();
//                        tmp.doClick();
                        ((MenuButton)e.getSource()).doClick();
                    }
                        
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    
                }
            });
            
        }

        @Override
        public void focusGained(FocusEvent e) {
            this.setBackground(Color.CYAN);
            this.setOpaque(true);          //podswietla kolorem button
            this.setBorderPainted(false);  //wypelnia kolorem button
        }

        @Override
        public void focusLost(FocusEvent e) {
            this.setBackground(Color.WHITE);

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Test wcisniecia Enter");
//            this.setBackground(Color.red);
            JOptionPane.showMessageDialog(this, "Wcisnales Enter");
            JOptionPane.showMessageDialog(this, ((MenuButton)e.getSource()).getText());//pobiera text buttona ktory wywolal zrodlo
        }
        
    }

    
    public static void main(String[] args) {
        new MenuKeyListener2().setVisible(true);
    }
    
}
