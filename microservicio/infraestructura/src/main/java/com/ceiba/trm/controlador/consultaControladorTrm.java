package com.ceiba.trm.controlador;

import com.ceiba.infraestructura.excepcion.ExcepcionTecnica;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/trm")
public class consultaControladorTrm {

    @GetMapping
    public ResponseEntity<JsonNode> obtenerTCRM() throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        final String url = "https://www.superfinanciera.gov.co/SuperfinancieraWebServiceTRM/TCRMServicesWebService/TCRMServicesWebService";
        final LocalDate fecha = LocalDate.now();

        StringBuilder body = new StringBuilder();
        body.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" \n");
        body.append("xmlns:act=\"http://action.trm.services.generic.action.superfinanciera.nexura.sc.com.co/\"> \n");
        body.append("<soapenv:Header/>\n");
        body.append("<soapenv:Body>\n");
        body.append("<act:queryTCRM>\n");
        body.append("<tcrmQueryAssociatedDate>");
        body.append(fecha);
        body.append("</tcrmQueryAssociatedDate>\n");
        body.append("</act:queryTCRM>\n");
        body.append("</soapenv:Body>\n");
        body.append("</soapenv:Envelope>");

        HttpEntity<String> request = new HttpEntity<>(body.toString(), headers);

        RestTemplate restTemplate = new RestTemplate();
        final String xmlEnTexto = restTemplate.postForEntity(url, request, String.class).getBody();

        return ResponseEntity.ok(this.obtenerTCRM(xmlEnTexto));
    }

    private JsonNode obtenerTCRM(String xml) throws IOException {
        if (xml == null) {
            throw new ExcepcionTecnica("TCMR no encontrado");
        }
        XmlMapper mapper = new XmlMapper();
        JsonNode jsonNode = mapper.readTree(xml.getBytes());
        return jsonNode.get("Body").get("queryTCRMResponse").get("return");

    }
}
