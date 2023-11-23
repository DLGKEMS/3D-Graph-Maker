package com.Create_a_graph.easygraphing.service;

import com.Create_a_graph.easygraphing.repository.KeyValueEntityRepository;
import com.Create_a_graph.easygraphing.repository.MemoryJson;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DbCRUD {
    private EntityManager entityManager;
    private KeyValueEntityRepository keyValueEntityRepository;

    public DbCRUD(EntityManager entityManager, KeyValueEntityRepository keyValueEntityRepository) {
        this.entityManager = entityManager;
        this.keyValueEntityRepository = keyValueEntityRepository;
    }

    @Transactional
    public Map<String, Long> getConditionData(String xcolumn, String[] condition, String[] option, String[] equal, String[] column) {

        Map<String, Long> map = new HashMap<>();
        MemoryJson memoryJson = new MemoryJson();
        if(memoryJson.getMap() != null){memoryJson.clearing();}
        List<Object[]> result = null;

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT mainData.columnValue, count(e) ");
        queryBuilder.append("FROM KeyValueEntity e ");
        queryBuilder.append("JOIN e.columnDataList mainData ON mainData.columnName = '" + xcolumn + "' ");

        for (int i = 0; i < column.length; i++) {
            String joinCondition = "JOIN e.columnDataList conditionData" + String.valueOf(i) + " ON conditionData" + String.valueOf(i) + ".columnName = '" + column[i] + "' ";
            queryBuilder.append(joinCondition);
        }

        for(int i = 0; i<condition.length; i++){
            if(i==0){queryBuilder.append("WHERE ");}
            if(i > 0 && i <= option.length){
                System.out.println("option 들어옴");
                queryBuilder.append("" + option[i-1] + " ");
            }
            String conditionString = "conditionData" + String.valueOf(i) +".columnValue " + equal[i] + " '" + condition[i] + "' ";
            queryBuilder.append(conditionString);
        }

        queryBuilder.append(" GROUP BY mainData.columnValue");
        String query = queryBuilder.toString();

        result = entityManager.createQuery(query, Object[].class).getResultList();


        for (Object[] row : result) {
            String columnName = (String) row[0];
            Long columnValue = (Long) row[1];
            memoryJson.setMap(columnName, columnValue);
        }


        System.out.println(map);

        return map;
    }
}
