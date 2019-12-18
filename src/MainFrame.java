import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;


public class MainFrame extends JFrame  {
    private JPanel optionPanel;
    private Board board;
    private String elements = "BINARY AVL TREE";
    private boolean isSearch = false;

    private void initMainPanel() {
        board = new Board((int) ((this.getWidth() - 200) / 2 - 17.5), 15);
        board.setBackground(new Color(180, 180, 180));
        board.setBorder(new LineBorder(new Color(48, 56, 58), 1));
        this.add(board, BorderLayout.CENTER);
    }

    private void initHeadPanel() {
        JPanel headPanel = new JPanel();
        headPanel.setPreferredSize(new Dimension(100, 30));
        headPanel.setBorder(new LineBorder(new Color(48, 56, 58), 1));
        headPanel.setBackground(new Color(21, 249, 48));
        JLabel headLabel = new JLabel("BINARY AVL TREE");
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headPanel.add(headLabel);
        this.add(headPanel, BorderLayout.NORTH);
    }

    private void showWarring() {
        JOptionPane.showOptionDialog(this,
                elements, "WARRING", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, null, "default");
    }

    private void initRadioPanel() {

        JPanel radioPanel = new JPanel();
        radioPanel.setBorder(new LineBorder(Color.blue, 1));
        radioPanel.setPreferredSize(new Dimension(90, 100));
        radioPanel.setBackground(new Color(29, 249, 50));
        radioPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel head = new JLabel("INSERT");
        radioPanel.add(head);
        JTextField textInsert = new JTextField(7);
        radioPanel.add(textInsert);
        ActionListener actionListener = e -> {
            if (!textInsert.getText().matches("-?[0-9]+")) {
                elements = "You can only enter integers";
                this.showWarring();
                return;
            }
            board.insertNode(Integer.parseInt(textInsert.getText()));
            textInsert.setText("");
            repaint();
        };
        JButton buttonInsert = new JButton("Enter");
        buttonInsert.addActionListener(actionListener);
        radioPanel.add(buttonInsert);
        optionPanel.add(radioPanel);

        JPanel removePanel = new JPanel();
        removePanel.setBorder(new LineBorder(Color.WHITE, 1));
        removePanel.setPreferredSize(new Dimension(90, 100));
        removePanel.setBackground(new Color(22, 249, 33));
        removePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel remove = new JLabel("REMOVE");
        removePanel.add(remove);
        JTextField textRemove = new JTextField(7);
        removePanel.add(textRemove);
        ActionListener listener = e -> {
            if (!textRemove.getText().matches("-?[0-9]+")) {
                elements = "You can only enter integers";
                this.showWarring();
                return;
            }
            board.removeNode(Integer.parseInt(textRemove.getText()));
            textRemove.setText("");
            repaint();
        };
        JButton buttonRemove = new JButton("Enter");
        buttonRemove.addActionListener(listener);
        removePanel.add(buttonRemove);
        optionPanel.add(removePanel);

        JPanel findPanel = new JPanel();
        findPanel.setBorder(new LineBorder(Color.blue, 1));
        findPanel.setPreferredSize(new Dimension(90, 100));
        findPanel.setBackground(new Color(18, 249, 78));
        findPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel find = new JLabel("  FIND  ");
        findPanel.add(find);
        JTextField textFind = new JTextField(7);
        findPanel.add(textFind);
        ActionListener listener1 = e -> {
            if (!textFind.getText().matches("-?[0-9]+")) {
                elements = "You can only enter integers";
                this.showWarring();
                return;
            }
            board.findNode(Integer.parseInt(textFind.getText()));
            textFind.setText("");
            this.repaint();
            if (!isSearch) {
                elements = " You found node ";
                this.showWarring();
            }

        };
        JButton buttonFind = new JButton("Enter");
        buttonFind.addActionListener(listener1);
        findPanel.add(buttonFind);
        optionPanel.add(findPanel);

        JPanel travelPanel = new JPanel();
        travelPanel.setBorder(new LineBorder(Color.blue, 1));
        travelPanel.setPreferredSize(new Dimension(90, 70));
        travelPanel.setBackground(new Color(19, 249, 54));
        travelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel travel = new JLabel(" PRINT TREE ");
        travelPanel.add(travel);
        ActionListener aL = e -> {
            elements = board.toString();
            this.showWarring();
        };
        JButton buttonTravel = new JButton("Enter");
        buttonTravel.addActionListener(aL);
        travelPanel.add(buttonTravel);
        optionPanel.add(travelPanel);
    }

    private void initOptionPanel() {
        optionPanel = new JPanel();
        optionPanel.setPreferredSize(new Dimension(600, 90));
        optionPanel.setBorder(new LineBorder(Color.PINK, 1));
        optionPanel.setBackground(new Color(24, 112, 24, 255));
        initRadioPanel();
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        optionPanel.add(exitButton);
        ActionListener actionListener = e -> {
            board.clearBoard();
            repaint();
        };
        JButton clear = new JButton("Clear");
        clear.addActionListener(actionListener);
        optionPanel.add(clear);
        this.add(optionPanel, BorderLayout.SOUTH);
    }

    private MainFrame(String s) {
        super(s);
        setSize(1000, 800);
        this.setLayout(new BorderLayout());
        initMainPanel();
        initHeadPanel();
        initOptionPanel();
        setVisible(true);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame("AVL Tree");
    }
}
