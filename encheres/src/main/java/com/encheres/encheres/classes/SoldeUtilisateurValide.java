package com.encheres.encheres.classes;

import java.security.Timestamp;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

//JPA Entity
@Entity
@Table(name = "soldeutilisateurvalide")
public class SoldeUtilisateurValide {

 @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "soldeutilisateurvalide_idsoldeutilisateurvalide_seq")
 @SequenceGenerator(name = "soldeutilisateurvalide_idsoldeutilisateurvalide_seq", sequenceName = "soldeutilisateurvalide_idsoldeutilisateurvalide_seq", allocationSize = 1)
 private Integer idsoldeutilisateurvalide;

 private Integer valeur;

 @Column(name = "dateoperation")
 private Timestamp dateOperation;

 @ManyToOne
 @JoinColumn(name = "idutilisateur", referencedColumnName = "idutilisateur")
 private Utilisateur utilisateur;

 // Getters and Setters
 public Integer getIdsoldeutilisateurvalide() {
     return idsoldeutilisateurvalide;
 }
 public void setIdsoldeutilisateurvalide(Integer idsoldeutilisateurvalide) {
     this.idsoldeutilisateurvalide = idsoldeutilisateurvalide;
 }
 public Integer getValeur() {
     return valeur;
 }
 public void setValeur(Integer valeur) {
     this.valeur = valeur;
 }
 public Timestamp getDateOperation() {
     return dateOperation;
 }
 public void setDateOperation(Timestamp dateOperation) {
     this.dateOperation = dateOperation;
 }
 public Utilisateur getUtilisateur() {
     return utilisateur;
 }
 public void setUtilisateur(Utilisateur utilisateur) {
     this.utilisateur = utilisateur;
 }
}
