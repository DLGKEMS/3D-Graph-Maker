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
    public Map<String, Long> getConditionData(String xcolumn, String[] condition, String[] logic, String[] equal, String[] column, String[] type) {

        Map<String, Long> map = new HashMap<>();
        MemoryJson memoryJson = new MemoryJson();
        if(memoryJson.getMap() != null){memoryJson.clearing();}
        List<Object[]> result = null;
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT mainData.columnValue, count(e) ");
        queryBuilder.append("FROM KeyValueEntity e ");
        queryBuilder.append("JOIN e.columnDataList mainData ON mainData.columnName = '" + xcolumn + "' ");
        int typeIndex = 0;
        for (int i = 0; i < column.length; i++) {
            if(type[i].equals("String")) {
                String joinCondition = "JOIN e.columnDataList conditionData" + String.valueOf(i) + " ON conditionData" + String.valueOf(i) + ".columnName = '" + column[i] + "' ";
                queryBuilder.append(joinCondition);
            }
            else if(type[i].equals("Integer")){
                String joinCondition = "JOIN e.columnDataList conditionData" + String.valueOf(i) + " ON conditionData" + String.valueOf(i) + ".columnName = '" + column[i] + "' ";
                queryBuilder.append(joinCondition);
            }
        }
        if(condition.length > 0) {
            queryBuilder.append("WHERE ");
            for (int i = 0; i < condition.length; i++) {
                //if(i==0){queryBuilder.append("WHERE ");}
                if (i > 0 && i <= logic.length) {
                    queryBuilder.append("" + logic[i - 1] + " ");
                }
                System.out.println("i : " + i);
                System.out.println("condition.length : " + condition.length);
                System.out.println("typeIndex" + typeIndex);
                System.out.println("type[typeIndex]" + type[typeIndex]);
                if(type[typeIndex].equals("String")) {
                    String conditionString = "conditionData" + String.valueOf(i) + ".columnValue " + equal[i] + " '" + condition[i] + "' ";
                    queryBuilder.append(conditionString);
                }
                else if(type[typeIndex].equals("Integer")){
                    if(equal[i].equals("BETWEEN")){
                        //String conditionString = "conditionData" + String.valueOf(i) + ".columnValue " + equal[i] + " " + numbericCondition2 + " ";
                        String conditionString = "conditionData" + String.valueOf(i) + ".columnValue "  + equal[i] + " '" + condition[i] + "' AND '" + condition[i+1] + "' ";
                        queryBuilder.append(conditionString);
                        i++;
                        System.out.println("i in between : " + i);

                    }
                    else {
                        //int numbericCondition1 = Integer.parseInt(condition[i]);
                        String test = condition[i];
                        String conditionString = "conditionData" + String.valueOf(i) + ".columnValue " + equal[i] + " '" + condition[i] + "' ";
                        System.out.println(conditionString);
                        queryBuilder.append(conditionString);
                    }
                }
                //System.out.println(typeIndex);
                typeIndex = typeIndex + 1;
            }
        }
        queryBuilder.append(" GROUP BY mainData.columnValue");
        String query = queryBuilder.toString();
        System.out.println(query);
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
