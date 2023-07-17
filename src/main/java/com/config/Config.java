package com.config;

import com.api.HolidayAPI;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.line.LineNotifyService;
import com.model.Constants;
import com.model.Holiday;
import com.model.MyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

import static com.model.Constants.getNow;

@Configuration
public class Config {
    @Autowired
    HolidayAPI holidayAPI;
    private static final Logger logger = LoggerFactory.getLogger(Config.class);

    @Bean
    public MyConfig init() throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(MyConfig.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//        File file = new File("C:\\EIPHelper\\config.xml");
        //本地端電腦
//        File file = new File("/EIPHelper/config.xml");
        //linux
        File file = new File("./config.xml");
        String path = file.getPath();
        System.out.println("config.xml 載入路徑 : " + path);
        MyConfig myConfig = (MyConfig) unmarshaller.unmarshal(file);
        getHolidays(myConfig);
        logger.info("init" + myConfig);
        System.err.println(getNow() + " init:" + myConfig);

        new LineNotifyService(myConfig.getToken()).sendNotification(getNow() + " " + "EIPHleper start👍🍆🐱‍🚀🌽😍");
        return myConfig;
    }

    public MyConfig getHolidays(MyConfig myConfig) throws Exception {
        try {
            int nowYear = Constants.getYear();
            String holiday = holidayAPI.getHoliday(String.valueOf(nowYear));
            ObjectMapper objectMapper = new ObjectMapper();
            List<Holiday> holidays = objectMapper.readValue(holiday, new TypeReference<List<Holiday>>() {
            });
            myConfig.setHolidays(holidays);
//            holidays.forEach(e-> System.err.println(e));
            System.err.println(getNow() + " 國定假日設定成功");
            logger.info("國定假日設定成功");
            return myConfig;
        } catch (Exception e) {
            throw e;
        }
    }
}
