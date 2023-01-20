package com.encheres.encheres.controlller;

import java.sql.Connection;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encheres.encheres.classes.Enchere;
import com.encheres.encheres.classes.EnchereRepository;
import com.encheres.encheres.classes.ObjetBDD;

@CrossOrigin
@RestController
@RequestMapping()
public class CommunController {
	@Autowired
	EnchereRepository enchererep;
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
	
}
