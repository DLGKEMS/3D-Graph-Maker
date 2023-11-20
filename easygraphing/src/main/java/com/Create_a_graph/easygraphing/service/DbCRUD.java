package com.Create_a_graph.easygraphing.service;

import com.Create_a_graph.easygraphing.Entity.KeyValueEntity;
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
    public Map<String, Long> getNoConditionData(String xcolumn, String[] condition) {
        Map<String, Long> map = new HashMap<>();
        MemoryJson memoryJson = new MemoryJson();
        if(memoryJson.getMap() != null){memoryJson.clearing();}

        if (keyValueEntityRepository == null) {
            System.out.println("KeyValueEntityRepository is not initialized.");
            return null;
        }
        List<KeyValueEntity> entities = keyValueEntityRepository.findAll();


        for(int i = 0; i<condition.length; i++){
            System.out.println("condition : " + condition[i]);
        }

        String o = "or";

        if(condition.length == 2) {
            List<Object[]> result = entityManager.createQuery(
                            "SELECT mainData.columnValue, count(e) " +
                                    "FROM KeyValueEntity e " +
                                    "JOIN e.columnDataList mainData ON mainData.columnName = '" + xcolumn + "' " +
                                    "JOIN e.columnDataList conditionData ON conditionData.columnName = '" + condition[0] + "' " +
                                    "WHERE conditionData.columnValue = '" + condition[1] + "' " +
                                    "GROUP BY mainData.columnValue",
                            Object[].class)
                    .getResultList();
            for (Object[] row : result) {
                String columnName = (String) row[0];
                Long columnValue = (Long) row[1];
                memoryJson.setMap(columnName, columnValue);
            }
        }
        else if(condition.length == 4){
            List<Object[]> result = entityManager.createQuery(
                            "SELECT mainData.columnValue, count(e) " +
                                    "FROM KeyValueEntity e " +
                                    "JOIN e.columnDataList mainData ON mainData.columnName = '" + xcolumn + "' " +
                                    "JOIN e.columnDataList conditionData1 ON conditionData1.columnName = '" + condition[0] + "' " +
                                    "JOIN e.columnDataList conditionData2 ON conditionData2.columnName = '" + condition[2] + "' " +
                                    "WHERE conditionData1.columnValue != '" + condition[1] + "' " +
                                    "and " +
                                    "conditionData2.columnValue = '" + condition[3] + "' " +
                                    "GROUP BY mainData.columnValue",
                            Object[].class)
                    .getResultList();
            for (Object[] row : result) {
                String columnName = (String) row[0];
                Long columnValue = (Long) row[1];
                memoryJson.setMap(columnName, columnValue);
            }
        }
        else if(condition.length == 3){
            List<Object[]> result = entityManager.createQuery(
                            "SELECT mainData.columnValue, count(e) " +
                                    "FROM KeyValueEntity e " +
                                    "JOIN e.columnDataList mainData ON mainData.columnName = '" + xcolumn + "' " +
                                    "JOIN e.columnDataList ageData ON ageData.columnName = 'age' " +
                                    "JOIN e.columnDataList addrData ON addrData.columnName = 'address' " +
                                    "WHERE CAST(ageData.columnValue AS integer) >= 26 " +
                                    "and " +
                                    "addrData.columnValue like '%616%' " +
                                    "GROUP BY mainData.columnValue",
                            Object[].class)
                    .getResultList();
            for (Object[] row : result) {
                String columnName = (String) row[0];
                Long columnValue = (Long) row[1];
                memoryJson.setMap(columnName, columnValue);
            }
        }


        System.out.println(map);

        return map;
    }
}
