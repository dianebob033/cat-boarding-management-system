import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Lead Author(s): Jiaqi Zhang
 * 
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java:
 * Object-Oriented Problem Solving.
 * Retrieved from:
 * https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 * 
 * Oracle Java Documentation:
 * https://docs.oracle.com/javase/8/docs/api/
 * 
 * Responsibilities of class:
 * The CatBoardingGUI class creates the graphical user interface
 */
public class CatBoardingGUI extends JFrame
{
	private BoardingManager bm;

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

	public CatBoardingGUI()
	{
		// I use one BoardingManager object here because the GUI should not
		// store cat data directly.
		// The manager keeps the data logic separate from the screen.
		bm = new BoardingManager();

		setTitle("Cat Boarding Management System");
		setSize(1000, 1200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new BackgroundPanel());
		// BorderLayout helps separate the title, main form, and output area.
		getContentPane().setLayout(new BorderLayout(15, 15));
		
		try
		{
			// I use Nimbus because the default Java Swing style looks old.
			// This makes the project easier to read when I demo it.
			UIManager.setLookAndFeel(
					"javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch (Exception e)
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

	/**
	 * Creates the title area.
	 */
	private void createTitlePanel()
	{
		JLabel titleLabel = new JLabel("Cat Boarding Management System",
				SwingConstants.CENTER);
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		titleLabel.setForeground(new Color(45, 55, 72));
		titleLabel.setBorder(BorderFactory.createEmptyBorder(55, 10, 0, 10));

		JLabel subtitleLabel = new JLabel(
				"Manage boarding cats, care notes, dates, and categories",
				SwingConstants.CENTER);
		subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		subtitleLabel.setForeground(new Color(110, 116, 139));

		JPanel titlePanel = new JPanel(new GridLayout(2, 1));
		titlePanel.setOpaque(false);
		titlePanel.add(titleLabel);
		titlePanel.add(subtitleLabel);

		add(titlePanel, BorderLayout.NORTH);
	}

	/**
	 * Creates the main input and action area.
	 */
	private void createMainPanel()
	{
		JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		mainPanel.setOpaque(false);

		JPanel inputPanel = createInputPanel();
		JPanel actionPanel = createActionPanel();

		JPanel leftPanel = new JPanel(new BorderLayout(10, 10));
		leftPanel.setOpaque(false);
		leftPanel.add(inputPanel, BorderLayout.CENTER);
		leftPanel.add(createSmartImportPanel(), BorderLayout.SOUTH);

		mainPanel.add(leftPanel, BorderLayout.CENTER);
		mainPanel.add(actionPanel, BorderLayout.EAST);

		add(mainPanel, BorderLayout.CENTER);
	}

	/**
	 * Creates input fields.
	 */
	private JPanel createInputPanel()
	{
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(new Color(255, 255, 255, 180));
		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(226, 232, 240)),
				BorderFactory.createEmptyBorder(15, 15, 15, 15)));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(7, 7, 7, 7);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// JTextField lets the user type simple text information.
		nameField = new JTextField(18);

		// These default values show the format the user should follow.
		// It also makes testing faster during the demo.
		birthDateField = new JTextField("2022-05-01", 18);
		// Example times make testing faster and reduce input mistakes.
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
	 * This helper method keeps the GUI layout code shorter and cleaner.
	 * It also makes the rows look more consistent.
	 */
	private void addRow(JPanel panel, GridBagConstraints gbc, int row,
			String labelText, JTextField field)
	{
		JLabel label = new JLabel(labelText);
		label.setFont(new Font("Segoe UI", Font.BOLD, 13));
		label.setForeground(new Color(51, 65, 85));

		field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		field.setPreferredSize(new Dimension(230, 32));

		// GridBagLayout allows labels and fields to stay aligned neatly.
		gbc.gridx = 0;
		gbc.gridy = row;
		gbc.weightx = 0.2;
		// Labels are placed in column 0.
		panel.add(label, gbc);

		gbc.gridx = 1;
		gbc.gridy = row;
		gbc.weightx = 0.8;
		// Text fields are placed in column 1.
		panel.add(field, gbc);
	}

	/**
	 * Creates action buttons.
	 */
	private JPanel createActionPanel()
	{
		JPanel panel = new JPanel(new GridLayout(7, 1, 10, 10));
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(226, 232, 240)),
				BorderFactory.createEmptyBorder(15, 15, 15, 15)));

		// All buttons use the same style so the GUI looks more organized.
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
	 */
	private JButton createStyledButton(String text)
	{
		JButton button = new JButton(text);
		button.setFont(new Font("Segoe UI", Font.BOLD, 13));
		button.setPreferredSize(new Dimension(140, 38));
		button.setFocusPainted(false);
		return button;
	}

	/**
	 * Creates smart import area.
	 * Smart Fill is separate from Add Cat because the user should review the
	 * fields first.
	 * This avoids adding wrong data automatically.
	 */
	private JPanel createSmartImportPanel()
	{
		JPanel panel = new JPanel(new BorderLayout(5, 5));
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("Smart Import"));

		// Smart Fill allows users to paste raw text instead of typing
		// every field one by one.
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
	 */
	private void createOutputPanel()
	{
		// I use a JTextArea because the program may display a lot of results.
		outputArea = new JTextArea(6, 60);
		outputArea.setEditable(false);
		outputArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
		outputArea.setLineWrap(true);
		outputArea.setWrapStyleWord(true);

		JScrollPane scrollPane = new JScrollPane(outputArea);
		scrollPane.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(0, 20, 20, 20),
				BorderFactory.createTitledBorder("Output Area")));

		add(scrollPane, BorderLayout.SOUTH);
	}

	/**
	 * Adds event handlers to buttons.
	 */
	private void addEventHandlers()
	{
		// connecting buttons to what they actually do
		addButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				handleAddCat();
			}
		});

		removeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				handleRemoveCat();
			}
		});

		searchButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				handleSearchCat();
			}
		});

		showAllButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				outputArea.setText(bm.getAllCatsAsString());
			}
		});

		saveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				handleSave();
			}
		});

		loadButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				handleLoad();
			}
		});

		clearButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearFields();
				outputArea.setText("");
			}
		});

		smartFillButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				handleSmartFill();
			}
		});
	}

	/**
	 * Handles adding a cat.
	 */
	private void handleAddCat()
	{
		try
		{
			// The GUI reads text from the input fields before creating objects.
			String name = nameField.getText().trim();
			// The text dates are converted into LocalDate objects.
			LocalDate birthDate = LocalDate
					.parse(birthDateField.getText().trim());
			LocalTime dropOffTime = LocalTime
					.parse(dropOffTimeField.getText().trim());
			LocalTime pickUpTime = LocalTime
					.parse(pickUpTimeField.getText().trim());
			String ownerName = ownerField.getText().trim();
			String phone = phoneField.getText().trim();
			String careNotes = careNotesField.getText().trim();
			String medicalNotes = medicalNotesField.getText().trim();

			LocalDate startDate = LocalDate
					.parse(startDateField.getText().trim());
			LocalDate endDate = LocalDate.parse(endDateField.getText().trim());

			// Important fields should not be empty.
			if (name.isEmpty() || ownerName.isEmpty())
			{
				outputArea.setText(
						"Error: Cat name and owner name cannot be empty.");
				return;
			}

			// End date should not happen before the start date.
			if (endDate.isBefore(startDate))
			{
				outputArea.setText(
						"Error: End date cannot be before start date.");
				return;
			}

			Owner owner = new Owner(ownerName, phone);
			Cat cat;

			// Regular cats are created when there are no medical notes.
			if (medicalNotes.isEmpty())
			{

				cat = new Cat(name, birthDate, owner, careNotes, startDate,
						endDate, dropOffTime, pickUpTime);

			}
			// Cats with medical notes become SpecialCat objects.
			else
			{

				cat = new SpecialCat(name, birthDate, owner, careNotes,
						startDate, endDate, dropOffTime, pickUpTime,
						medicalNotes);
			}

			// The new object is added to the main cat list.
			bm.addCat(cat);

			outputArea.setText("Cat added successfully.\n");
			outputArea.append("Auto Category: " + cat.getCategory() + "\n\n");
			outputArea.append(bm.getAllCatsAsString());

			clearInputOnly();

		}
		// Invalid date or time input should not crash the GUI.
		catch (Exception e)
		{

			outputArea.setText("Error: Please check the date or time format.\n"
					+ "Date format: yyyy-mm-dd\n" + "Time format: HH:mm");
		}
	}

	/**
	 * Handles removing a cat.
	 */
	private void handleRemoveCat()
	{
		String name = nameField.getText().trim();

		if (name.isEmpty())
		{
			name = searchField.getText().trim();
		}

		boolean removed = bm.removeCat(name);

		if (removed)
		{
			outputArea.setText("Cat removed successfully.\n\n");
		}
		else
		{
			outputArea.setText("Cat not found.\n\n");
		}

		outputArea.append(bm.getAllCatsAsString());
	}

	/**
	 * Handles searching for a cat.
	 */
	private void handleSearchCat()
	{
		String name = searchField.getText().trim();

		if (name.isEmpty())
		{
			name = nameField.getText().trim();
		}

		// The recursive search method is used here to demonstrate LO9.
		Cat foundCat = bm.recursiveSearchByName(name);

		if (foundCat != null)
		{
			outputArea.setText(foundCat.toString() + "\nFeeding: "
					+ foundCat.getFeedMessage());
		}
		else
		{
			outputArea.setText("Cat not found.");
		}
	}

	/**
	 * Handles saving file.
	 */
	private void handleSave()
	{
		bm.saveToFile("cats.txt");
		outputArea.setText("Cat data saved to cats.txt");
	}

	/**
	 * Handles loading file.
	 */
	private void handleLoad()
	{
		outputArea.setText(bm.loadFromFile("cats.txt"));
	}

	/**
	 * Clears all fields.
	 */
	private void clearFields()
	{
		clearInputOnly();
		searchField.setText("");
	}

	/**
	 * Handles Smart Fill button.
	 */
	private void handleSmartFill()
	{

		String text = smartImportArea.getText();

		if (text.trim().isEmpty())
		{
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

		if (!birthDate.isEmpty())
		{
			birthDateField.setText(birthDate);
		}

		if (!startDate.isEmpty())
		{
			startDateField.setText(startDate);
		}

		if (!endDate.isEmpty())
		{
			endDateField.setText(endDate);
		}

		if (!dropOffTime.isEmpty())
		{
			dropOffTimeField.setText(dropOffTime);
		}

		if (!pickUpTime.isEmpty())
		{
			pickUpTimeField.setText(pickUpTime);
		}

		careNotesField.setText(careNotes);
		medicalNotesField.setText(medicalNotes);

		outputArea.setText("Smart Fill completed.\n"
				+ "Please review the fields and click Add Cat.");
	}

	/**
	 * Clears only input fields.
	 * This method resets the form so the next cat can be entered more easily.
	 */
	private void clearInputOnly()
	{
		// Reset the form to default example values for the next cat.
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

	public static void main(String[] args)
	{
		new CatBoardingGUI();
	}
}