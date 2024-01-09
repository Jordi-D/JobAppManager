package com.jobs.jobapp.controller;


import com.jobs.jobapp.model.Request;
import com.jobs.jobapp.model.User;
import com.jobs.jobapp.model.Vacancy;
import com.jobs.jobapp.service.IRequestsService;
import com.jobs.jobapp.service.IUsersService;
import com.jobs.jobapp.service.IVacanciesService;
import com.jobs.jobapp.util.Utility;
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
    @Autowired
    private IVacanciesService iVacanciesService;
    @Autowired
    private IUsersService iUsersService;
    @Autowired
    private IRequestsService iRequestsService;

    @GetMapping("/indexPaginate")
    private String showIndexPaginated(Model model, Pageable page) {
        Page<Request> list = iRequestsService.findAll(page);
        model.addAttribute("requests", list);
        return "requests/listRequests";
    }

    @GetMapping("/create/{idVacancy}")
    public String create(Request request, @PathVariable("idVacancy") Integer idVacancy, Model model) {
        Vacancy vacancy = iVacanciesService.findById(idVacancy);
        model.addAttribute("vacancy", vacancy);
        return "requests/formRequest";

    }

    @PostMapping("/save")
    public String save(Request request, BindingResult bindingResult,
                          @RequestParam("fileCv") MultipartFile multipartFile,
                          Authentication authentication, RedirectAttributes attributes) {

        String username = authentication.getName();

        if (bindingResult.hasErrors()) {
            System.out.println("ERRORES ERRORREES");
            return "requests/formRequest";
        }

        if (!multipartFile.isEmpty()) {
            String nameFile = Utility.saveFile(multipartFile, routeCv);
            if (nameFile != null) {
                request.setFile(nameFile);
            }
        }
        User user = iUsersService.findByUsername(username);
        request.setUser(user);

        iRequestsService.save(request);
        attributes.addFlashAttribute("msg", "Thank you for applying to the job vacancy!");
        System.out.println("Request " + request);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer idRequest,RedirectAttributes attributes){
        iRequestsService.delete(idRequest);
        attributes.addFlashAttribute("msg", "Request deleted!");
        return "redirect:/requests/indexPaginate";

    }
}
