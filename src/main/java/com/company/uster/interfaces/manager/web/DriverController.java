package com.company.uster.interfaces.manager.web;

import com.company.uster.interfaces.manager.facade.ManagerServiceFacade;
import com.company.uster.interfaces.manager.facade.dto.DriverDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping(value = "/drivers")
@SessionAttributes("driver-table-page")
public class DriverController {

    private static final String VIEWS_LIST = "drivers/list";
    private static final String VIEWS_FORM = "drivers/form";
    private static final String VIEWS_DETAILS = "drivers/details";

    private final ManagerServiceFacade managerServiceFacade;

    public DriverController(ManagerServiceFacade managerServiceFacade) {
        this.managerServiceFacade = managerServiceFacade;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping
    public ModelAndView listDriver(@PageableDefault(size = 5) Pageable pageable) {
        Page<DriverDTO> dtoPage = managerServiceFacade.driverFindAll(pageable);
        ModelAndView modelAndView = new ModelAndView(VIEWS_LIST);
        modelAndView.addObject("page", dtoPage);
        return modelAndView;
    }

    @GetMapping("/{driverId}")
    public ModelAndView showDriver(@PathVariable("driverId") long driverId) {
        DriverDTO driver = managerServiceFacade.driverFindById(driverId);
        ModelAndView modelAndView = new ModelAndView(VIEWS_DETAILS);
        modelAndView.addObject("driver", driver);
        return modelAndView;
    }

    @GetMapping("/new")
    public String initCreationForm(Map<String, Object> model) {
        model.put("driver", new DriverDTO());
        return VIEWS_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(
            @Valid @ModelAttribute(value = "driver") DriverDTO driverDTO,
            BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_FORM;
        }
        this.managerServiceFacade.driverSave(driverDTO);
        return "redirect:/drivers/" + driverDTO.getId();
    }

    @GetMapping("/{driverId}/edit")
    public String initUpdateForm(@PathVariable("driverId") long driverId, Model model) {
        DriverDTO driver = this.managerServiceFacade.driverFindById(driverId);
        model.addAttribute("driver", driver);
        return VIEWS_FORM;
    }

    @PostMapping("/{driverId}/edit")
    public String processUpdateForm(
            @PathVariable("driverId") long driverId,
            @Valid @ModelAttribute(value = "driver") DriverDTO driverDTO,
            BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_FORM;
        }
        driverDTO.setId(driverId);
        this.managerServiceFacade.driverSave(driverDTO);
        return "redirect:/drivers/{driverId}";
    }

    @PostMapping("/{driverId}/delete")
    public String processDelete(@PathVariable("driverId") long driverId) {
        this.managerServiceFacade.driverDeleteById(driverId);
        return "redirect:/drivers";
    }
}
