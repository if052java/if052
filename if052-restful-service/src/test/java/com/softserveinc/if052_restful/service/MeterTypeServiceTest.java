package com.softserveinc.if052_restful.service;

import com.softserveinc.if052_core.domain.MeterType;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests of meter type service
 *
 * @author Bogdan Pastushkevych
 * @version 1.0
 * @see com.softserveinc.if052_restful.service.MeterTypeService
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:context.xml")
public class MeterTypeServiceTest {

    @Autowired
    private MeterTypeService meterTypeService;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testGetAllMeterTypes() {
        List<MeterType> meterTypes = meterTypeService.getAllMeterTypes();
        assertNotNull("Meter types list is null", meterTypes);

        int expectedLength = 4;

        assertEquals("Number of meter types should be " + expectedLength + "",
                expectedLength, meterTypes.size());
        for (MeterType meterType : meterTypes) {
            assertNotNull("Meter type is null", meterType);
        }
    }

    @Test
    public void testGetMeterTypeById() {
        MeterType meterType = meterTypeService.getMeterTypeById(1);
        assertNotNull("Meter type is null", meterType);

        String expectedType = "Холодна вода";

        assertEquals("Meter type  should be '" + expectedType + "'",
                expectedType, meterType.getType());
    }

    @Test
    public void testInsertMeterTypeExceptionIsThrown() {
        exception.expect(DataIntegrityViolationException.class);
        MeterType meterType = meterTypeService.getMeterTypeById(1);

        String existedType = meterType.getType();

        MeterType createdMeterType = new MeterType();
        createdMeterType.setType(existedType);
        meterTypeService.insertMeterType(createdMeterType);
    }

    @Test
    public void testInsertMeterType() {
        MeterType meterType = new MeterType();
        meterType.setType("вода");
        meterTypeService.insertMeterType(meterType);
        assertTrue("ID of meter type should not be zero",
                meterType.getMeterTypeId() != 0);
        MeterType createdMeterType =
                meterTypeService.getMeterTypeById(meterType.getMeterTypeId());
        assertNotNull("Created meter type is null", createdMeterType);

        String expectedType = meterType.getType();

        assertEquals("Type of meter should be " + expectedType + "",
                expectedType, createdMeterType.getType());
        meterTypeService.deleteMeterType(meterType.getMeterTypeId());
    }

    @Test
    public void testUpdateMeterTypeExceptionIsThrown() {
        exception.expect(DataIntegrityViolationException.class);
        MeterType meterType = meterTypeService.getMeterTypeById(1);

        String existedType = meterType.getType();

        MeterType updatedMeterType = meterTypeService.getMeterTypeById(2);
        updatedMeterType.setType(existedType);
        meterTypeService.updateMeterType(updatedMeterType);
    }

    @Test
    public void testUpdateMeterType() {
        MeterType meterType = meterTypeService.getMeterTypeById(2);

        String expectedType = "тверде паливо";

        meterType.setType(expectedType);
        meterTypeService.updateMeterType(meterType);
        MeterType updatedMeterType = meterTypeService.getMeterTypeById(2);
        assertNotNull("Updated meter type is null", updatedMeterType);
        assertEquals("Meter type  should be '" + expectedType + "'",
                expectedType, updatedMeterType.getType());
    }

    @Test
    public void testDeleteMeterType() {
        MeterType meterType = new MeterType();
        meterType.setType("повітря");
        meterTypeService.insertMeterType(meterType);
        meterTypeService.deleteMeterType(meterType.getMeterTypeId());
        MeterType deletedMeterType =
                meterTypeService.getMeterTypeById(meterType.getMeterTypeId());
        assertNull("Deleted meter type is not null", deletedMeterType);
    }
}