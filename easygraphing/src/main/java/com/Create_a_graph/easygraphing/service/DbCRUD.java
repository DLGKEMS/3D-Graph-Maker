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
    public Map<String, Long> getNoConditionData(String xcolumn, String[] condition, String[] option, String[] equal) {
        Map<String, Long> map = new HashMap<>();
        MemoryJson memoryJson = new MemoryJson();
        if(memoryJson.getMap() != null){memoryJson.clearing();}
        List<Object[]> result = null;

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
            result = entityManager.createQuery(
                            "SELECT mainData.columnValue, count(e) " +
                                    "FROM KeyValueEntity e " +
                                    "JOIN e.columnDataList mainData ON mainData.columnName = '" + xcolumn + "' " +
                                    "JOIN e.columnDataList conditionData ON conditionData.columnName = '" + condition[0] + "' " +
                                    "WHERE conditionData.columnValue " + equal[0] + " '" + condition[1] + "' " +
                                    "GROUP BY mainData.columnValue",
                            Object[].class)
                    .getResultList();
        } // 조건 한개

        else if(condition.length == 4){
            result = entityManager.createQuery(
                            "SELECT mainData.columnValue, count(e) " +
                                    "FROM KeyValueEntity e " +
                                    "JOIN e.columnDataList mainData ON mainData.columnName = '" + xcolumn + "' " +
                                    "JOIN e.columnDataList conditionData1 ON conditionData1.columnName = '" + condition[0] + "' " +
                                    "JOIN e.columnDataList conditionData2 ON conditionData2.columnName = '" + condition[2] + "' " +
                                    "WHERE conditionData1.columnValue " + equal[0] + " '" + condition[1] + "' " +
                                    option[0] + " " +
                                    "conditionData2.columnValue " + equal[1] + " '" + condition[3] + "' " +
                                    "GROUP BY mainData.columnValue",
                            Object[].class)
                    .getResultList();
        }// 조건 2개

        else if(condition.length == 6){
            result = entityManager.createQuery(
                            "SELECT mainData.columnValue, count(e) " +
                                    "FROM KeyValueEntity e " +
                                    "JOIN e.columnDataList mainData ON mainData.columnName = '" + xcolumn + "' " +
                                    "JOIN e.columnDataList conditionData1 ON conditionData1.columnName = '" + condition[0] + "' " +
                                    "JOIN e.columnDataList conditionData2 ON conditionData2.columnName = '" + condition[2] + "' " +
                                    "JOIN e.columnDataList conditionData3 ON conditionData3.columnName = '" + condition[4] + "' " +
                                    "WHERE conditionData1.columnValue " + equal[0] + " '" + condition[1] + "' " +
                                    option[0] + " " +
                                    "conditionData2.columnValue " + equal[1] + " '" + condition[3] + "' " +
                                    option[1] + " " +
                                    "conditionData3.columnValue " + equal[2] + " '" + condition[5] + "' " +
                                    "GROUP BY mainData.columnValue",
                            Object[].class)
                    .getResultList();
        } // 조건 3개

        else if(condition.length == 8){
            result = entityManager.createQuery(
                            "SELECT mainData.columnValue, count(e) " +
                                    "FROM KeyValueEntity e " +
                                    "JOIN e.columnDataList mainData ON mainData.columnName = '" + xcolumn + "' " +
                                    "JOIN e.columnDataList conditionData1 ON conditionData1.columnName = '" + condition[0] + "' " +
                                    "JOIN e.columnDataList conditionData2 ON conditionData2.columnName = '" + condition[2] + "' " +
                                    "JOIN e.columnDataList conditionData3 ON conditionData3.columnName = '" + condition[4] + "' " +
                                    "JOIN e.columnDataList conditionData4 ON conditionData4.columnName = '" + condition[6] + "' " +
                                    "WHERE conditionData1.columnValue " + equal[0] + " '" + condition[1] + "' " +
                                    option[0] + " " +
                                    "conditionData2.columnValue " + equal[1] + " '" + condition[3] + "' " +
                                    option[1] + " " +
                                    "conditionData3.columnValue " + equal[2] + " '" + condition[5] + "' " +
                                    option[2] + " " +
                                    "conditionData4.columnValue " + equal[3] + " '" + condition[7] + "' " +
                                    "GROUP BY mainData.columnValue",
                            Object[].class)
                    .getResultList();
        } // 조건 4개

        else if(condition.length == 10){
            result = entityManager.createQuery(
                            "SELECT mainData.columnValue, count(e) " +
                                    "FROM KeyValueEntity e " +
                                    "JOIN e.columnDataList mainData ON mainData.columnName = '" + xcolumn + "' " +
                                    "JOIN e.columnDataList conditionData1 ON conditionData1.columnName = '" + condition[0] + "' " +
                                    "JOIN e.columnDataList conditionData2 ON conditionData2.columnName = '" + condition[2] + "' " +
                                    "JOIN e.columnDataList conditionData3 ON conditionData3.columnName = '" + condition[4] + "' " +
                                    "JOIN e.columnDataList conditionData4 ON conditionData4.columnName = '" + condition[6] + "' " +
                                    "JOIN e.columnDataList conditionData4 ON conditionData4.columnName = '" + condition[8] + "' " +
                                    "WHERE conditionData1.columnValue " + equal[0] + " '" + condition[1] + "' " +
                                    option[0] + " " +
                                    "conditionData2.columnValue " + equal[1] + " '" + condition[3] + "' " +
                                    option[1] + " " +
                                    "conditionData3.columnValue " + equal[2] + " '" + condition[5] + "' " +
                                    option[2] + " " +
                                    "conditionData4.columnValue " + equal[3] + " '" + condition[7] + "' " +
                                    option[3] + " " +
                                    "conditionData5.columnValue " + equal[4] + " '" + condition[9] + "' " +
                                    "GROUP BY mainData.columnValue",
                            Object[].class)
                    .getResultList();
        } // 조건 5개 end

        else if(condition.length <= 1){
            result = entityManager.createQuery(
                            "SELECT mainData.columnValue, count(e) " +
                                    "FROM KeyValueEntity e " +
                                    "JOIN e.columnDataList mainData ON mainData.columnName = '" + xcolumn + "' " +
                                    "GROUP BY mainData.columnValue",
                            Object[].class)
                    .getResultList();
        } // 조건 x

        for (Object[] row : result) {
            String columnName = (String) row[0];
            Long columnValue = (Long) row[1];
            memoryJson.setMap(columnName, columnValue);
        }


        System.out.println(map);

        return map;
    }
}
