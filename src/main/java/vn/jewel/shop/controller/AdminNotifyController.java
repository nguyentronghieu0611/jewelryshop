package vn.jewel.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.jewel.shop.model.NotifyModel;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminNotifyController {
    @Autowired
    private SimpMessagingTemplate template;

    @RequestMapping(value = "/admin/sendNotify", method = RequestMethod.POST)
    public ResponseEntity<String> sendNotify(@RequestBody NotifyModel model, HttpServletRequest request){
        String username = request.getUserPrincipal().getName();
        NotifyModel notifyModel = new NotifyModel(model.getContent(),username);
        template.convertAndSend("/topic/publicNotify",notifyModel);
        return new ResponseEntity<String>("", HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/notify")
    public String index() {
        return "admin_send_notify/index";
    }
}
