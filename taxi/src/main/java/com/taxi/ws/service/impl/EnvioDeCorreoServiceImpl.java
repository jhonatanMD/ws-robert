package com.taxi.ws.service.impl;

import com.taxi.ws.models.SolicitudTransporte;
import com.taxi.ws.models.dto.DatosDeUsuario;
import com.taxi.ws.service.EnvioDeCorreoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.mail.Address;
import javax.mail.internet.MimeMessage;

@Service
public class EnvioDeCorreoServiceImpl implements EnvioDeCorreoService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public Mono<String> envioDeCorreo(DatosDeUsuario datosDeUsuario) {

        try {

        String msj ="<html>" +
                "<head></head>" +
                "<table  border=\"1\">\n" +
                "  <tr>\n" +
                "    <th>Hoy</th>\n" +
                "    <th>Mañana</th>\n" +
                "    <th>Miércoles</th>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>Soleado</td>\n" +
                "    <td>Mayormente soleado</td>\n" +
                "    <td>Parcialmente nublado</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>19°C</td>\n" +
                "    <td>17°C</td>\n" +
                "    <td>12°C</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>E 13 km/h</td>\n" +
                "    <td>E 11 km/h</td>\n" +
                "    <td>S 16 km/h</td>\n" +
                "  </tr>\n" +
                "</table>" +
                "</html>";


        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        mimeMessage.setContent(msj, "text/html"); /** Use this or below line **/
        helper.setText(msj, true); // Use this or above line.
        helper.setTo(datosDeUsuario.getEmpleado().getEmail());
        helper.setSubject("Solicitud de Taxi");
        helper.setFrom("infosiscomsur@gmail.com");

            javaMailSender.send(mimeMessage);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return Mono.just("todo good");
    }

    @Override
    public Mono<String> envioDeCorreo(DatosDeUsuario datosDeUsuario, SolicitudTransporte solicitudTransporte)  {

        try {
            String msj ="<html>" +
                    "<head></head>" +
                    "<p>Hola "+datosDeUsuario.getEmpleado().getNombre().concat(" "+datosDeUsuario.getEmpleado().getApe_pat())+" su solicitud a sido generada.</p><br>"+
                    "<table  border=\"1\">\n" +
                    "  <tr>\n" +
                    "    <th>Dirección Origen</th>\n" +
                    "    <th>Dirección Destino</th>\n" +
                    "    <th>Distancia</th>\n" +
                    "    <th>Fecha Solicitud</th>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td>"+solicitudTransporte.getDireccion_origen()+" </td>\n" +
                    "    <td>"+solicitudTransporte.getDirrecion_destino()+" </td>\n" +
                    "    <td>"+solicitudTransporte.getKilometraje()+" metros </td>\n" +
                    "    <td>"+solicitudTransporte.getFecha_solicitud()+" </td>\n" +
                    "  </tr>\n" +
                    "</table><br>" +
                    "<p>Gracias.</p>"+
                    "</html>";


            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            mimeMessage.setContent(msj, "text/html"); /** Use this or below line **/
            helper.setText(msj, true); // Use this or above line.
            helper.setTo(datosDeUsuario.getEmpleado().getEmail());
            helper.setSubject("Solicitud de Transporte");
            helper.setFrom("infosiscomsur@gmail.com");
            javaMailSender.send(mimeMessage);
        }catch (Exception e){

            System.out.println(e.getMessage());

        }



        return Mono.just("Enviado");
    }
}
