package ro.ubb.donation.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.donation.core.model.Center;
import ro.ubb.donation.core.service.CenterService;
import ro.ubb.donation.web.response.CentersResponse;

import java.util.List;

@RestController
public class CenterController {

    @Autowired
    private CenterService centerService;

    @RequestMapping(value = "/centers", method = RequestMethod.GET)
    public CentersResponse getAllCenters(){
        List<Center> allCenters = this.centerService.findAll();
        return CentersResponse.builder()
                .centers(allCenters)
                .isError(false)
                .status("Success")
                .message("These are all centers!")
                .build();
    }
}
