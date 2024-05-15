package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Agency;
import peaksoft.service.AgencyService;

@Controller
@RequestMapping("/agencies")
@RequiredArgsConstructor
public class AgencyApi {
    private final AgencyService agencyService;

    @GetMapping("/new")
    public String createAgency(Model model){
        model.addAttribute("newAgency",new Agency());
        return "/agency/newAgency";
    }


    @PostMapping("/saveAgency")
    public String saveAgency(@ModelAttribute("newAgency") Agency agency){
        agencyService.saveAgency(agency);
        return "redirect:/agencies";
    }

    @GetMapping()
    public String getAllAgencies(Model model){
        model.addAttribute("agencies",agencyService.getAllAgency());
        return "/agency/agencyMainPage";
    }


    @GetMapping("{agencyId}")
    public String getById(@PathVariable Long agencyId,Model model){
        model.addAttribute("agency",agencyService.getById(agencyId));
        return "redirect:/agencies";
    }


    @PostMapping("/update/{agencyId}")
    public String updateAgency(@PathVariable Long agencyId,@ModelAttribute ("agency") Agency agency){
        agencyService.updateAgencyById(agencyId,agency);
        return "redirect:/agencies";
    }


    @PostMapping("/{agencyId}")
    public String delete(@PathVariable Long agencyId){
        agencyService.deleteById(agencyId);
        return "redirect:/agencies";
    }

}
