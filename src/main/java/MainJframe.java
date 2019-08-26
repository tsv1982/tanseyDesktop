
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.util.Iterator;



public class MainJframe extends JFrame {

   JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
   File filePut;
   int wait = 1;


    public MainJframe() throws IOException {
        super("Simple Example");
        this.setBounds(100, 100, 800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout());

        getContentPane().add(tabbedPane);
        getJTabbedPaneAddStudent();
        getJTabbedPane2();

    }

    private void getJTabbedPaneAddStudent() throws IOException {
        final JPanel panel = new JPanel(null);

        Button buttonOpenFile = new Button("Открыть файл");
        buttonOpenFile.setLocation(30, 450);
        buttonOpenFile.setSize(120,20);
        tabbedPane.add("добавить ученика", panel);
        panel.add(buttonOpenFile);


        buttonOpenFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Открыть файл");

                if (ret == JFileChooser.APPROVE_OPTION) {
                    filePut = fileopen.getSelectedFile();

//                    try {
//                        tt();
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }finally {
//                        wait = 2;
//                    }
//
//                    while (wait == 1){}

                    BufferedImage img = null;
                    try {
                        img = ImageIO.read(filePut);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    ImageIcon imageIcon = new ImageIcon(img); // load the image to a imageIcon
                    Image image = imageIcon.getImage(); // transform it
                    Image newimg = image.getScaledInstance(250, 350, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    imageIcon = new ImageIcon(newimg);  // transform it back
                    JLabel label = new JLabel(imageIcon);
                    final JPanel panel1 = new JPanel();
                    panel1.add(createPanelBorder(new LineBorder(Color.ORANGE, 4), "LineBorder"));
                    panel1.add(label);
                    panel1.setLocation(20,20);
                    panel1.setSize(200,450);
                    panel.add(panel1);

                }
            }
        });

//        String IMG_PATH = "https://firebasestorage.googleapis.com/v0/b/ex1firebaseproject-162ac.appspot.com/o/LentaNewsFoto%2F%D1%82%D0%B5%D0%BC%D0%B0%20%D0%BD%D0%BE%D0%B2%D0%BE%D1%81%D1%82%D0%B80.jpg?alt=media&token=267cd9dd-2e9a-4469-bf57-530cb7a14cb3";
//        URL url = new URL(IMG_PATH);
//        BufferedImage img = ImageIO.read(url);

    }

    private JPanel createPanelBorder(Border border, String text) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JButton(text));
        panel.setBorder(new CompoundBorder(new EmptyBorder(12,12,12,12), border));
        return panel;
    }


    private void getJTabbedPane2() {
        JPanel panel = new JPanel();
        tabbedPane.add("панель 2", panel);
    }

    void tt() throws IOException {
        Thumbnails.of(filePut)
                .size(640, 480)
                .outputFormat("jpg")
                .toFiles(Rename.NO_CHANGE);
//                .toFiles(Rename.PREFIX_DOT_THUMBNAIL);
    }

    void CompressorFoto() throws IOException {
//        File input = new File("original_image.jpg");
        BufferedImage image = ImageIO.read(filePut);

        File compressedImageFile = new File("compressed_image.jpg");
        OutputStream os = new FileOutputStream(compressedImageFile);

        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
        ImageWriter writer = (ImageWriter) writers.next();

        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
        writer.setOutput(ios);

        ImageWriteParam param = writer.getDefaultWriteParam();

        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(0.05f);  // Change the quality value you prefer
        writer.write(null, new IIOImage(image, null, null), param);

        os.close();
        ios.close();
        writer.dispose();
    }


    public static void main(String[] args) throws IOException {

        MainJframe app = new MainJframe();
        app.setVisible(true);
    }
}
