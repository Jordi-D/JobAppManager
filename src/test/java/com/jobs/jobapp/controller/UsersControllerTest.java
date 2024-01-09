package com.jobs.jobapp.controller;


import com.jobs.jobapp.model.User;
import com.jobs.jobapp.service.IUsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UsersControllerTest {

    @Mock
    private IUsersService usersService;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private UsersController usersController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testShowIndex() {
        // Arrange
        List<User> userList = new ArrayList<>();
        when(usersService.findAll()).thenReturn(userList);

        // Act
        String result = usersController.showIndex(model);

        // Assert
        assertEquals("users/listUsers", result);
        verify(usersService).findAll();
        verify(model).addAttribute("users", userList);
    }

    @Test
    void testDelete() {
        // Arrange
        int idUser = 1;

        // Act
        String result = usersController.delete(idUser, redirectAttributes);

        // Assert
        assertEquals("redirect:/users/index", result);
        verify(usersService).delete(idUser);
        verify(redirectAttributes).addFlashAttribute("msg", "El usuario fue eliminado!.");
    }
    }



