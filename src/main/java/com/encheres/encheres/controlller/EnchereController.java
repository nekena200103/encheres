package com.encheres.encheres.controlller;

import java.sql.Connection;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encheres.encheres.classes.Enchere;
import com.encheres.encheres.classes.Mise;
import com.encheres.encheres.classes.ObjetBDD;


@CrossOrigin
@RestController
@RequestMapping("encheres")
public class EnchereController {
    @GetMapping("")
    public ArrayList<Object> getListeEncheres(){
        Connection c=null;
        try {
            c=ObjetBDD.connect();
            Enchere e=new Enchere();
            return e.find(c);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @GetMapping("/{id}")
    public Enchere getFicheEncheres(@PathVariable(name="id")int id){
        Connection c=null;
        try {
            c=ObjetBDD.connect();
            Enchere e=new Enchere();
            e.setIdEnchere(id);
            Enchere retour=(Enchere)e.findSpecific(c,"idEnchere").get(0);
            return retour;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @PostMapping("/mise")
    public void insertMise(@RequestBody Mise mise){
        Connection c=null;
        try {
            c=ObjetBDD.connect();
            mise.create(c);
            c.commit();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @PostMapping("")
    public void insertEnchere(@RequestBody Enchere enchere){
        Connection c=null;
        try {
            c=ObjetBDD.connect();
            enchere.create(c);
            c.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
