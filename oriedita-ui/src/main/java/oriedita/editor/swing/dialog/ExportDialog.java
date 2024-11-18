package oriedita.editor.swing.dialog;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import oriedita.editor.export.api.FileExporter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ExportDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList<ExportOption> list1;
    private JPanel optionsCard;
    private JTextField textField1;
    private JTextField textField2;
    private JCheckBox foldedFiguresCheckBox;
    private JCheckBox creasePatternCheckBox;
    private JSlider slider1;
    private JTextField textField3;
    private JTextField textField4;
    private JLabel qualityLabel;
    private JComboBox<String> comboBox1;
    private JSlider slider2;
    private JSlider slider3;
    private JTextField textField5;
    private JTextField textField6;
    private JPanel orhPanel;
    private JPanel jpgPanel;
    private JPanel svgPanel;
    private JPanel pngPanel;
    private JPanel cpPanel;
    private boolean cancelled = true;

    private static class ExportOption {
        private final String key;
        private final String value;

        public String getKey() {
            return key;
        }

        // public String getValue() {
        //     return value;
        // }

        public ExportOption(String key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public ExportDialog(Frame owner, Iterable<FileExporter> exporters) {
        super(owner, "Export options", ModalityType.APPLICATION_MODAL);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        CardLayout cardLayout = (CardLayout) optionsCard.getLayout();

        DefaultListModel<ExportOption> listModel = new DefaultListModel<>();
        for (FileExporter exporter : StreamSupport.stream(exporters.spliterator(), false).sorted().collect(Collectors.toList())) {
            listModel.addElement(new ExportOption(exporter.getExtension(), exporter.getName() + " (" + exporter.getExtension() + ")"));
        }

        list1.setModel(listModel);

        list1.addListSelectionListener(e -> {
            ExportOption value = list1.getModel().getElementAt(list1.getSelectedIndex());

            cardLayout.show(optionsCard, value.getKey());
        });
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 1) {
                    onOk();
                }
            }
        });
        list1.setSelectedIndex(0);

        slider1.addChangeListener(e -> qualityLabel.setText(String.valueOf(slider1.getValue())));

        buttonCancel.addActionListener(e -> onClose());
        buttonOK.addActionListener(e -> onOk());

        setModal(true);

        pack();

        setLocationRelativeTo(owner);
    }

    public static String showExportDialog(Frame owner, Iterable<FileExporter> exporters) {
        ExportDialog exportDialog = new ExportDialog(owner, exporters);

        exportDialog.setVisible(true);

        String exportType = exportDialog.getExportType();

        if (exportDialog.cancelled) {
            exportType = null;
        }

        exportDialog.dispose();

        return exportType;
    }

    private void onOk() {
        cancelled = false;

        setVisible(false);
    }

    public String getExportType() {
        return list1.getSelectedValue().getKey();
    }

    private void onClose() {
        setVisible(false);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
        panel1.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonOK = new JButton();
        buttonOK.setText("OK");
        panel2.add(buttonOK, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCancel = new JButton();
        buttonCancel.setText("Cancel");
        panel2.add(buttonCancel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        list1 = new JList<>();
        panel3.add(list1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        optionsCard = new JPanel();
        optionsCard.setLayout(new CardLayout(0, 0));
        optionsCard.setVisible(false);
        panel3.add(optionsCard, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        pngPanel = new JPanel();
        pngPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        optionsCard.add(pngPanel, ".png");
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        pngPanel.add(panel4);
        textField3 = new JTextField();
        textField3.setColumns(3);
        panel4.add(textField3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(40, -1), null, 0, false));
        textField4 = new JTextField();
        textField4.setColumns(3);
        textField4.setText("");
        panel4.add(textField4, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(40, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("x");
        panel4.add(label1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Dimensions");
        panel4.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        svgPanel = new JPanel();
        svgPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        optionsCard.add(svgPanel, ".svg");
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(6, 3, new Insets(0, 0, 0, 0), -1, -1));
        svgPanel.add(panel5);
        foldedFiguresCheckBox = new JCheckBox();
        foldedFiguresCheckBox.setText("Folded Figures");
        panel5.add(foldedFiguresCheckBox, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        creasePatternCheckBox = new JCheckBox();
        creasePatternCheckBox.setText("Crease Pattern");
        panel5.add(creasePatternCheckBox, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$(null, Font.BOLD, -1, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setText("Export");
        panel5.add(label3, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBox1 = new JComboBox<>();
        final DefaultComboBoxModel<String> defaultComboBoxModel1 = new DefaultComboBoxModel<>();
        defaultComboBoxModel1.addElement("COLOR");
        defaultComboBoxModel1.addElement("COLOR_AND_SHAPE");
        defaultComboBoxModel1.addElement("BLACK_ONE_DOT");
        defaultComboBoxModel1.addElement("BLACK_TWO_DOT");
        comboBox1.setModel(defaultComboBoxModel1);
        panel5.add(comboBox1, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Line type");
        panel5.add(label4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Line width");
        panel5.add(label5, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Point size");
        panel5.add(label6, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        slider2 = new JSlider();
        panel5.add(slider2, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        slider3 = new JSlider();
        panel5.add(slider3, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField5 = new JTextField();
        textField5.setColumns(2);
        panel5.add(textField5, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(40, -1), null, 0, false));
        textField6 = new JTextField();
        textField6.setColumns(2);
        panel5.add(textField6, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(40, -1), null, 0, false));
        jpgPanel = new JPanel();
        jpgPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        optionsCard.add(jpgPanel, ".jpg");
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        jpgPanel.add(panel6);
        textField1 = new JTextField();
        textField1.setColumns(3);
        panel6.add(textField1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Dimensions");
        panel6.add(label7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField2 = new JTextField();
        textField2.setColumns(3);
        panel6.add(textField2, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("x");
        panel6.add(label8, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        slider1 = new JSlider();
        slider1.setPaintLabels(true);
        slider1.setValue(75);
        panel6.add(slider1, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("Quality");
        panel6.add(label9, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        qualityLabel = new JLabel();
        qualityLabel.setText("75");
        panel6.add(qualityLabel, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        orhPanel = new JPanel();
        orhPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        optionsCard.add(orhPanel, ".orh");
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        orhPanel.add(panel7);
        final JLabel label10 = new JLabel();
        Font label10Font = this.$$$getFont$$$(null, Font.ITALIC, -1, label10.getFont());
        if (label10Font != null) label10.setFont(label10Font);
        label10.setText("No export options");
        panel7.add(label10, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cpPanel = new JPanel();
        cpPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        optionsCard.add(cpPanel, ".cp");
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        cpPanel.add(panel8);
        final JLabel label11 = new JLabel();
        Font label11Font = this.$$$getFont$$$(null, Font.ITALIC, -1, label11.getFont());
        if (label11Font != null) label11.setFont(label11Font);
        label11.setText("No export options");
        panel8.add(label11, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}
