package com.creffer.controllers.system;

import com.creffer.models.system.ClickModel;
import com.creffer.repository.system.ClickRepo;
import com.creffer.utils.HTTPHeadersSorter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ClickController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ClickRepo requestClickRepo;

    @RequestMapping(value = "/track",
            params = {"aff_sub","offer_id","pub_id"},
            method = RequestMethod.GET)
    @ResponseBody
    public void getClick(@RequestParam("aff_sub") String affSub,
                               @RequestParam("aff_sub2") String affSub2,
                               @RequestParam("offer_id") int offerId,
                               @RequestParam("pub_id") int pubId){
        ClickModel click = new ClickModel();
        HTTPHeadersSorter sorter = new HTTPHeadersSorter(request);
        click.setTransactionId(affSub);
        click.setOfferId(offerId);
        click.setPubId(pubId);
        click.setSubPubId(affSub2);
        click.setClickIp(sorter.getClientIpAddress());
        /*click.setClickCountry();
        click.setDeviceBrand();
        click.setDeviceModel();
        click.setDeviceOs();
        click.setDeviceOsVersion();
        click.setDeviceId();
        click.setMacAddress();
        click.setDevicePlatform();
        click.setIdfaGaid();
        click.setClickDate();
        click.setPageLanguage();
        click.setClickForwarded();*/
        click.setUserAgent(sorter.getUserAgent());
        //click.setViaProxy();
        requestClickRepo.save(click);
    }
}
