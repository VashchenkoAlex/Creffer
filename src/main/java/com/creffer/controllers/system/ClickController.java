package com.creffer.controllers.system;

import com.creffer.models.system.ClickModel;
import com.creffer.repository.system.ClickRepo;
import com.creffer.services.click.ClickService;
import com.creffer.services.click.ClickServiceImpl;
import com.creffer.services.utils.HTTPHeadersSorter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ClickController {
    private static final Logger log = LoggerFactory.getLogger(ClickController.class);

    @Autowired
    @Qualifier("httpHeadersSorter")
    private HTTPHeadersSorter headersSorter;
    @Autowired
    @Qualifier("clickService")
    private ClickService clickService;
    @GetMapping(value = "/tracking", params = {"pub_id","offer_id","aff_sub","aff_sub2"})
    public void click(@RequestParam("aff_sub")String affSub,
                      @RequestParam("aff_sub2")String affSub2,
                      @RequestParam("pub_id")int pubId,
                      @RequestParam("offer_id")int offerId,
                      HttpServletRequest request){
        log.info("Click controller");
        headersSorter.setRequest(request);
        ClickModel click = new ClickModel(affSub,offerId,pubId,affSub2);
        clickService.saveClick(click);
    }
}
