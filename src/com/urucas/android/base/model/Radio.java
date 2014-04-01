package com.urucas.android.base.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.util.Log;

public class Radio {

	private String nombre, dial, streaming, streaming1 = "", streaming2, imagen, fechaInsert, nom_ciudad, genero, telefono;
	private int calculated_ranking, id;
	private String direccion, web = "", fb="",tw="";
	private int wranking;
	private int cranking;
	private int cantFollowers;
	private int cantVisits;
	private int cantVotos;
	private String pais = "", provincia = "";
	
	public Radio(int id, String nombre, String dial, String streaming, String imagen, String nom_ciudad, String genero) {
		this.id = id;
		this.nombre = nombre;
		this.dial = dial;
		this.streaming = streaming;
		this.imagen = imagen;
		this.nom_ciudad = nom_ciudad;
		this.genero = genero;
	}
	
	public Radio(int id, String nombre, String dial, String streaming, String imagen, String fechaInsert, String nom_ciudad, String genero) {
		this.id = id;
		this.nombre = nombre;
		this.dial = dial;
		this.streaming = streaming;
		this.imagen = imagen;
		this.fechaInsert = fechaInsert;
		this.nom_ciudad = nom_ciudad;
		this.genero = genero;
	}
	
	public Radio(int id, String nombre, String dial, String streaming, String imagen, String genero) {
		this.id = id;
		this.nombre = nombre;
		this.imagen = imagen;
		this.streaming = streaming;
		this.dial = dial;
		this.genero = genero;
	}
	
	public Radio(int id, String nombre, String imagen, String dial) {
		this.id = id;
		this.nombre = nombre;
		this.imagen = imagen;
		this.dial = dial;
	}
	
	public int getId(){
		return id;
	}
	
	public String getImagen() {
		return imagen;
	}
	
	public String getStreaming() {
		if(!streaming1.equalsIgnoreCase("")) 
			return streaming1;
		
		return streaming;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDial() {
		return dial;
	}
	
	public String getCiudad() {
		return nom_ciudad;
	}
	
	public String getGenero() {
		return genero;
	}

	public long getCalculated_ranking() {
		return calculated_ranking;
	}
	
	public Date getInsertDate(){
		SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date date;
		try {
			date = format.parse(fechaInsert);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			date = new Date();
			e.printStackTrace();
		} 
		Log.i("fecha insert",String.valueOf(date));
		return date;
	}

	public void setDireccion(String _direccion) {
		direccion = _direccion;
	}
	
	public void setWeb(String _web) {
		web = _web;
	}
	
	public void setFb(String _fb) {
		fb = _fb;
	}
	
	public void setTw(String _tw) {
		tw = _tw;
	}
	
	public void setWorldRanking(int _ranking) {
		wranking = _ranking;
	}
	
	public void setCountryRanking(int _ranking) {
		cranking = _ranking;
	}
	
	public void setCantFollowers(int _cfollowers) {
		cantFollowers = _cfollowers;
	}
	
	public void setCantVisits(int _cvisits) {
		cantVisits = _cvisits;
	}
	
	public void setCantVotos(int _cvotos) {
		cantVotos = _cvotos;
	}
	
	public void setPais(String _pais) {
		pais = _pais;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getWeb() {
		return web;
	}
	
	public String getFb() {
		return fb;
	}
	
	public String getTw() {
		return tw;
	}

	public int getWorldRanking() {
		return wranking;
	}

	public String getPais() {
		return pais;
	}

	public int getPaisRanking() {
		return cranking;
	}

	public int getVotos() {
		return cantVotos;
	}

	public int getVisitas() {
		return cantVisits;
	}

	public int getCantSeguidores() {
		return cantFollowers;
	}

	public void setStreaming1(String streaming1) {
		this.streaming1 = streaming1;
	}
	
	public void setStreaming2(String streaming2) {
		this.streaming2 = streaming2;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String nom_provincia) {
		this.provincia = nom_provincia;
	}
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String tel) {
		this.telefono = tel;
	}
}
