import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**************************
*  H A U P T K L A S S E  *
**************************/
public class Wurzel_Gui extends JFrame implements ActionListener
{
        /***************************************
        *  Objekt- und JTable-Initialisierung  *
        ***************************************/
        private Object[][] data;
        private JTable jtb;
 
        /************************************************************
        *  Eingabe von Radikand, Näheringswert und Abbruchschranke  *
        ************************************************************/
        private JLabel L1 = new JLabel("Eingabe Radikand:");
        private JLabel L2 = new JLabel("Eingabe Näherungswert:");
        private JLabel L3 = new JLabel("Eingabe Abbruchschranke:");
        private JTextField radikand  = new JTextField("36", 40);
        private JTextField startwert = new JTextField("6",40);
        private JTextField schranke  = new JTextField("0.001",40);
        private JButton los = new JButton("Los!");
        
       
 
        /****************
        *  Konstruktor  *
        ****************/
        public Wurzel_Gui()
        {
                /********************************************
                *  Aufruf des Konstruktors der Basisklasse  *
                ********************************************/
                super("Numerische Quadratwurzelberechnung");
 
                /******************************
                *  Erzeugen des Content Pane  *
                ******************************/
                JPanel cp = new JPanel();
 
                /*********************************
                *  BoxLayout als Layout-Manager  *
                *********************************/
                cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
 
                /*********************************************
                *  JLabels und JButton linksbündig anordnen  *
                *********************************************/
 
                L1.setAlignmentX(Component.LEFT_ALIGNMENT);
                L2.setAlignmentX(Component.LEFT_ALIGNMENT);
                L3.setAlignmentX(Component.LEFT_ALIGNMENT);
                los.setAlignmentX(Component.LEFT_ALIGNMENT);
 
                /****************************************
                *  Buttons zum Content Pane hinzufügen  *
                ****************************************/
                cp.add(L1);
                cp.add(radikand);
                cp.add(L2);
                cp.add(startwert);
                cp.add(L3);
                cp.add(schranke);
                cp.add(los);
 
                radikand.addActionListener(this);
                startwert.addActionListener(this);
                schranke.addActionListener(this);
                los.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						Wurzel_Gui.main(null);
						
					}
				});
 
                /****************************
                *  Content Pane aktivieren  *
                ****************************/
                setContentPane(cp);
 
                /*********************************
                *  Festlegung der Tabellengröße  *
                *********************************/
                setSize(400, 300);
 
                /************************************
                *  Maximale Anzahl von Iterationen  *
                ************************************/
                int max_iterationen;
 
                /**************************************************
                *  Angabe des Radikanden und der Abbruchschranke  *
                **************************************************/
                double radikant = Double.parseDouble(radikand.getText()), schrank = Double.parseDouble(schranke.getText());
 
                /*************************
                *  Spaltenüberschriften  *
                *************************/
                String[] columnNames =
                {
                "Iterationen",
                "Heronverfahren"
                };
 
                /*****************************************
                *  Datenberechnung nach den 4 Verfahren  *
                *****************************************/
                Vector<Double> bisektion = bisektion(radikant, schrank);
                Vector<Double> heron = heron(radikant, schrank);
 
                /************************************
                *  Tabellendimension in y-Richtung  *
                ************************************/
                max_iterationen = Math.max(bisektion.size(), heron.size());
 
                /*****************************************************
                *  Erzeugung eines Datencontainers.                  *
                *  In den data[x][0] sind die einzelnen Iterationen  *
                *  enthalten                                         *
                *****************************************************/
                data = new Object[max_iterationen][columnNames.length];
 
                /********************
                *  Initialisierung  *
                ********************/
                for(int x = 0; x < max_iterationen; x++)
                {
                        for(int y = 0; y < columnNames.length; y++)
                        {
                                /********************************
                                *  1.Spalte: Iterationsindizes  *
                                ********************************/
                                if(y == 0)
                                        data[x][y] = x + 1;
 
                                /**********************************
                                *  2.Spalte: Bisektionsverfahren  *
                                **********************************/
                                else if(y == 1 && x < bisektion.size())
                                        data[x][y] = bisektion.get(x);
 
                                /*****************************
                                *  3.Spalte: Heronverfahren  *
                                *****************************/
                                else if(y == 2 && x < heron.size())
                                        data[x][y] = heron.get(x);
 
                                /*******************************************
                                *  Keine Daten vorhanden => leere Spalten  *
                                ********************************************/
                                else
                                        data[x][y] = "";
                        }
                }
 
                /********************************************************
                *  Erstellung des JTable mit den zu berechnenden Daten  *
                ********************************************************/
                jtb = new JTable(data, columnNames);
                JScrollPane scrollPane = new JScrollPane(jtb);
                jtb.setFillsViewportHeight(true);
                getContentPane().add(scrollPane);
 
                setVisible(true);
        }
 
        /**********************************
        *  Konvertierung in Double-Werte  *
        **********************************/
 
        public void actionPerformed(ActionEvent ae)
        {
        Object quelle = ae.getSource();
        double rdk = Double.parseDouble(radikand.getText()); // Konvertierung des Eingabetextes in double-Werte
        double stw = Double.parseDouble(startwert.getText());
        double schr = Double.parseDouble(schranke.getText());
        }
        /************************
        *  Bisektionsverfahren  * //Wird zur größenbestimmung der Gui gebraucht
        ************************/
        private Vector<Double> bisektion(double rdk, double schranke)
        {
                Vector<Double> data = new Vector<Double>();
                // Bestimmung der linken und rechten Grenze für die Intervallhalbierung
                double x_links = 1, x_rechts = 1, m = 1;
                do
                {      x_links  = x_links + 1;
                       x_rechts = x_links + 1;
                }
                while(x_rechts * x_rechts < rdk);
 
                while(x_rechts - x_links > schranke)
                {
                        m = 0.5d * (x_links + x_rechts);
                        if(m * m <= rdk)
                               x_links = m;
                        else
                               x_rechts = m;
                        data.add(m);
                }
 
                return data;
 
 
        }
 
        /*******************
        *  Heronverfahren  *
        *******************/
        private Vector<Double> heron(double a, double schranke)
        {
                Vector<Double> data = new Vector<Double>();
                double x_neu = 6.0d, x_alt = 1;
                while(Math.abs(x_neu - x_alt) > schranke)
                {
                        x_alt = x_neu;
                        x_neu = 0.5d * (x_alt + (a / x_alt));
                        data.add(x_neu);
                }
                return data;
        }
	
        /******************************
        *  H A U P T P R O G R A M M  *
        ******************************/
        public static void main(String[] args)
        {
               JFrame rahmen = new Wurzel_Gui();
               WindowListener listener = new WindowAdapter()
               {
 
                public void windowClosing(WindowEvent we)
                {
                  System.exit(0);
                }
 
               };
               rahmen.addWindowListener(listener);
               
 
        }
}