package com.rtcab.cedda.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.global.UuidProvider;
import org.hibernate.validator.constraints.Email;
import com.haulmont.cuba.core.entity.BaseUuidEntity;
import com.haulmont.chile.core.annotations.NamePattern;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.UUID;

@NamePattern("%s|name")
@MetaClass(name = "cedda$Customer")
@DynamoDBTable(tableName = "cuba-customers")
public class Customer extends BaseUuidEntity {
    private static final long serialVersionUID = -2735342546528178666L;


    @Id
    @Column(name = "ID")
    protected UUID id;


    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    @MetaProperty
    protected String name;

    @MetaProperty
    protected String firstName;

    @Email
    @MetaProperty
    protected String email;

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute
    public String getName() {
        return name;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @DynamoDBAttribute
    public String getFirstName() {
        return firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @DynamoDBAttribute
    public String getEmail() {
        return email;
    }


}