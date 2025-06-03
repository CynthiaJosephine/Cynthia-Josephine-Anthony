import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StyledToDoList extends JFrame {
    private JTextField taskInput;
    private JButton addButton;
    private JPanel taskPanel;
    private JScrollPane scrollPane;
    private ArrayList<JPanel> taskItems;

    public StyledToDoList() {
        setTitle("To-Do List");
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        taskItems = new ArrayList<>();

        // Header Panel (like CSS header)
        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(new EmptyBorder(10, 10, 10, 10));
        header.setBackground(new Color(0x283593));

        JLabel title = new JLabel("My To-Do List");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        header.add(title, BorderLayout.WEST);

        add(header, BorderLayout.NORTH);

        // Input Panel (like form section)
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        taskInput = new JTextField();
        taskInput.setFont(new Font("Arial", Font.PLAIN, 16));
        taskInput.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        inputPanel.add(taskInput, BorderLayout.CENTER);

        addButton = new JButton("Add");
        addButton.setBackground(new Color(0x4CAF50));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        inputPanel.add(addButton, BorderLayout.EAST);

        add(inputPanel, BorderLayout.SOUTH);

        // Task List Panel
        taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        taskPanel.setBackground(Color.WHITE);

        scrollPane = new JScrollPane(taskPanel);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        // Action for Add Button
        addButton.addActionListener(e -> addTask(taskInput.getText().trim()));

        // Enter key press in text field
        taskInput.addActionListener(e -> addTask(taskInput.getText().trim()));
    }

    private void addTask(String taskText) {
        if (taskText.isEmpty()) return;

        JPanel taskItem = new JPanel(new BorderLayout());
        taskItem.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        taskItem.setBackground(Color.WHITE);
        taskItem.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        JCheckBox checkBox = new JCheckBox(taskText);
        checkBox.setFont(new Font("Arial", Font.PLAIN, 16));
        checkBox.setBackground(Color.WHITE);

        JButton deleteButton = new JButton("X");
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setBackground(Color.RED);
        deleteButton.setFocusPainted(false);
        deleteButton.setPreferredSize(new Dimension(40, 30));

        deleteButton.addActionListener(e -> {
            taskPanel.remove(taskItem);
            taskPanel.revalidate();
            taskPanel.repaint();
        });

        taskItem.add(checkBox, BorderLayout.CENTER);
        taskItem.add(deleteButton, BorderLayout.EAST);

        taskPanel.add(taskItem);
        taskPanel.revalidate();

        taskInput.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StyledToDoList().setVisible(true);
        });
    }
}
