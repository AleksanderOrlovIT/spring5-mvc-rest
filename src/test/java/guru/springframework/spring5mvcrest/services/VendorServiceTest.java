package guru.springframework.spring5mvcrest.services;

import guru.springframework.spring5mvcrest.api.v1.mapper.CategoryMapper;
import guru.springframework.spring5mvcrest.api.v1.mapper.VendorMapper;
import guru.springframework.spring5mvcrest.api.v1.model.CategoryDTO;
import guru.springframework.spring5mvcrest.api.v1.model.CustomerDTO;
import guru.springframework.spring5mvcrest.api.v1.model.VendorDTO;
import guru.springframework.spring5mvcrest.api.v1.model.VendorListDTO;
import guru.springframework.spring5mvcrest.domain.Category;
import guru.springframework.spring5mvcrest.domain.Customer;
import guru.springframework.spring5mvcrest.domain.Vendor;
import guru.springframework.spring5mvcrest.repository.CategoryRepository;
import guru.springframework.spring5mvcrest.repository.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class VendorServiceTest {

    public static final Long ID = 2L;
    public static final String NAME = "Vendor";
    VendorService vendorService;

    @Mock
    VendorRepository vendorRepository;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
    }

    @Test
    public void getAllVendors() throws Exception {

        //given
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor(), new Vendor());

        when(vendorRepository.findAll()).thenReturn(vendors);

        //when
        VendorListDTO vendorDTOS = vendorService.getAllVendors();

        //then
        assertEquals(3, vendorDTOS.getVendors().size());

    }

    @Test
    public void getVendorById() throws Exception {

        //given
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));

        //when
        VendorDTO vendorDTO = vendorService.getVendorById(ID);

        assertEquals(NAME, vendorDTO.getName());
    }

    @Test
    public void createNewVendor() throws Exception {

        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor savedVendor = new Vendor();
        savedVendor.setName(vendorDTO.getName());
        savedVendor.setId(1L);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        //when
        VendorDTO savedDto = vendorService.createNewVendor(vendorDTO);

        //then
        assertEquals(vendorDTO.getName(), savedDto.getName());
        assertEquals("/api/v1/vendors/1", savedDto.getVendorUrl());
    }

    @Test
    public void saveVendorByDTO() throws Exception {

        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor savedVendor = new Vendor();
        savedVendor.setName(vendorDTO.getName());
        savedVendor.setId(1L);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        //when
        VendorDTO savedDto = vendorService.createNewVendor(vendorDTO);

        //then
        assertEquals(vendorDTO.getName(), savedDto.getName());
        assertEquals("/api/v1/vendors/1", savedDto.getVendorUrl());
    }

    @Test
    public void deleteById() throws Exception{
        Long id = 1L;

        vendorRepository.deleteById(id);

        verify(vendorRepository, times(1)).deleteById(anyLong());
    }

}