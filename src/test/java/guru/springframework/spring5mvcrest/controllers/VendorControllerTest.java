
package guru.springframework.spring5mvcrest.controllers;

import guru.springframework.spring5mvcrest.api.v1.model.CustomerDTO;
import guru.springframework.spring5mvcrest.api.v1.model.VendorDTO;
import guru.springframework.spring5mvcrest.api.v1.model.VendorListDTO;
import guru.springframework.spring5mvcrest.controllers.v1.CustomerController;
import guru.springframework.spring5mvcrest.controllers.v1.VendorController;
import guru.springframework.spring5mvcrest.services.CustomerService;
import guru.springframework.spring5mvcrest.services.ResourceNotFoundException;
import guru.springframework.spring5mvcrest.services.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static guru.springframework.spring5mvcrest.controllers.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VendorControllerTest {

    public static final String NAME = "Vendor";

    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(vendorController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();

    }

    @Test
    public void testListVendors() throws Exception {
        VendorDTO vendor1 = new VendorDTO();
        vendor1.setName(NAME);

        VendorDTO vendor2 = new VendorDTO();
        vendor2.setName("NewName");

        VendorListDTO vendorListDTO = new VendorListDTO(Arrays.asList(vendor1, vendor2));

        when(vendorService.getAllVendors()).thenReturn(vendorListDTO);

        mockMvc.perform(get("/api/v1/vendors/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    public void testGetByIdVendor() throws Exception {
        VendorDTO vendor1 = new VendorDTO();
        vendor1.setName(NAME);

        when(vendorService.getVendorById(anyLong())).thenReturn(vendor1);

        mockMvc.perform(get("/api/v1/vendors/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }

    @Test
    public void createNewVendor() throws Exception {
        //given
        VendorDTO vendor1 = new VendorDTO();
        vendor1.setName(NAME);

        VendorDTO returnDTO = new VendorDTO();
        returnDTO.setName(vendor1.getName());
        returnDTO.setVendorUrl(VendorController.BASE_URL + "/1");

        when(vendorService.createNewVendor(vendor1)).thenReturn(returnDTO);

        //when/then
        mockMvc.perform(post("/api/v1/vendors/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendor1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendor_url", equalTo("/api/v1/vendors/1")));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        //given
        VendorDTO vendor1 = new VendorDTO();
        vendor1.setName(NAME);

        VendorDTO returnDTO = new VendorDTO();
        returnDTO.setName(vendor1.getName());
        returnDTO.setVendorUrl(VendorController.BASE_URL + "/1");

        when(vendorService.saveVendorByDTO(anyLong(), any(VendorDTO.class))).thenReturn(returnDTO);

        //when/then
        mockMvc.perform(put("/api/v1/vendors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendor1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendor_url", equalTo("/api/v1/vendors/1")));
    }

    @Test
    public void testPatchCustomer() throws Exception {

        //given
        VendorDTO vendor1 = new VendorDTO();
        vendor1.setName(NAME);

        VendorDTO returnDTO = new VendorDTO();
        returnDTO.setName(vendor1.getName());
        returnDTO.setVendorUrl(VendorController.BASE_URL + "/1");

        when(vendorService.patchVendor(anyLong(), any(VendorDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(patch("/api/v1/vendors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendor1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendor_url", equalTo("/api/v1/vendors/1")));
    }

    @Test
    public void testDeleteCustomer() throws Exception{
        mockMvc.perform(delete("/api/v1/vendors/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(vendorService).deleteVendorById(anyLong());
    }

    @Test
    public void testNotFoundException() throws Exception {

        when(vendorService.getVendorById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(CustomerController.BASE_URL + "/222")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}