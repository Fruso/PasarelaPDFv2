package com.pasarela.controller;

import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.io.IOException;
import static java.lang.String.format;
import java.util.HashMap;
import java.util.Locale;
import org.springframework.http.HttpHeaders;
import static org.springframework.http.MediaType.APPLICATION_PDF;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.pasarela.model.OrdenModel;
import javax.annotation.Resource;
import com.pasarela.service.OrdenService;
import com.pasarela.service.documentoService;
import java.nio.charset.StandardCharsets;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import java.io.FileInputStream;
import org.json.JSONObject;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class IndexController 
{


@Resource
private OrdenService ordenService;
@Resource
private documentoService invocarService;


@GetMapping("/")
public HashMap<String, Object> index() {
    HashMap<String, Object> map = new HashMap<>();
    map.put("status", "is work!");
    return map;
}

//General el dococumento en formato json mediante post
@PostMapping(value = "/generarComprobantePost")
public HashMap<String, Object> generarComprobantePost(@RequestBody String data, @RequestParam(name = "codigo", defaultValue = "XYZ123456789") String codigo,
                                                           @RequestParam(name = "lang", defaultValue = "en") String lang) throws IOException {
    
    //Get token y idTransacion
    JSONObject jsonObject = new JSONObject(data);
    System.out.println(jsonObject.getString("token"));
        
    final OrdenModel orden = ordenService.getOrderByCode(codigo);
    final File invocarPdf = invocarService.generateInvoiceFor(orden, Locale.forLanguageTag(lang));

    String out = encodeFileToBase64Binary(invocarPdf.toString());
    //System.out.println(out); 

    HashMap<String, Object> map = new HashMap<>();
    map.put("documentPDF", out);

    return map;
}


//General el dococumento en formato json mediante get
@GetMapping(value = "/generarComprobanteGet")
public HashMap<String, Object> generarComprobanteGet(@RequestParam(name = "codigo", defaultValue = "XYZ123456789") String codigo,
                                                           @RequestParam(name = "lang", defaultValue = "en") String lang) throws IOException {
  
    final OrdenModel orden = ordenService.getOrderByCode(codigo);
    final File invocarPdf = invocarService.generateInvoiceFor(orden, Locale.forLanguageTag(lang));

    String out = encodeFileToBase64Binary(invocarPdf.toString());
    //System.out.println(out); 

    HashMap<String, Object> map = new HashMap<>();
    map.put("file", out);

    return map;
}

private static String encodeFileToBase64Binary(String fileName) throws IOException {
    File file = new File(fileName);
    byte[] encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
    return new String(encoded, StandardCharsets.US_ASCII);
}

//General el dococumento como archivo descargable desde el navegador
@GetMapping(value = "/generarComprobanteFile", produces = "application/pdf")
public ResponseEntity<InputStreamResource> generarComprobanteFile(@RequestParam(name = "codigo", defaultValue = "XYZ123456789") String codigo,
                                                           @RequestParam(name = "lang", defaultValue = "en") String lang) throws IOException {
 
    final OrdenModel orden = ordenService.getOrderByCode(codigo);
    final File invocarPdf = invocarService.generateInvoiceFor(orden, Locale.forLanguageTag(lang));

    final HttpHeaders httpHeaders = getHttpHeaders(codigo, lang, invocarPdf);

    return new ResponseEntity<>(new InputStreamResource(new FileInputStream(invocarPdf)), httpHeaders, OK);
}


private HttpHeaders getHttpHeaders(String codigo, String lang, File invoicePdf) {
    HttpHeaders respHeaders = new HttpHeaders();
    respHeaders.setContentType(APPLICATION_PDF);
    respHeaders.setContentLength(invoicePdf.length());
    respHeaders.setContentDispositionFormData("attachment", format("%s-%s.pdf", codigo, lang));
    return respHeaders;
}


}
