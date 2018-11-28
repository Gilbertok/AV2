package model;

import java.util.Date;

public class TaxiModel {

//	1,2008-02-02 20:30:34,116.49625,39.9146
//	taxi id, date time, longitude, latitude
	
	private Integer codigoTaxi;
	private Date dataCorrida;
	private Double longitude;
	private Double latitude;
	
	public TaxiModel(Integer codigoTaxi, Date dataCorrida, Double longitude, Double latitude) {
		this.codigoTaxi = codigoTaxi;
		this.dataCorrida = dataCorrida;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Date getDataCorrida() {
		return dataCorrida;
	}
	public void setDataCorrida(Date dataCorrida) {
		this.dataCorrida = dataCorrida;
	}
	@Override
	public String toString() {
		return "Taxi [codigoTaxi=" + codigoTaxi + ", dataCorrida=" + dataCorrida + ", longitude=" + longitude + ", latitude=" + latitude + "]";
	}

	public Integer getCodigoTaxi() {
		return codigoTaxi;
	}

	public void setCodigoTaxi(Integer codigoTaxi) {
		this.codigoTaxi = codigoTaxi;
	}
	
}
