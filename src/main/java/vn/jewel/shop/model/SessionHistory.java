package vn.jewel.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "sessionhistory")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class SessionHistory extends AbstractModel<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(nullable = false, unique = true)
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "descreption")
	private String descreption;

	@Column(name = "create_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Date create_at;
	
	@Column(name = "name_broswer")
	private String name_broswer;
	
	@Column(name = "ip")
	private String ip;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescreption() {
		return descreption;
	}

	public void setDescreption(String descreption) {
		this.descreption = descreption;
	}

	public Date getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}

	public String getName_broswer() {
		return name_broswer;
	}

	public void setName_broswer(String name_broswer) {
		this.name_broswer = name_broswer;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
