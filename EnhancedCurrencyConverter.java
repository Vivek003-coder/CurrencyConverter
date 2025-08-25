import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class EnhancedCurrencyConverter extends JFrame implements ActionListener {

    private JComboBox<String> fromCurrency;
    private JComboBox<String> toCurrency;
    private JTextField amountField;
    private JButton convertButton;
    private JLabel resultLabel;

    private HashMap<String, Double> rates;

    public EnhancedCurrencyConverter() {
        setTitle("Currency Converter");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize currency rates relative to USD
        rates = new HashMap<>();
        rates.put("USD ($)", 1.0);
        rates.put("EUR (€)", 0.93);
        rates.put("GBP (£)", 0.81);
        rates.put("INR (₹)", 83.0);
        rates.put("JPY (¥)", 144.0);

        // Main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Amount Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Amount:"), gbc);

        // Amount TextField
        gbc.gridx = 1;
        amountField = new JTextField();
        amountField.setFont(new Font("Arial", Font.PLAIN, 16));
        mainPanel.add(amountField, gbc);

        // From Currency Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("From Currency:"), gbc);

        // From Currency ComboBox
        gbc.gridx = 1;
        fromCurrency = new JComboBox<>(rates.keySet().toArray(new String[0]));
        fromCurrency.setFont(new Font("Arial", Font.PLAIN, 16));
        mainPanel.add(fromCurrency, gbc);

        // To Currency Label
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("To Currency:"), gbc);

        // To Currency ComboBox
        gbc.gridx = 1;
        toCurrency = new JComboBox<>(rates.keySet().toArray(new String[0]));
        toCurrency.setFont(new Font("Arial", Font.PLAIN, 16));
        mainPanel.add(toCurrency, gbc);

        // Convert Button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        convertButton = new JButton("Convert");
        convertButton.setFont(new Font("Arial", Font.BOLD, 16));
        convertButton.setBackground(new Color(52, 152, 219));
        convertButton.setForeground(Color.WHITE);
        convertButton.addActionListener(this);
        mainPanel.add(convertButton, gbc);

        // Result Label
        gbc.gridy = 4;
        resultLabel = new JLabel("Converted Amount: ");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultLabel.setForeground(new Color(34, 49, 63));
        mainPanel.add(resultLabel, gbc);

        add(mainPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String from = (String) fromCurrency.getSelectedItem();
            String to = (String) toCurrency.getSelectedItem();

            double amountInUSD = amount / rates.get(from);
            double convertedAmount = amountInUSD * rates.get(to);

            resultLabel.setText(String.format("Converted Amount: %.2f %s", convertedAmount, to));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EnhancedCurrencyConverter());
    }
}
