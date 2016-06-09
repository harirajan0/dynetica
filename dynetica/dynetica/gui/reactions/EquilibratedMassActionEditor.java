/*
 * EMAEditor.java
 *
 * Created on August 24, 2001, 11:54 AM
 */

package dynetica.gui.reactions;

/**
 * 
 * @author You
 * @version
 */
public class EquilibratedMassActionEditor extends javax.swing.JPanel {

    dynetica.reaction.EquilibratedMassAction reaction;

    /** Creates new form EMAEditor */
    public EquilibratedMassActionEditor(
            dynetica.reaction.EquilibratedMassAction reaction) {
        this.reaction = reaction;
        initComponents();
         //6/2016. Added by LY to enable copy/cut/paste in Dynetica.
        dynetica.gui.DyneticaGUITools.installContextMenu(this);       
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed"
    // desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        stoichiometryText = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        kText = new javax.swing.JTextArea();

        setLayout(new java.awt.BorderLayout());

        jLabel3.setText("<html> <i> Equilibrated Reaction </i> "
                + getReactionName());
        add(jLabel3, java.awt.BorderLayout.NORTH);

        jSplitPane1.setDividerSize(1);
        jSplitPane1.setPreferredSize(new java.awt.Dimension(400, 72));

        jPanel1.setPreferredSize(new java.awt.Dimension(150, 70));
        jPanel1.setLayout(new java.awt.GridLayout(1, 1));

        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setText("Stoichiometry");
        jPanel1.add(jLabel1);

        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel2.setText("Equilibrium Constant");
        jPanel1.add(jLabel2);

        jSplitPane1.setLeftComponent(jPanel1);

        jPanel2.setPreferredSize(new java.awt.Dimension(200, 70));
        jPanel2.setLayout(new java.awt.GridLayout(1, 1));

        jScrollPane4.setMaximumSize(new java.awt.Dimension(200, 40));

        stoichiometryText.setWrapStyleWord(true);
        stoichiometryText.setLineWrap(true);
        stoichiometryText.setText(getStoichiometry());
        stoichiometryText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                stoichiometryTextFocusLost(evt);
            }
        });
        jScrollPane4.setViewportView(stoichiometryText);

        jPanel2.add(jScrollPane4);

        jScrollPane3.setMaximumSize(new java.awt.Dimension(200, 40));

        kText.setWrapStyleWord(true);
        kText.setLineWrap(true);
        kText.setText(getK());
        kText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                kTextFocusLost(evt);
            }
        });
        jScrollPane3.setViewportView(kText);

        jPanel2.add(jScrollPane3);

        jSplitPane1.setRightComponent(jPanel2);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void kTextFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_kTextFocusLost
        setK();
    }// GEN-LAST:event_kTextFocusLost

    private void stoichiometryTextFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_stoichiometryTextFocusLost
        setStoichiometry();
    }// GEN-LAST:event_stoichiometryTextFocusLost

    private String getK() {
        if (reaction.getK() != null)
            return reaction.getK().toString();
        else
            return "";
    }

    private String getReactionName() {
        return reaction.getName();
    }

    private String getStoichiometry() {
        if (reaction.getStoichiometry() != null)
            return reaction.getStoichiometry();
        else
            return null;
    }

    private void setK() {
        String kString = kText.getText().trim();
        String oldKString = getK();
        if (kString.length() > 0 && kString.compareTo(oldKString) != 0) {
            try {
                reaction.setK(kString);
                firePropertyChange("EntityName", oldKString, kString);
            } catch (dynetica.expression.IllegalExpressionException iee) {
                System.out.println(iee);
            }
        }
    }

    private void setStoichiometry() {
        String stoichiometry = stoichiometryText.getText().trim();
        String oldStoichiometry = getStoichiometry();

        if (stoichiometry.length() > 0
                && stoichiometry.compareTo(oldStoichiometry) != 0) {
            reaction.setStoichiometry(stoichiometry);
            firePropertyChange("EntityName", oldStoichiometry, stoichiometry);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTextArea kText;
    private javax.swing.JTextArea stoichiometryText;

    // End of variables declaration//GEN-END:variables

    public static class Tester {
        public static void main(String[] args) {
            dynetica.reaction.EquilibratedMassAction reaction = new dynetica.reaction.EquilibratedMassAction(
                    "TestReaction", new dynetica.system.ReactiveSystem("Test"));
            try {
                reaction.setProperty("stoichiometry",
                        "A + B + C + D -> E + F + K + L");
                reaction.setProperty("K",
                        "k1 * [A] * sin([B]) * log([C]) / ([E]^[F] * [F]^100 * [K] * [L])");
                javax.swing.JFrame frame = new javax.swing.JFrame();

                frame.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                frame.getContentPane().add(reaction.editor());
                frame.pack();
                frame.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
