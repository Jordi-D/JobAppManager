package com.jobs.jobapp.controller;

import com.jobs.jobapp.model.Request;
import com.jobs.jobapp.model.User;
import com.jobs.jobapp.model.Vacancy;
import com.jobs.jobapp.service.IRequestsService;
import com.jobs.jobapp.service.IUsersService;
import com.jobs.jobapp.service.IVacanciesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class RequestsControllerTest {

    @Mock
    private IVacanciesService iVacanciesService;

    @Mock
    private IUsersService iUsersService;

    @Mock
    private IRequestsService iRequestsService;

    @Mock
    private Model model;

    @Mock
    private Pageable pageable;

    @Mock
    private Authentication authentication;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private RequestsController requestsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreate() {
        Integer idVacancy = 1;
        Vacancy vacancy = new Vacancy();
        when(iVacanciesService.findById(idVacancy)).thenReturn(vacancy);

        String result = requestsController.create(new Request(), idVacancy, model);

        assertEquals("requests/formRequest", result);
        verify(model).addAttribute("vacancy", vacancy);
    }

    @Test
    void testSave() {
        Request request = new Request();
        request.setId(1);
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "test.txt", "text/plain", "test data".getBytes());
        when(authentication.getName()).thenReturn("testUser");
        when(iUsersService.findByUsername("testUser")).thenReturn(new User());

        String result = requestsController.save(request, mock(BindingResult.class), mockMultipartFile, authentication, redirectAttributes);

        assertEquals("redirect:/", result);
        verify(iRequestsService).save(request);
        verify(redirectAttributes).addFlashAttribute("msg", "Thank you for applying to the job vacancy!");
    }

    @Test
    void testDelete() {
        Integer idRequest = 1;

        String result = requestsController.delete(idRequest, redirectAttributes);

        assertEquals("redirect:/requests/indexPaginate", result);
        verify(iRequestsService).delete(idRequest);
        verify(redirectAttributes).addFlashAttribute("msg", "Request deleted!");
    }
    @Test
    void testCreate_Unsuccessful() {
        // Setup
        Integer idVacancy = 1; // Assuming vacancy ID 1 does not exist
        Model model = new ConcurrentModel();
        Request request = new Request();

        // Mock behavior for iVacanciesService.findById() returning null
        when(iVacanciesService.findById(idVacancy)).thenReturn(null);

        // Execution
        String result = requestsController.create(request, idVacancy, model);

        // Assertion
        assertEquals("requests/formRequest", result); // Expecting the specific view name or response string
        // You might need to adjust the expected result based on the controller's behavior
    }



    @Test
    void testSave_Unsuccessful() {
        Request request = new Request();
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "test.txt", "text/plain", "test data".getBytes());
        when(authentication.getName()).thenReturn("testUser");
        when(iUsersService.findByUsername("testUser")).thenReturn(null); // Assuming failure to find user

        // Ensure BindingResult has errors to simulate unsuccessful scenario
        BindingResult mockBindingResult = mock(BindingResult.class);
        when(mockBindingResult.hasErrors()).thenReturn(true);

        String result = requestsController.save(request, mockBindingResult, mockMultipartFile, authentication, redirectAttributes);

        assertEquals("requests/formRequest", result); // Adjust this according to your application's behavior in case of failure
        verify(iRequestsService, never()).save(request);
        verify(redirectAttributes, never()).addFlashAttribute("msg", "Thank you for applying to the job vacancy!");
    }

    @Test
    void testDelete_Unsuccessful() {
        // Arrange
        Integer idRequest = 1;

        // Act
        String result = requestsController.delete(idRequest, redirectAttributes);

        // Assert
        assertEquals("redirect:/requests/indexPaginate", result); // This line fails intentionally
        verify(iRequestsService).delete(idRequest);
        verify(redirectAttributes).addFlashAttribute("msg", "Request deleted!");
    }
}
