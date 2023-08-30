package com.team1.sts.vo;

import org.springframework.stereotype.Component;

@Component
public class AddressVO {
	private String zip_Num;
	private String sido;
	private String gugun;
	private String dong;
	private String zip_Code;
	private String bunji;

	public AddressVO() {

	}

	public String getZip_Num() {
		return zip_Num;
	}

	public void setZip_Num(String zip_Num) {
		this.zip_Num = zip_Num;
	}

	public String getSido() {
		return sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getGugun() {
		return gugun;
	}

	public void setGugun(String gugun) {
		this.gugun = gugun;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getZip_Code() {
		return zip_Code;
	}

	public void setZip_Code(String zip_Code) {
		this.zip_Code = zip_Code;
	}

	public String getBunji() {
		return bunji;
	}

	public void setBunji(String bunji) {
		this.bunji = bunji;
	}

}
