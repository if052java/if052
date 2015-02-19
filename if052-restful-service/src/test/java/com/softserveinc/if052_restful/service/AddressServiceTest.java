package com.softserveinc.if052_restful.service;

import com.softserveinc.if052_restful.domain.Address;
import com.softserveinc.if052_restful.domain.WaterMeter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class AddressServiceTest
{
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;

    @Test
    public void testGetAddressById()
    {
        Address address = addressService.getAddressById(1);
        List<WaterMeter> waterMeters = address.getWaterMeters();
        for(WaterMeter wm : waterMeters) {
            System.out.println(wm);
        }
        Assert.assertNotNull(address);
        System.out.println(address.getUser().getName());
        System.out.println(address);
    }

    @Test
    public void testGetAddressesByUserId()
    {
        List<Address> addresses = addressService.getAllAddressesByUserId(1);
        Assert.assertNotNull(addresses);
        for (Address address : addresses)
        {
            System.out.println(address);
        }
    }
    @Test
    public void testGetAllAddresses()
    {
        List<Address> addresses = addressService.getAllAddresses();
        Assert.assertNotNull(addresses);
        for (Address address : addresses)
        {
            System.out.println(address);
        }

    }

    @Test
    public void testInsertAddress() {
        long generateOrigin = System.currentTimeMillis();

        Address address = new Address();
        address.setCity("Івано-Франківськ");
        address.setStreet("Сахарова");
        address.setBuilding("23");
        address.setApartment("503");
        address.setTariff( generateOrigin / 1000000000 );
        address.setUser(userService.getUserById(1));

        addressService.insertAddress(address);
        System.out.println(address.getAddressId());

        Assert.assertTrue(address.getAddressId() != 0);
        Address createdAddress = addressService.getAddressById(address.getAddressId());
        Assert.assertNotNull(createdAddress);
        Assert.assertEquals(address.getAddressId(), createdAddress.getAddressId());
        Assert.assertEquals(address.getCity(), createdAddress.getCity());
        Assert.assertEquals(address.getStreet(), createdAddress.getStreet());
        Assert.assertEquals(address.getBuilding(), createdAddress.getBuilding());
        Assert.assertEquals(address.getApartment(), createdAddress.getApartment());
        Assert.assertEquals(address.getTariff(), createdAddress.getTariff(), 0.0001);
        Assert.assertEquals(address.getUser().getUserId(), createdAddress.getUser().getUserId());
    }
    @Test
    public void testUpdateAddress()
    {
        // searching of last record id for update
        List<Address> addresses = addressService.getAllAddresses();
        int lastId = addresses.get(addresses.size() - 1).getAddressId();

        Address address = addressService.getAddressById(lastId);
        address.setCity("Львів");
        address.setStreet("Садова");
        address.setBuilding("2а");
        address.setApartment("1");
        address.setTariff(50);
        addressService.updateAddress(address);
        Address updatedAddress = addressService.getAddressById(lastId);
        Assert.assertEquals(address.getCity(), updatedAddress.getCity());
        Assert.assertEquals(address.getStreet(), updatedAddress.getStreet());
        Assert.assertEquals(address.getBuilding(), updatedAddress.getBuilding());
        Assert.assertEquals(address.getApartment(), updatedAddress.getApartment());
        Assert.assertEquals(address.getTariff(), updatedAddress.getTariff(), 0.1);
    }

    @Test
    public void testDeleteAddress()
    {
        Address address = new Address();
        address.setCity("Івано-Франківськ");
        address.setStreet("Сахарова");
        address.setBuilding("23");
        address.setApartment("503");
        address.setTariff(0.23);
        address.setUser(userService.getUserById(1));
        addressService.insertAddress(address);

//        // searching of last record id for update
//        List<Address> addresses = addressService.getAllAddresses();
//        int lastId = addresses.get(addresses.size() - 1).getAddressId();

//        Address address = addressService.getAddressById(lastId);
        addressService.deleteAddress(address.getAddressId());
        Address deletedAddress = addressService.getAddressById(address.getAddressId());
        Assert.assertNull(deletedAddress);
    }
}


