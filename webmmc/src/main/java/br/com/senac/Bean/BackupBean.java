/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.Bean;

/**
 *
 * @author Rafael
 */
 
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author Qubit
 */
@ManagedBean(name = "backupDB")
public class BackupBean {

    ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    String basePath = ctx.getRealPath("/");
    // private String txtPath = "G:\\New Folder\\";
    private String txtPath = basePath + "BackupRestoreMysqldb//";
    private String lblMessage;

    public void backup() {
        if (txtPath.equals("")) {
            lblMessage = ("Please choose path to save!");
        } else {
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
            //File file = new File(dateFormat.format(now));
            String strFilename = dateFormat.format(now);

            long nowLong = now.getTime();
            //String strFilename;
            //strFilename = nowLong.toString();
            //strFilename = String.valueOf(nowLong);
            System.out.println(strFilename);
            String command = "C:\\Program Files\\MySQL\\mysql-5.7.9-winx64\\bin\\mysqldump --host=127.0.0.1 --user=root --port=3306 --add-drop-database -B --databases mmc -r " + "\"" + txtPath.toString() + "\\" + strFilename + ".sql\"";
            //String command = "G:/MYSQL(Installed)/Soft/bin/mysqldump -uroot -proot --add-drop-database -B test_db -r " + "\"" + txtPath.toString() + "\\" + strFilename + ".sql\"";
            System.out.println(command);
            Process p = null;
            try {
                Runtime runtime = Runtime.getRuntime();
                p = runtime.exec(command);

                int processComplete = p.waitFor();

                if (processComplete == 0) {

                    // System.out.println("<html><font color='green'>Backup created successfully!</font></html>");
                    lblMessage = "Backup created successfully to " + txtPath.toString() + "\\" + strFilename + ".sql";

                } else {
                    lblMessage = "Could not create the backup";
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}