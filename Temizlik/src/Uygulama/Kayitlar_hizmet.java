package Uygulama;

public class Kayitlar_hizmet {
	
	private int id;
	private String ad;
	private String tur;
	private String tel;
	private String adres;
	
	public Kayitlar_hizmet(int id, String ad, String tur, String tel, String adres) {
		this.id=id;
		this.ad=ad;
		this.tur=tur;
		this.tel=tel;
		this.adres=adres;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getTur() {
		return tur;
	}

	public void setTur(String tur) {
		this.tur = tur;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}
	
}

	