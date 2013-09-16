/*
 * EntityEditorFrame.java
 *
 * Created on August 5, 2001, 1:45 PM
 */

package dynetica.gui.entities;

/**
 *
 * @author  Lingchong You
 * @version 0.01
 */

import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class EntityEditorFrame extends javax.swing.JFrame {
    List entityNames = new ArrayList();

    /** Creates new form EntityEditorFrame */
    public EntityEditorFrame() {
        initComponents();
        // editorPane.addMouseListener(new PopupListener());
        // pack ();
    }

    public void addEditor(String entityName, JPanel editor) {
        if (entityNames.contains(entityName)) {
            editorPane.setSelectedComponent(editorPane
                    .getComponentAt(entityNames.indexOf(entityName)));
        }

        else {
            editorPane.add(entityName, editor);
            editorPane.setSelectedComponent(editor);
            entityNames.add(entityName);
            // editor.addMouseListener(new PopupListener());
        }
    }

    public void removeEditor(String entityName) {
        if (entityNames.contains(entityName)) {
            int index = entityNames.indexOf(entityName);
            entityNames.remove(index);
            editorPane.remove(index);
        }
    }

    public int getComponentCount() {
        return editorPane.getComponentCount();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed"
    // desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        editorPaneMenu = new javax.swing.JPopupMenu();
        closeItem = new javax.swing.JMenuItem();
        newViewItem = new javax.swing.JMenuItem();
        editorPane = new javax.swing.JTabbedPane();

        editorPaneMenu.setInvoker(this);

        closeItem.setText("Close");
        closeItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeItemActionPerformed(evt);
            }
        });
        editorPaneMenu.add(closeItem);

        newViewItem.setText("Open in a new window");
        editorPaneMenu.add(newViewItem);

        setTitle("Entity Property Editor");
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        editorPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1,
                1, 1));
        editorPane.setTabPlacement(3);
        getContentPane().add(editorPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void closeItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_closeItemActionPerformed
        int editorIndex = editorPane.getSelectedIndex();
        entityNames.remove(editorIndex);
        editorPane.remove(editorIndex);
        if (editorPane.getComponentCount() == 0)
            this.setVisible(false);
        else
            editorPane.setSelectedIndex(Math.min(0, editorIndex - 1));
    }// GEN-LAST:event_closeItemActionPerformed

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_exitForm
        editorPane.removeAll();
        entityNames.removeAll(entityNames);
        dispose();
    }// GEN-LAST:event_exitForm

    class PopupListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            showPopup(e);
        }

        public void mouseReleased(MouseEvent e) {
            showPopup(e);
        }

        private void showPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                // editorPaneMenu.setLocation( e.getX(), e.getY());
                //
                // LY: why doesn't the following code work???
                //
                editorPaneMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem closeItem;
    private javax.swing.JTabbedPane editorPane;
    private javax.swing.JPopupMenu editorPaneMenu;
    private javax.swing.JMenuItem newViewItem;
    // End of variables declaration//GEN-END:variables

}