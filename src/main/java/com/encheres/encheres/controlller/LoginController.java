package com.encheres.encheres.controlller;

import java.sql.Connection;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encheres.encheres.classes.Admin;
import com.encheres.encheres.classes.ObjetBDD;
import com.encheres.encheres.classes.Utilisateur;

@CrossOrigin
@RestController
@RequestMapping("Login/")
public class LoginController {
   
    @PostMapping("checkUser")
    public int checkUser(@RequestBody Utilisateur user){
        Connection c=null;
        try {
            c=ObjetBDD.connect();
            Utilisateur u=new Utilisateur();
            ArrayList<Object> list=u.find(c);
		    for (int i = 0; i < list.size(); i++) {
                Utilisateur current=(Utilisateur)list.get(i);
			    if (current.getPseudo().equals(user.getPseudo())&& current.getMdp().equals(user.getMdp())) {
			        return current.getIdUtilisateur(); 
                }
		    }  
        } catch (Exception e) {
            System.out.println(e);
        }
		 return -1;
    }
    @PostMapping("checkAdmin")
    public int checkAdmin(@RequestBody Admin user){
        Connection c=null;
        try {
            c=ObjetBDD.connect();
            Admin u=new Admin();
            ArrayList<Object> list=u.find(c);
		    for (int i = 0; i < list.size(); i++) {
                Admin current=(Admin)list.get(i);
			    if (current.getPseudo().equals(user.getPseudo())&& current.getMdp().equals(user.getMdp())) {
			        return current.getIdAdmin();
                }
		    }  
        } catch (Exception e) {
            System.out.println(e);
        }
		 return -1;
    }
}
