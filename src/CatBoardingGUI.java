import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Lead Author(s): Jiaqi Zhang
 *
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java:
 * Object-Oriented Problem Solving.
 * https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *
 * Oracle JFrame Documentation:
 * https://docs.oracle.com/javase/8/docs/api/javax/swing/JFrame.html
 *
 * Oracle ActionListener Documentation:
 * https://docs.oracle.com/javase/8/docs/api/java/awt/event/ActionListener.html
 *
 * Oracle BorderLayout Documentation:
 * https://docs.oracle.com/javase/8/docs/api/java/awt/BorderLayout.html
 *
 * Swing Layout Tutorial:
 * https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
 *
 * Responsibilities of class:
 * CatBoardingGUI creates the screen and sends data work to BoardingManager.
 */
public class CatBoardingGUI extends JFrame
{
	private static final String DATA_FILE_NAME = "cats.txt";
	private static final String DEFAULT_BIRTH_DATE = "2022-05-01";
	private static final String DEFAULT_START_DATE = "2026-05-01";
	private static final String DEFAULT_END_DATE = "2026-05-07";
	private static final String DEFAULT_DROP_OFF_TIME = "10:00";
	private static final String DEFAULT_PICK_UP_TIME = "17:00";

	// The GUI has-a BoardingManager, but the manager owns the actual data.
	private BoardingManager boardingManager;

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
	private JButton scheduleButton;
	private JButton undoButton;
	private JButton clearButton;

	/**
	 * Creates the GUI window.
	 */
	public CatBoardingGUI()
	{
		boardingManager = new BoardingManager();

		setTitle("Cat Boarding Management System");
		setSize(1150, 950);
		setMinimumSize(new Dimension(1050, 800));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new BackgroundPanel());
		getContentPane().setLayout(new BorderLayout(15, 15));

		try
		{
			// Nimbus is cleaner for users than the older default Swing style.
			UIManager.setLookAndFeel(
					"javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException problem)
		{
			System.out.println("Nimbus look and feel not available.");
		}

		createTitlePanel();
		createMainPanel();
		createOutputPanel();
		addEventHandlers();

		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void createTitlePanel()
	{
		JLabel titleLabel = new JLabel("Cat Boarding Management System",
				SwingConstants.CENTER);
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 34));
		titleLabel.setForeground(new Color(45, 55, 72));
		titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 10, 0, 10));

		JLabel subtitleLabel = new JLabel(
				"Manage cats, owners, care notes, dates, and special needs",
				SwingConstants.CENTER);
		subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		subtitleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
		subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		subtitleLabel.setForeground(new Color(75, 85, 99));

		JPanel titlePanel = new JPanel(new GridLayout(2, 1));
		titlePanel.setOpaque(false);
		titlePanel.add(titleLabel);
		titlePanel.add(subtitleLabel);

		add(titlePanel, BorderLayout.NORTH);
	}

	private void createMainPanel()
	{
		JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		mainPanel.setOpaque(false);

		JPanel leftPanel = new JPanel(new BorderLayout(10, 10));
		leftPanel.setOpaque(false);
		leftPanel.add(createInputPanel(), BorderLayout.CENTER);
		leftPanel.add(createSmartImportPanel(), BorderLayout.SOUTH);

		mainPanel.add(leftPanel, BorderLayout.CENTER);
		mainPanel.add(createActionPanel(), BorderLayout.EAST);

		JScrollPane mainScrollPane = new JScrollPane(mainPanel);
		mainScrollPane.setBorder(BorderFactory.createEmptyBorder());
		mainScrollPane.setOpaque(false);
		mainScrollPane.getViewport().setOpaque(false);

		add(mainScrollPane, BorderLayout.CENTER);
	}

	private JPanel createInputPanel()
	{
		JPanel panel = createSoftPanel();
		panel.setLayout(new GridBagLayout());

		GridBagConstraints gridConstraints = new GridBagConstraints();
		gridConstraints.insets = new Insets(7, 7, 7, 7);
		gridConstraints.fill = GridBagConstraints.HORIZONTAL;

		nameField = new JTextField(18);
		birthDateField = new JTextField(DEFAULT_BIRTH_DATE, 18);
		dropOffTimeField = new JTextField(DEFAULT_DROP_OFF_TIME, 18);
		pickUpTimeField = new JTextField(DEFAULT_PICK_UP_TIME, 18);
		ownerField = new JTextField(18);
		phoneField = new JTextField(18);
		startDateField = new JTextField(DEFAULT_START_DATE, 18);
		endDateField = new JTextField(DEFAULT_END_DATE, 18);
		careNotesField = new JTextField(18);
		medicalNotesField = new JTextField(18);
		searchField = new JTextField(18);

		addRow(panel, gridConstraints, 0, "Cat Name:", nameField);
		addRow(panel, gridConstraints, 1, "Birth Date:", birthDateField);
		addRow(panel, gridConstraints, 2, "Owner Name:", ownerField);
		addRow(panel, gridConstraints, 3, "Phone Number:", phoneField);
		addRow(panel, gridConstraints, 4, "Start Date:", startDateField);
		addRow(panel, gridConstraints, 5, "End Date:", endDateField);
		addRow(panel, gridConstraints, 6, "Drop-off Time:", dropOffTimeField);
		addRow(panel, gridConstraints, 7, "Pick-up Time:", pickUpTimeField);
		addRow(panel, gridConstraints, 8, "Care Notes:", careNotesField);
		addRow(panel, gridConstraints, 9, "Medical Notes:", medicalNotesField);
		addRow(panel, gridConstraints, 10, "Search Cat Name:", searchField);

		return panel;
	}

	private void addRow(JPanel panel, GridBagConstraints gridConstraints,
			int row, String labelText, JTextField field)
	{
		JLabel label = new JLabel(labelText);
		label.setFont(new Font("Segoe UI", Font.BOLD, 13));
		label.setForeground(new Color(51, 65, 85));

		field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		field.setPreferredSize(new Dimension(230, 32));

		// GridBagLayout keeps the form lined up when labels have different
		// lengths.
		gridConstraints.gridx = 0;
		gridConstraints.gridy = row;
		gridConstraints.weightx = 0.2;
		panel.add(label, gridConstraints);

		gridConstraints.gridx = 1;
		gridConstraints.gridy = row;
		gridConstraints.weightx = 0.8;
		panel.add(field, gridConstraints);
	}

	private JPanel createActionPanel()
	{
		JPanel panel = createSoftPanel();
		panel.setLayout(new GridLayout(9, 1, 10, 10));

		addButton = createStyledButton("Add Cat");
		removeButton = createStyledButton("Remove Cat");
		searchButton = createStyledButton("Search Cat");
		showAllButton = createStyledButton("Show All");
		saveButton = createStyledButton("Save File");
		loadButton = createStyledButton("Load File");
		scheduleButton = createStyledButton("Schedule");
		undoButton = createStyledButton("Undo Add");
		clearButton = createStyledButton("Clear");

		panel.add(addButton);
		panel.add(removeButton);
		panel.add(searchButton);
		panel.add(showAllButton);
		panel.add(saveButton);
		panel.add(loadButton);
		panel.add(scheduleButton);
		panel.add(undoButton);
		panel.add(clearButton);

		return panel;
	}

	private JButton createStyledButton(String text)
	{
		JButton button = new JButton(text);
		button.setFont(new Font("Segoe UI", Font.BOLD, 13));
		button.setPreferredSize(new Dimension(140, 38));
		button.setFocusPainted(false);
		return button;
	}

	private JPanel createSmartImportPanel()
	{
		JPanel panel = new JPanel(new BorderLayout(5, 5));
		panel.setBackground(new Color(255, 255, 255, 185));
		panel.setBorder(BorderFactory.createTitledBorder("Smart Import"));

		// Smart Fill lets the user review fields before the cat is saved.
		smartImportArea = new JTextArea(5, 30);
		smartImportArea.setLineWrap(true);
		smartImportArea.setWrapStyleWord(true);
		smartImportArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		smartFillButton = createStyledButton("Smart Fill");

		panel.add(new JScrollPane(smartImportArea), BorderLayout.CENTER);
		panel.add(smartFillButton, BorderLayout.SOUTH);

		return panel;
	}

	private void createOutputPanel()
	{
		outputArea = new JTextArea(5, 60);
		outputArea.setEditable(false);
		outputArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
		outputArea.setLineWrap(true);
		outputArea.setWrapStyleWord(true);
		outputArea.setBackground(new Color(255, 255, 255, 230));

		JScrollPane scrollPane = new JScrollPane(outputArea);
		scrollPane.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(0, 20, 20, 20),
				BorderFactory.createTitledBorder("Output Area")));

		add(scrollPane, BorderLayout.SOUTH);
	}

	private void addEventHandlers()
	{
		// Each listener calls a helper, so the button code stays easy to read.
		addButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				handleAddCat();
			}
		});

		removeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				handleRemoveCat();
			}
		});

		searchButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				handleSearchCat();
			}
		});

		showAllButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				outputArea.setText(boardingManager.getAllCatsAsString());
			}
		});

		saveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				handleSave();
			}
		});

		loadButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				handleLoad();
			}
		});

		scheduleButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				outputArea.setText(boardingManager.getScheduleReport());
			}
		});

		undoButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				handleUndoAdd();
			}
		});

		clearButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				clearFields();
				outputArea.setText("");
			}
		});

		smartFillButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				handleSmartFill();
			}
		});
	}

	private void handleAddCat()
	{
		try
		{
			Cat cat = createCatFromFields();
			boolean added = boardingManager.addCat(cat);

			if (added)
			{
				outputArea.setText("Cat added successfully.\n");
				outputArea.append("Auto Category: " + cat.getCategory()
						+ "\n\n");
				outputArea.append(boardingManager.getAllCatsAsString());
				clearInputOnly();
			}
			else
			{
				outputArea.setText(
						"Error: This cat name is already in the system.");
			}
		}
		catch (IllegalArgumentException problem)
		{
			outputArea.setText(problem.getMessage());
		}
		catch (DateTimeParseException problem)
		{
			outputArea.setText("Error: Please check the date or time format.\n"
					+ "Date format: yyyy-mm-dd\n" + "Time format: HH:mm");
		}
	}

	private Cat createCatFromFields()
	{
		String name = nameField.getText().trim();
		String ownerName = ownerField.getText().trim();
		String phone = phoneField.getText().trim();
		String careNotes = careNotesField.getText().trim();
		String medicalNotes = medicalNotesField.getText().trim();

		validateRequiredText(name, ownerName);
		String cleanPhone = validatePhone(phone);

		LocalDate birthDate = LocalDate.parse(birthDateField.getText().trim());
		LocalDate startDate = LocalDate.parse(startDateField.getText().trim());
		LocalDate endDate = LocalDate.parse(endDateField.getText().trim());
		LocalTime dropOffTime = LocalTime
				.parse(dropOffTimeField.getText().trim());
		LocalTime pickUpTime = LocalTime.parse(pickUpTimeField.getText().trim());

		validateDates(birthDate, startDate, endDate);

		Owner owner = new Owner(ownerName, cleanPhone);
		Cat cat;

		if (medicalNotes.isEmpty())
		{
			cat = new Cat(name, birthDate, owner, careNotes, startDate, endDate,
					dropOffTime, pickUpTime);
		}
		else
		{
			// Medical notes decide the subclass, while the rest of the program
			// can still treat it as a Cat.
			cat = new SpecialCat(name, birthDate, owner, careNotes, startDate,
					endDate, dropOffTime, pickUpTime, medicalNotes);
		}

		return cat;
	}

	private void validateRequiredText(String name, String ownerName)
	{
		if (name.isEmpty() || ownerName.isEmpty())
		{
			throw new IllegalArgumentException(
					"Error: Cat name and owner name cannot be empty.");
		}
	}

	private String validatePhone(String phone)
	{
		String cleanPhone = phone.replace("-", "").replace(" ", "");

		if (!cleanPhone.matches("\\d{10}"))
		{
			throw new IllegalArgumentException(
					"Error: Phone number must contain 10 digits.");
		}

		return cleanPhone;
	}

	private void validateDates(LocalDate birthDate, LocalDate startDate,
			LocalDate endDate)
	{
		if (birthDate.isAfter(LocalDate.now()))
		{
			throw new IllegalArgumentException(
					"Error: Birth date cannot be in the future.");
		}

		if (endDate.isBefore(startDate))
		{
			throw new IllegalArgumentException(
					"Error: End date cannot be before start date.");
		}
	}

	private void handleRemoveCat()
	{
		String name = nameField.getText().trim();

		if (name.isEmpty())
		{
			name = searchField.getText().trim();
		}

		boolean removed = boardingManager.removeCat(name);

		if (removed)
		{
			outputArea.setText("Cat removed successfully.\n\n");
		}
		else
		{
			outputArea.setText("Cat not found.\n\n");
		}

		outputArea.append(boardingManager.getAllCatsAsString());
	}

	private void handleSearchCat()
	{
		String name = searchField.getText().trim();

		if (name.isEmpty())
		{
			name = nameField.getText().trim();
		}

		outputArea.setText(boardingManager.searchAllCatsByName(name));
	}

	private void handleSave()
	{
		try
		{
			boardingManager.saveToFile(DATA_FILE_NAME);
			outputArea.setText("Cat data saved to " + DATA_FILE_NAME + ".");
		}
		catch (IOException problem)
		{
			outputArea.setText("Error: Could not save cat data.");
		}
	}

	private void handleLoad()
	{
		try
		{
			outputArea.setText(boardingManager.loadFromFile(DATA_FILE_NAME));
		}
		catch (IOException problem)
		{
			outputArea.setText("Error: Could not load cat data.");
		}
	}

	private void handleUndoAdd()
	{
		boolean undone = boardingManager.undoLastAdd();

		if (undone)
		{
			outputArea.setText("The most recent added cat was removed.\n\n");
			outputArea.append(boardingManager.getAllCatsAsString());
		}
		else
		{
			outputArea.setText("There is no recent add to undo.");
		}
	}

	private void clearFields()
	{
		clearInputOnly();
		searchField.setText("");
		smartImportArea.setText("");
	}

	private void handleSmartFill()
	{
		String text = smartImportArea.getText();

		if (text.trim().isEmpty())
		{
			outputArea.setText("Error: Please paste cat information first.");
		}
		else
		{
			fillFieldsFromSmartText(text);
			outputArea.setText("Smart Fill completed.\n"
					+ "Please review the fields and click Add Cat.");
		}
	}

	private void fillFieldsFromSmartText(String text)
	{
		nameField.setText(SmartCatParser.findCatName(text));
		ownerField.setText(SmartCatParser.findOwnerName(text));
		phoneField.setText(SmartCatParser.findPhone(text));

		setFieldIfFound(birthDateField, SmartCatParser.findFirstDate(text));
		setFieldIfFound(startDateField, SmartCatParser.findSecondDate(text));
		setFieldIfFound(endDateField, SmartCatParser.findThirdDate(text));
		setFieldIfFound(dropOffTimeField, SmartCatParser.findFirstTime(text));
		setFieldIfFound(pickUpTimeField, SmartCatParser.findSecondTime(text));

		careNotesField.setText(SmartCatParser.findCareNotes(text));
		medicalNotesField.setText(SmartCatParser.findMedicalNotes(text));
	}

	private void setFieldIfFound(JTextField field, String value)
	{
		if (!value.isEmpty())
		{
			// Empty parser results should not erase the helpful example values.
			field.setText(value);
		}
	}

	private void clearInputOnly()
	{
		nameField.setText("");
		birthDateField.setText(DEFAULT_BIRTH_DATE);
		dropOffTimeField.setText(DEFAULT_DROP_OFF_TIME);
		pickUpTimeField.setText(DEFAULT_PICK_UP_TIME);
		ownerField.setText("");
		phoneField.setText("");
		careNotesField.setText("");
		medicalNotesField.setText("");
		startDateField.setText(DEFAULT_START_DATE);
		endDateField.setText(DEFAULT_END_DATE);
	}

	private JPanel createSoftPanel()
	{
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255, 185));
		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(226, 232, 240)),
				BorderFactory.createEmptyBorder(15, 15, 15, 15)));
		return panel;
	}

	/**
	 * Starts the GUI program.
	 *
	 * @param args command-line arguments
	 */
	public static void main(String[] args)
	{
		new CatBoardingGUI();
	}
}
