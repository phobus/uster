package com.hiberus.uster.interfaces.manager.web;

import com.hiberus.uster.interfaces.manager.facade.ManagerServiceFacade;
import com.hiberus.uster.interfaces.manager.facade.dto.DriverDTO;
import com.hiberus.uster.interfaces.manager.facade.dto.TripDTO;
import com.hiberus.uster.interfaces.manager.facade.dto.VehicleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(value = "/trips")
@SessionAttributes("trip")
public class TripController {

    public enum WizardSteps {
        DATE_SELECTION, VEHICLE_SELECTION, DRIVER_SELECTION, DONE;
    }

    private static final String VIEWS_LIST = "trips/list";
    private static final String VIEWS_WIZARD = "trips/wizard";

    private final ManagerServiceFacade managerServiceFacade;

    public TripController(ManagerServiceFacade managerServiceFacade) {
        this.managerServiceFacade = managerServiceFacade;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping
    public ModelAndView listTrip(@PageableDefault(size = 5) Pageable pageable) {
        Page<TripDTO> dtoPage = managerServiceFacade.tripFindAll(pageable);
        ModelAndView modelAndView = new ModelAndView(VIEWS_LIST);
        modelAndView.addObject("page", dtoPage);
        return modelAndView;
    }

    @GetMapping("/wizard")
    public String initWizard(final ModelMap modelMap) {
        TripDTO trip = new TripDTO();
        trip.setDate(LocalDate.now());
        modelMap.addAttribute("step", WizardSteps.DATE_SELECTION);
        modelMap.addAttribute("trip", trip);
        modelMap.addAttribute("vehicles", null);
        modelMap.addAttribute("drivers", null);
        return VIEWS_WIZARD;
    }

    @PostMapping("/date")
    public String processDate(
            Model model,
            @Valid @ModelAttribute(value = "trip") TripDTO trip,
            BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_WIZARD;
        }
        List<VehicleDTO> vehicles = this.managerServiceFacade.findFreeVehiclesByDate(trip.getDate());
        model.addAttribute("step", WizardSteps.VEHICLE_SELECTION);
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("drivers", null);
        return VIEWS_WIZARD;
    }

    @PostMapping("/vehicle")
    public String processVehicle(
            Model model,
            @RequestParam("id") long vehicleId,
            @Valid @ModelAttribute(value = "trip") TripDTO trip,
            BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return VIEWS_WIZARD;
        }
        model.addAttribute("step", WizardSteps.DRIVER_SELECTION);

        List<VehicleDTO> vehicles = this.managerServiceFacade.findFreeVehiclesByDate(trip.getDate());
        model.addAttribute("vehicles", vehicles);

        VehicleDTO selectedVehicle = getSelectedVehicle(vehicles, vehicleId);

        List<DriverDTO> drivers = this.managerServiceFacade.findFreeDriversByDateAndLicense(
                trip.getDate(), selectedVehicle.getLicenseRequired()
        );
        model.addAttribute("drivers", drivers);

        trip.setVehicle(selectedVehicle);
        trip.setDriver(null);

        return VIEWS_WIZARD;
    }

    @PostMapping("/driver")
    public String processDriver(
            Model model,
            @RequestParam("id") long driverId,
            @Valid @ModelAttribute(value = "trip") TripDTO trip,
            BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return VIEWS_WIZARD;
        }
        model.addAttribute("step", WizardSteps.DONE);
        List<VehicleDTO> vehicles = this.managerServiceFacade.findFreeVehiclesByDate(trip.getDate());
        model.addAttribute("vehicles", vehicles);

        VehicleDTO selectedVehicle = getSelectedVehicle(vehicles, trip.getVehicle().getId());

        List<DriverDTO> drivers = this.managerServiceFacade.findFreeDriversByDateAndLicense(
                trip.getDate(), selectedVehicle.getLicenseRequired()
        );
        model.addAttribute("drivers", drivers);

        DriverDTO selectedDriver = getSelectedDriver(drivers, driverId);
        trip.setVehicle(selectedVehicle);
        trip.setDriver(selectedDriver);

        return VIEWS_WIZARD;
    }

    @PostMapping("/new")
    public String processCreationForm(
            @Valid @ModelAttribute(value = "trip") TripDTO tripDTO,
            BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return VIEWS_WIZARD;
        }
        this.managerServiceFacade.tripSave(tripDTO);
        return "redirect:/trips";
    }

    private VehicleDTO getSelectedVehicle(List<VehicleDTO> vehicles, Long id) throws Exception {
        return vehicles.stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElseThrow(Exception::new);
    }

    private DriverDTO getSelectedDriver(List<DriverDTO> drivers, long driverId) throws Exception {
        return drivers.stream()
                .filter(d -> d.getId() == driverId)
                .findFirst()
                .orElseThrow(Exception::new);
    }
}
