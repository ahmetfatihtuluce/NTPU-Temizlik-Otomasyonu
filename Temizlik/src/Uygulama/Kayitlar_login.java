package Uygulama;

public class Kayitlar_login {
	
	private int id;
	private String kul_ad;
	private String sifre;
	private int yetki;
	
	Kayitlar_login(){
		
	}
	Kayitlar_login(int id,String kul_ad, String sifre, int yetki){
		this.id=id;
		this.kul_ad=kul_ad;
		this.sifre=sifre;
		this.yetki=yetki;
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKul_ad() {
		return kul_ad;
	}
	public void setKul_ad(String kul_ad) {
		this.kul_ad = kul_ad;
	}
	public String getSifre() {
		return sifre;
	}
	public void setSifre(String sifre) {
		this.sifre = sifre;
	}
	public int getYetki() {
		return yetki;
	}
	public void setYetki(int yetki) {
		this.yetki = yetki;
	}
	
	
}
