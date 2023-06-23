package Uygulama;

public class Kayitlar_envanter {
	private int id;
	private String urunadi;
	private int adet;
	private int tutar;
	
	public Kayitlar_envanter(int id, String urunadi, int adet, int tutar) {
		this.id=id;
		this.urunadi=urunadi;
		this.adet=adet;
		this.tutar=tutar;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrunadi() {
		return urunadi;
	}

	public void setUrunadi(String urunadi) {
		this.urunadi = urunadi;
	}

	public int getAdet() {
		return adet;
	}

	public void setAdet(int adet) {
		this.adet = adet;
	}

	public int getTutar() {
		return tutar;
	}

	public void setTutar(int tutar) {
		this.tutar = tutar;
	}

	
	
	
	
	
}
