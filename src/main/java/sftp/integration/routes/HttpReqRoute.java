package sftp.integration.routes;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import sftp.integration.processors.JsonToCsvProcessor;
import sftp.integration.processors.RestResponseProcessor;

@Component
public class HttpReqRoute extends RouteBuilder{

    Date nowDate = new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    String formatedDate = format.format(nowDate);

    @Value("${http.uri}")
    String httpUri;

    @Value("${sftp.uri}")
    String sftpUri;

    @Autowired
    RestResponseProcessor responseProcessor;

    @Autowired
    JsonToCsvProcessor jsonToCsvProcessor;

    @Override
    public void configure() throws Exception {

        

        from("quartz://integrationTimer?cron=0+*+*+?+*+*")
        .log("Sending request to endpoint")
        .to(httpUri)
            .convertBodyTo(String.class)
            .process(responseProcessor)
            .setBody(simple("${exchangeProperty.json}"))
            .log("Uploading JSON to SFTP")
            .to(sftpUri+"?fileName=data_" + formatedDate + ".json")
            .log("Uploading CSV Summary to SFTP")
            .setBody(simple("${exchangeProperty.SummaryCsv}"))
            .to(sftpUri+"?fileName=summary_" + formatedDate + ".csv")
            .log("Creating CSV ETL")
            .setBody(simple("${exchangeProperty.json}"))
            .process(jsonToCsvProcessor)
            .log("Uploading ETL CSV to SFTP")
            .setBody(simple("${exchangeProperty.ETLCsv}"))
            .to(sftpUri+"?fileName=ETL_" + formatedDate + ".csv")
            .log("Users were parsed")
        .end();
        
    }

}

