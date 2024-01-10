package com.jobs.jobapp.controller;

import com.jobs.jobapp.service.IUsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.mockito.Mockito.verify;

public class UsersControllerTest {

    @Mock
    private IUsersService usersService;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private UsersController usersController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteUser_Successful() {
        // Given
        int userId = 1;

        // When
        String result = usersController.deleteUser(userId, redirectAttributes);

        // Then
        verify(usersService).delete(userId);
        verify(redirectAttributes).addFlashAttribute("msg", "The user has been deleted.");
    }

}
