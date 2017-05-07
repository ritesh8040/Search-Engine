package com.app;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;

public class Utility {
	public static boolean isValidURL(String url) {
		String urlPattern = "^http(s{0,1})://[a-zA-Z0-9_/\\-\\.]+\\.([A-Za-z/]{2,5})[a-zA-Z0-9_/\\&\\?\\=\\-\\.\\~\\%]*";
		if (url.matches(urlPattern))
			return true;
		else
			return false;
	}

	public static String getDomain(String url) throws URISyntaxException {
		if((!url.startsWith("https:/")) && (!url.startsWith("http:/")) ){
			url="http://"+url;
		}
           System.out.println(url);
		URI uri = new URI(url);
		String domain = uri.getAuthority();
		if(domain.startsWith("www.")){
			domain=domain.replaceFirst("www.","");
		}

		return domain;

	}

	public static void main(String[] args)throws Exception {
		
	}
}
