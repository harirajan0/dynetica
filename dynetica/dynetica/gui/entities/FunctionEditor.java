/*
 * FunctionEditor.java
 *
 * Created on September 14, 2001, 10:57 PM
 */

package dynetica.gui.entities;

/**
 *
 * @author  Lingchong You
 * @version 0.1
 */
import java.util.*;
import javax.swing.*;
import dynetica.util.SystemProperties;
import java.io.*;

public class FunctionEditor extends JPanel {
    static String root = dynetica.util.DyneticaProperties.HOME + "/Dynetica";
    static java.io.File funNameFile = new java.io.File(
            dynetica.util.DyneticaProperties.HOME + "/Dynetica/function.d");
    static BufferedReader reader; // = new BufferedReader( new
                                  // FileReader(funNameFile));
    static PrintWriter writer; // = new PrintWriter(new
                               // FileWriter(funNameFile));
    static DefaultListModel funListModel = new DefaultListModel();

    /** Creates new form FunctionEditor */
    public FunctionEditor() {
        // super("Define new function");
        initComponents();
        buildListModel();
        if (funListModel.getSize() > 0)
            funList.setSelectedIndex(0);
        System.out.println(root);
    }

    private void buildListModel() {
        if (reader == null) {
            try {
                reader = new BufferedReader(new FileReader(funNameFile));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (reader != null) {
            while (true) {
                try {
                    String line = reader.readLine();
                    if (line == null)
                        break;
                    line = line.trim();
                    String funName;
                    if (line.length() > 0) {
                        funName = line.substring(0, line.lastIndexOf(".d"))
                                .trim();
                        // System.out.println(funName);
                        funListModel.addElement(funName);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setFunction(File in) {
        try {
            BufferedReader funReader = new BufferedReader(new FileReader(in));
            StringBuffer str = new StringBuffer(funReader.readLine());
            while (true) {
                String line = funReader.readLine();
                if (line == null)
                    break;
                str.append(line);
            }
            String funString = str.toString();
            StringTokenizer tokenizer = new StringTokenizer(funString);
            String funName = tokenizer.nextToken("(").trim();
            String arguments = tokenizer.nextToken("()").trim();
            String expr = funString.substring(funString.indexOf('{') + 1,
                    funString.indexOf("}//end")).trim();

            nameField.setText(funName);
            argumentsArea.setText(arguments);
            exprArea.setText(expr);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void saveFunction() {
        String name = nameField.getText().trim();
        if (!funListModel.contains(name))
            funListModel.addElement(name);

        StringBuffer function = new StringBuffer(name);
        function.append('(');
        java.util.StringTokenizer argumentsTokenizer = new java.util.StringTokenizer(
                argumentsArea.getText(), " ,\t\n");
        for (;;) {
            function.append(argumentsTokenizer.nextToken());
            if (argumentsTokenizer.hasMoreTokens())
                function.append(", ");
            else
                break;
        }

        function.append(')');
        function.append(" {" + SystemProperties.NEWLINE);
        function.append(exprArea.getText() + SystemProperties.NEWLINE);
        function.append("}//end");
        try {
            java.io.File file = new java.io.File(root + name + ".d");
            java.io.PrintWriter out = new java.io.PrintWriter(
                    new java.io.FileOutputStream(file));
            out.println(function);
            out.close();

            java.io.PrintWriter pw = new java.io.PrintWriter(
                    new java.io.FileWriter(new java.io.File(root
                            + "/function.d")));

            for (int i = 0; i < funListModel.getSize(); i++) {
                pw.println("\t" + funListModel.get(i) + ".d");
            }

            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed"
    // desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        saveButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jSplitPane2 = new javax.swing.JSplitPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        funList = new javax.swing.JList();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Expression = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        nameField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        argumentsArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        exprArea = new javax.swing.JTextArea();

        setLayout(new java.awt.BorderLayout());

        jToolBar1.setFloatable(false);

        saveButton.setToolTipText("Save the customized function.");
        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(saveButton);

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(deleteButton);

        add(jToolBar1, java.awt.BorderLayout.SOUTH);

        jSplitPane2.setDividerSize(6);

        funList.setModel(funListModel);
        funList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                funListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(funList);

        jSplitPane2.setLeftComponent(jScrollPane3);

        jSplitPane1.setDividerLocation(100);
        jSplitPane1.setDividerSize(1);

        jPanel1.setPreferredSize(new java.awt.Dimension(130, 100));
        jPanel1.setLayout(new java.awt.GridLayout(3, 1, 2, 2));

        jLabel3.setText("Function Name");
        jLabel3.setToolTipText("The name of the function to be defined.");
        jPanel1.add(jLabel3);

        jLabel1.setPreferredSize(new java.awt.Dimension(130, 25));
        jLabel1.setMinimumSize(new java.awt.Dimension(100, 25));
        jLabel1.setText("Arguments");
        jLabel1.setToolTipText("Symbols (separated by ',') representing the arguments of this function.");
        jLabel1.setMaximumSize(new java.awt.Dimension(400, 25));
        jPanel1.add(jLabel1);

        Expression.setPreferredSize(new java.awt.Dimension(100, 25));
        Expression.setMinimumSize(new java.awt.Dimension(100, 25));
        Expression.setText("Expression");
        Expression
                .setToolTipText("A mathematical expression using the listed symbols");
        Expression.setMaximumSize(new java.awt.Dimension(100, 25));
        jPanel1.add(Expression);

        jSplitPane1.setLeftComponent(jPanel1);

        jPanel2.setLayout(new java.awt.GridLayout(3, 1, 2, 2));
        jPanel2.add(nameField);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(200, 40));

        argumentsArea.setWrapStyleWord(true);
        argumentsArea.setLineWrap(true);
        jScrollPane1.setViewportView(argumentsArea);

        jPanel2.add(jScrollPane1);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(200, 40));

        exprArea.setWrapStyleWord(true);
        exprArea.setLineWrap(true);
        jScrollPane2.setViewportView(exprArea);

        jPanel2.add(jScrollPane2);

        jSplitPane1.setRightComponent(jPanel2);

        jSplitPane2.setRightComponent(jSplitPane1);

        add(jSplitPane2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_deleteButtonActionPerformed
        int index = funList.getSelectedIndex();
        String funName = (String) (funListModel.get(index));
        funListModel.remove(index);
        if (funListModel.getSize() > 0)
            index = Math.min(index, funListModel.getSize() - 1);
        funList.setSelectedIndex(index);
        try {
            System.out.println(root + funName + ".d");
            java.io.File file = new java.io.File(root + funName + ".d");
            file.delete();
            java.io.PrintWriter pw = new java.io.PrintWriter(
                    new java.io.FileWriter(new java.io.File(root
                            + "/function.d")));
            for (int i = 0; i < funListModel.getSize(); i++) {
                pw.println("\t" + funListModel.get(i) + ".d");
            }
            pw.close();
        } catch (Exception e) {
        }

    }// GEN-LAST:event_deleteButtonActionPerformed

    private void funListValueChanged(javax.swing.event.ListSelectionEvent evt) {// GEN-FIRST:event_funListValueChanged
        int index = funList.getSelectedIndex();
        String fileName = root + (funListModel.get(index)) + ".d";
        try {
            File in = new File(fileName);
            setFunction(in);
        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }// GEN-LAST:event_funListValueChanged

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_saveButtonActionPerformed
        saveFunction();
    }// GEN-LAST:event_saveButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Expression;
    private javax.swing.JTextArea argumentsArea;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextArea exprArea;
    private javax.swing.JList funList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField nameField;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables

}
