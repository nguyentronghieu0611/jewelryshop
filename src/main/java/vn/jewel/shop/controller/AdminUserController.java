package vn.jewel.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.jewel.shop.model.User;
import vn.jewel.shop.repository.UserRepository;
import vn.jewel.shop.service.UserService;

@Controller
public class AdminUserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/admin/user")
    public String index() {
        return "admin_user/index";
    }

    @RequestMapping(value = "/admin/getUser", method = RequestMethod.GET)
    public ResponseEntity<Object> getUser(@RequestParam(value = "page", defaultValue = "1") int page,
                                               @RequestParam(value = "limit", defaultValue = "10") int limit) {
        return new ResponseEntity<Object>(userService.getListUser(page,limit), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/getUser/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> get(@PathVariable Long id) {
        return new ResponseEntity<Object>(userService.get(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/deleteUser/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<Object>(id, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/saveUser", method = RequestMethod.POST)
    public ResponseEntity<Object> save(@RequestBody User user) {
        return new ResponseEntity<Object>(userService.save(user), HttpStatus.OK);
    }
}
