package dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TaxiCorrida {

//	1,2008-02-02 20:30:34,116.49625,39.9146
//	taxi id, date time, longitude, latitude
	
	@Id
	@GeneratedValue
	private Integer id;
	private Integer codigoTaxi;
	private Date dataCorrida;
	private Double longitude;
	private Double latitude;
	
	public TaxiCorrida(){}
	
	public TaxiCorrida(Integer codigoTaxi, Date dataCorrida, Double latitude, Double longitude) {
		this.codigoTaxi = codigoTaxi;
		this.dataCorrida = dataCorrida;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodigoTaxi() {
		return codigoTaxi;
	}
	public void setCodigoTaxi(Integer codigoTaxi) {
		this.codigoTaxi = codigoTaxi;
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
	
}
