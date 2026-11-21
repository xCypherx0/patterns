package demo;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class DemoRunner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Паттерны проектирования | Вариант 12 | Запуск программ");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JTabbedPane tabs = new JTabbedPane();
            for (int number = 9; number <= 15; number++) {
                JTextArea output = new JTextArea();
                output.setEditable(false);
                output.setFont(new Font("Consolas", Font.PLAIN, 16));
                output.setMargin(new java.awt.Insets(12, 14, 12, 14));
                output.setText(run(number));
                output.setCaretPosition(0);
                tabs.addTab("Лаба " + number, new JScrollPane(output));
            }
            frame.add(tabs, BorderLayout.CENTER);
            frame.setSize(1060, 610);
            frame.setLocation(25, 25);
            frame.setVisible(true);
            if (args.length > 0 && args[0].equals("--capture")) {
                new Thread(() -> {
                    try {
                        java.awt.Robot robot = new java.awt.Robot();
                        Thread.sleep(12000);
                        for (int i = 0; i < 7; i++) {
                            final int selected = i;
                            SwingUtilities.invokeAndWait(() -> {
                                tabs.setSelectedIndex(selected);
                                JScrollPane pane = (JScrollPane) tabs.getComponentAt(selected);
                                JTextArea text = (JTextArea) pane.getViewport().getView();
                                frame.setSize(1060, Math.min(610, 90 + text.getLineCount() * 19));
                                frame.toFront();
                            });
                            Thread.sleep(800);
                            java.awt.Rectangle bounds = frame.getBounds();
                            javax.imageio.ImageIO.write(robot.createScreenCapture(bounds), "png",
                                    new java.io.File("screens/lab" + (i + 9) + ".png"));
                        }
                    } catch (Exception e) { e.printStackTrace(); }
                }).start();
            }
        });
    }
    private static String run(int number) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream original = System.out;
        try (PrintStream capture = new PrintStream(bytes, true, StandardCharsets.UTF_8)) {
            System.setOut(capture);
            Class.forName("lab" + number + ".Main").getMethod("main", String[].class)
                    .invoke(null, (Object) new String[0]);
        } catch (Exception error) {
            error.printStackTrace(new PrintStream(bytes));
        } finally {
            System.setOut(original);
        }
        return bytes.toString(StandardCharsets.UTF_8);
    }
}



