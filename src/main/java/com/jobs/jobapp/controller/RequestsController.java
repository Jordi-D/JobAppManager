package com.jobs.jobapp.controller;

import com.jobs.jobapp.model.Request;
import com.jobs.jobapp.model.User;
import com.jobs.jobapp.model.Vacancy;
import com.jobs.jobapp.service.IRequestsService;
import com.jobs.jobapp.service.IUsersService;
import com.jobs.jobapp.service.IVacanciesService;
import com.jobs.jobapp.util.Utility;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/requests")
public class RequestsController {

    @Value("${jobs.route.cv}")
    private String routeCv;

    private final IVacanciesService vacanciesService;
    private final IUsersService usersService;
    private final IRequestsService requestsService;

    @Autowired
    public RequestsController(IVacanciesService vacanciesService, IUsersService usersService, IRequestsService requestsService) {
        this.vacanciesService = vacanciesService;
        this.usersService = usersService;
        this.requestsService = requestsService;
    }

    @Operation(summary = "Show paginated list of requests")
    @GetMapping("/indexPaginate")
    public String showIndexPaginated(Model model, Pageable page) {
        Page<Request> requestPage = requestsService.findAll(page);
        model.addAttribute("requests", requestPage);
        return "requests/listRequests";
    }

    @Operation(summary = "Show form to create a request for a specific vacancy")
    @GetMapping("/create/{idVacancy}")
    public String createRequestForm(Request request, @PathVariable("idVacancy") Integer idVacancy, Model model) {
        Vacancy vacancy = vacanciesService.findById(idVacancy);
        model.addAttribute("vacancy", vacancy);
        return "requests/formRequest";
    }

    @Operation(summary = "Save a new request")
    @PostMapping("/save")
    public String saveRequest(Request request, BindingResult bindingResult,
                              @RequestParam("fileCv") MultipartFile multipartFile,
                              Authentication authentication, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            return "requests/formRequest";
        }

        if (!multipartFile.isEmpty()) {
            String fileName = Utility.saveFile(multipartFile, routeCv);
            if (fileName != null) {
                request.setFile(fileName);
            }
        }

        String username = authentication.getName();
        User user = usersService.findByUsername(username);
        request.setUser(user);

        requestsService.save(request);
        attributes.addFlashAttribute("msg", "Thank you for applying to the job vacancy!");

        return "redirect:/";
    }

    @Operation(summary = "Delete a request by ID")
    @GetMapping("/delete/{id}")
    public String deleteRequest(@PathVariable("id") Integer idRequest, RedirectAttributes attributes) {
        requestsService.delete(idRequest);
        attributes.addFlashAttribute("msg", "Request deleted!");
        return "redirect:/requests/indexPaginate";
    }
}
