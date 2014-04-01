package com.urucas.android.base.parser;

import org.json.JSONException;
import org.json.JSONObject;

import com.urucas.android.base.model.Radio;

public abstract class RadioParser {

	public static Radio parse(JSONObject result) {
		
		try {
			int id = result.getInt("id");
			String nombre = result.getString("nombre");
			String streaming = result.getString("filename").trim();
			String streaming1 = result.getString("filename1").trim();
			String streaming2 = result.getString("filename2").trim();
			String dial = result.getString("dial");
			String imagen = result.getString("imagen");
			String nom_ciudad = result.getString("nom_ciudad");
			
			String subgenero = result.getString("subgenero");
			Radio r = new Radio(id, nombre, dial, streaming, imagen, nom_ciudad, subgenero);
			try { 
				String nom_pais = result.getString("nom_pais");
				r.setPais(nom_pais);
				
			}catch(Exception e) {}
			
			try { 
				String nom_provincia = result.getString("nom_provincia");
				r.setProvincia(nom_provincia);
				
			}catch(Exception e) {}
			
			
			r.setStreaming1(streaming1);
			r.setStreaming2(streaming1);
			
			return r;
			
		} catch (JSONException e) { }
		
		return null;
	}
	
	public static Radio parseFull(JSONObject result) {
		
		try {
			int id = result.getInt("id");
			String nombre = result.getString("nombre");
			String streaming = result.getString("filename");
			String dial = result.getString("dial");
			String imagen = result.getString("imagen");
			String nom_ciudad = result.getString("nom_ciudad");
			String subgenero = result.getString("subgenero");
			
			Radio radio = new Radio(id, nombre, dial, streaming, imagen, nom_ciudad, subgenero);
			radio.setDireccion(result.getString("direccion"));
			radio.setCantFollowers(result.getInt("seguidores"));
			radio.setCantVisits(result.getInt("visitas"));
			radio.setCantVotos(result.getInt("votos"));
			radio.setPais(result.getString("nom_pais"));
			radio.setWorldRanking(result.getInt("ranking"));
			radio.setCountryRanking(result.getInt("ranking_pais"));
			radio.setTelefono(result.getString("telefono"));
			radio.setWeb(result.getString("web"));
			radio.setFb(result.getString("fb"));
			radio.setTw(result.getString("tw"));
			
			return radio;
			
		} catch (JSONException e) { 
			e.printStackTrace();
		}
		
		return null;
	}
}
