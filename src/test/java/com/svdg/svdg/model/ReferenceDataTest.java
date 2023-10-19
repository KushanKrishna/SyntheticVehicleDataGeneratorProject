package com.svdg.svdg.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ReferenceDataTest {



    @Test
    public void testReferenceDataSettersAndGetters() {
        ReferenceData referenceData = new ReferenceData();

        long id = 2L;
        String type = "Type2";
        String typeCode = "T2";
        String value = "Value2";

        referenceData.setId(id);
        referenceData.setType(type);
        referenceData.setTypeCode(typeCode);
        referenceData.setValue(value);

        assertEquals(id, referenceData.getId());
        assertEquals(type, referenceData.getType());
        assertEquals(typeCode, referenceData.getTypeCode());
        assertEquals(value, referenceData.getValue());
    }
}
