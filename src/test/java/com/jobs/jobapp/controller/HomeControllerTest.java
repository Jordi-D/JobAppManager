package com.jobs.jobapp.controller;

import com.jobs.jobapp.model.User;
import com.jobs.jobapp.service.ICategoriesService;
import com.jobs.jobapp.service.IUsersService;
import com.jobs.jobapp.service.IVacanciesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class HomeControllerTest {

    @Mock
    private IVacanciesService vacanciesService;

    @Mock
    private ICategoriesService categoriesService;

    @Mock
    private IUsersService usersService;

    @Mock
    private PasswordEncoder passwordEncoder;

    private MockHttpSession mockSession;

    @InjectMocks
    private HomeController homeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // Your unit tests here...

    @Test
    void testSaveRecord() {
        // Simulating a user
        User user = new User();
        user.setUsername("testuser");
        user.setPassword(passwordEncoder.encode("testpassword"));
        // Simulating RedirectAttributes
        RedirectAttributes mockAttributes = mock(RedirectAttributes.class);

        // Configuring the simulated behavior for the void save method of the user service
        doNothing().when(usersService).save(user);

        // Method call
        String result = homeController.saveRecord(user, mockAttributes);

        // Verifying that the method redirects to the expected path
        assertEquals("redirect:/users/index", result);

        // Verifying that the 'save' method of the user service was called exactly once with the simulated user
        verify(usersService, times(1)).save(user);

        // Verifying that the appropriate message was added to the flash attributes
        verify(mockAttributes, times(1)).addFlashAttribute("msg", "The record was saved successfully!");
    }

    @Test
    void testSaveRecord_UnsuccessfulSave() {
        // Simulating a user
        User user = new User();
        user.setUsername("testuser");
        user.setPassword(passwordEncoder.encode("testpassword"));

        // Simulating RedirectAttributes
        RedirectAttributes mockAttributes = mock(RedirectAttributes.class);

        // Simulating an exception when saving the user
        doThrow(RuntimeException.class).when(usersService).save(user);

        // Testing that when the exception occurs, RuntimeException is thrown
        assertThrows(RuntimeException.class, () -> homeController.saveRecord(user, mockAttributes));

        // Verifying that the 'save' method of the user service was called exactly once with the simulated user
        verify(usersService, times(1)).save(user);

        // Verifying that the success message is not added to the flash attributes due to the exception
        verify(mockAttributes, never()).addFlashAttribute("msg", "The record was saved successfully!");
    }


    // More unit tests...
}
