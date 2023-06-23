package Uygulama;

public class Kayitlar_gecmis {
	
	private int id;
	private String kullanici;
	
	public Kayitlar_gecmis(int id, String kullanici) {
		this.id=id;
		this.kullanici=kullanici;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKullanici() {
		return kullanici;
	}

	public void setKullanici(String kullanici) {
		this.kullanici = kullanici;
	}
	
}
