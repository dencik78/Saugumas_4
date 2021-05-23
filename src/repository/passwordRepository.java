package repository;

import cryptRepository.DesCrypt;
import model.PasswordList;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class passwordRepository {

//    private String urlFile;
    private List<PasswordList> pass = new ArrayList<>();
    private DesCrypt crypt = new DesCrypt();

    public void addPassword(PasswordList pass1) throws Exception{
        DesCrypt crypt = new DesCrypt();

        String cryptPassword = crypt.encrypt(pass1.getPassword());
        pass1.setPassword(cryptPassword);
        pass.add(pass1);
    }

    public void inPassList(List<PasswordList> pass){
        this.pass = pass;
    }

    public void listString(File file) throws FileNotFoundException {
        List<String> string = new ArrayList<>();
        List<PasswordList> passwordList = new ArrayList<>();

        Scanner scr = new Scanner(file);
        while (scr.hasNextLine()) {
            string.add(scr.nextLine());
        }
        scr.close();
        String[] strg = string.toArray(new String[0]);
        String[] result = null;
        for (int i = 0; i < strg.length; i++) {
                result = strg[i].split(",");
                passwordList.add(new PasswordList(result[0],result[1],result[2],result[3]));
                result = null;

        }
        file.delete();
        this.inPassList(passwordList);
    }

    public List<PasswordList> getPasswordList(){
        return pass;
    }

    public void nullPasswordList(){
        this.pass = null;
    }

    public void updatePassword(PasswordList pas) throws Exception {
        int count = 0;

        for(PasswordList pass : pass) {
            if (pass.getLogin().equals(pas.getLogin())) {
                count++;
                pass.setPassword(pas.getPassword());
                pass.setMore_Information(pas.getMore_Information());
                pass.setUrl_Site(pas.getUrl_Site());
                JOptionPane.showMessageDialog(null, "Information Update");
                break;
            }
        }
            if(count != 0){
               JOptionPane.showMessageDialog(null,"Not have this login");
            }
        }

        public List<PasswordList> getLoginPasswordList(String login) throws Exception {
        List<PasswordList> pasList = new ArrayList<>();

        for(PasswordList pas: pass){
            if(pas.getLogin().equals(login)){
                pasList.add(pas);
            }
        }

//        for(PasswordList pass : pasList){
//            pass.setPassword(crypt.decrypt(pass.getPassword()));
//        }
        return pasList;
        }

        public void deletePassword(PasswordList passL){
        List<PasswordList> newPassList = pass;
            for(PasswordList pas : pass){
                if(passL == pas){
                    newPassList.remove(pas);
                }
            }
            this.pass = newPassList;
        }
    }

