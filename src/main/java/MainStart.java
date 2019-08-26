import javafx.application.Application;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.text.Text;

public class MainStart extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Загружаем изображение (указываем путь)
        Image image = new Image("image.png");
        // Загруженное изображение отображаем в контейнере.
        ImageView imageView = new ImageView(image);

        // Контейнеру прописываем расположение в контейнере верхнего уровня
        imageView.setLayoutX(0);
        imageView.setLayoutY(0);

        // Контейнером верхнего уровня будет Группа. Она же будет корнем сцены приложения.
        Group root = new Group();
        // Добавляем дочерний элемент к корню - наш контейнер отображения картинки.
        root.getChildren().add(imageView);

        // Создаём сцену. (корень, ширина окна, высота окна)
        Scene scene = new Scene(root, 300, 250);

        // Имя приложения в шапке окна.
        primaryStage.setTitle("Картинка");
        // Назначаем сцену приложению.
        primaryStage.setScene(scene);
        // Отображаем сцену.
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
//    поделитьсяулучшить этот ответ
//        ответ дан 23 май '16 в 11:35

//        DimXenon
//        1,38099 серебряных знаков2222 бронзовых знака
//        Если использование JFrame принципиально: решение есть здесь: stackoverflow.com/questions/18777893/jframe-background-image (загрузка изображения и установка его как фона окна) – DimXenon 23 май '16 в 11:52
//        добавить комментарий
//
//        0
//
//        Создайте новый метод:
//
//        import java.io.IOException;
//        import java.awt.BorderLayout;
//        import javax.imageio.ImageIO;
//        import javax.swing.JLabel;
//protected void setImage(String path /* Принимает путь картинки */) {
//        File f; // Создаём файл
//        f = new File(path); // Устанавливаем путь для файла
//        try {
//        img = ImageIO.read(f); // Считываем картинку
//        } catch(IOException ioe) {
//        JOptionPane.showConfirmDialog(null, "Что-то неправильно!\n" + ioe.toString(),
//        "Error!", JOptionPane.PLAIN_MESSAGE);
//        System.exit(0);
//        }
//        JLabel jl = new JLabel(new ImageIcon(img));
//        JPanel jp = new JPanel();
//        jp.setLayout(new BorderLayout());
//        jp.add("Center", jl);
//
//        }