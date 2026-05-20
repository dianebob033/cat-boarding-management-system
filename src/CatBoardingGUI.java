import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Lead Author(s): Jiaqi Zhang
 * 
 * Responsibilities of class:
 * The CatBoardingGUI class creates the graphical user interface
 * 
 * 中文说明：
 * 这个类负责创建猫咪寄养系统的图形界面，并处理按钮点击事件。
 */
public class CatBoardingGUI extends JFrame {
    private BoardingManager manager;

    private JTextField nameField;
    private JTextField birthDateField;
    private JTextField dropOffTimeField;
    private JTextField pickUpTimeField;
    private JTextField ownerField;
    private JTextField phoneField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextField careNotesField;
    private JTextField medicalNotesField;
    private JTextField searchField;
    
    private JTextArea smartImportArea;
    private JButton smartFillButton;
    
    private JTextArea outputArea;

    private JButton addButton;
    private JButton removeButton;
    private JButton searchButton;
    private JButton showAllButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton clearButton;

    public CatBoardingGUI() {
        manager = new BoardingManager();

        setTitle("Cat Boarding Management System");
        setSize(1000, 1200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("Nimbus look and feel not available.");
        }

        createTitlePanel();
        createMainPanel();
        createOutputPanel();
        addEventHandlers();

        getContentPane().setBackground(new Color(245, 247, 250));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Creates the title area.
     * 创建标题区域。
     */
    private void createTitlePanel() {
        JLabel titleLabel = new JLabel("Cat Boarding Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(45, 55, 72));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        JLabel subtitleLabel = new JLabel("Manage boarding cats, care notes, dates, and categories", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(100, 116, 139));

        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setBackground(new Color(245, 247, 250));
        titlePanel.add(titleLabel);
        titlePanel.add(subtitleLabel);

        add(titlePanel, BorderLayout.NORTH);
    }

    /**
     * Creates the main input and action area.
     * 创建主要输入和按钮区域。
     */
    private void createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        mainPanel.setBackground(new Color(245, 247, 250));

        JPanel inputPanel = createInputPanel();
        JPanel actionPanel = createActionPanel();

        JPanel leftPanel = new JPanel(new BorderLayout(10, 10));
        leftPanel.setBackground(new Color(245, 247, 250));
        leftPanel.add(inputPanel, BorderLayout.CENTER);
        leftPanel.add(createSmartImportPanel(), BorderLayout.SOUTH);

        mainPanel.add(leftPanel, BorderLayout.CENTER);
        mainPanel.add(actionPanel, BorderLayout.EAST);

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Creates input fields.
     * 创建输入框。
     */
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240)),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 7, 7, 7);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        nameField = new JTextField(18);
        birthDateField = new JTextField("2022-05-01", 18);
        dropOffTimeField = new JTextField("10:00", 18);
        pickUpTimeField = new JTextField("17:00", 18);
        ownerField = new JTextField(18);
        phoneField = new JTextField(18);
        startDateField = new JTextField("2026-05-01", 18);
        endDateField = new JTextField("2026-05-07", 18);
        careNotesField = new JTextField(18);
        medicalNotesField = new JTextField(18);
        searchField = new JTextField(18);

        addRow(panel, gbc, 0, "Cat Name:", nameField);
        addRow(panel, gbc, 1, "Birth Date:", birthDateField);
        addRow(panel, gbc, 2, "Owner Name:", ownerField);
        addRow(panel, gbc, 3, "Phone Number:", phoneField);
        addRow(panel, gbc, 4, "Start Date:", startDateField);
        addRow(panel, gbc, 5, "End Date:", endDateField);
        addRow(panel, gbc, 6, "Drop-off Time:", dropOffTimeField);
        addRow(panel, gbc, 7, "Pick-up Time:", pickUpTimeField);
        addRow(panel, gbc, 8, "Care Notes:", careNotesField);
        addRow(panel, gbc, 9, "Medical Notes:", medicalNotesField);
        addRow(panel, gbc, 10, "Search Cat Name:", searchField);

        return panel;
    }

    /**
     * Helper method for adding one row of label and text field.
     * 辅助方法：添加一行标签和输入框。
     */
    private void addRow(JPanel panel, GridBagConstraints gbc, int row, String labelText, JTextField field) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 13));
        label.setForeground(new Color(51, 65, 85));

        field.setFont(new Font("Arial", Font.PLAIN, 13));
        field.setPreferredSize(new Dimension(230, 32));

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.2;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.weightx = 0.8;
        panel.add(field, gbc);
    }

    /**
     * Creates action buttons.
     * 创建按钮区域。
     */
    private JPanel createActionPanel() {
        JPanel panel = new JPanel(new GridLayout(7, 1, 10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240)),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        addButton = createStyledButton("Add Cat");
        removeButton = createStyledButton("Remove Cat");
        searchButton = createStyledButton("Search Cat");
        showAllButton = createStyledButton("Show All");
        saveButton = createStyledButton("Save File");
        loadButton = createStyledButton("Load File");
        clearButton = createStyledButton("Clear");

        panel.add(addButton);
        panel.add(removeButton);
        panel.add(searchButton);
        panel.add(showAllButton);
        panel.add(saveButton);
        panel.add(loadButton);
        panel.add(clearButton);

        return panel;
    }

    /**
     * Creates a styled button.
     * 创建统一风格的按钮。
     */
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setPreferredSize(new Dimension(140, 38));
        button.setFocusPainted(false);
        return button;
    }
    
    /**
     * Creates smart import area.
     * 创建智能导入区域。
     */
    private JPanel createSmartImportPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Smart Import"));

        smartImportArea = new JTextArea(5, 30);
        smartImportArea.setLineWrap(true);
        smartImportArea.setWrapStyleWord(true);

        smartFillButton = createStyledButton("Smart Fill");

        panel.add(new JScrollPane(smartImportArea), BorderLayout.CENTER);
        panel.add(smartFillButton, BorderLayout.SOUTH);

        return panel;
    }
    
    /**
     * Creates output area.
     * 创建输出区域。
     */
    private void createOutputPanel() {
        outputArea = new JTextArea(6, 60);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 20, 20, 20),
                BorderFactory.createTitledBorder("Output Area")
        ));

        add(scrollPane, BorderLayout.SOUTH);
    }

    /**
     * Adds event handlers to buttons.
     * 给按钮添加事件监听。
     */
    private void addEventHandlers() {
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleAddCat();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleRemoveCat();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleSearchCat();
            }
        });

        showAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                outputArea.setText(manager.getAllCatsAsString());
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleSave();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLoad();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
                outputArea.setText("");
            }
        });
        
        smartFillButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleSmartFill();
            }
        });
    }

    /**
     * Handles adding a cat.
     * 处理添加猫。
     */
    private void handleAddCat() {
        try {
            String name = nameField.getText().trim();
            LocalDate birthDate = LocalDate.parse(birthDateField.getText().trim());
            LocalTime dropOffTime = LocalTime.parse(dropOffTimeField.getText().trim());
            LocalTime pickUpTime = LocalTime.parse(pickUpTimeField.getText().trim());
            String ownerName = ownerField.getText().trim();
            String phone = phoneField.getText().trim();
            String careNotes = careNotesField.getText().trim();
            String medicalNotes = medicalNotesField.getText().trim();

            LocalDate startDate = LocalDate.parse(startDateField.getText().trim());
            LocalDate endDate = LocalDate.parse(endDateField.getText().trim());

            if (name.isEmpty() || ownerName.isEmpty()) {
                outputArea.setText("Error: Cat name and owner name cannot be empty.");
                return;
            }

            if (endDate.isBefore(startDate)) {
                outputArea.setText("Error: End date cannot be before start date.");
                return;
            }

            Owner owner = new Owner(ownerName, phone);
            Cat cat;

            if (medicalNotes.isEmpty()) {

                cat = new Cat(
                        name,
                        birthDate,
                        owner,
                        careNotes,
                        startDate,
                        endDate,
                        dropOffTime,
                        pickUpTime
                );

            } else {

                cat = new SpecialCat(
                        name,
                        birthDate,
                        owner,
                        careNotes,
                        startDate,
                        endDate,
                        dropOffTime,
                        pickUpTime,
                        medicalNotes
                );
            }

            manager.addCat(cat);

            outputArea.setText("Cat added successfully.\n");
            outputArea.append("Auto Category: " + cat.getCategory() + "\n\n");
            outputArea.append(manager.getAllCatsAsString());

            clearInputOnly();

        } catch (Exception e) {

            outputArea.setText(
                    "Error: Please check the date or time format.\n"
                    + "Date format: yyyy-mm-dd\n"
                    + "Time format: HH:mm"
            );
        }
    }

    /**
     * Handles removing a cat.
     * 处理删除猫。
     */
    private void handleRemoveCat() {
        String name = nameField.getText().trim();

        if (name.isEmpty()) {
            name = searchField.getText().trim();
        }

        boolean removed = manager.removeCat(name);

        if (removed) {
            outputArea.setText("Cat removed successfully.\n\n");
        } else {
            outputArea.setText("Cat not found.\n\n");
        }

        outputArea.append(manager.getAllCatsAsString());
    }

    /**
     * Handles searching for a cat.
     * 处理查询猫。
     */
    private void handleSearchCat() {
        String name = searchField.getText().trim();

        if (name.isEmpty()) {
            name = nameField.getText().trim();
        }

        Cat foundCat = manager.recursiveSearchByName(name);

        if (foundCat != null) {
            outputArea.setText(
                    foundCat.toString()
                    + "\nFeeding: "
                    + foundCat.getFeedMessage()
            );
        } else {
            outputArea.setText("Cat not found.");
        }
    }

    /**
     * Handles saving file.
     * 处理保存文件。
     */
    private void handleSave() {
        manager.saveToFile("cats.txt");
        outputArea.setText("Cat data saved to cats.txt");
    }

    /**
     * Handles loading file.
     * 处理读取文件。
     */
    private void handleLoad() {
        outputArea.setText(manager.loadFromFile("cats.txt"));
    }

    /**
     * Clears all fields.
     * 清空所有输入框和搜索框。
     */
    private void clearFields() {
        clearInputOnly();
        searchField.setText("");
    }
    
    /**
     * Handles Smart Fill button.
     * 处理智能填入按钮。
     */
    private void handleSmartFill() {

        String text = smartImportArea.getText();

        if (text.trim().isEmpty()) {
            outputArea.setText("Error: Please paste cat information first.");
            return;
        }

        String catName = SmartCatParser.findCatName(text);
        String ownerName = SmartCatParser.findOwnerName(text);
        String phone = SmartCatParser.findPhone(text);

        String birthDate = SmartCatParser.findFirstDate(text);
        String startDate = SmartCatParser.findSecondDate(text);
        String endDate = SmartCatParser.findThirdDate(text);

        String dropOffTime = SmartCatParser.findFirstTime(text);
        String pickUpTime = SmartCatParser.findSecondTime(text);

        String careNotes = SmartCatParser.findCareNotes(text);
        String medicalNotes = SmartCatParser.findMedicalNotes(text);

        nameField.setText(catName);
        ownerField.setText(ownerName);
        phoneField.setText(phone);

        if (!birthDate.isEmpty()) {
            birthDateField.setText(birthDate);
        }

        if (!startDate.isEmpty()) {
            startDateField.setText(startDate);
        }

        if (!endDate.isEmpty()) {
            endDateField.setText(endDate);
        }

        if (!dropOffTime.isEmpty()) {
            dropOffTimeField.setText(dropOffTime);
        }

        if (!pickUpTime.isEmpty()) {
            pickUpTimeField.setText(pickUpTime);
        }

        careNotesField.setText(careNotes);
        medicalNotesField.setText(medicalNotes);

        outputArea.setText(
                "Smart Fill completed.\n"
                + "Please review the fields and click Add Cat."
        );
    }

    /**
     * Clears only input fields.
     * 只清空猫咪信息输入框。
     */
    private void clearInputOnly() {
        nameField.setText("");
        birthDateField.setText("2022-05-01");
        dropOffTimeField.setText("10:00");
        pickUpTimeField.setText("17:00");
        ownerField.setText("");
        phoneField.setText("");
        careNotesField.setText("");
        medicalNotesField.setText("");
        startDateField.setText("2026-05-01");
        endDateField.setText("2026-05-07");
    }

    public static void main(String[] args) {
        new CatBoardingGUI();
    }
}