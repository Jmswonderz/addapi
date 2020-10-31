package com.businessad.addapi.entities;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "adverts")
@EntityListeners(AuditingEntityListener.class)
public class Advert {

    @javax.persistence.Id
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Body", nullable = false)
    private String body;

    @Column(name = "BusinessId", nullable = false)
    private String businessId;


    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getBody(){
        return body;
    }

    public void setBody(String body){
        this.body = body;
    }

    public String getBusinessId(){
        return businessId;
    }

    public void setBusinessId(String businessId){
        this.businessId = businessId;
    }
}
