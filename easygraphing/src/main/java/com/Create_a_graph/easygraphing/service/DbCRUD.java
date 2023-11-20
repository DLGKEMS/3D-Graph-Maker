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
    public Map<String, Long> getNoConditionData(String xcolumn, String[] condition, String[] option) {
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

        for(int i = 0; i<option.length; i++){
            System.out.println("condition : " + option[i]);
        }

        String o = "or";

        if(condition.length == 2) {
            List<Object[]> result = entityManager.createQuery(
                            "SELECT mainData.columnValue, count(e) " +
                                    "FROM KeyValueEntity e " +
                                    "JOIN e.columnDataList mainData ON mainData.columnName = '" + xcolumn + "' " +
                                    "JOIN e.columnDataList conditionData ON conditionData.columnName = '" + condition[0] + "' " +
                                    "WHERE conditionData.columnValue != '" + condition[1] + "' " +
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
                                    "conditionData2.columnValue like '%616%' " +
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
//        List<KeyValueEntity> result = entityManager.createQuery(
//                        "SELECT e FROM KeyValueEntity e JOIN e.columnDataList d " +
//                                "WHERE d.columnName = '도로명' AND d.columnValue = '종로'",
//                        KeyValueEntity.class)
//                .getResultList();
//
//        for (KeyValueEntity entity : result) {
//            System.out.println("Found matching entity:");
//            System.out.println("id: " + entity.getId());
//            for (ColumnData data : entity.getColumnDataList()) {
//                System.out.println("  columnName: " + data.getColumnName());
//                System.out.println("  columnValue: " + data.getColumnValue());
//            }
//        }
    }
}
//System.out.println("  columnName: " + columnData.getColumnName());\
//System.out.println("1" + t.trim().replace("\uFEFF", "").equals("자치구명"));
//        System.out.println("2" + "자치구명".equals(t.trim().replace("\uFEFF", "")));
//
// for (int i = 0; i < t.length(); i++) {
//        System.out.println("Character at index " + i + ": " + t.charAt(i) + " (Unicode: " + (int) t.charAt(i) + ")");
//        }