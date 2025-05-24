import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter extends JFrame {
    private static final Map<String, Double> EXCHANGE_RATES = new HashMap<>();
    
    static {
        EXCHANGE_RATES.put("USD", 0.012);    
        EXCHANGE_RATES.put("EUR", 0.011);    
        EXCHANGE_RATES.put("GBP", 0.0095);   
        EXCHANGE_RATES.put("JPY", 1.79);     
        EXCHANGE_RATES.put("AUD", 0.018);    
        EXCHANGE_RATES.put("CAD", 0.017);    
        EXCHANGE_RATES.put("CHF", 0.011);    
        EXCHANGE_RATES.put("CNY", 0.087);    
        EXCHANGE_RATES.put("INR", 1.0);      
    }
    
    private JTextField amountField;
    private JComboBox<String> fromCurrency;
    private JComboBox<String> toCurrency;
    private JLabel resultLabel;
    private JButton convertButton;
    private JButton swapButton;
    private JButton clearButton;
    
    public CurrencyConverter() {
        setTitle("Currency Converter - INR to Foreign Exchange");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        
        
        initializeComponents();
        setupLayout();
        addEventListeners();
        
        getContentPane().setBackground(new Color(240, 240, 240));
    }
    
    private void initializeComponents() {
        amountField = new JTextField("1.00", 15);
        amountField.setFont(new Font("Arial", Font.PLAIN, 14));
        amountField.setHorizontalAlignment(JTextField.RIGHT);
        
        String[] currencies = {"INR", "USD", "EUR", "GBP", "JPY", "AUD", "CAD", "CHF", "CNY"};
        fromCurrency = new JComboBox<>(currencies);
        toCurrency = new JComboBox<>(currencies);
        
        fromCurrency.setSelectedItem("INR");
        toCurrency.setSelectedItem("USD");
        
        fromCurrency.setFont(new Font("Arial", Font.PLAIN, 12));
        toCurrency.setFont(new Font("Arial", Font.PLAIN, 12));
        
        resultLabel = new JLabel("0.00 USD", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));
        resultLabel.setForeground(new Color(0, 100, 0));
        resultLabel.setBorder(BorderFactory.createLoweredBevelBorder());
        resultLabel.setPreferredSize(new Dimension(300, 40));
        resultLabel.setOpaque(true);
        resultLabel.setBackground(Color.WHITE);
        
        convertButton = new JButton("Convert");
        swapButton = new JButton("⇄ Swap");
        clearButton = new JButton("Clear");
        
        Font buttonFont = new Font("Arial", Font.BOLD, 12);
        convertButton.setFont(buttonFont);
        swapButton.setFont(buttonFont);
        clearButton.setFont(buttonFont);
        
        convertButton.setBackground(new Color(70, 130, 180));
        convertButton.setForeground(Color.WHITE);
        convertButton.setFocusPainted(false);
        
        swapButton.setBackground(new Color(255, 165, 0));
        swapButton.setForeground(Color.WHITE);
        swapButton.setFocusPainted(false);
        
        clearButton.setBackground(new Color(220, 20, 60));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(25, 25, 112));
        headerPanel.setPreferredSize(new Dimension(0, 60));
        JLabel titleLabel = new JLabel("Currency Converter", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(245, 245, 240));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JPanel amountPanel = new JPanel(new BorderLayout());
        amountPanel.setBorder(new TitledBorder("Enter Amount"));
        amountPanel.setBackground(new Color(240, 240, 240));
        amountPanel.add(amountField, BorderLayout.CENTER);
        
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(amountPanel, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(new JLabel("From:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(fromCurrency, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(new JLabel("To:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(toCurrency, gbc);
        
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBorder(new TitledBorder("Converted Amount"));
        resultPanel.setBackground(new Color(240, 240, 240));
        resultPanel.add(resultLabel, BorderLayout.CENTER);
        
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(resultPanel, gbc);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.add(convertButton);
        buttonPanel.add(swapButton);
        buttonPanel.add(clearButton);
        
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);
        
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(169, 169, 169));
        footerPanel.setPreferredSize(new Dimension(0, 30));
        JLabel footerLabel = new JLabel("Project By Tech Challangers.", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 10));
        footerLabel.setForeground(Color.BLACK);
        footerPanel.add(footerLabel);
        
        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
    }
    
    private void addEventListeners() {
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performConversion();
            }
        });
        
        swapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapCurrencies();
            }
        });
        
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        amountField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performConversion();
            }
        });
        
        fromCurrency.addActionListener(e -> performConversion());
        toCurrency.addActionListener(e -> performConversion());
    }
    
    private void performConversion() {
        try {
            String amountText = amountField.getText().trim();
            if (amountText.isEmpty()) {
                amountText = "0";
            }
            
            double amount = Double.parseDouble(amountText);
            String from = (String) fromCurrency.getSelectedItem();
            String to = (String) toCurrency.getSelectedItem();
            
            double result = convertCurrency(amount, from, to);
            
            DecimalFormat df = new DecimalFormat("#,##0.00");
            resultLabel.setText(df.format(result) + " " + to);
            
            if (result > 0) {
                resultLabel.setForeground(new Color(0, 100, 0));
            } else {
                resultLabel.setForeground(Color.BLACK);
            }
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a valid number", 
                "Invalid Input", 
                JOptionPane.ERROR_MESSAGE);
            amountField.requestFocus();
        }
    }
    
    private double convertCurrency(double amount, String from, String to) {
        if (from.equals(to)) {
            return amount;
        }
        
        double inrAmount;
        if (from.equals("INR")) {
            inrAmount = amount;
        } else {
            inrAmount = amount / EXCHANGE_RATES.get(from);
        }
        
        if (to.equals("INR")) {
            return inrAmount;
        } else {
            return inrAmount * EXCHANGE_RATES.get(to);
        }
    }
    
    private void swapCurrencies() {
        String temp = (String) fromCurrency.getSelectedItem();
        fromCurrency.setSelectedItem(toCurrency.getSelectedItem());
        toCurrency.setSelectedItem(temp);
        performConversion();
    }
    
    private void clearFields() {
        amountField.setText("1.00");
        fromCurrency.setSelectedItem("INR");
        toCurrency.setSelectedItem("USD");
        resultLabel.setText("0.00 USD");
        resultLabel.setForeground(new Color(0, 100, 0));
        amountField.requestFocus();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                new CurrencyConverter().setVisible(true);
            }
        });
    }
}
