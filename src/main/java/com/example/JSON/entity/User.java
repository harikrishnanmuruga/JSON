package com.example.JSON.entity;

import com.example.JSON.common.Constant;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User_Table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String gender;
    private String emailId;
    private String phoneNumber;
    private String usertype= Constant.USER_TYPE.NORMAL;
    private String password;
    private boolean isActive = true;
    private Integer loginCount = 0;
    private String ssoType;
    private DateTime loginAt;
    //@Type(type ="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime createdAt;
   // @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime updatedAt;

    @PrePersist
    public void onsave(){
        //create at and update at
        DateTime currentDateTime = new DateTime();
        this.createdAt = currentDateTime;
        this.updatedAt = currentDateTime;
    }

    @PostPersist
    public void onUpdate() {
        //update at
        DateTime currentDateTime = new DateTime();
        this.updatedAt = currentDateTime;
    }

}
