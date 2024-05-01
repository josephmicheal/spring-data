package com.example.demo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;

@Repository
public class CustomDateRepository{
    @Autowired
    private EntityManager entityManager;

    public Date getDateFromString(String dateString) {
        Query query = (Query) entityManager.createNativeQuery("SELECT COMMON_UTILS.ConvertStringToDate(:dateString) FROM DUAL", Date.class);
        query.setParameter("dateString", dateString);
        return (Date) query.getSingleResult();
    }

    public Integer convertStringToNumber(String inputString) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("common_utils.ConvertStringToNumber")
                .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, inputString);

        query.execute();
        return (Integer) query.getOutputParameterValue(2);
    }
}
