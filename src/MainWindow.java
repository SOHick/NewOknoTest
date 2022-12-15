import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
public class MainWindow extends  JFrame
{


    public MainWindow(){

        Dimension minSize = new Dimension(800, 600);
        setSize(minSize);
        setMinimumSize(minSize);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        GroupLayout gl = new GroupLayout(getContentPane()); // Наше окно без рамок
        setLayout(gl);
        MyText myText = new MyText();
        JTextPane mainPanel = new JTextPane();
        mainPanel.setContentType("text/html");
        JPanel controlPanel = new JPanel();

        mainPanel.setBackground(Color.white);

        mainPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.LOWERED),
                BorderFactory.createEmptyBorder(25, 25, 25, 25)));
        controlPanel.setBackground(Color.lightGray);
        controlPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.black, 2),
                BorderFactory.createEmptyBorder(25, 25, 25, 25)));


        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(8)
                .addGroup(gl.createParallelGroup()
                        .addComponent(mainPanel,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                        .addComponent(controlPanel,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                )
                .addGap(8)
        );



        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(8)
                .addGroup(gl.createSequentialGroup()
                        .addComponent(mainPanel,GroupLayout.DEFAULT_SIZE,400,GroupLayout.DEFAULT_SIZE)
                        .addGap(8)
                        .addComponent(controlPanel,GroupLayout.DEFAULT_SIZE,200,200)

                )
                .addGap(8)
        );
       mainPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0))
                {

                    mainPanel.setText(myText.getText());

                    String str = myText.getText();
                    String pattern1 = ">";
                    TextParser tp1 = new TextParser(pattern1);
                    str = tp1.replace(str, "&gt;");

                    String pattern2 = "<";
                    TextParser tp2 = new TextParser(pattern2);
                    str = tp2.replace(str,"&lt;");
                    mainPanel.setText(str);
                    mainPanel.repaint();
                }
                if((e.getKeyCode() == KeyEvent.VK_D) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0))
                {

                    String str = mainPanel.getText();
                    Parser tp3 = new Parser("(?<=^|)((?:\\w+@mail.ru)|(?:www.\\w+.ru))\\b");
                    str = tp3.replace(str,(matchResult) ->
                    {

                        String  r = "<a href =" + "\"" + matchResult.group(1) + "\">" + matchResult.group(1) + "</a>";
                        return r;

                    });
                    String pattern = "s*(?i)hrefs*=s*('([^']*')|'[^']*'|([^'>s]+))";
                    TextParser tp = new TextParser(pattern);
                    str = tp.replace(str,(matchResult)->{
                        return "<a href =" + "\"" + matchResult.group(1) + "\">" + matchResult.group(1) + "</a>" ;
                    });
                    mainPanel.setText(str);
                    mainPanel.repaint();
                }
            }
        });

    }


}
