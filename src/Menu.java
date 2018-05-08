import javax.swing.*;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JFrame;
import java.awt.event.*;
import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.lang.String;

public class Menu extends JFrame {

    private JList listOfMovies;
    private JPanel root;
    private JButton deleteButton;
    private JButton clearButton;
    private JButton updateButton;
    private JTextField textSearch;
    private JTextField textProducer;
    private JTextField textTitle;
    private JButton addButton;
    private IMyListModel listModel;
    private ArrayList<Movie> movies = new ArrayList<>();


    public Menu() {

        listModel = new MyListModel();
        listOfMovies.setModel(listModel);

        setTitle("Movies");
        setSize(800, 900);

        //The Roots too GUI form
        setContentPane(root);


        //Create menuBar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);


        //Create files for menuBar
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);


        //Create menuBar files
        JMenuItem save = new JMenuItem("Save");
        JMenuItem load = new JMenuItem("Load");
        JMenuItem exit = new JMenuItem("Exit");


        //files for fileMenuBar
        fileMenu.add(save);
        fileMenu.add(load);
        fileMenu.add(exit);


        //Adding Title & Producer to listOfMovies

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // listModel.add(textTitle.getText()+ " + " + textProducer.getText());
                Movie m = new Movie(textTitle.getText(), textProducer.getText());
                movies.add(m);
                listModel.add(m);

            }
        });


        //Delete Movie file
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movies.remove(listModel.remove(listOfMovies.getSelectedIndex()));

            }
        });


        // Clear Movie List
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel.clear();
                movies.clear();

            }
        });


        //Edit/Update files
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // listModel.update(listOfMovies.getSelectedIndex(), new Movie(textTitle.getText(), textProducer.getText()));
                listModel.update(listOfMovies.getSelectedIndex(), new Movie(textTitle.getText(), textProducer.getText()));
                movies.set(listOfMovies.getSelectedIndex(), new Movie(textTitle.getText(), textProducer.getText()));


            }
        });


        // Exit with itemMenu
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent exit) {
                System.exit(0);
            }
        });

        //Search Movies
        textSearch.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent event) {
                String st = textSearch.getText();
                listModel = new MyListModel();
                listOfMovies.setModel(listModel);
                for (Movie m : movies) {
                    if (m.getTitle().toLowerCase().contains(st.toLowerCase())) {
                        listModel.add(m);
                    }
                }
            }
        });


        //Load file
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                int i = fc.showOpenDialog(null);
                if (i == JFileChooser.APPROVE_OPTION) {
                    try {
                        FileReader fileReader = new FileReader(fc.getSelectedFile());

                        BufferedReader br = new BufferedReader(fileReader);
                        String row;
                        while ((row = br.readLine()) != null) {

                            String[] posts = row.split("|");
                            movies.add(new Movie(posts[0], posts[1]));
                        }
                        br.close();
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }
            }
        });


        //Save file
/*        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fc = new JFileChooser();
                int i = fc.showSaveDialog(null);
                if (i == JFileChooser.APPROVE_OPTION) {
                    try {
                        FileWriter fileWriter = new FileWriter(fc.getSelectedFile());

                        BufferedWriter br = new BufferedWriter(fileWriter);
                        String row;
                        while ((br.write(row)) != null) {

                            String[] posts = row.split("|");
                            movies.add(new Movie(posts[0], posts[1]));
                        }
                        br.close();
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();

                    }
                }
            }

        });


*/
        }


    public static void main(String[] args) {


        //Look and feel
        try {

            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {

        } catch (ClassNotFoundException e) {

        } catch (InstantiationException e) {

        } catch (IllegalAccessException e) {

        }

        Menu menu = new Menu();
        menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menu.setVisible(true);
        menu.setLocationRelativeTo(null);

    }

}
