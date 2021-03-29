package com.pasarela.service;

import com.pasarela.model.OrdenModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.jasperreports.JasperReportsUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class documentoService {

    @Value("${invoice.logo.path}")
    private String logo_path;

    @Value("${invoice.img1.path}")
    private String img1_path;

    @Value("${invoice.img2.path}")
    private String img2_path;

    @Value("${invoice.img3.path}")
    private String img3_path;

    @Value("${invoice.template.path}")
    private String template;

    public File generateInvoiceFor(OrdenModel order, Locale locale) throws IOException {

        File pdfFile = File.createTempFile("my-invoice", ".pdf");
 
        try(FileOutputStream pos = new FileOutputStream(pdfFile))
        {

            final JasperReport report = loadTemplate();

            final Map<String, Object> parameters = parameters(order, locale);

            final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList("Invoice"));

            JasperReportsUtils.renderAsPdf(report, parameters, dataSource, pos);

            return pdfFile;
        }
        catch (final Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    private Map<String, Object> parameters(OrdenModel order, Locale locale) {
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("logo", getClass().getResourceAsStream(logo_path));
        parameters.put("img1", getClass().getResourceAsStream(img1_path));
        parameters.put("img2", getClass().getResourceAsStream(img2_path));
        parameters.put("img3", getClass().getResourceAsStream(img3_path));
        parameters.put("order",  order);
        parameters.put("REPORT_LOCALE", locale);
        return parameters;
    }

    private JasperReport loadTemplate() throws JRException {

        final InputStream reportInputStream = getClass().getResourceAsStream(template);
        final JasperDesign jasperDesign = JRXmlLoader.load(reportInputStream);

        return JasperCompileManager.compileReport(jasperDesign);
    }

}
