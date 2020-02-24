package com.company.uster.interfaces.manager.web;

import com.company.uster.interfaces.manager.facade.dto.VehicleDTO;
import com.company.uster.interfaces.manager.facade.ManagerServiceFacade;
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
@RequestMapping(value = "/vehicles")
public class VehicleController {

    private static final String VIEWS_LIST = "vehicles/list";
    private static final String VIEWS_FORM = "vehicles/form";
    private static final String VIEWS_DETAILS = "vehicles/details";

    private final ManagerServiceFacade managerServiceFacade;

    public VehicleController(ManagerServiceFacade managerServiceFacade) {
        this.managerServiceFacade = managerServiceFacade;
    }


    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping
    public ModelAndView listVehicle(@PageableDefault(size = 5) Pageable pageable) {
        Page<VehicleDTO> dtoPage = managerServiceFacade.vehicleFindAll(pageable);
        ModelAndView modelAndView = new ModelAndView(VIEWS_LIST);
        modelAndView.addObject("page", dtoPage);
        return modelAndView;
    }

    @GetMapping("/{vehicleId}")
    public ModelAndView showVehicle(@PathVariable("vehicleId") long vehicleId) {
        VehicleDTO vehicle = managerServiceFacade.vehicleFindById(vehicleId);
        ModelAndView modelAndView = new ModelAndView(VIEWS_DETAILS);
        modelAndView.addObject("vehicle", vehicle);
        return modelAndView;
    }

    @GetMapping("/new")
    public String initCreationForm(Map<String, Object> model) {
        model.put("vehicle", new VehicleDTO());
        return VIEWS_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(
            @Valid @ModelAttribute(value = "vehicle") VehicleDTO vehicleDTO,
            BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_FORM;
        }
        this.managerServiceFacade.vehicleSave(vehicleDTO);
        return "redirect:/vehicles/" + vehicleDTO.getId();
    }

    @GetMapping("/{vehicleId}/edit")
    public String initUpdateForm(@PathVariable("vehicleId") long vehicleId, Model model) {
        VehicleDTO vehicle = this.managerServiceFacade.vehicleFindById(vehicleId);
        model.addAttribute("vehicle", vehicle);
        return VIEWS_FORM;
    }

    @PostMapping("/{vehicleId}/edit")
    public String processUpdateForm(
            @PathVariable("vehicleId") long vehicleId,
            @Valid @ModelAttribute(value = "vehicle") VehicleDTO vehicleDTO,
            BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_FORM;
        }
        vehicleDTO.setId(vehicleId);
        this.managerServiceFacade.vehicleSave(vehicleDTO);
        return "redirect:/vehicles/{vehicleId}";
    }

    @PostMapping("/{vehicleId}/delete")
    public String processDelete(@PathVariable("vehicleId") long vehicleId) {
        this.managerServiceFacade.vehicleDeleteById(vehicleId);
        return "redirect:/vehicles";
    }
}
