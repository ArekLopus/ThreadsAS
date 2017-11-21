package transactionSynchronizationRegistry;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import javax.transaction.TransactionSynchronizationRegistry;

@Entity
public class Entityk {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String surname;
	
	@Transient
	@Resource
	TransactionSynchronizationRegistry tsr;
	
	public Entityk() {}
	
	public Entityk(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
	}


	@PostConstruct
    public void postCon() {
        tsr.putResource("4", "Four");
    }
	
	@PostPersist
    public void postPer() {
        System.out.println("Persisted");
        //tsr.putResource("4", "Four");
    }
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
