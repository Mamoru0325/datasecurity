package th.ac.ku.kps.eng.cpe.datasecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

// Generated Oct 16, 2023, 11:42:57 AM by Hibernate Tools 5.6.3.Final

/**
 * Cipher generated by hbm2java
 */
public class Cipher implements java.io.Serializable {

	private Integer cipherId;
	@JsonIgnore
	private Type type;
	private String plainText;
	private String cipherKey;
	private String level;

	public Cipher() {
	}

	public Cipher(Type type, String plainText, String cipherKey, String level) {
		this.type = type;
		this.plainText = plainText;
		this.cipherKey = cipherKey;
		this.level = level;
	}

	public Integer getCipherId() {
		return this.cipherId;
	}

	public void setCipherId(Integer cipherId) {
		this.cipherId = cipherId;
	}

	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getPlainText() {
		return this.plainText;
	}

	public void setPlainText(String plainText) {
		this.plainText = plainText;
	}

	public String getCipherKey() {
		return this.cipherKey;
	}

	public void setCipherKey(String cipherKey) {
		this.cipherKey = cipherKey;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}
