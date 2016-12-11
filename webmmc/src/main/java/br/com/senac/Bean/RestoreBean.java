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
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;

@ManagedBean(name = "restoeDB")
@SessionScoped
public class RestoreBean implements Serializable {

    private String lblMessage;
    ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    String basePath = ctx.getRealPath("/");
    // private String txtPath = "G:\\New Folder\\";
    private String txtPath = basePath + "BackupRestoreMysqldb//";

    public void upload(FileUploadEvent event) {




        if (event.getFile().getFileName().equals("")) {
            lblMessage = "Please Select file to restore!";
            System.out.println(lblMessage);
        } else {
            //restoreDB("root", "root", "G:\\New Folder\\" + event.getFile().getFileName());
            restoreDB("root", "root", txtPath + event.getFile().getFileName());
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAA" + event.getFile().getFileName());
        }
    }

    public boolean restoreDB(String dbUserName, String dbPassword, String source) {

        //  String[] executeCmd = new String[]{"C:\\Program Files\\MySQL\\MySQL Server 5.0\\bin\\mysql ", "--user=" + dbUserName, "--password=" + dbPassword, "-e", "source " + source};
        String[] executeCmd = new String[]{"G:/MYSQL(Installed)/Soft/bin/mysql ", "--user=" + dbUserName, "--password=" + dbPassword, "-e", "source " + source};

        Process runtimeProcess;
        try {

            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                lblMessage = "Backup restored successfully!";
                System.out.println(lblMessage);
                return true;
            } else {
                lblMessage = "Could not restore the backup!";
                System.out.println(lblMessage);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }
}
