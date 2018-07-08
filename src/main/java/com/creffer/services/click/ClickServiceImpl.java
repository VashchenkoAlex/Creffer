package com.creffer.services.click;

import com.creffer.models.system.ClickModel;
import com.creffer.repository.system.ClickRepo;
import com.creffer.services.utils.HTTPHeadersSorter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("clickService")
public class ClickServiceImpl implements ClickService {
    private static final Logger log = LoggerFactory.getLogger(ClickServiceImpl.class);
    @Autowired
    @Qualifier("httpHeadersSorter")
    private HTTPHeadersSorter headersSorter;
    @Autowired
    private ClickRepo clickRepo;
    @Override
    public void saveClick(ClickModel click) {

        click.setClickIp(headersSorter.getClientIpAddress());
        click.setUserAgent(headersSorter.getUserAgent());
        click.setPageLanguage(headersSorter.getAcceptLanguage());
        click.setReferer(headersSorter.getReferer());
        click.setClickDate(headersSorter.getClickDate());
        clickRepo.save(click);

    }
}
