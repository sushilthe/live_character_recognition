
import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sushil
 */
public class charRecog extends javax.swing.JFrame {

    String imagedir = "";
    int filelength = 0;
    String filename = "";
    int Length  = 0;
    String[] imageFileNames = new String[999];
    ImagePlus I2;
    int i =0;
    /**
     * Creates new form charRecog
     */
    public charRecog() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        openimageButton = new javax.swing.JButton();
        nextimageButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("charRecog");

        openimageButton.setText("Open Image Folder");
        openimageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openimageButtonActionPerformed(evt);
            }
        });

        nextimageButton.setText("Next Image");
        nextimageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextimageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(openimageButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                    .addComponent(nextimageButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(openimageButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(nextimageButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openimageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openimageButtonActionPerformed
        // TODO add your handling code here:
        i=0;
        javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
            fc.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
            int returnVal = fc.showOpenDialog(null);

            if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
               // setpanel();
               // buttonBar.removeAll();
               // buttonBar.repaint();

                java.io.File file = fc.getSelectedFile();
                imagedir = file.getPath().replace('\\', '/') + '/';
                Length = 0;
            java.io.File[] files = new java.io.File(imagedir).listFiles();
            filelength = files.length;
            int j = 0;
            for (int k = 0; k < filelength; k++) {
                if (files[k].isFile()) {
                    filename = files[k].getName();
                    if (filename.toLowerCase().endsWith(".jpg")) {
                        imageFileNames[Length] = filename;
                        Length++;
                    }
                }
            }
        }
    }//GEN-LAST:event_openimageButtonActionPerformed

    private void nextimageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextimageButtonActionPerformed
        // TODO add your handling code here:
        if(i<Length){
            if(i>0)
            I2.close();
            I2 = IJ.openImage(imagedir + imageFileNames[i]);
             I2.show();
             IJ.run(I2,"Set... ", "zoom=3200 x=2 y=3");
             String path = "";
             int s[] = new int[35];
             String name = javax.swing.JOptionPane.showInputDialog(null, "Which character is this ? ", null, 1);
             if((name != null) && (name.length() > 0)){
                path = imagedir+imageFileNames[i];
                s = extractBytes(path);
                try (java.io.BufferedWriter out = new java.io.BufferedWriter(new java.io.FileWriter("sample", true))) {
                    out.write(name+"");
                    for(int k=0;k<35;k++){ out.write(s[k]+"");}
                    out.newLine();
                    out.close();
                } catch (java.io.IOException ert) {
                    javax.swing.JOptionPane.showMessageDialog(null, ert, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
             }else{}
            name = "";
            i++;
        }
    }//GEN-LAST:event_nextimageButtonActionPerformed

    public int[] extractBytes (String ImageName){
     // open image
        int n=0;
        int chardata[] = new int[35];
        ImagePlus I3 = IJ.openImage(ImageName);
        ImageProcessor ip = I3.getProcessor();
        for(int j=0;j<7;j++){
            for(int k=0;k<5;k++){
                int temp = ip.getPixel(k, j);
               if(temp==255){
                    chardata[n]=1;
                }
                else{
                    chardata[n]=0;
                }
                //System.out.println("getpixel  = "+temp);
                n++;
            }
        }
        return chardata;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(charRecog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(charRecog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(charRecog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(charRecog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new charRecog().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton nextimageButton;
    private javax.swing.JButton openimageButton;
    // End of variables declaration//GEN-END:variables
}
