package com.rtcab.cedda.core;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import com.haulmont.cuba.core.sys.AppContext;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component("sample_AppLifecycle")
public class CreateDynamoDbTable implements AppContext.Listener {


    @Inject
    private AmazonDynamoDB amazonDynamoDB;
    private static final String TABLE_NAME = "cuba-customers";


    public void initTable() {

        if (!tableExists()) {
            createTable();
        }

    }

    private Boolean tableExists() {
        ListTablesResult listTablesResult = amazonDynamoDB.listTables();

        return listTablesResult.getTableNames().stream()
                .anyMatch(currentTableName -> currentTableName.equals(TABLE_NAME));
    }

    private void createTable() {
        List<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
        attributeDefinitions.add(new AttributeDefinition().withAttributeName("id").withAttributeType("S"));

        List<KeySchemaElement> keySchemaElements = new ArrayList<KeySchemaElement>();
        keySchemaElements.add(new KeySchemaElement().withAttributeName("id").withKeyType(KeyType.HASH));

        CreateTableRequest request = new CreateTableRequest()
                .withTableName(TABLE_NAME)
                .withKeySchema(keySchemaElements)
                .withAttributeDefinitions(attributeDefinitions)
                .withProvisionedThroughput(new ProvisionedThroughput().withReadCapacityUnits(1L)
                        .withWriteCapacityUnits(1L));

        amazonDynamoDB.createTable(request);
    }

    public CreateDynamoDbTable() {
        AppContext.addListener(this);
    }

    @Override
    public void applicationStarted() {
        initTable();
    }

    @Override
    public void applicationStopped() {

    }
}