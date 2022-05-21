package hammingcodebilgisayarmimarisiproje;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.ListIterator;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumnModel;

public class GUI_main extends JFrame implements ActionListener, ItemListener, MouseListener {

    JPanel jpnl;
    JLabel jlbDataStart, jlbCodeWordStart, jlbHomePage;
    JLabel jlb, jlb1, jlb2, jlb3, jlb4, jlb5, jlb6, jlb7, jlb8, jlb9, jlb10, jlb11, jlb12, jlb13, jlb14, jlb15;
    JButton jbt, jbt1, jbt2, jbt3;
    JCheckBox jcb, jcb1, jcb2, jcb3;
    JTextField jtf, jtf1, jtf2;
    JTable jtbl, jtbl2, jtbl3, jtbl4;
    JScrollPane js, js2, js3, js4;
    TableColumnModel columnModel, columnModel2, columnModel3, columnModel4;
    CustomRenderer BahoxRenderer, BahoxRendererManipule, BahoxRendererRepaired;
    Object[][] dataObj;
    Object[] colmnname;
    Object[][] dataObjProcessed;
    Object[] colmnnameProcessed;
    Object[][] dataObjManipule;
    Object[] colmnnameManipule;
    Object[][] dataObjRepaired;
    Object[] colmnnameRepaired;
    boolean flag = true;
    boolean flag2 = true;
    boolean flag3 = true;
    boolean flag4 = true;
    private BitData inputBitData;
    int parityBitAdeti;

    public GUI_main(String s) {
        super(s);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocation(100, 70); // pencerenin ilk açıldığı konum (pc ekranına göre).

        jpnl = new JPanel();
        jpnl.setBounds(0, 10, 1280, 700); // companentin konumu
        jpnl.setLayout(null);
        this.add(jpnl);

        jlb = new JLabel("Ahmet Bahadır Aksakal / 20360859079");
        jlb.setBounds(1000, 610, 250, 20);
        jpnl.add(jlb); //  jpnl e ekleme işlemleri...

        jlbDataStart = new JLabel("<html><strong>Parity Bitleri Hesaplanmamış Bir Data Girmek İçin Tıklayın!<br><br>"
                + "Parity Bitleri Hesaplanacak ve Daha Sonra Programın Diğer Adımları Çalışacaktır.<br><br><br>"
                + "<h3> EĞER SONUÇLAR DOĞRU ÇIKMAZ İSE GİRDİĞİNİZ DEĞERİ TERSTEN YAZMAYI DENEYİNİZ !!!</h3></strong></html>");
        jlbDataStart.setBounds(170, 150, 400, 300);
        jlbDataStart.setBackground(Color.RED);
        jlbDataStart.setOpaque(true);
        jlbDataStart.addMouseListener(this);
        jlbDataStart.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jpnl.add(jlbDataStart);

        jlbCodeWordStart = new JLabel("<html><strong>Parity Bitleri Hesaplanmış Bir CodeWord Girmek İçin Tıklayın!<br><br>"
                + "Parity Bitleri Hesaplama Adımı Atlanacak ve Programın Diğer Adımları Çalışacaktır.<br><br><br>"
                + "<h3> EĞER SONUÇLAR DOĞRU ÇIKMAZ İSE GİRDİĞİNİZ CODEWORLD'Ü TERSTEN YAZMAYI DENEYİNİZ !!!</h3></strong></html>");
        jlbCodeWordStart.setBounds(650, 150, 400, 300);
        jlbCodeWordStart.setBackground(Color.RED);
        jlbCodeWordStart.setOpaque(true);
        jlbCodeWordStart.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jlbCodeWordStart.addMouseListener(this);
        jpnl.add(jlbCodeWordStart);

        jlbHomePage = new JLabel("<html><strong>Ana Sayfa &#8617; </strong></html>");
        jlbHomePage.setBounds(20, 600, 200, 30);
        jlbHomePage.setBackground(Color.ORANGE);
        jlbHomePage.setOpaque(true);
        jlbHomePage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jlbHomePage.setVisible(false);
        jlbHomePage.addMouseListener(this);
        jpnl.add(jlbHomePage);

        jcb = new JCheckBox("Decimal Format", true);
        jcb.setBounds(20, 0, 140, 30);
        jcb.setVisible(false);
        jcb.addItemListener(this);
        jpnl.add(jcb);

        jcb1 = new JCheckBox("Binary Format", false);
        jcb1.setBounds(160, 0, 140, 30);
        jcb1.setVisible(false);
        jcb1.addItemListener(this);
        jpnl.add(jcb1);

        jtf = new JTextField("İnput - Decimal Or Binary Sayı Giriniz");
        jtf.setBounds(20, 35, 245, 30);
        jtf.setVisible(false);
        jtf.addMouseListener(this);
        jpnl.add(jtf);

        jbt = new JButton("Hamming Code Run");
        jbt.setBounds(20, 75, 245, 30); //x,y,w,h
        jbt.setVisible(false);
        jbt.addActionListener(this);
        jpnl.add(jbt);

        jlb1 = new JLabel("Girilen Data:");
        jlb1.setBounds(300, 0, 180, 30);
        jlb1.setVisible(false);
        jpnl.add(jlb1);

        jlb2 = new JLabel("Kaç Adet Parity Bit Kullanılacak:");
        jlb2.setBounds(870, 0, 200, 30);
        jlb2.setVisible(false);
        jpnl.add(jlb2);

        jlb3 = new JLabel("<html>m+r+1 &#8804; 2<sup>r</sup>&emsp;&emsp;m &#8594; Data Bit Sayısı. &emsp;&emsp; r &#8594; Parity Bit Sayısı.</html>");
        jlb3.setBounds(870, 20, 400, 30);
        jlb3.setVisible(false);
        jpnl.add(jlb3);

        jlb4 = new JLabel("<html>?+r+1 &#8804; 2<sup>r</sup>&emsp;&emsp;m &#8594; Data Bit Sayısı.</html>");
        jlb4.setBounds(870, 40, 400, 30);
        jlb4.setVisible(false);
        jpnl.add(jlb4);

        jlb5 = new JLabel("<html>? &#8594; Parity Bit Sayısı.</html>");
        jlb5.setBounds(870, 60, 400, 30);
        jlb5.setVisible(false);
        jpnl.add(jlb5);

        jlb6 = new JLabel("<html>? &#8594; Parity Bit Sayısı.</html>");
        jlb6.setBounds(870, 60, 400, 30);
        jlb6.setVisible(false);
        jpnl.add(jlb6);

        jlb7 = new JLabel("CodeWord:");
        jlb7.setBounds(300, 110, 180, 30);
        jlb7.setVisible(false);
        jpnl.add(jlb7);

        jlb8 = new JLabel("Parity Bitlerin XOR İle Bulunması:");
        jlb8.setBounds(870, 110, 200, 30);
        jlb8.setVisible(false);
        jpnl.add(jlb8);

        jlb9 = new JLabel();
        jlb9.setBounds(870, 130, 600, 140);
        jlb9.setVisible(false);
        jpnl.add(jlb9);

        //data nın manupule edilmesi ve hata tespiti.
        jcb2 = new JCheckBox("Auto Manipüle", true);
        jcb2.setBounds(20, 110, 140, 30);
        jcb2.addItemListener(this);
        jcb2.setVisible(false);
        jpnl.add(jcb2);

        jcb3 = new JCheckBox("Manuel manipüle", false);
        jcb3.setBounds(160, 110, 140, 30);
        jcb3.addItemListener(this);
        jcb3.setVisible(false);
        jpnl.add(jcb3);

        jtf1 = new JTextField("Manipüle Etmek İstediğiniz İndex");
        jtf1.setBounds(20, 145, 245, 30);
        jtf1.addMouseListener(this);
        jtf1.setVisible(false);
        jpnl.add(jtf1);

        jbt1 = new JButton("Manipüle Et");
        jbt1.setBounds(20, 185, 245, 30); //x,y,w,h
        jbt1.addMouseListener(this);
        jbt1.addActionListener(this);
        jbt1.setVisible(false);
        jpnl.add(jbt1);

        jlb10 = new JLabel("Manipüle Edilmiş CodeWord:");
        jlb10.setBounds(300, 200, 400, 110);
        jlb10.setVisible(false);
        jpnl.add(jlb10);

        jlb11 = new JLabel();
        jlb11.setBounds(870, 285, 600, 30);
        jlb11.setVisible(false);
        jpnl.add(jlb11);

        jbt2 = new JButton("Repair CodeWord");
        jbt2.setBounds(20, 290, 245, 30); //x,y,w,h
        jbt2.addMouseListener(this);
        jbt2.addActionListener(this);
        jbt2.setVisible(false);
        jpnl.add(jbt2);

        jlb12 = new JLabel("Repaired CodeWord:");
        jlb12.setBounds(300, 360, 400, 30);
        jlb12.setVisible(false);
        jpnl.add(jlb12);

        jlb13 = new JLabel();
        jlb13.setBounds(870, 360, 800, 140);
        jlb13.setVisible(false);
        jpnl.add(jlb13);

        jlb14 = new JLabel("<html><b>--SONUÇ--<b></hmtl>");
        jlb14.setBounds(510, 490, 140, 30);
        jlb14.setVisible(false);
        jpnl.add(jlb14);

        jlb15 = new JLabel();
        jlb15.setBounds(300, 520, 800, 60);
        jlb15.setVisible(false);
        jpnl.add(jlb15);

        // codeword girişi adım 2 ----------
        jtf2 = new JTextField("Binary CodeWord Giriniz - ParityBitleri Olan");
        jtf2.setBounds(20, 35, 245, 30);
        jtf2.setVisible(false);
        jtf2.addMouseListener(this);
        jpnl.add(jtf2);

        jbt3 = new JButton("Hamming Code Run");
        jbt3.setBounds(20, 75, 245, 30); //x,y,w,h
        jbt3.setVisible(false);
        jbt3.addActionListener(this);
        jpnl.add(jbt3);

        this.setSize(1280, 720);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(jbt)) {
            try {
                inputBitData = new BitData(jtf.getText());
                inputBitData.parityBitAdetiHesapla(inputBitData.dataTxt, 0);
                inputBitData.genarateParityBit();
                doldur();
                doldurCodeWord();

                jcb2.setVisible(true);
                jcb3.setVisible(true);
                jtf1.setVisible(false);
                jbt1.setVisible(true);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Girdiğiniz Sayı Geçersiz Lütfen tekrar giriniz", "Bilgi", 1);
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(null, "Bitler işlenirken bir hata Oluştu", "Bilgi", 1);
            }

            if (!flag3) {
                jlb10.setVisible(false);
                jlb11.setVisible(false);
                jcb2.setSelected(true);
                js3.setVisible(false);

                jbt2.setVisible(false);
                jlb12.setVisible(false);
                jlb13.setVisible(false);
                jlb14.setVisible(false);
                jlb15.setVisible(false);
                if (js4 != null) {
                    js4.setVisible(false);
                }
                flag3 = true;
            }

            System.gc();
        }

        if (e.getSource().equals(jbt1)) {
            try {
                if (jcb2.isSelected()) {
                    inputBitData.manipuleOneBitAuto();

                } else {
                    inputBitData.manipuleOneBitManuel(Integer.parseInt(jtf1.getText()));
                }
                doldurMapulatingCodeWorld();
                jbt2.setVisible(true);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Girdiğiniz İndex 0 dan küçük ve datanızdan büyük olamaz", "Bilgi", 1);
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(null, "Bitler İşlenirken Hata Oluştu Adım-2", "Bilgi", 1);
            }

            jtf1.setText("Manipüle Etmek İstediğiniz İndex");

            if (!flag4) {
                jlb12.setVisible(false);
                jlb13.setVisible(false);
                jlb14.setVisible(false);
                jlb15.setVisible(false);
                js4.setVisible(false);
                flag4 = true;
            }

            System.gc();
        }

        if (e.getSource().equals(jbt2)) {

            try {
                jbt2.setVisible(false);
                inputBitData.repairCodeWord();
                doldurRepairCode();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Girdiğiniz Sayı Geçersiz Lütfen tekrar giriniz", "Bilgi", 1);
            } catch (IndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null, """
                                                    Girdi\u011finiz CodeWord ve Parity Bit De\u011ferleri E\u015flemiyor.
                                                    Parity Bitleri Do\u011fru Hesaplanm\u0131\u015f Bir CodeWord Giriniz.
                                                    Veya Ana Sayfaya S\u00f6n\u00fcp Saf Sata \u00fczerinden Parity Bit hesaplayınız.
                                                    Ve \u0130\u015flemlere Bu \u015eekilde Devam Ediniz.""", "Bilgi", 1);
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(null, "Bitler işlenirken bir hata Oluştu", "Bilgi", 1);
            }

        }

        if (e.getSource().equals(jbt3)) {
            try {
                BitData.bitOrIntGUI = false;
                inputBitData = new BitData(jtf2.getText());

                inputBitData.parityBitAdetiHesapla(inputBitData.dataTxt, 1);

                doldur();

                jlb2.setVisible(false);
                jlb3.setVisible(false);
                jlb4.setVisible(false);
                jlb5.setVisible(false);
                jcb2.setVisible(true);
                jcb3.setVisible(true);
                jtf1.setVisible(false);
                jbt1.setVisible(true);
                jtf2.setVisible(true);
                jbt3.setVisible(true);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Girdiğiniz Sayı Geçersiz Lütfen tekrar giriniz", "Bilgi", 1);
            } catch (IndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null, """
                                                    Girdi\u011finiz CodeWord ve Parity Bit De\u011ferleri E\u015flemiyor.
                                                     Parity Bitleri Do\u011fru Hesaplanm\u0131\u015f bir CodeWord Giriniz.
                                                    Veya Ana sayfaya d\u00f6n\u00fcp saf data \u00fczerinden parity bit hesaplayan\u0131z. Ve \u0130\u015flemlere
                                                    Bu \u015eekilde Devam Ediniz.""", "Bilgi", 1);
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(null, "Bitler işlenirken bir hata Oluştu", "Bilgi", 1);
            }

            if (!flag3) {
                jlb10.setVisible(false);
                jlb11.setVisible(false);
                jcb2.setSelected(true);
                js3.setVisible(false);

                jbt2.setVisible(false);
                jlb12.setVisible(false);
                jlb13.setVisible(false);
                jlb14.setVisible(false);
                jlb15.setVisible(false);
                if (js4 != null) {
                    js4.setVisible(false);
                }
                flag3 = true;
            }

            System.gc();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource().equals(this.jcb)) {
            jcb1.setSelected(!jcb.isSelected());
            BitData.bitOrIntGUI = jcb.isSelected();
        }
        if (e.getSource().equals(this.jcb1)) {
            jcb.setSelected(!jcb1.isSelected());
            BitData.bitOrIntGUI = jcb.isSelected();
        }
        if (e.getSource().equals(this.jcb2)) {
            jcb3.setSelected(!jcb2.isSelected());
            jtf1.setVisible(false);
            if (!jcb2.isSelected()) {
                jtf1.setVisible(true);
            }
        }
        if (e.getSource().equals(this.jcb3)) {
            jcb2.setSelected(!jcb3.isSelected());
            jtf1.setText("Manipüle Etmek İstediğiniz İndex");
            jtf1.setVisible(true);
            if (!jcb3.isSelected()) {
                jtf1.setVisible(false);
            }
        }
    }

    private void doldur() {

        colmnname = new Object[inputBitData.dataTxt.length()];
        dataObj = new Object[1][inputBitData.dataTxt.length()];
        for (int j = 0; j < inputBitData.dataTxt.length(); j++) {
            String temp = "<html> P<sub>" + (j + 1) + "</sub> </html>";
            colmnname[j] = (Object) temp;
            String temp2 = String.valueOf(inputBitData.dataTxt.charAt(j));
            dataObj[0][j] = (Object) temp2;

        }
        jtbl = new JTable(dataObj, colmnname);
        jtbl.setEnabled(false);
        jtbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jtbl.getTableHeader().setResizingAllowed(false);
        jtbl.getTableHeader().setReorderingAllowed(false);
        jtbl.setPreferredScrollableViewportSize(new Dimension(100, 200));
        jtbl.setRowHeight(30);

        columnModel = jtbl.getColumnModel();
        for (int i = 0; i < colmnname.length; i++) {
            columnModel.getColumn(i).setPreferredWidth(30);
        }

        if (flag) {
            jlb1.setVisible(true);
            jlb2.setVisible(true);
            jlb3.setVisible(true);
            jlb4.setVisible(true);
            jlb5.setVisible(true);
            js = new JScrollPane(jtbl);
            js.setHorizontalScrollBarPolicy(32);
            js.setBounds(300, 30, 520, 75);
            jpnl.add(js);
            flag = false;
        }
        jlb4.setText("<html>" + (colmnname.length + 1) + "+r &#8804; 2<sup>r</sup>&emsp;&emsp;&emsp;&nbsp;" + colmnname.length + " &#8594; Data Bit Sayısı. &emsp;&emsp; r &#8594; Parity Bit Sayısı.</html>");
        jlb5.setText("<html>" + (colmnname.length + 1) + "+" + parityBitAdeti + " &#8804; 2<sup>" + parityBitAdeti + "</sup>&emsp;&emsp;&emsp;&nbsp;" + colmnname.length + " &#8594; Data Bit Sayısı. &emsp;&emsp; " + parityBitAdeti + " &#8594; Parity Bit Sayısı.</html>");
        js.setViewportView(jtbl);//js i guncelliyorum ki yeni table ile  scroll patlamasın.
    }

    public void doldurCodeWord() {
        colmnnameProcessed = new Object[inputBitData.codeWordTxt.length()];
        dataObjProcessed = new Object[1][inputBitData.codeWordTxt.length()];
        for (int j = 0; j < inputBitData.codeWordTxt.length(); j++) {
            String temp = "<html> P<sub>" + (j + 1) + "</sub> </html>";
            colmnnameProcessed[j] = (Object) temp;
            String temp2 = String.valueOf(inputBitData.codeWordTxt.charAt(j));
            dataObjProcessed[0][j] = (Object) temp2;

        }
        jtbl2 = new JTable(dataObjProcessed, colmnnameProcessed);
        jtbl2.setEnabled(false);
        jtbl2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jtbl2.getTableHeader().setResizingAllowed(false);
        jtbl2.getTableHeader().setReorderingAllowed(false);
        jtbl2.setPreferredScrollableViewportSize(new Dimension(100, 200));
        jtbl2.setRowHeight(30);

        BahoxRenderer = new CustomRenderer(inputBitData, 0);
        jtbl2.setDefaultRenderer(Object.class, BahoxRenderer);

        columnModel2 = jtbl2.getColumnModel();
        for (int i = 0; i < colmnnameProcessed.length; i++) {
            columnModel2.getColumn(i).setPreferredWidth(30);
        }

        //--XOR ların guisini göstermek için--
        StringBuilder tempJlb9 = new StringBuilder("<html><br>");
        ListIterator iterator;
        for (int i = 0; i < inputBitData.parityBitAdeti; i++) {
            String tempJlb9String = "P<sub>" + (int) Math.pow(2, i) + "</sub> ->";
            tempJlb9.append(tempJlb9String);
            iterator = inputBitData.parityBitForXORgui[i].listIterator();
            while (iterator.hasNext()) {
                String next = (String) iterator.next();
                if (iterator.hasNext()) {
                    tempJlb9String = next + " &#8853; ";
                    tempJlb9.append(tempJlb9String);
                } else {
                    tempJlb9String = next + " = " + inputBitData.parityBitsArr[i] + "<br>";
                    tempJlb9.append(tempJlb9String);
                }

            }
        }
        tempJlb9.append("</html>");
        jlb9.setText(tempJlb9.toString());
        //----------------------------------------------------------------------

        if (flag2) {
            jlb7.setVisible(true);
            jlb8.setVisible(true);
            jlb9.setVisible(true);
            js2 = new JScrollPane(jtbl2);
            js2.setHorizontalScrollBarPolicy(32);
            js2.setBounds(300, 140, 520, 75);
            jpnl.add(js2);
            flag2 = false;
        }
        js2.setViewportView(jtbl2);

    }

    public void doldurMapulatingCodeWorld() {
        colmnnameManipule = new Object[inputBitData.codeWordTxt.length()];
        dataObjManipule = new Object[1][inputBitData.codeWordTxt.length()];
        for (int j = 0; j < inputBitData.codeWordTxt.length(); j++) {
            String temp = "<html> P<sub>" + (j + 1) + "</sub> </html>";
            colmnnameManipule[j] = (Object) temp;
            String temp2 = String.valueOf(inputBitData.codeWordManupilating.charAt(j));
            dataObjManipule[0][j] = (Object) temp2;

        }

        jtbl3 = new JTable(dataObjManipule, colmnnameManipule);
        jtbl3.setEnabled(false);
        jtbl3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jtbl3.getTableHeader().setResizingAllowed(false);
        jtbl3.getTableHeader().setReorderingAllowed(false);
        jtbl3.setPreferredScrollableViewportSize(new Dimension(100, 200));
        jtbl3.setRowHeight(30);

        BahoxRendererManipule = new CustomRenderer(inputBitData, 1);
        jtbl3.setDefaultRenderer(Object.class, BahoxRendererManipule);

        columnModel3 = jtbl3.getColumnModel();
        for (int i = 0; i < colmnnameManipule.length; i++) {
            columnModel3.getColumn(i).setPreferredWidth(30);
        }

        jlb11.setText("<html>Manipüle Edilen Bit: " + inputBitData.indexForManipule + ".index  |  P<sub>" + (inputBitData.indexForManipule + 1) + "</sub> &#8594; " + inputBitData.codeWordManupilating.charAt(inputBitData.indexForManipule) + "</html>");
        if (flag3) {
            jlb10.setVisible(true);
            jlb11.setVisible(true);
            js3 = new JScrollPane(jtbl3);
            js3.setHorizontalScrollBarPolicy(32);
            js3.setBounds(300, 270, 520, 75);
            jpnl.add(js3);
            flag3 = false;
        }
        js3.setViewportView(jtbl3);
    }

    public void doldurRepairCode() {
        colmnnameRepaired = new Object[inputBitData.codeWordTxt.length()];
        dataObjRepaired = new Object[1][inputBitData.codeWordTxt.length()];
        for (int j = 0; j < inputBitData.codeWordTxt.length(); j++) {
            String temp = "<html> P<sub>" + (j + 1) + "</sub> </html>";
            colmnnameRepaired[j] = (Object) temp;
            String temp2 = String.valueOf(inputBitData.codeWordRepaired.charAt(j));
            dataObjRepaired[0][j] = (Object) temp2;

        }

        jtbl4 = new JTable(dataObjRepaired, colmnnameRepaired);
        jtbl4.setEnabled(false);
        jtbl4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jtbl4.getTableHeader().setResizingAllowed(false);
        jtbl4.getTableHeader().setReorderingAllowed(false);
        jtbl4.setPreferredScrollableViewportSize(new Dimension(100, 200));
        jtbl4.setRowHeight(30);

        BahoxRendererRepaired = new CustomRenderer(inputBitData, 2);
        jtbl4.setDefaultRenderer(Object.class, BahoxRendererRepaired);

        columnModel4 = jtbl4.getColumnModel();
        for (int i = 0; i < colmnnameRepaired.length; i++) {
            columnModel4.getColumn(i).setPreferredWidth(30);
        }
        StringBuilder tempRepairStrBld = new StringBuilder("<html>Repair İşlemi:<br><br>");
        StringBuilder sonucStrBld = new StringBuilder("<html> P<sub>?</sub> &#8594; ( ");
        int[] tempCursorPoints = new int[inputBitData.parityBitRepairForXORgui.length];

        int lastIndex = inputBitData.parityBitRepairForXORgui.length;
        for (int i = 0; i < lastIndex; i++) {
            Iterator<Integer> iterator = inputBitData.parityBitRepairForXORguiIndex[i].iterator();
            String tempRepairStr = "R<sub>" + (i + 1) + "</sub> &#8594; (";
            tempRepairStrBld.append(tempRepairStr);
            if (i + 1 == inputBitData.parityBitRepairForXORgui.length) {
                tempRepairStr = "R<sub>" + (lastIndex - i) + "</sub> ";
                sonucStrBld.append(tempRepairStr);
            } else {
                tempRepairStr = "R<sub>" + (lastIndex - i) + "</sub>, ";
                sonucStrBld.append(tempRepairStr);
            }

            while (iterator.hasNext()) {
                Integer next = iterator.next() + 1;
                if (iterator.hasNext()) {
                    tempRepairStr = " P<sub>" + next + "</sub>,";
                    tempRepairStrBld.append(tempRepairStr);
                } else {
                    tempRepairStr = " P<sub>" + next + "</sub> ) &#8649;   <br>";
                    tempRepairStrBld.append(tempRepairStr);
                }

            }
            tempCursorPoints[i] = tempRepairStrBld.length() - 5;
        }

        sonucStrBld.append(")<sub>2</sub> = ( ");
        StringBuilder tempRepairStrBldChild = new StringBuilder();
        String tempSonuc = inputBitData.parityBitsRepairArrStrBuilder.reverse().toString();
        String tempSonucNotReverse = inputBitData.parityBitsRepairArrStrBuilder.reverse().toString();

        for (int i = 0; i < lastIndex; i++) {
            Iterator<String> iterator = inputBitData.parityBitRepairForXORgui[i].iterator();
            String tempRepairStrChild;
            while (iterator.hasNext()) {
                String next = iterator.next();
                if (iterator.hasNext()) {
                    tempRepairStrChild = "" + next + " &#8853; ";
                    tempRepairStrBldChild.append(tempRepairStrChild);
                } else {
                    tempRepairStrChild = "" + next + " = " + tempSonuc.charAt(i);
                    tempRepairStrBldChild.append(tempRepairStrChild);
                    if (i + 1 == lastIndex) {
                        tempRepairStrChild = tempSonucNotReverse.charAt(i) + " ";
                        sonucStrBld.append(tempRepairStrChild);
                    } else {
                        tempRepairStrChild = tempSonucNotReverse.charAt(i) + ", ";
                        sonucStrBld.append(tempRepairStrChild);
                    }

                }

            }

            tempRepairStrBld.insert(tempCursorPoints[i], tempRepairStrBldChild);
            if (i + 1 != lastIndex) {
                for (int j = i; j < lastIndex - 1; j++) {
                    tempCursorPoints[j + 1] += tempRepairStrBldChild.length();
                }
                tempRepairStrBldChild.setLength(0);
            }
        }
        tempRepairStrBld.append("</html>");
        String tempSonucStr = ")<sub>2</sub> = (" + (inputBitData.corruptedIndex + 1) + ")<sub>10</sub> &#8594; P<sub>" + (inputBitData.corruptedIndex + 1) + "</sub> numaralı datada hata bulunmuştur Ve başarıyla düzeltilmiştir.</html>";
        sonucStrBld.append(tempSonucStr);

        jlb15.setText(sonucStrBld.toString());
        jlb13.setText(tempRepairStrBld.toString());

        if (flag4) {
            jlb12.setVisible(true);
            jlb13.setVisible(true);
            jlb14.setVisible(true);
            jlb15.setVisible(true);
            js4 = new JScrollPane(jtbl4);
            js4.setHorizontalScrollBarPolicy(32);
            js4.setBounds(300, 390, 520, 75);
            jpnl.add(js4);
            flag4 = false;
        }
        js4.setViewportView(jtbl4);
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (e.getSource().equals(jtf)) {
            jtf.setText("");
        }
        if (e.getSource().equals(jtf1)) {
            jtf1.setText("");
        }
        if (e.getSource().equals(jtf2)) {
            jtf2.setText("");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource().equals(jbt1)) {
            System.gc();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(jlbDataStart)) {
            jlbDataStart.setVisible(false);
            jlbCodeWordStart.setVisible(false);
            jlbHomePage.setVisible(true);
            jcb.setVisible(true);
            jcb1.setVisible(true);
            jtf.setVisible(true);
            jbt.setVisible(true);

        }
        if (e.getSource().equals(jlbCodeWordStart)) {
            jlbDataStart.setVisible(false);
            jlbCodeWordStart.setVisible(false);
            jlbHomePage.setVisible(true);

            jtf2.setVisible(true);
            jbt3.setVisible(true);
        }
        if (e.getSource().equals(jlbHomePage)) {
            BitData.bitOrIntGUI = true;
            this.dispose();
            AnaSayfa();
            System.gc();

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource().equals(jlbDataStart)) {
            jlbDataStart.setBackground(Color.GREEN);
        } else if (e.getSource().equals(jlbCodeWordStart)) {
            jlbCodeWordStart.setBackground(Color.GREEN);
        } else if (e.getSource().equals(jlbHomePage)) {
            jlbHomePage.setBackground(Color.CYAN);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(jlbDataStart)) {
            jlbDataStart.setBackground(Color.RED);
        } else if (e.getSource().equals(jlbCodeWordStart)) {
            jlbCodeWordStart.setBackground(Color.RED);
        } else if (e.getSource().equals(jlbHomePage)) {
            jlbHomePage.setBackground(Color.ORANGE);
        }
    }

    public BitData getInputBitData() {
        return inputBitData;
    }

    public GUI_main AnaSayfa() {
        GUI_main guiTemp = new GUI_main("Hamming Code - Bilgisayar Mimarisi Proje Ödevi");
        return guiTemp;
    }

}
