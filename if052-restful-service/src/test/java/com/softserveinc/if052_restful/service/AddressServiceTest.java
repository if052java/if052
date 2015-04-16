package com.softserveinc.if052_restful.service;

import com.softserveinc.if052_core.domain.Address;
import com.softserveinc.if052_core.domain.WaterMeter;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.easymock.EasyMock.createMock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:context.xml"})
public class AddressServiceTest {
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;


    private static Logger LOGGER = Logger.getLogger(AddressServiceTest.class);

    @BeforeClass
    public static void setAuth(){
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                "1", "PASS1111", authorities);
        HttpServletRequest request = new MockHttpServletRequest();

        token.setDetails(new WebAuthenticationDetails(request));

        LOGGER.debug("Logging in with " + token.getPrincipal().toString());
        SecurityContextHolder.getContext().setAuthentication(token);
        LOGGER.debug(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        LOGGER.debug(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
    }

    @Test
    public void testGetAddressById() {
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
    public void testGetAddressesByUserId() {
        List<Address> addresses = addressService.getAllAddressesByUserId(1);
        Assert.assertNotNull(addresses);
        for (Address address : addresses) {
            System.out.println(address);
        }
    }

    @Test
    public void testGetAllAddresses() {
        List<Address> addresses = addressService.getAllAddresses();
        Assert.assertNotNull(addresses);
        for (Address address : addresses) {
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
        address.setApartment(10);
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
        Assert.assertEquals(address.getUser().getUserId(), createdAddress.getUser().getUserId());
    }

    @Test
    public void testUpdateAddress() {
        // searching of last record id for update
//        setAuth();
        List<Address> addresses = addressService.getAllAddresses();
        int lastId = addresses.get(addresses.size() - 1).getAddressId();

        Address address = addressService.getAddressById(lastId);
        address.setCity("Львів");
        address.setStreet("Садова");
        address.setBuilding("2а");
        address.setApartment(10);
        addressService.updateAddress(address);
        Address updatedAddress = addressService.getAddressById(lastId);

        Assert.assertEquals(address.getCity(), updatedAddress.getCity());
        Assert.assertEquals(address.getStreet(), updatedAddress.getStreet());
        Assert.assertEquals(address.getBuilding(), updatedAddress.getBuilding());
        Assert.assertEquals(address.getApartment(), updatedAddress.getApartment());
    }

    @Test
    public void testDeleteAddress() {
        Address address = new Address();
        address.setCity("Івано-Франківськ");
        address.setStreet("Сахарова");
        address.setBuilding("23");
        address.setApartment(10);
        address.setUser(userService.getUserById(1));
        addressService.insertAddress(address);

        // searching of last record id for update
        // List<Address> addresses = addressService.getAllAddresses();
        // int lastId = addresses.get(addresses.size() - 1).getAddressId();

        // Address address = addressService.getAddressById(lastId);
        addressService.deleteAddress(address.getAddressId());
        Address deletedAddress = addressService.getAddressById(address.getAddressId());
        Assert.assertNull(deletedAddress);
    }
}